package medical.clinic.API.usecase.validations.appointment;

import medical.clinic.API.dto.appointment.DataAppointmentDTO;
import medical.clinic.API.infra.exception.ValidationException;
import medical.clinic.API.interfaces.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorActivePatient implements ValidatorAppointmentScheduling {

    @Autowired
    private PatientRepository repository;

    public void validation(DataAppointmentDTO data){
        var patientActive = repository.findActiveById(data.idPatient());
        if(!patientActive){
            throw new ValidationException("Consultation cannot be scheduled with an excluded patient");
        }
    }
}