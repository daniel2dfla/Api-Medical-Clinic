package medical.clinic.API.dto.doctor;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import medical.clinic.API.dto.address.AddressDataDTO;

public record DoctorDTO(
        @NotBlank(message = "Name is required")
        String name,

        @NotBlank
        @Email
        String email,

        @NotBlank
        String cellphone,

        @NotBlank
        @Pattern(regexp = "\\d{4,6}")
        String crm,

        @NotNull
        SpecialtyDTO specialty,

        @NotNull
        @Valid
        AddressDataDTO address
) {
}
