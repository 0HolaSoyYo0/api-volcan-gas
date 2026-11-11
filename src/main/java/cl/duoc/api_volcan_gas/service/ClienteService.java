package cl.duoc.api_volcan_gas.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import cl.duoc.api_volcan_gas.dto.request.ClienteRequest;
import cl.duoc.api_volcan_gas.dto.response.ClienteResponse;
import cl.duoc.api_volcan_gas.entity.Cliente;
import cl.duoc.api_volcan_gas.repository.ClienteRepository;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ClienteService {

    private final ClienteRepository repository;

    // 1. Obtener todos (mapear cada Entity a Response)
    public List<ClienteResponse> obtenerTodos() {
        return repository.findAll()
                .stream()
                .map(this::mapToResponse)
                .toList();
    }

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

    // --- Métodos auxiliares de mapeo ---
    private ClienteResponse mapToResponse(Cliente cliente) {
        return new ClienteResponse(
                cliente.getId(),
                cliente.getRut(),
                cliente.getNombre(),
                cliente.getApellido(),
                cliente.getEmail(),
                cliente.getTelefono(),
                cliente.getDireccion(),
                cliente.getCreadoEn(),
                cliente.getComunaId(),
                cliente.getRolId()
        );
    }

    private Cliente mapToEntity(ClienteRequest request) {
        Cliente cliente = new Cliente();
        cliente.setRut(request.getRut());
        cliente.setNombre(request.getNombre());
        cliente.setApellido(request.getApellido());
        cliente.setEmail(request.getEmail());
        cliente.setTelefono(request.getTelefono());
        cliente.setContrasena(request.getContrasena());
        cliente.setDireccion(request.getDireccion());
        cliente.setComunaId(request.getComunaId());
        cliente.setRolId(request.getRolId());
        return cliente;
    }
}
