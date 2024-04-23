package medical.clinic.API.usecase.validations.appointment;

import medical.clinic.API.dto.appointment.DataAppointmentDTO;

public interface ValidatorAppointmentScheduling {
    void validation(DataAppointmentDTO data);
}
