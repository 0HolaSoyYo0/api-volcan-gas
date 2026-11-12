package cl.duoc.api_volcan_gas.service;

import cl.duoc.api_volcan_gas.entity.Venta;
import cl.duoc.api_volcan_gas.mapper.MapperCliente;
import cl.duoc.api_volcan_gas.repository.ProductoRepository;
import cl.duoc.api_volcan_gas.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaService {

    private final VentaRepository ventaRepository;
    private final MapperCliente mapperCliente;

    public VentaService(VentaRepository ventaRepository, MapperCliente mapperCliente) {
        this.ventaRepository = ventaRepository;
        this.mapperCliente = mapperCliente;
    }

    public List<Venta> getVentas() {
        return ventaRepository.findAll();
    }

    public Optional<Venta> getVenta(Long id) {
        return ventaRepository.findById(id);
    }

    public Venta saveVenta(Venta venta) {
        return ventaRepository.save(venta);
    }

    public Venta updateVenta(Long id, Venta venta) {
        if (!ventaRepository.existsById(id))
            throw new RuntimeException("Venta no encontrada con id: " + id);
        venta.setId(id);
        return ventaRepository.save(venta);
    }

    public void deleteVenta(Long id) {
        if (!ventaRepository.existsById(id))
            throw new RuntimeException("Venta no encontrada con id: " + id);
        ventaRepository.deleteById(id);
    }
}
