package cl.duoc.api_volcan_gas.exception;

public class VentaNoEncontradaException extends RuntimeException {

    public VentaNoEncontradaException(Long id) {
        super("Venta no encontrada con id: " + id);
    }
}
