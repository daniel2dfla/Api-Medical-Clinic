package medical.clinic.API.usecase.validations.cancellation;

import medical.clinic.API.dto.appointment.ConsultationCancellationDataDTO;

public interface ValidatorQueryCancellation {
    void validation(ConsultationCancellationDataDTO data);
}
