package cl.duoc.api_volcan_gas.exception;

public class ClienteNoEncontradoException extends RuntimeException {

    public ClienteNoEncontradoException(Long id) {
        super("Cliente no encontrado con id: " + id);
    }
}
