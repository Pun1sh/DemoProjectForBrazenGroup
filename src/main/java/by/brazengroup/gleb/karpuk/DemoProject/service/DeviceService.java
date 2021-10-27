package by.brazengroup.gleb.karpuk.DemoProject.service;

import by.brazengroup.gleb.karpuk.DemoProject.converters.DeviceDtoConverter;
import by.brazengroup.gleb.karpuk.DemoProject.dto.DeviceDTO;
import by.brazengroup.gleb.karpuk.DemoProject.dto.SummaryInfo;
import by.brazengroup.gleb.karpuk.DemoProject.model.Device;
import by.brazengroup.gleb.karpuk.DemoProject.model.Event;
import by.brazengroup.gleb.karpuk.DemoProject.enums.Type;
import by.brazengroup.gleb.karpuk.DemoProject.repository.DeviceRepository;
import by.brazengroup.gleb.karpuk.DemoProject.repository.EventRepository;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class DeviceService {
    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private DeviceDtoConverter deviceDtoConverter;

    public List<DeviceDTO> getAllDevicesByProjectId(Long id) {
        return deviceDtoConverter.listToDTO(deviceRepository.getAllByProjectId(id));
    }

    public SummaryInfo makeSummaryInfoForDevice(Device device) {
        List<Event> all = eventRepository.findAllByDeviceId(device.getId());
        List<Type> types = all.stream().map(Event::getType).collect(Collectors.toList());
        SummaryInfo summaryInfo = new SummaryInfo();
        summaryInfo.setErrorCount(types.stream().filter(type -> type.equals(Type.ERROR)).count());
        summaryInfo.setEventCount(types.stream().filter(type -> type.equals(Type.ERROR)).count());
        summaryInfo.setWarningCount(types.stream().filter(type -> type.equals(Type.ERROR)).count());
        return summaryInfo;
    }
}
