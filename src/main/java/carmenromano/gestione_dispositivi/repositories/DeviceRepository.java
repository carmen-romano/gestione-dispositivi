package carmenromano.gestione_dispositivi.repositories;

import carmenromano.gestione_dispositivi.entities.Device;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeviceRepository extends JpaRepository<Device,Integer> {
}
