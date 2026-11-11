package cl.duoc.api_volcan_gas.service;

import cl.duoc.api_volcan_gas.dto.request.VentaRequest;
import cl.duoc.api_volcan_gas.dto.response.VentaResponse;
import cl.duoc.api_volcan_gas.entity.Venta;
import cl.duoc.api_volcan_gas.repository.VentaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class VentaService {

    private final VentaRepository repository;

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

    // --- Métodos auxiliares de mapeo ---
    private VentaResponse mapToResponse(Venta venta) {
        return new VentaResponse(
                venta.getId(),
                venta.getIdCliente(),
                venta.getIdProducto(),
                venta.getCantidad(),
                venta.getPrecio(),
                venta.getSubtotal(),
                venta.getTotal(),
                venta.getFecha(),
                venta.getTipoPago(),
                venta.getFechaPago()
        );
    }

    private Venta mapToEntity(VentaRequest request) {
        Venta venta = new Venta();
        venta.setIdCliente(request.getIdCliente());
        venta.setIdProducto(request.getIdProducto());
        venta.setCantidad(request.getCantidad());
        venta.setPrecio(request.getPrecio());
        venta.setSubtotal(request.getSubtotal());
        venta.setTotal(request.getTotal());
        venta.setFecha(request.getFecha());
        venta.setTipoPago(request.getTipoPago());
        venta.setFechaPago(request.getFechaPago());
        return venta;
    }
}
