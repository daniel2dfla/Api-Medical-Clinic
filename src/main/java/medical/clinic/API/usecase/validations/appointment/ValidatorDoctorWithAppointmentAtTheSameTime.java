package medical.clinic.API.usecase.validations.appointment;

import medical.clinic.API.dto.appointment.DataAppointmentDTO;
import medical.clinic.API.infra.exception.ValidationException;
import medical.clinic.API.interfaces.AppointmentRepository;
import medical.clinic.API.usecase.validations.appointment.ValidatorAppointmentScheduling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorDoctorWithAppointmentAtTheSameTime implements ValidatorAppointmentScheduling {

    @Autowired
    private AppointmentRepository repository;

    public void validation(DataAppointmentDTO data){
        var doctorWithAppointmentAtTheSameTime = repository.existsByDoctorIdAndDateAndCancellationReasonDTOIsNull(data.idDoctor(), data.date());
        if (doctorWithAppointmentAtTheSameTime){
            throw new ValidationException("The doctor already has another appointment scheduled at the same time.");
        }
    }
}
