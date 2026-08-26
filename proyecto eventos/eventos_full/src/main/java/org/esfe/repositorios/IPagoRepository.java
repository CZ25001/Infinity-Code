package org.esfe.repositorios;

import org.esfe.modelos.EstadoPago;
import org.esfe.modelos.Pago;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface IPagoRepository extends JpaRepository<Pago, Integer> {

    // HU Usuario 15: consultar el estado de mi pago
    List<Pago> findByReservaId(Integer reservaId);

    // HU Administrador 14,15: ver pagos filtrados por estado
    List<Pago> findByEstado(EstadoPago estado);

    // HU Usuario 14: buscar el pago a partir del numero de comprobante entregado
    Optional<Pago> findByNumeroComprobante(String numeroComprobante);

    // HU Administrador 18: consultar los ingresos totales obtenidos (solo pagos procesados)
    @Query("SELECT COALESCE(SUM(p.monto), 0) FROM Pago p WHERE p.estado = 'PROCESADO'")
    BigDecimal obtenerTotalIngresos();
}
