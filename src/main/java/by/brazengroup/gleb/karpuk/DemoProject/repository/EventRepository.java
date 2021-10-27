package by.brazengroup.gleb.karpuk.DemoProject.repository;

import by.brazengroup.gleb.karpuk.DemoProject.model.Event;
import by.brazengroup.gleb.karpuk.DemoProject.enums.Type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventRepository extends JpaRepository<Event, Long> {
    List<Event> findAllByDeviceId(Long id);
}
