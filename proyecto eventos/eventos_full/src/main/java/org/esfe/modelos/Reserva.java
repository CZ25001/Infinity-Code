package org.esfe.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.Set;

// HU Usuario 5,6,9,10,11,16,17,18,19,20 / HU Administrador 8,9,10,11,17,19:
// solicitud de reserva de un evento con su salon, invitados, servicios y costo total
@Entity
@Table(name = "reservas")
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "usuario_id", nullable = false)
    private Usuario usuario;

    @ManyToOne
    @JoinColumn(name = "salon_id", nullable = false)
    private Salon salon;

    @ManyToOne
    @JoinColumn(name = "tipo_evento_id", nullable = false)
    private TipoEvento tipoEvento;

    // Fecha en la que se realizara el evento (HU Usuario 5)
    @NotNull(message = "La fecha del evento es requerida")
    @FutureOrPresent(message = "La fecha del evento no puede ser en el pasado")
    @Column(name = "fecha_evento", nullable = false)
    private LocalDate fechaEvento;

    // Fecha en que el usuario envio la solicitud (HU Usuario 10)
    @Column(name = "fecha_solicitud", nullable = false)
    private LocalDateTime fechaSolicitud = LocalDateTime.now();

    @NotNull(message = "La cantidad de invitados es requerida")
    @Min(value = 1, message = "Debe indicar al menos 1 invitado")
    @Column(name = "cantidad_invitados", nullable = false)
    private Integer cantidadInvitados;

    // Costo total calculado: salon + paquetes + servicios adicionales (HU Usuario 9)
    @NotNull(message = "El costo total es requerido")
    @DecimalMin(value = "0.0", message = "El costo total no puede ser negativo")
    @Column(name = "costo_total", precision = 10, scale = 2, nullable = false)
    private BigDecimal costoTotal;

    // Porcentaje de anticipo aplicado al momento de crear la reserva (HU Administrador 13)
    @Column(name = "porcentaje_anticipo", precision = 5, scale = 2)
    private BigDecimal porcentajeAnticipo;

    @NotNull
    @Enumerated(EnumType.STRING)
    @Column(length = 20, nullable = false)
    private EstadoReserva estado = EstadoReserva.PENDIENTE;

    // Motivo indicado por el administrador al rechazar o cancelar (HU Administrador 9,19)
    @Column(length = 255)
    private String observacion;

    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ReservaPaquete> paquetes = new HashSet<>();

    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<ReservaServicio> servicios = new HashSet<>();

    @OneToMany(mappedBy = "reserva", cascade = CascadeType.ALL, orphanRemoval = true)
    private Set<Pago> pagos = new HashSet<>();

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Salon getSalon() {
        return salon;
    }

    public void setSalon(Salon salon) {
        this.salon = salon;
    }

    public TipoEvento getTipoEvento() {
        return tipoEvento;
    }

    public void setTipoEvento(TipoEvento tipoEvento) {
        this.tipoEvento = tipoEvento;
    }

    public LocalDate getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(LocalDate fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public LocalDateTime getFechaSolicitud() {
        return fechaSolicitud;
    }

    public void setFechaSolicitud(LocalDateTime fechaSolicitud) {
        this.fechaSolicitud = fechaSolicitud;
    }

    public Integer getCantidadInvitados() {
        return cantidadInvitados;
    }

    public void setCantidadInvitados(Integer cantidadInvitados) {
        this.cantidadInvitados = cantidadInvitados;
    }

    public BigDecimal getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(BigDecimal costoTotal) {
        this.costoTotal = costoTotal;
    }

    public BigDecimal getPorcentajeAnticipo() {
        return porcentajeAnticipo;
    }

    public void setPorcentajeAnticipo(BigDecimal porcentajeAnticipo) {
        this.porcentajeAnticipo = porcentajeAnticipo;
    }

    public EstadoReserva getEstado() {
        return estado;
    }

    public void setEstado(EstadoReserva estado) {
        this.estado = estado;
    }

    public String getObservacion() {
        return observacion;
    }

    public void setObservacion(String observacion) {
        this.observacion = observacion;
    }

    public Set<ReservaPaquete> getPaquetes() {
        return paquetes;
    }

    public void setPaquetes(Set<ReservaPaquete> paquetes) {
        this.paquetes = paquetes;
    }

    public Set<ReservaServicio> getServicios() {
        return servicios;
    }

    public void setServicios(Set<ReservaServicio> servicios) {
        this.servicios = servicios;
    }

    public Set<Pago> getPagos() {
        return pagos;
    }

    public void setPagos(Set<Pago> pagos) {
        this.pagos = pagos;
    }
}
