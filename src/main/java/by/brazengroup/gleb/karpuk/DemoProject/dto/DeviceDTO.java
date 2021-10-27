package by.brazengroup.gleb.karpuk.DemoProject.dto;

import lombok.Data;

@Data
public class DeviceDTO {
    private Long id;
    private String serialNumber;
    private Long projectId;
    private Boolean hasErrors;
    private SummaryInfo summaryInfo;
}
