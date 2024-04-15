package medical.clinic.API.dto.patient;

import medical.clinic.API.entity.Patient;

public record ListDataPatientDTO(
        Long id,
        String name,
        String email,
        String cpf

) {
    public ListDataPatientDTO(Patient patient){
        this(patient.getId(), patient.getName(), patient.getEmail(), patient.getCpf());
    }
}
