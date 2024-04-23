package medical.clinic.API.usecase.validations;

import medical.clinic.API.dto.appointment.DataAppointmentDTO;
import medical.clinic.API.infra.exception.ValidationException;
import org.springframework.stereotype.Component;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

@Component
public class ValidatorTimeAdvance implements ValidatorAppointmentScheduling {
    public void validation(DataAppointmentDTO data){
       var dateAppointment = data.date();
       var now = LocalDateTime.now();
       var differenceOfMinutes = Duration.between(now, dateAppointment).toMinutes();

       if(differenceOfMinutes < 30){
           throw new ValidationException("Appointment must be scheduled at least 30 minutes in advance");
       }
    }
}
