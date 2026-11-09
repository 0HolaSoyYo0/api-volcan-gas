package cl.duoc.api_volcan_gas.entity;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Table(name = "ventas")
public class Venta {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "cliente_id")
    private Long idProducto;

    @Column(name = "producto_id")
    private Long idCliente;

    private Integer cantidad;

    @Column(name = "precio_unitario")
    private BigDecimal precio;

    private BigDecimal subtotal;
    private BigDecimal total;
    private LocalDateTime fecha;

    @Column(name = "tipo_pago")
    private String tipoPago;

    @Column(name = "fecha_pago")
    private LocalDateTime fechaPago;

}
