package by.brazengroup.gleb.karpuk.DemoProject.converters;

import by.brazengroup.gleb.karpuk.DemoProject.dto.DeviceDTO;
import by.brazengroup.gleb.karpuk.DemoProject.dto.SummaryInfo;
import by.brazengroup.gleb.karpuk.DemoProject.model.Device;
import by.brazengroup.gleb.karpuk.DemoProject.service.DeviceService;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Data
@Component
public class DeviceDtoConverter {
    @Autowired
    private DeviceService deviceService;

    public List<DeviceDTO> listToDTO(List<Device> devices) {
        List<DeviceDTO> deviceDTOS = new ArrayList<>();
        devices.forEach(device -> {
            DeviceDTO dto = new DeviceDTO();
            SummaryInfo summaryInfo = deviceService.makeSummaryInfoForDevice(device);
            dto.setId(device.getId());
            dto.setSerialNumber(device.getSerialNumber());
            dto.setProjectId(device.getProject().getId());
            if (summaryInfo.getErrorCount() > 0) {
                dto.setHasErrors(true);
            } else {
                dto.setHasErrors(false);
            }
            dto.setSummaryInfo(summaryInfo);
            deviceDTOS.add(dto);
        });
        return deviceDTOS;
    }
}
