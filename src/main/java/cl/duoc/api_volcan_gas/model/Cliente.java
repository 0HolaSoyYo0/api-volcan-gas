package cl.duoc.api_volcan_gas.model;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;


@Entity
@Getter
@Setter
@Table(name = "clientes")
public class Cliente {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String rut;
    private String nombre;
    private String apellido;
    private String email;
    private String telefono;

    private String contrasena;
    private String direccion;

    @Column(name = "creado_en")
    private LocalDateTime creadoEn;

    @Column(name = "comuna_id")
    private Long comunaId;

    @Column(name = "rol_id")
    private Long rolId;
}