package medical.clinic.API.dto.patient;

import medical.clinic.API.entity.Address;
import medical.clinic.API.entity.Patient;

public record DetailDataPatientDTO(String name, String email, String cellphone, String cpf, Address address) {
    public DetailDataPatientDTO (Patient patient){
        this(patient.getName(), patient.getEmail(), patient.getCellphone(), patient.getCpf(), patient.getAddress());
    }
}
