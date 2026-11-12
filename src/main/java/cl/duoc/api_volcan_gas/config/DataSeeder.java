package cl.duoc.api_volcan_gas.config;

import cl.duoc.api_volcan_gas.entity.Producto;
import cl.duoc.api_volcan_gas.repository.ProductoRepository;
import net.datafaker.Faker;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.Locale;

@Component
public class DataSeeder implements CommandLineRunner {

    private final ProductoRepository productoRepository;

    public DataSeeder(ProductoRepository productoRepository) {
        this.productoRepository = productoRepository;
    }

    @Override
    public void run(String... args) {
        // Solo carga datos si la tabla está vacía para evitar duplicados al reiniciar
        if (productoRepository.count() == 0) {
            Faker faker = new Faker(new Locale("es", "CL"));

            // Opciones acotadas al dominio de gas licuado
            Integer[] capacidades = {5, 11, 15, 45};
            String[] tiposValvula = {"Normal", "Catalítico", "Uso Industrial"};

            for (int i = 0; i < 8; i++) {
                int capacidad = faker.options().option(capacidades);
                String tipo = faker.options().option(tiposValvula);

                // Precio coherente según los kilos del cilindro (aprox. $1.500 - $1.800 por kg)
                int precioBase = capacidad * faker.number().numberBetween(1400, 1800);
                // Redondear a la centena más cercana (ej. 16500)
                BigDecimal precioRedondeado = BigDecimal.valueOf((precioBase / 100) * 100);

                Producto producto = new Producto();
                producto.setNombre("Cilindro de Gas " + capacidad + " kg (" + tipo + ")");
                producto.setDescripcion("Cilindro de gas licuado de " + capacidad + " kg, formato "
                        + tipo.toLowerCase() + ". Ideal para "
                        + (capacidad <= 11 ? "hogar, cocina y calefacción ligera." : "alta demanda y uso continuo."));
                producto.setPrecio(precioRedondeado);


                // Si tu entidad tiene campo capacidad o imagen, puedes asignarlos así:
                producto.setCapacidad(capacidad);
                // producto.setImagen("/assets/img/cilindro_" + capacidad + ".png");

                productoRepository.save(producto);
            }

            System.out.println("Catálogo de cilindros de prueba generado con DataFaker.");
        }
    }
}