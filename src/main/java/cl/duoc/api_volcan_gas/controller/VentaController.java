package cl.duoc.api_volcan_gas.controller;


import cl.duoc.api_volcan_gas.dto.request.VentaRequest;
import cl.duoc.api_volcan_gas.dto.response.VentaResponse;
import cl.duoc.api_volcan_gas.service.VentaService;

import jakarta.validation.Valid;

import lombok.RequiredArgsConstructor;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ventas")
public class VentaController {

    private final VentaService ventaService;

    @GetMapping("/obtener")
    public ResponseEntity<List<VentaResponse>> obtenerVentas() {
        return ResponseEntity.ok(ventaService.obtenerTodos());
    }

    @GetMapping("/obtener/{id}")
    public ResponseEntity<VentaResponse> obtenerVenta(@PathVariable Long id) {
        return ResponseEntity.ok(ventaService.obtenerPorId(id));
    }

    @PostMapping("/crear")
    public ResponseEntity<VentaResponse> crearVenta(@Valid @RequestBody VentaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ventaService.crearVenta(request));
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<VentaResponse> actualizarVenta(@PathVariable Long id, @Valid @RequestBody VentaRequest request) {
        return ResponseEntity.ok(ventaService.actualizarVenta(id, request));
    }

    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Void> borrarVenta(@PathVariable Long id) {
        ventaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
