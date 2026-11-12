package cl.duoc.api_volcan_gas.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import cl.duoc.api_volcan_gas.dto.request.ClienteRequest;
import cl.duoc.api_volcan_gas.dto.response.ClienteResponse;
import cl.duoc.api_volcan_gas.entity.Cliente;
import cl.duoc.api_volcan_gas.dto.response.ClienteResponse;
import cl.duoc.api_volcan_gas.mapper.MapperCliente;
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
    // 2. Obtener por ID
    public ClienteResponse obtenerPorId(Long id) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));
        return mapToResponse(cliente);
    }

    // 3. Crear (Request -> Entity -> Guardar -> Response)
    public ClienteResponse crearCliente(ClienteRequest request) {
        Cliente cliente = mapToEntity(request);
        cliente.setCreadoEn(LocalDateTime.now());
        Cliente guardado = repository.save(cliente);
        return mapToResponse(guardado);
    }

    // 4. Actualizar
    public ClienteResponse actualizarCliente(Long id, ClienteRequest request) {
        Cliente cliente = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Cliente no encontrado con id: " + id));

        cliente.setRut(request.getRut());
        cliente.setNombre(request.getNombre());
        cliente.setApellido(request.getApellido());
        cliente.setEmail(request.getEmail());
        cliente.setTelefono(request.getTelefono());
        cliente.setDireccion(request.getDireccion());
        cliente.setComunaId(request.getComunaId());
        cliente.setRolId(request.getRolId());
        if (request.getContrasena() != null && !request.getContrasena().isBlank()) {
            cliente.setContrasena(request.getContrasena());
        }

        return mapToResponse(repository.save(cliente));
    }

    // 5. Eliminar
    public void eliminar(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Cliente no encontrado con id: " + id);
        }
        repository.deleteById(id);
    }
/*
    public List<Cliente> getClientesActivos() {
        return repository.findClientesActivos();
    }
    */
}
