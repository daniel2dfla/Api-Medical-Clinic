package medical.clinic.API.interfaces;

import medical.clinic.API.dto.address.AddressDataDTO;
import medical.clinic.API.dto.doctor.DoctorDTO;
import medical.clinic.API.dto.doctor.SpecialtyDTO;
import medical.clinic.API.dto.patient.PatientDTO;
import medical.clinic.API.entity.Appointment;
import medical.clinic.API.entity.Doctor;
import medical.clinic.API.entity.Patient;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;


import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class DoctorRepositoryTest {

    @Autowired
    private  DoctorRepository doctorRepository;

    @Autowired
    private TestEntityManager em;

    @Test
    @DisplayName("It should return null as the only registered doctor is not available on that date.")
    void chooseFreeRandomDoctorOnDateScenario1() {
        var nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var doctor = registerDoctor("Doctor", "doctor@hotmail.com", "123456", SpecialtyDTO.gynecology);
        var patient = registerPatient("Patient", "patient@hotmail.com", "95874837658");
        registerAppointment(doctor, patient, nextMondayAt10);


        var doctorFree = doctorRepository.chooseFreeRandomDoctorOnDate(SpecialtyDTO.gynecology, nextMondayAt10);
        assertThat(doctorFree).isNull();
    }

    @Test
    @DisplayName("You should return the doctor when he is available on the date")
    void chooseFreeRandomDoctorOnDateScenario2(){
        var nextMondayAt10 = LocalDate.now()
                .with(TemporalAdjusters.next(DayOfWeek.MONDAY))
                .atTime(10, 0);
        var doctor = registerDoctor("Doctor", "doctor@hotmail.com", "123456", SpecialtyDTO.gynecology);
        var doctorFree = doctorRepository.chooseFreeRandomDoctorOnDate(SpecialtyDTO.gynecology, nextMondayAt10);
        assertThat(doctorFree).isEqualTo(doctor);

    }

    private void registerAppointment(Doctor doctor, Patient patient, LocalDateTime date) {
        em.persist(new Appointment(null, doctor, patient, date, null));
    }

    private Doctor registerDoctor(String name, String email, String crm, SpecialtyDTO specialty) {
        var doctor = new Doctor(dataDoctor(name, email, crm, specialty));
        em.persist(doctor);
        return doctor;
    }

    private Patient registerPatient(String nome, String email, String cpf) {
        var patient = new Patient(dataPatient(nome, email, cpf));
        em.persist(patient);
        return patient;
    }

    private DoctorDTO dataDoctor(String name, String email, String crm, SpecialtyDTO specialty) {
        return new DoctorDTO(
                name,
                email,
                "61999999999",
                crm,
                specialty,
                addressDataDTO()
        );
    }

    private PatientDTO dataPatient(String name, String email, String cpf) {
        return new PatientDTO(
                name,
                email,
                "61999999999",
                cpf,
                addressDataDTO()
        );
    }

    private AddressDataDTO addressDataDTO() {
        return new AddressDataDTO(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }
}