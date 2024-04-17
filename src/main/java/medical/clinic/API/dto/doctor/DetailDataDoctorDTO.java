package medical.clinic.API.dto.doctor;

import medical.clinic.API.dto.address.AddressDataDTO;
import medical.clinic.API.entity.Address;
import medical.clinic.API.entity.Doctor;

public record DetailDataDoctorDTO(Long id, String name, String email, String crm, String cellphone, SpecialtyDTO specialty, Address address) {
    public DetailDataDoctorDTO(Doctor doctor){
        this(doctor.getId(), doctor.getName(), doctor.getEmail(), doctor.getCrm(), doctor.getCellphone(), doctor.getSpecialty(), doctor.getAddress());
    }
}
