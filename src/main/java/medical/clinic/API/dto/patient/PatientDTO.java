package medical.clinic.API.dto.patient;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Pattern;
import medical.clinic.API.dto.address.addressDataDTO;

public record PatientDTO(
        Boolean active,

        @NotBlank
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String cellphone,

        @NotBlank
        @Pattern(regexp = "\\d{3}\\.?\\d{3}\\.?\\d{3}\\-?\\d{2}")
        String cpf,

        @NotNull
        @Valid
        addressDataDTO address
) {
}
