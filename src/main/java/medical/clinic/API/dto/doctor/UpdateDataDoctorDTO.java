package medical.clinic.API.dto.doctor;

import jakarta.validation.constraints.NotNull;
import medical.clinic.API.dto.address.addressDataDTO;

public record UpdateDataDoctorDTO(
        @NotNull
        Long id,
        String name,
        String cellphone,
        addressDataDTO address
) {
}
