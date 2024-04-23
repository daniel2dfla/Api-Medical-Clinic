package medical.clinic.API.usecase.validations;

import medical.clinic.API.dto.appointment.DataAppointmentDTO;
import medical.clinic.API.infra.exception.ValidationException;

import java.time.DayOfWeek;

public class ValidatorClinicOpeningHours {
    public void validator(DataAppointmentDTO data){
        var dateAppointment = data.date();

        var sunday = dateAppointment.getDayOfWeek().equals(DayOfWeek.SUNDAY);
        var beforeTheClinicOpens = dateAppointment.getHour() < 7;
        var afterTheClinicCloses = dateAppointment.getHour() > 18;
        if (sunday || beforeTheClinicOpens || afterTheClinicCloses){
            throw new ValidationException("Appointment outside clinic opening hours");
        }
    }
}
