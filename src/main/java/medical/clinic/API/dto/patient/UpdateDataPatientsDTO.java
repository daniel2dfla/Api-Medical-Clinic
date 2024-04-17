package medical.clinic.API.dto.patient;

import jakarta.validation.Valid;
import medical.clinic.API.dto.address.AddressDataDTO;

public record UpdateDataPatientsDTO(
        Long id,

        String name,

        String cellphone,

        @Valid
        AddressDataDTO address
) {
}
