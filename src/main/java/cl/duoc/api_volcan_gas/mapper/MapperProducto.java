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

    public ProductoResponse toResponse(Producto producto) {
        ProductoResponse response = new ProductoResponse();

        response.setId(producto.getId());
        response.setNombre(producto.getNombre());
        response.setPrecio(producto.getPrecio());
        response.setCapacidad(producto.getCapacidad());
        response.setDescripcion(producto.getDescripcion());
        response.setImagen(producto.getImagen());

        return response;
    }
}
