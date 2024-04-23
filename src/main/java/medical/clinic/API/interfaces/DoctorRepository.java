package medical.clinic.API.interfaces;

import medical.clinic.API.dto.doctor.SpecialtyDTO;
import medical.clinic.API.entity.Doctor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDateTime;

public interface DoctorRepository extends JpaRepository<Doctor, Long> {
    Page<Doctor> findByActiveTrue(Pageable pagination);

    @Query("""
            select m from Doctor m
            where
            m.active = true
            and
            m.specialty = :specialty
            and
            m.id not in(
                select c.doctor.id from Appointment c
                where
                c.date = :date
            )
            order by rand()
            limit 1
            """)
    Doctor chooseFreeRandomDoctorOnDate(SpecialtyDTO specialty, LocalDateTime date);

    @Query(
            """
            select m.active
            from Doctor m
            where
            m.id = :id
            """
    )
    Boolean findActiveById(Long id);
}
