package cl.duoc.api_volcan_gas.service;

import cl.duoc.api_volcan_gas.entity.Cliente;
import cl.duoc.api_volcan_gas.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {
    @Autowired
    private ClienteRepository repository;

    public List<Cliente> getClientes() {
        return repository.findAll();
    }

    public Optional<Cliente> getCliente(Long id) {
        return repository.findById(id);
    }

    public Cliente saveCliente(Cliente cliente) {
        return repository.save(cliente);
    }

    public Cliente updateCliente(Long id, Cliente cliente) {
        if (!repository.existsById(id))
            throw new RuntimeException("Cliente no encontrado con id: " + id);
        cliente.setId(id);
        return repository.save(cliente);
    }

    public void deleteCliente(Long id) {
        if (!repository.existsById(id))
            throw new RuntimeException("Cliente no encontrado con id: " + id);
        repository.deleteById(id);
    }
/*
    public List<Cliente> getClientesActivos() {
        return repository.findClientesActivos();
    }
    */
}
