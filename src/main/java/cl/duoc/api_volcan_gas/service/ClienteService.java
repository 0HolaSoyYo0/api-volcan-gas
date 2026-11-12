package cl.duoc.api_volcan_gas.service;

import cl.duoc.api_volcan_gas.entity.Cliente;
import cl.duoc.api_volcan_gas.dto.response.ClienteResponse;
import cl.duoc.api_volcan_gas.mapper.MapperCliente;
import cl.duoc.api_volcan_gas.repository.ClienteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteService {

    private final ClienteRepository clienteRepository;
    private final MapperCliente mapperCliente;

    public ClienteService(ClienteRepository clienteRepository, MapperCliente mapperCliente) {
        this.clienteRepository = clienteRepository;
        this.mapperCliente = mapperCliente;
    }


    public List<ClienteResponse> obtenerTodos() {
        return clienteRepository.findAll()
                .stream()
                .map(mapperCliente::toResponse)
                .toList();
    }

    public Optional<Cliente> obtenerPorId(Long id) {
        return clienteRepository.findById(id);
    }

    public Cliente crearCliente(Cliente cliente) {
        return clienteRepository.save(cliente);
    }

    public Cliente actualizarCliente(Long id, Cliente cliente) {
        if (!clienteRepository.existsById(id))
            throw new RuntimeException("Cliente no encontrado con id: " + id);
        cliente.setId(id);
        return clienteRepository.save(cliente);
    }

    public void eliminarCliente(Long id) {
        if (!clienteRepository.existsById(id))
            throw new RuntimeException("Cliente no encontrado con id: " + id);
        clienteRepository.deleteById(id);
    }

    /*
     public List<Cliente> getClientesActivos() {
        return repository.findClientesActivos();
     }
    */
}
