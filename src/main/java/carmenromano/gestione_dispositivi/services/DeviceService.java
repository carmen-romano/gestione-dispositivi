package carmenromano.gestione_dispositivi.services;

import carmenromano.gestione_dispositivi.entities.Device;
import carmenromano.gestione_dispositivi.entities.Employee;
import carmenromano.gestione_dispositivi.enums.DeviceType;
import carmenromano.gestione_dispositivi.exceptions.BadRequestException;
import carmenromano.gestione_dispositivi.exceptions.NotFoundException;
import carmenromano.gestione_dispositivi.payloads.DevicePayload;
import carmenromano.gestione_dispositivi.payloads.EmployeePayload;
import carmenromano.gestione_dispositivi.repositories.DeviceRepository;

import carmenromano.gestione_dispositivi.repositories.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Optional;

@Service
public class DeviceService {

    @Autowired
    private DeviceRepository deviceRepository;
    @Autowired
    private EmployeeRepository employeeRepository;


    public Device save(DevicePayload body) throws IOException {

        Device device = new Device();
        device.setStatus(body.status());
        device.setType(body.type());

        return deviceRepository.save(device);
    }

    public Page<Device> getAllDevice(int pageNumber, int pageSize, String sortBy) {
        if (pageSize > 50) pageSize = 50;
        Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.by(sortBy));
        return deviceRepository.findAll(pageable);
    }

    public Device findById(int id) {
        return deviceRepository.findById(id).orElseThrow(() -> new NotFoundException(id));
    }

    public void findByIdAndDelete(int id) {
        Device found = this.findById(id);
        deviceRepository.delete(found);
    }


    public Device findByIdAndUpdate(int id, Device body) {
        Optional<Device> deviceOptional = deviceRepository.findById(id);
        if (deviceOptional.isPresent()) {
            Device found = deviceOptional.get();
            found.setStatus(body.getStatus());
            found.setType(body.getType());
            return deviceRepository.save(found);
        } else {
            throw new NotFoundException(id);
        }
    }



}
