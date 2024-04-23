package medical.clinic.API.usecase.validations.cancellation;

import medical.clinic.API.dto.appointment.ConsultationCancellationDataDTO;
import medical.clinic.API.infra.exception.ValidationException;
import medical.clinic.API.interfaces.AppointmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidatorTimeAdvanceCancellation")
public class ValidatorScheduleAdvance implements ValidatorQueryCancellation{

    @Autowired
    private AppointmentRepository repository;

    @Override
    public void validation(ConsultationCancellationDataDTO data){
        var query = repository.getReferenceById(data.idAppointment());
        var now = LocalDateTime.now();
        var hoursDifference = Duration.between(now, query.getDate()).toHours();

        if(hoursDifference < 24){
            throw new ValidationException("Consultation can only be canceled at least 24 hours in advance!");
        }

    }
}
