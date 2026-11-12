package cl.duoc.api_volcan_gas.service;


import cl.duoc.api_volcan_gas.dto.request.ProductoRequest;
import cl.duoc.api_volcan_gas.dto.response.ProductoResponse;
import cl.duoc.api_volcan_gas.entity.Producto;
import cl.duoc.api_volcan_gas.exception.ProductoNoEncontradoException;
import cl.duoc.api_volcan_gas.mapper.MapperProducto;
import cl.duoc.api_volcan_gas.repository.ProductoRepository;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final MapperProducto mapperProducto;

    public ProductoService(
            ProductoRepository productoRepository,
            MapperProducto mapperProducto
    ) {
        this.productoRepository = productoRepository;
        this.mapperProducto = mapperProducto;
    }


    public List<ProductoResponse> obtenerTodos() {
        return productoRepository.findAll()
                .stream()
                .map(mapperProducto::toResponse)
                .toList();
    }

    public ProductoResponse obtenerPorId(Long id) {
        Optional<Producto> resultado = productoRepository.findById(id);
        if (resultado.isEmpty()) {
            throw new ProductoNoEncontradoException(id);
        }
        return mapperProducto.toResponse(resultado.get());
    }

    public ProductoResponse crearProducto(ProductoRequest producto) {
        Producto productoGuardado = mapperProducto.toEntity(producto);
        productoRepository.save(productoGuardado);
        return mapperProducto.toResponse(productoGuardado);
    }

    public ProductoResponse actualizarProducto(Long id, ProductoRequest producto) {

        Optional<Producto> resultado = productoRepository.findById(id);

        if (resultado.isEmpty()) {
            throw new ProductoNoEncontradoException(id);
        }

        Producto productoExistente = resultado.get();

        productoExistente.setNombre(producto.getNombre());
        productoExistente.setPrecio(producto.getPrecio());
        productoExistente.setCapacidad(producto.getCapacidad());
        productoExistente.setDescripcion(producto.getDescripcion());
        productoExistente.setImagen(producto.getImagen());

        productoRepository.save(productoExistente);
        return mapperProducto.toResponse(productoExistente);
    }

    public void eliminarProducto(Long id) {
        if (!productoRepository.existsById(id))
            throw new ProductoNoEncontradoException(id);
        productoRepository.deleteById(id);
    }
}
