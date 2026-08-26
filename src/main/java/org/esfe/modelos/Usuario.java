package org.esfe.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import org.esfe.modelos.Rol;

@Entity
@Table(name = "usuarios")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre de usario es requerido")
    private String login;

    @NotBlank(message = "La contraseña es requerida")
    private String clave;
    private Integer status;

    @ManyToOne
    @JoinColumn(name ="rol_id")
    private Rol rol;
}
