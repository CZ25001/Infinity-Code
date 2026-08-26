package org.esfe.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;

// HU Usuario 8,9,11: detalle de los servicios adicionales (DJ, fotografo, pastel, meseros...) de una reserva
@Entity
@Table(name = "reservas_servicios")
public class ReservaServicio {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "reserva_id", nullable = false)
    private Reserva reserva;

    @ManyToOne
    @JoinColumn(name = "servicio_adicional_id", nullable = false)
    private ServicioAdicional servicioAdicional;

    // Precio del servicio al momento de agregarlo (evita que cambios futuros de precio alteren la cotizacion)
    @NotNull(message = "El precio del servicio es requerido")
    @DecimalMin(value = "0.0", inclusive = false, message = "El precio debe ser mayor a 0")
    @Column(name = "precio_aplicado", precision = 10, scale = 2, nullable = false)
    private BigDecimal precioAplicado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Reserva getReserva() {
        return reserva;
    }

    public void setReserva(Reserva reserva) {
        this.reserva = reserva;
    }

    public ServicioAdicional getServicioAdicional() {
        return servicioAdicional;
    }

    public void setServicioAdicional(ServicioAdicional servicioAdicional) {
        this.servicioAdicional = servicioAdicional;
    }

    public BigDecimal getPrecioAplicado() {
        return precioAplicado;
    }

    public void setPrecioAplicado(BigDecimal precioAplicado) {
        this.precioAplicado = precioAplicado;
    }
}
