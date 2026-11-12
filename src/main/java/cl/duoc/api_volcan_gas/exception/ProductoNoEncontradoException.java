package cl.duoc.api_volcan_gas.exception;

public class ProductoNoEncontradoException extends RuntimeException {

//    public ProductoNoEncontradoException(String message) {
//        super(message);
//    }

    public ProductoNoEncontradoException(Long id) {
        super("Producto no encontrado con id: " + id);
    }

}
