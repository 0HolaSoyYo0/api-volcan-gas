package cl.duoc.api_volcan_gas.mapper;


import cl.duoc.api_volcan_gas.dto.request.ProductoRequest;
import cl.duoc.api_volcan_gas.dto.response.ProductoResponse;
import cl.duoc.api_volcan_gas.entity.Producto;

import org.springframework.stereotype.Component;


@Component
public class MapperProducto {

    public Producto toEntity(ProductoRequest request) {
        Producto producto = new Producto();

        producto.setNombre(request.getNombre());
        producto.setPrecio(request.getPrecio());
        producto.setCapacidad(request.getCapacidad());
        producto.setDescripcion(request.getDescripcion());
        producto.setImagen(request.getImagen());

        return producto;
    }

    public ProductoResponse toResponse (Producto producto) {
        ProductoResponse response = new ProductoResponse();

        producto.setNombre(producto.getNombre());
        producto.setPrecio(producto.getPrecio());
        producto.setCapacidad(producto.getCapacidad());
        producto.setDescripcion(producto.getDescripcion());
        producto.setImagen(producto.getImagen());

        return response;
    }
}
