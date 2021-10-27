package by.brazengroup.gleb.karpuk.DemoProject.repository;

import by.brazengroup.gleb.karpuk.DemoProject.model.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    List<Device> getAllByProjectId(Long id);
}
