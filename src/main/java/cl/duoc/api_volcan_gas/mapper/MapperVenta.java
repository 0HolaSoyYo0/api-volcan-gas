package cl.duoc.api_volcan_gas.mapper;

import cl.duoc.api_volcan_gas.dto.request.VentaRequest;
import cl.duoc.api_volcan_gas.dto.response.VentaResponse;
import cl.duoc.api_volcan_gas.entity.Venta;
import org.springframework.stereotype.Component;

@Component
public class MapperVenta {

    public Venta toEntity(VentaRequest request) {
        Venta venta = new Venta();

        venta.setCantidad(request.getCantidad());
        venta.setPrecio(request.getPrecio());
        venta.setSubtotal(request.getSubtotal());
        venta.setTotal(request.getTotal());
        venta.setFecha(request.getFecha());
        venta.setTipoPago(request.getTipoPago());
        venta.setFechaPago(request.getFechaPago());

        return venta;
    }

    public VentaResponse toResponse(Venta venta) {
        VentaResponse response = new VentaResponse();

        response.setCantidad(venta.getCantidad());
        response.setPrecio(venta.getPrecio());
        response.setSubtotal(venta.getSubtotal());
        response.setTotal(venta.getTotal());
        response.setFecha(venta.getFecha());
        response.setTipoPago(venta.getTipoPago());
        response.setFechaPago(venta.getFechaPago());

        return response;
    }

}
