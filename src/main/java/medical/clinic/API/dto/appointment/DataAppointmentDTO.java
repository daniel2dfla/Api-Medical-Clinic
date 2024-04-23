package medical.clinic.API.dto.appointment;

import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import medical.clinic.API.dto.doctor.SpecialtyDTO;
import medical.clinic.API.entity.Appointment;

import java.time.LocalDateTime;

public record DataAppointmentDTO(
        Long idDoctor,

        @NotNull
        Long idPatient,

        @NotNull
        @Future
        LocalDateTime date,

        SpecialtyDTO specialty) {

}
