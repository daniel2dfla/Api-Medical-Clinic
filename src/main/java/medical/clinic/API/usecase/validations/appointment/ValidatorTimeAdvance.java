package medical.clinic.API.usecase.validations.appointment;

import medical.clinic.API.dto.appointment.DataAppointmentDTO;
import medical.clinic.API.infra.exception.ValidationException;
import medical.clinic.API.usecase.validations.appointment.ValidatorAppointmentScheduling;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.LocalDateTime;

@Component("ValidatorScheduleAdvanceSchedule")
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
