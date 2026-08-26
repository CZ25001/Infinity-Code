package org.esfe.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name="docentes")
public class Docente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    //Tamaño del campo y si es obligatorio o no en la BD
    @Column(length = 50, nullable = false)
    @NotBlank(message = "El nombre es requerido")
    private String nombre;

    @Column(length = 50, nullable = false)
    @NotBlank(message = "El apellido es obligatorio")
    private String apellido;

    @Column(length = 150, nullable = false)
    @NotBlank(message = "El email es obligatorio")
    @Email(message = "la entrada no corresponde a un email valido")
    private String email;

    @Column(length = 9, nullable = false)
    @NotBlank(message = "El telefono es requerido")
    private String telefono;

    @Column(length = 220, nullable = false)
    @NotBlank(message = "El nombre de la escuela es requerido")
    private String escuela;

    @ManyToMany
    @JoinTable(
            name = "docentes_grupos",
            joinColumns = @JoinColumn(name = "docente_id"),
            inverseJoinColumns = @JoinColumn(name = "grupo_id")
    )

    private Set<Grupo> grupos = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEscuela() {
        return escuela;
    }

    public void setEscuela(String escuela) {
        this.escuela = escuela;
    }

    public Set<Grupo> getGrupos() {
        return grupos;
    }

    public void setGrupos(Set<Grupo> grupos) {
        this.grupos = grupos;
    }
}
