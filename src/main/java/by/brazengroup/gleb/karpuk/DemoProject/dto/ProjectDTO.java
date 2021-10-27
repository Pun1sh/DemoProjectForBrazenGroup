package by.brazengroup.gleb.karpuk.DemoProject.dto;

import lombok.Data;

import java.util.List;

@Data
public class ProjectDTO {
    private Long id;
    private String projectName;
    private Stats stats;
    private List<String> devices;
}
