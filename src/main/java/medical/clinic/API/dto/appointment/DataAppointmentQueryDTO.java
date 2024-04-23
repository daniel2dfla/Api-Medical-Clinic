package medical.clinic.API.dto.appointment;

import medical.clinic.API.entity.Appointment;

import java.time.LocalDateTime;

public record DataAppointmentQueryDTO(Long id, Long idDoctor, Long idPatient, LocalDateTime date){
    public DataAppointmentQueryDTO(Appointment appointment) {
        this(appointment.getId(), appointment.getDoctor().getId(), appointment.getPatient().getId(), appointment.getDate());
    }
}
