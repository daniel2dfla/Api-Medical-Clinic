package medical.clinic.API.dto.appointment;

import jakarta.validation.constraints.NotNull;

public record ConsultationCancellationDataDTO (
        @NotNull
        Long idAppointment,

        @NotNull
        CancellationReasonDTO reason
){
}
