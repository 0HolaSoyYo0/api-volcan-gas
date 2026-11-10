package cl.duoc.api_volcan_gas.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VentaResponse {

    private Long id;
    private Long idCliente;
    private Long idProducto;
    private Integer cantidad;
    private BigDecimal precio;
    private BigDecimal subtotal;
    private BigDecimal total;
    private LocalDateTime fecha;
    private String tipoPago;
    private LocalDateTime fechaPago;
}
