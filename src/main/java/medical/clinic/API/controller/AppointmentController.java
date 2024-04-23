package medical.clinic.API.controller;

import jakarta.validation.Valid;
import medical.clinic.API.dto.appointment.DataAppointmentDTO;
import medical.clinic.API.dto.appointment.DataAppointmentQueryDTO;
import medical.clinic.API.dto.appointment.ConsultationCancellationDataDTO;
import medical.clinic.API.usecase.AppointmentScheduling;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("appointment")
public class AppointmentController {

    private AppointmentScheduling appointmentScheduling;

    @PostMapping
    @Transactional
    public ResponseEntity appointment(@RequestBody @Valid DataAppointmentDTO data){
        appointmentScheduling.appointment(data);
        return ResponseEntity.ok(new DataAppointmentQueryDTO(null, null, null, null));
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancel(@RequestBody @Valid ConsultationCancellationDataDTO data) {
        appointmentScheduling.cancel(data);
        return ResponseEntity.noContent().build();
    }
}
