package org.esfe.repositorios;

import org.esfe.modelos.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IReservaRepository extends JpaRepository<Reserva, Integer> {

    List<Reserva> findByUsuarioId(Integer usuarioId);
}