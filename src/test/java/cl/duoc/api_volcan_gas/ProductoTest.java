package cl.duoc.api_volcan_gas;

import cl.duoc.api_volcan_gas.entity.Producto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests unitarios para la entidad Producto")
class ProductoTest {

    private Producto producto;

    @BeforeEach
    void setUp() {
        producto = new Producto();
    }

    @Test
    @DisplayName("Debe instanciar un producto vacio correctamente")
    void testCrearProductoInstanciaVacia() {
        assertNotNull(producto);
        assertNull(producto.getId());
        assertNull(producto.getNombre());
        assertNull(producto.getPrecio());
        assertNull(producto.getCapacidad());
        assertNull(producto.getDescripcion());
        assertNull(producto.getImagen());
    }

    @Test
    @DisplayName("Debe permitir actualizar los datos del producto")
    void testActualizarAtributos() {
        producto.setPrecio(new BigDecimal("20000.00"));
        assertEquals(new BigDecimal("20000.00"), producto.getPrecio());

        producto.setPrecio(new BigDecimal("22500.00"));
        assertEquals(new BigDecimal("22500.00"), producto.getPrecio());
    }
}
