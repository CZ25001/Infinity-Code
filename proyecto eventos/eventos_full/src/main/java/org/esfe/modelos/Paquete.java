package org.esfe.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.Set;

// HU Usuario 7 / HU Administrador 5,12: paquetes de comida y decoracion que personalizan el evento
@Entity
@Table(name = "paquetes")
public class Paquete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(length = 100, nullable = false)
    @NotBlank(message = "El nombre del paquete es requerido")
    private String nombre;

    @Column(length = 255)
    private String descripcion;

    @NotNull(message = "El tipo de paquete es requerido")
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private TipoPaquete tipo;

    @NotNull(message = "El precio es requerido")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
    @Column(precision = 10, scale = 2, nullable = false)
    private BigDecimal precio;

    private Boolean activo = true;

    @OneToMany(mappedBy = "paquete")
    private Set<ReservaPaquete> reservaPaquetes = new HashSet<>();

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

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public TipoPaquete getTipo() {
        return tipo;
    }

    public void setTipo(TipoPaquete tipo) {
        this.tipo = tipo;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public Boolean getActivo() {
        return activo;
    }

    public void setActivo(Boolean activo) {
        this.activo = activo;
    }

    public Set<ReservaPaquete> getReservaPaquetes() {
        return reservaPaquetes;
    }

    public void setReservaPaquetes(Set<ReservaPaquete> reservaPaquetes) {
        this.reservaPaquetes = reservaPaquetes;
    }
}
