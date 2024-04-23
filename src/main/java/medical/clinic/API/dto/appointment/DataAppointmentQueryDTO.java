package medical.clinic.API.dto.appointment;

import java.time.LocalDateTime;

public record DataAppointmentQueryDTO(Long id, Long idDoctor, Long idPatient, LocalDateTime date){
}
