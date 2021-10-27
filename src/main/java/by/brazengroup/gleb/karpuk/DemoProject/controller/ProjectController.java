package by.brazengroup.gleb.karpuk.DemoProject.controller;

import by.brazengroup.gleb.karpuk.DemoProject.dto.DeviceDTO;
import by.brazengroup.gleb.karpuk.DemoProject.dto.ProjectDTO;
import by.brazengroup.gleb.karpuk.DemoProject.model.Project;
import by.brazengroup.gleb.karpuk.DemoProject.service.DeviceService;
import by.brazengroup.gleb.karpuk.DemoProject.service.ProjectService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping(value = "/project")
public class ProjectController {
    @Autowired
    private DeviceService deviceService;
    @Autowired
    private ProjectService projectService;

    @GetMapping(value = "/devices/{id}")
    public Map<String, DeviceDTO> getAllDevicesByProjectId(@PathVariable Long id) {
        List<DeviceDTO> allDevicesByProjectId = deviceService.getAllDevicesByProjectId(id);
        return allDevicesByProjectId.stream().collect(Collectors.toMap(DeviceDTO::getSerialNumber, deviceDTO -> deviceDTO, (a, b) -> b, HashMap::new));
    }

    @GetMapping
    public List<ProjectDTO> getAllProjects() {
        return projectService.getAllProjects();
    }

}
