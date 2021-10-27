package by.brazengroup.gleb.karpuk.DemoProject.service;

import by.brazengroup.gleb.karpuk.DemoProject.converters.ProjectDtoConverter;
import by.brazengroup.gleb.karpuk.DemoProject.dto.ProjectDTO;
import by.brazengroup.gleb.karpuk.DemoProject.dto.Stats;
import by.brazengroup.gleb.karpuk.DemoProject.model.Device;
import by.brazengroup.gleb.karpuk.DemoProject.model.Event;
import by.brazengroup.gleb.karpuk.DemoProject.model.Project;
import by.brazengroup.gleb.karpuk.DemoProject.enums.Type;
import by.brazengroup.gleb.karpuk.DemoProject.repository.DeviceRepository;
import by.brazengroup.gleb.karpuk.DemoProject.repository.EventRepository;
import by.brazengroup.gleb.karpuk.DemoProject.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository projectRepository;
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private ProjectDtoConverter projectDtoConverter;

    public List<ProjectDTO> getAllProjects() {
        return projectDtoConverter.listToDto(projectRepository.findAll());
    }


    public Stats makeStatsForProject(Project project) {
        Stats stats = new Stats();
        List<Device> devices = deviceRepository.getAllByProjectId(project.getId());
        stats.setDeviceCount((long) devices.size());
        List<Device> stableDevices = findStableDevices(devices);
        List<Device> deviceWithErrors = findDeviceWithErrors(devices);
        stats.setDeviceWithErrors((long) deviceWithErrors.size());
        stats.setStableDevices((long) stableDevices.size());
        return stats;
    }

    public List<Device> findStableDevices(List<Device> devices) {
        List<Device> stableDevices = devices.stream().filter(device -> device.getEvents().stream().map(event -> device.getEvents()
                                .stream().map(Event::getType).collect(Collectors.toList()))
                        .allMatch(collect -> collect.contains(Type.EVENT)))
                .collect(Collectors.toList());
        devices.removeAll(stableDevices);
        return stableDevices;
    }

    public List<Device> findDeviceWithErrors(List<Device> devices) {
        List<Device> deviceWithErrors = new ArrayList<>();
        devices.forEach(device -> {
            device.getEvents().forEach(event -> {
                Duration duration = Duration.between(event.getDate(), LocalDateTime.now());
                if (event.getType() != Type.EVENT && duration.toHours() <= 24) {
                    deviceWithErrors.add(device);
                }
            });
        });
        return deviceWithErrors;
    }
}

