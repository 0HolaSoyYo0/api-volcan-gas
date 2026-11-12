package cl.duoc.api_volcan_gas.service;


import cl.duoc.api_volcan_gas.dto.request.ProductoRequest;
import cl.duoc.api_volcan_gas.dto.response.ProductoResponse;
import cl.duoc.api_volcan_gas.entity.Producto;
import cl.duoc.api_volcan_gas.exception.ProductoNoEncontradoException;
import cl.duoc.api_volcan_gas.mapper.MapperProducto;
import cl.duoc.api_volcan_gas.repository.ProductoRepository;

import org.springframework.stereotype.Service;

import java.util.List;


@Service
@RequiredArgsConstructor
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

    // --- Métodos auxiliares de mapeo ---
    private ProductoResponse mapToResponse(Producto producto) {
        return new ProductoResponse(
                producto.getId(),
                producto.getNombre(),
                producto.getPrecio(),
                producto.getCapacidad(),
                producto.getDescripcion(),
                producto.getImagen()
        );
    }

    private Producto mapToEntity(ProductoRequest request) {
        Producto producto = new Producto();
        producto.setNombre(request.getNombre());
        producto.setPrecio(request.getPrecio());
        producto.setCapacidad(request.getCapacidad());
        producto.setDescripcion(request.getDescripcion());
        producto.setImagen(request.getImagen());
        return producto;
    }
}
