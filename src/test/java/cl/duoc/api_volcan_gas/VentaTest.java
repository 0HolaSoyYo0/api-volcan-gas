package cl.duoc.api_volcan_gas;

import cl.duoc.api_volcan_gas.entity.Venta;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests unitarios para la entidad Venta")
class VentaTest {

    private Venta venta;

    @BeforeEach
    void setUp() {
        venta = new Venta();
    }

    @Test
    @DisplayName("Debe instanciar una venta vacía correctamente")
    void testCrearVentaInstanciaVacia() {
        assertNotNull(venta);
        assertNull(venta.getId());
        assertNull(venta.getIdCliente());
        assertNull(venta.getIdProducto());
        assertNull(venta.getCantidad());
        assertNull(venta.getPrecio());
        assertNull(venta.getSubtotal());
        assertNull(venta.getTotal());
        assertNull(venta.getFecha());
        assertNull(venta.getTipoPago());
        assertNull(venta.getFechaPago());
    }

    @Test
    @DisplayName("Debe permitir actualizar valores calculados de la venta")
    void testActualizarAtributos() {
        venta.setCantidad(1);
        venta.setTotal(new BigDecimal("15000.00"));

        venta.setCantidad(3);
        venta.setTotal(new BigDecimal("45000.00"));

        assertEquals(3, venta.getCantidad());
        assertEquals(new BigDecimal("45000.00"), venta.getTotal());
    }
}
