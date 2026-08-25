package org.esfe.modelos;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotNull;
import jakarta.persistence.*;

import java.math.BigDecimal;

// HU Usuario 7,9,11: detalle de los paquetes de comida/decoracion elegidos para una reserva
@Entity
@Table(name = "reservas_paquetes")
public class ReservaPaquete {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "reserva_id", nullable = false)
    private Reserva reserva;

    @ManyToOne
    @JoinColumn(name = "paquete_id", nullable = false)
    private Paquete paquete;

    // Precio del paquete al momento de agregarlo (evita que cambios futuros de precio alteren la cotizacion)
    @NotNull(message = "El precio del paquete es requerido")
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

    public Paquete getPaquete() {
        return paquete;
    }

    public void setPaquete(Paquete paquete) {
        this.paquete = paquete;
    }

    public BigDecimal getPrecioAplicado() {
        return precioAplicado;
    }

    public void setPrecioAplicado(BigDecimal precioAplicado) {
        this.precioAplicado = precioAplicado;
    }
}
