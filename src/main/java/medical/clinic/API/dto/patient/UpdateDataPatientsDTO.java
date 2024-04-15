package medical.clinic.API.dto.patient;

import jakarta.validation.Valid;
import medical.clinic.API.dto.address.addressDataDTO;

public record UpdateDataPatientsDTO(
        Long id,

        String name,

        String cellphone,

        @Valid
        addressDataDTO address
) {
}
