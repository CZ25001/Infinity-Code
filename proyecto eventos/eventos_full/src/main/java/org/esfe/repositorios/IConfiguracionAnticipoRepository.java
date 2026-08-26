package org.esfe.repositorios;

import org.esfe.modelos.ConfiguracionAnticipo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IConfiguracionAnticipoRepository extends JpaRepository<ConfiguracionAnticipo, Integer> {

    // HU Administrador 13: obtener el porcentaje de anticipo vigente (el mas reciente y activo)
    ConfiguracionAnticipo findFirstByActivoTrueOrderByFechaActualizacionDesc();
}
