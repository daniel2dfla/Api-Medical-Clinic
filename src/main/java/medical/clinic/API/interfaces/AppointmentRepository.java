package medical.clinic.API.interfaces;

import medical.clinic.API.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean existsByPacienteIdAndDataBetween(Long idPaciente, LocalDateTime primeiroHorario, LocalDateTime ultimoHorario);

    boolean existsByDoctorIdAndDateAndReasonCancelattionIsNull(Long idMedico, LocalDateTime data);
}
