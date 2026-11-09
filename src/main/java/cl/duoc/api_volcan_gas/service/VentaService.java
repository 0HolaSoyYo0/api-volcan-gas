package cl.duoc.api_volcan_gas.service;

import cl.duoc.api_volcan_gas.entity.Venta;
import cl.duoc.api_volcan_gas.repository.VentaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VentaService {
    @Autowired
    private VentaRepository repository;

    public List<Venta> getVentas() {
        return repository.findAll();
    }

    public Optional<Venta> getVenta(Long id) {
        return repository.findById(id);
    }

    public Venta saveVenta(Venta venta) {
        return repository.save(venta);
    }

    public Venta updateVenta(Long id, Venta venta) {
        if (!repository.existsById(id))
            throw new RuntimeException("Venta no encontrada con id: " + id);
        venta.setId(id);
        return repository.save(venta);
    }

    public void deleteVenta(Long id) {
        if (!repository.existsById(id))
            throw new RuntimeException("Venta no encontrada con id: " + id);
        repository.deleteById(id);
    }
}
