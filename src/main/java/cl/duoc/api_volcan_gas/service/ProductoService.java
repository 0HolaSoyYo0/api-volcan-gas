package cl.duoc.api_volcan_gas.service;

import cl.duoc.api_volcan_gas.dto.request.ProductoRequest;
import cl.duoc.api_volcan_gas.dto.response.ProductoResponse;
import cl.duoc.api_volcan_gas.entity.Producto;
import cl.duoc.api_volcan_gas.repository.ProductoRespository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRespository repository;

    // 1. Obtener todos
    public List<ProductoResponse> obtenerTodos() {
        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // 2. Obtener por ID
    public ProductoResponse obtenerPorId(Long id) {
        Producto producto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));
        return mapToResponse(producto);
    }

    // 3. Crear
    public ProductoResponse crearProducto(ProductoRequest request) {
        Producto producto = mapToEntity(request);
        Producto guardado = repository.save(producto);
        return mapToResponse(guardado);
    }

    // 4. Actualizar
    public ProductoResponse actualizarProducto(Long id, ProductoRequest request) {
        Producto producto = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Producto no encontrado con id: " + id));

        producto.setNombre(request.getNombre());
        producto.setPrecio(request.getPrecio());
        producto.setCapacidad(request.getCapacidad());
        producto.setDescripcion(request.getDescripcion());
        producto.setImagen(request.getImagen());

        return mapToResponse(repository.save(producto));
    }

    // 5. Eliminar
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Producto no encontrado con id: " + id);
        }
        repository.deleteById(id);
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
