package cl.duoc.api_volcan_gas.service;

import cl.duoc.api_volcan_gas.dto.request.VentaRequest;
import cl.duoc.api_volcan_gas.dto.response.VentaResponse;
import cl.duoc.api_volcan_gas.entity.Venta;
import cl.duoc.api_volcan_gas.mapper.MapperCliente;
import cl.duoc.api_volcan_gas.repository.ProductoRepository;
import cl.duoc.api_volcan_gas.repository.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;
    private final MapperCliente mapperCliente;

    public VentaService(VentaRepository ventaRepository, MapperCliente mapperCliente) {
        this.ventaRepository = ventaRepository;
        this.mapperCliente = mapperCliente;
    }

    // 1. Obtener todos
    public List<VentaResponse> obtenerTodos() {
        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

    // 2. Obtener por ID
    public VentaResponse obtenerPorId(Long id) {
        Venta venta = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con id: " + id));
        return mapToResponse(venta);
    }

    // 3. Crear
    public VentaResponse crearVenta(VentaRequest request) {
        Venta venta = mapToEntity(request);
        if (venta.getFecha() == null) {
            venta.setFecha(LocalDateTime.now());
        }
        Venta guardada = repository.save(venta);
        return mapToResponse(guardada);
    }

    // 4. Actualizar
    public VentaResponse actualizarVenta(Long id, VentaRequest request) {
        Venta venta = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Venta no encontrada con id: " + id));

        venta.setIdCliente(request.getIdCliente());
        venta.setIdProducto(request.getIdProducto());
        venta.setCantidad(request.getCantidad());
        venta.setPrecio(request.getPrecio());
        venta.setSubtotal(request.getSubtotal());
        venta.setTotal(request.getTotal());
        if (request.getFecha() != null) {
            venta.setFecha(request.getFecha());
        }
        venta.setTipoPago(request.getTipoPago());
        venta.setFechaPago(request.getFechaPago());

        return mapToResponse(repository.save(venta));
    }

    // 5. Eliminar
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Venta no encontrada con id: " + id);
        }
        repository.deleteById(id);
    }

}
