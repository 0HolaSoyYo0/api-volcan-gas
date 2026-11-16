package cl.duoc.api_volcan_gas.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.jupiter.api.DisplayName;

import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import net.datafaker.Faker;

import cl.duoc.api_volcan_gas.dto.request.ProductoRequest;
import cl.duoc.api_volcan_gas.dto.response.ProductoResponse;
import cl.duoc.api_volcan_gas.entity.Producto;
import cl.duoc.api_volcan_gas.exception.ProductoNoEncontradoException;
import cl.duoc.api_volcan_gas.mapper.MapperProducto;
import cl.duoc.api_volcan_gas.repository.ProductoRepository;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

import static org.mockito.Mockito.*;


@ExtendWith(MockitoExtension.class)
class ProductoServiceTest {

    @Mock
    private ProductoRepository productoRepository;

    @Mock
    private MapperProducto mapperProducto;

    @InjectMocks
    private ProductoService productoService;

    private final Faker faker = new Faker();


    @Test
    @DisplayName("Retorna la lista de productos")
    void obtenerTodos() {

        Producto producto = crearProductoDePrueba();
        ProductoResponse response = mock(ProductoResponse.class);

        when(productoRepository.findAll())
                .thenReturn(List.of(producto));

        when(mapperProducto.toResponse(producto))
                .thenReturn(response);


        List<ProductoResponse> resultado = productoService.obtenerTodos();


        assertEquals(1, resultado.size());
        assertSame(response, resultado.getFirst());

        verify(productoRepository).findAll();
        verify(mapperProducto).toResponse(producto);
    }


    @Test
    @DisplayName("Retorna el producto cuando existe")
    void obtenerPorIdCuandoExiste() {

        Long id = 1L;

        Producto producto = crearProductoDePrueba();
        ProductoResponse response = mock(ProductoResponse.class);

        when(productoRepository.findById(id))
                .thenReturn(Optional.of(producto));

        when(mapperProducto.toResponse(producto))
                .thenReturn(response);


        ProductoResponse resultado = productoService.obtenerPorId(id);


        assertNotNull(resultado);
        assertSame(response, resultado);

        verify(productoRepository).findById(id);
        verify(mapperProducto).toResponse(producto);
    }


    @Test
    @DisplayName("Lanza una excepción cuando el producto no existe")
    void obtenerPorIdCuandoNoExiste() {

        Long id = 999L;

        when(productoRepository.findById(id))
                .thenReturn(Optional.empty());


        assertThrows(
                ProductoNoEncontradoException.class,
                () -> productoService.obtenerPorId(id)
        );

        verify(productoRepository).findById(id);
        verifyNoInteractions(mapperProducto);
    }


    @Test
    @DisplayName("Guarda y retorna el producto creado")
    void crearProducto() {

        ProductoRequest request = crearRequestDePrueba();

        Producto productoNuevo = new Producto();
        Producto productoGuardado = crearProductoDePrueba();

        ProductoResponse response = mock(ProductoResponse.class);

        when(mapperProducto.toEntity(request))
                .thenReturn(productoNuevo);

        when(productoRepository.save(productoNuevo))
                .thenReturn(productoGuardado);

        when(mapperProducto.toResponse(productoGuardado))
                .thenReturn(response);


        ProductoResponse resultado = productoService.crearProducto(request);


        assertSame(response, resultado);

        verify(mapperProducto).toEntity(request);
        verify(productoRepository).save(productoNuevo);
        verify(mapperProducto).toResponse(productoGuardado);
    }


    @Test
    @DisplayName("Actualiza el producto cuando existe")
    void actualizarProductoCuandoExiste() {

        Long id = 1L;

        Producto productoExistente = crearProductoDePrueba();
        Producto productoGuardado = crearProductoDePrueba();

        ProductoRequest request = crearRequestDePrueba();
        ProductoResponse response = mock(ProductoResponse.class);

        when(productoRepository.findById(id))
                .thenReturn(Optional.of(productoExistente));

        when(productoRepository.save(productoExistente))
                .thenReturn(productoGuardado);

        when(mapperProducto.toResponse(productoGuardado))
                .thenReturn(response);


        ProductoResponse resultado =
                productoService.actualizarProducto(id, request);


        assertSame(response, resultado);

        assertEquals(request.getNombre(), productoExistente.getNombre());
        assertEquals(request.getPrecio(), productoExistente.getPrecio());
        assertEquals(request.getCapacidad(), productoExistente.getCapacidad());
        assertEquals(request.getDescripcion(), productoExistente.getDescripcion());
        assertEquals(request.getImagen(), productoExistente.getImagen());

        verify(productoRepository).findById(id);
        verify(productoRepository).save(productoExistente);
        verify(mapperProducto).toResponse(productoGuardado);
    }


    @Test
    @DisplayName("Lanza una excepción al actualizar un producto inexistente")
    void actualizarProductoCuandoNoExiste() {

        Long id = 999L;

        ProductoRequest request = crearRequestDePrueba();

        when(productoRepository.findById(id))
                .thenReturn(Optional.empty());


        assertThrows(
                ProductoNoEncontradoException.class,
                () -> productoService.actualizarProducto(id, request)
        );

        verify(productoRepository).findById(id);
        verify(productoRepository, never()).save(any(Producto.class));
        verifyNoInteractions(mapperProducto);
    }


    @Test
    @DisplayName("Elimina el producto cuando existe")
    void eliminarProductoCuandoExiste() {

        Long id = 1L;

        when(productoRepository.existsById(id))
                .thenReturn(true);


        productoService.eliminarProducto(id);


        verify(productoRepository).existsById(id);
        verify(productoRepository).deleteById(id);
    }


    @Test
    @DisplayName("Lanza una excepción al eliminar un producto inexistente")
    void eliminarProductoCuandoNoExiste() {

        Long id = 999L;

        when(productoRepository.existsById(id))
                .thenReturn(false);


        assertThrows(
                ProductoNoEncontradoException.class,
                () -> productoService.eliminarProducto(id)
        );

        verify(productoRepository).existsById(id);
        verify(productoRepository, never()).deleteById(id);
    }


    private Producto crearProductoDePrueba() {

        Producto producto = new Producto();

        producto.setId(1L);
        producto.setNombre(faker.lorem().word());

        producto.setPrecio(
                BigDecimal.valueOf(
                        faker.number().numberBetween(1000, 100000)
                )
        );

        producto.setCapacidad(15);
        producto.setDescripcion(faker.lorem().sentence());
        producto.setImagen("https://example.com/producto.jpg");

        return producto;
    }


    private ProductoRequest crearRequestDePrueba() {

        return new ProductoRequest(
                faker.lorem().word(),

                BigDecimal.valueOf(
                        faker.number().numberBetween(1000, 100000)
                ),

                15,

                faker.lorem().sentence(),

                "https://example.com/producto.jpg"
        );
    }

}