package cl.duoc.api_volcan_gas.controller;


import cl.duoc.api_volcan_gas.dto.request.ClienteRequest;
import cl.duoc.api_volcan_gas.dto.response.ClienteResponse;
import cl.duoc.api_volcan_gas.service.ClienteService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/clientes")
public class ClienteController {


    private final ClienteService clienteService;


    @GetMapping("/obtener")
    public ResponseEntity<List<ClienteResponse>> obtenerClientes() {
        return ResponseEntity.ok(clienteService.obtenerTodos());
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<ClienteResponse> obtenerCliente(@PathVariable Long id) {
        return ResponseEntity.ok(clienteService.obtenerPorId(id));
    }

    @PostMapping("/crear")
    public ResponseEntity<ClienteResponse> crearCliente(@Valid @RequestBody ClienteRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(clienteService.crearCliente(request));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<ClienteResponse> actualizarCliente(@PathVariable Long id, @Valid @RequestBody ClienteRequest request) {
        return ResponseEntity.ok(clienteService.actualizarCliente(id, request));
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Void> borrarCliente(@PathVariable Long id) {
        clienteService.eliminarCliente(id);
        return ResponseEntity.noContent().build();
    }

}
