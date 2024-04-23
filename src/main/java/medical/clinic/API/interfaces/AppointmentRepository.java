package medical.clinic.API.interfaces;

import medical.clinic.API.entity.Appointment;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;

public interface AppointmentRepository extends JpaRepository<Appointment, Long> {
    boolean existsByPatientIdAndDateBetween(Long idPatient, LocalDateTime firstTime, LocalDateTime lastTime);

    boolean existsByDoctorIdAndDateAndCancellationReasonDTOIsNull(Long idDoctor, LocalDateTime date);
}
