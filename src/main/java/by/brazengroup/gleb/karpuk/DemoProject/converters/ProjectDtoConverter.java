package by.brazengroup.gleb.karpuk.DemoProject.converters;

import by.brazengroup.gleb.karpuk.DemoProject.dto.ProjectDTO;
import by.brazengroup.gleb.karpuk.DemoProject.model.Device;
import by.brazengroup.gleb.karpuk.DemoProject.model.Project;
import by.brazengroup.gleb.karpuk.DemoProject.repository.DeviceRepository;
import by.brazengroup.gleb.karpuk.DemoProject.repository.ProjectRepository;
import by.brazengroup.gleb.karpuk.DemoProject.service.DeviceService;
import by.brazengroup.gleb.karpuk.DemoProject.service.ProjectService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Component
public class ProjectDtoConverter {
    @Autowired
    private ProjectService projectService;
    @Autowired
    private DeviceRepository deviceRepository;

    public List<ProjectDTO> listToDto(List<Project> projects) {
        List<ProjectDTO> projectDTOS = new ArrayList<>();
        projects.forEach(project -> {
            ProjectDTO dto = new ProjectDTO();
            dto.setId(project.getId());
            dto.setProjectName(project.getName());
            dto.setStats(projectService.makeStatsForProject(project));
            dto.setDevices(deviceRepository.getAllByProjectId(project.getId()).stream().map(Device::getSerialNumber).collect(Collectors.toList()));
            projectDTOS.add(dto);
        });
        return projectDTOS;
    }
}
