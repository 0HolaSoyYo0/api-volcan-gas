package cl.duoc.api_volcan_gas.service;

import cl.duoc.api_volcan_gas.entity.Producto;
import cl.duoc.api_volcan_gas.repository.ProductoRespository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProductoService {
    @Autowired
    private ProductoRespository repository;

    public List<Producto> getProductos() {
        return repository.findAll();
    }

    public Optional<Producto> getProducto(Long id) {
        return repository.findById(id);
    }

    public Producto saveProducto(Producto producto) {
        return repository.save(producto);
    }

    public Producto updateProducto(Long id, Producto producto) {
        if (!repository.existsById(id))
            throw new RuntimeException("Producto no encontrado con id: " + id);
        producto.setId(id);
        return repository.save(producto);
    }

    public void deleteProducto(Long id) {
        if (!repository.existsById(id))
            throw new RuntimeException("Producto no encontrado con id: " + id);
        repository.deleteById(id);
    }
}
