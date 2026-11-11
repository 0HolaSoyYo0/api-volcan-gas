package cl.duoc.api_volcan_gas.mapper;


import cl.duoc.api_volcan_gas.dto.request.ClienteRequest;
import cl.duoc.api_volcan_gas.dto.response.ClienteResponse;
import cl.duoc.api_volcan_gas.entity.Cliente;
import cl.duoc.api_volcan_gas.entity.Producto;

import org.springframework.stereotype.Component;

import java.time.LocalDateTime;


@Component
public class MapperCliente {

    public Cliente toEntity(ClienteRequest request) {
        Cliente cliente = new Cliente();

        cliente.setRut(request.getRut());
        cliente.setNombre(request.getNombre());
        cliente.setApellido(request.getApellido());
        cliente.setEmail(request.getEmail());
        cliente.setTelefono(request.getTelefono());
        cliente.setContrasena(request.getContrasena());
        cliente.setDireccion(request.getDireccion());
        cliente.setCreadoEn(LocalDateTime.now());

    /*
    * No asignar comunaId ni rolId: estos campos están en desuso y
    * actualmente no existen relaciones correspondientes en la entidad Cliente.
    */

    //  cliente.setComunaId(request.getComunaId());
    //  cliente.setRolId(request.getRolId());

        return cliente;
    }

    public ClienteResponse response(Cliente cliente) {
        ClienteResponse response = new ClienteResponse();

        response.setRut(cliente.getRut());
        response.setNombre(cliente.getNombre());
        response.setApellido(cliente.getApellido());
        response.setEmail(cliente.getEmail());
        response.setTelefono(cliente.getTelefono());
        response.setDireccion(cliente.getDireccion());
        response.setCreadoEn(cliente.getCreadoEn());

        return response;
    }
}
