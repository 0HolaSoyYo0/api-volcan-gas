package cl.duoc.api_volcan_gas.dto.request;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
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
public class VentaRequest {

    /*
     * No asignar idCliente: este campo está en desuso y el Front no envía el ID.
     */
    /*
     @NotNull(message = "El ID del cliente es obligatorio")
     private Long idCliente;
    */

    /*
     * No asignar idProducto: este campo está en desuso y el Front no envía el ID.
     */
    /*
    @NotNull(message = "El ID del producto es obligatorio")
    private Long idProducto;
    */

    @NotNull(message = "La cantidad es obligatoria")
    @Positive(message = "La cantidad debe ser mayor a 0")
    private Integer cantidad;

    @NotNull(message = "El precio es obligatorio")
    @Positive(message = "El precio debe ser mayor a 0")
    private BigDecimal precio;

    private BigDecimal subtotal;
    private BigDecimal total;
    private LocalDateTime fecha;
    private String tipoPago;
    private LocalDateTime fechaPago;
}
