package medical.clinic.API.usecase.validations;

import medical.clinic.API.dto.appointment.DataAppointmentDTO;
import medical.clinic.API.infra.exception.ValidationException;
import medical.clinic.API.interfaces.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorActiveDoctor implements ValidatorAppointmentScheduling{

    @Autowired
    private DoctorRepository doctorRepository;

    public void validation(DataAppointmentDTO data){
        if(data.idDoctor() == null){
            return;
        }

        var doctorIsActive = doctorRepository.findActiveById(data.idDoctor());
        if(!doctorIsActive){
            throw new ValidationException("Appointment cannot be scheduled with an inactive doctor");
        }
    }
}
