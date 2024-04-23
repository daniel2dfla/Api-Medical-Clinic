package medical.clinic.API.usecase.validations;

import medical.clinic.API.dto.appointment.DataAppointmentDTO;

public interface ValidatorAppointmentScheduling {
    void validation(DataAppointmentDTO data);
}
