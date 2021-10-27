package by.brazengroup.gleb.karpuk.DemoProject.repository;

import by.brazengroup.gleb.karpuk.DemoProject.model.Project;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project, Long> {
}
