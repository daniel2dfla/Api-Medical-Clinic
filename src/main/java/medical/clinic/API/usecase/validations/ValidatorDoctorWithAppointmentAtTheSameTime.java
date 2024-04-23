package medical.clinic.API.usecase.validations;

import medical.clinic.API.dto.appointment.DataAppointmentDTO;
import medical.clinic.API.infra.exception.ValidationException;
import medical.clinic.API.interfaces.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorDoctorWithAppointmentAtTheSameTime implements ValidatorAppointmentScheduling {

    @Autowired
    private AppointmentRepository repository;

    public void validator(DataAppointmentDTO data){
        var doctorWithAppointmentAtTheSameTime = repository.existsByDoctorIdAndDateAndReasonCancelattionIsNull(data.idDoctor(), data.date());
        if (doctorWithAppointmentAtTheSameTime){
            throw new ValidationException("The doctor already has another appointment scheduled at the same time.");
        }
    }

    @Override
    public void validation(DataAppointmentDTO data) {

    }
}
