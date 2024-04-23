package medical.clinic.API.usecase.validations;

import medical.clinic.API.dto.appointment.DataAppointmentDTO;
import medical.clinic.API.infra.exception.ValidationException;
import medical.clinic.API.interfaces.PatientRepository;

public class ValidatorActivePatient {

    private PatientRepository repository;

    public void validator(DataAppointmentDTO data){
        var patientActive = repository.findActiveById(data.idPatient());
        if(!patientActive){
            throw new ValidationException("Consultation cannot be scheduled with an excluded patient");
        }
    }
}