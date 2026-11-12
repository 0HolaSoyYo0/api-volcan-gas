package cl.duoc.api_volcan_gas.service;

import cl.duoc.api_volcan_gas.dto.request.VentaRequest;
import cl.duoc.api_volcan_gas.dto.response.VentaResponse;
import cl.duoc.api_volcan_gas.entity.Venta;
import cl.duoc.api_volcan_gas.exception.VentaNoEncontradaException;
import cl.duoc.api_volcan_gas.mapper.MapperVenta;
import cl.duoc.api_volcan_gas.repository.VentaRepository;

import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;
    private final MapperVenta mapperVenta;

    public VentaService(VentaRepository ventaRepository, MapperVenta mapperVenta) {
        this.ventaRepository = ventaRepository;
        this.mapperVenta = mapperVenta;
    }

    // 1. Obtener todos
    public List<VentaResponse> obtenerTodos() {
        return ventaRepository.findAll()
                .stream()
                .map(mapperVenta::toResponse)
                .toList();
    }

    // 2. Obtener por ID
    public VentaResponse obtenerPorId(Long id) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new VentaNoEncontradaException(id));
        return mapperVenta.toResponse(venta);
    }

    // 3. Crear
    public VentaResponse crearVenta(VentaRequest request) {
        Venta venta = mapperVenta.toEntity(request);
        if (venta.getFecha() == null) {
            venta.setFecha(LocalDateTime.now());
        }
        Venta guardada = ventaRepository.save(venta);
        return mapperVenta.toResponse(guardada);
    }

    // 4. Actualizar
    public VentaResponse actualizarVenta(Long id, VentaRequest request) {
        Venta venta = ventaRepository.findById(id)
                .orElseThrow(() -> new VentaNoEncontradaException(id));

        venta.setCantidad(request.getCantidad());
        venta.setPrecio(request.getPrecio());
        venta.setSubtotal(request.getSubtotal());
        venta.setTotal(request.getTotal());
        if (request.getFecha() != null) {
            venta.setFecha(request.getFecha());
        }
        venta.setTipoPago(request.getTipoPago());
        venta.setFechaPago(request.getFechaPago());

        return mapperVenta.toResponse(ventaRepository.save(venta));
    }


    public void eliminarVenta(Long id) {
        if (!ventaRepository.existsById(id))
            throw new VentaNoEncontradaException(id);
        ventaRepository.deleteById(id);
    }
}
