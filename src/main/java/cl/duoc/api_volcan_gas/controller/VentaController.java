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

import cl.duoc.api_volcan_gas.dto.request.VentaRequest;
import cl.duoc.api_volcan_gas.dto.response.VentaResponse;
import cl.duoc.api_volcan_gas.service.VentaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;


@RestController
@RequiredArgsConstructor
@RequestMapping("/api/ventas")
@Tag ( name = "Controller de ventas", description = "Permite realizar operaciones CRUD sobre las ventas")
public class VentaController {

    private final VentaService ventaService;

    @Operation(summary = "Obtener todas las ventas")
    @GetMapping("/obtener")
    public ResponseEntity<List<VentaResponse>> obtenerVentas() {
        return ResponseEntity.ok(ventaService.obtenerTodos());
    }

    @Operation(summary = "Obtener una venta por su ID")
    @GetMapping("/obtener/{id}")
    public ResponseEntity<VentaResponse> obtenerVenta(@PathVariable Long id) {
        return ResponseEntity.ok(ventaService.obtenerPorId(id));
    }

    @Operation(summary = "Crear una nueva venta")
    @PostMapping("/crear")
    public ResponseEntity<VentaResponse> crearVenta(@Valid @RequestBody VentaRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(ventaService.crearVenta(request));
    }

    @Operation(summary = "Actualizar una venta existente")
    @PutMapping("/actualizar/{id}")
    public ResponseEntity<VentaResponse> actualizarVenta(@PathVariable Long id, @Valid @RequestBody VentaRequest request) {
        return ResponseEntity.ok(ventaService.actualizarVenta(id, request));
    }

    @Operation(summary = "Eliminar una venta")
    @DeleteMapping("/borrar/{id}")
    public ResponseEntity<Void> borrarVenta(@PathVariable Long id) {
        ventaService.eliminar(id);
        return ResponseEntity.noContent().build();
    }

}
