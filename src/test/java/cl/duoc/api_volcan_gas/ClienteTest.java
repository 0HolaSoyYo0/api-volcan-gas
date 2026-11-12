package cl.duoc.api_volcan_gas;

import cl.duoc.api_volcan_gas.entity.Cliente;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Tests unitarios para la entidad Cliente")
public class ClienteTest {

    private Cliente cliente;

    @BeforeEach
    void setUp() {
        cliente = new Cliente();
    }

    @Test
    @DisplayName("Debe instanciar un cliente vacío correctamente")
    void crearCliente() {
        assertNotNull(cliente);
        assertNull(cliente.getId());
        assertNull(cliente.getRut());
        assertNull(cliente.getNombre());
        assertNull(cliente.getApellido());
        assertNull(cliente.getEmail());
        assertNull(cliente.getTelefono());
        assertNull(cliente.getContrasena());
        assertNull(cliente.getDireccion());
        assertNull(cliente.getCreadoEn());
        assertNull(cliente.getComunaId());
        assertNull(cliente.getRolId());
    }
    @Test
    @DisplayName("Verificar contraseña")
    void revisarContrasena() {
        assertNotNull(cliente.getContrasena());
        assertTrue(cliente.getContrasena().length() > 16);
    }
    @Test
    @DisplayName("Verificar formato de email")
    void correoValido() {
        cliente.setEmail("correo@prueba.com");

        assertTrue(cliente.getEmail().matches(
                "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$"
        ));
    }

    @Test
    @DisplayName("Debe permitir actualizar atributos existentes")
    void testActualizarAtributos() {
        cliente.setNombre("Carlos");
        assertEquals("Carlos", cliente.getNombre());

        cliente.setNombre("Andrés");
        assertEquals("Andrés", cliente.getNombre());
    }
}
