package medical.clinic.API.dto.doctor;

import medical.clinic.API.entity.Doctor;

public record ListDataDoctorDTO(
        String name,
        String email,
        String crm,
        SpecialtyDTO specialty

) {
    public ListDataDoctorDTO(Doctor doctor){
        this(doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getSpecialty());
    }
}
