package cl.duoc.api_volcan_gas.controller;


import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.api_volcan_gas.dto.request.ClienteRequest;
import cl.duoc.api_volcan_gas.dto.response.ClienteResponse;
import cl.duoc.api_volcan_gas.service.ClienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clientes")
@Tag ( name = "Controller de clientes", description = "Permite realizar operaciones CRUD sobre los clientes")
public class ClienteController {


    private final ClienteService clienteService;


    @Operation(summary = "Obtener todos los clientes")
    @GetMapping("/obtener")
    public ResponseEntity<List<ClienteResponse>> obtenerClientes() {
        return ResponseEntity.ok(clienteService.obtenerTodos());
    }

    @Operation(summary = "Obtener un cliente por su ID")
    @GetMapping("/obtener/{id}")
    public ResponseEntity<ClienteResponse> obtenerCliente(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.obtenerPorId(id));
    }

    @Operation(summary = "Crear un nuevo cliente")
    @PostMapping("/crear")
    public ResponseEntity<ClienteResponse> crearCliente(@Valid @RequestBody ClienteRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.crearCliente(request));
    }

    @Operation(summary = "Actualizar un cliente existente")
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ClienteResponse> actualizarCliente(@PathVariable Long id, @Valid @RequestBody ClienteRequest request) {
        return ResponseEntity.ok(clienteService.actualizarCliente(id, request));
    }

    @Operation(summary = "Eliminar un cliente")
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Void> borrarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

}
