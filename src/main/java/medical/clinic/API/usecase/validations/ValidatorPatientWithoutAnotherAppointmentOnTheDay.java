package medical.clinic.API.usecase.validations;

import medical.clinic.API.dto.appointment.DataAppointmentDTO;
import medical.clinic.API.infra.exception.ValidationException;
import medical.clinic.API.interfaces.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ValidatorPatientWithoutAnotherAppointmentOnTheDay implements ValidatorAppointmentScheduling{

    @Autowired
    private AppointmentRepository repository;

    public void validation(DataAppointmentDTO data){
        var  firstTime = data.date().withHour(7);
        var lastTime = data.date().withHour(18);
        var patientHasAnotherAppointmentOnDay = repository.existsByPacienteIdAndDataBetween(data.idPatient(), firstTime, lastTime);
        if (patientHasAnotherAppointmentOnDay){
            throw new ValidationException("Patient already has an appointment scheduled that day");
        }
    }
}
