package medical.clinic.API.usecase.validations;

import medical.clinic.API.dto.appointment.DataAppointmentDTO;
import medical.clinic.API.infra.exception.ValidationException;

import java.time.DayOfWeek;
import java.time.Duration;
import java.time.LocalDateTime;

public class ValidatorTimeAdvance {
    public void validator(DataAppointmentDTO data){
       var dateAppointment = data.date();
       var now = LocalDateTime.now();
       var differenceOfMinutes = Duration.between(now, dateAppointment).toMinutes();

       if(differenceOfMinutes < 30){
           throw new ValidationException("Appointment must be scheduled at least 30 minutes in advance");
       }
    }
}
