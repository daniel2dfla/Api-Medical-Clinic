package medical.clinic.API.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import medical.clinic.API.dto.appointment.DataAppointmentDTO;
import medical.clinic.API.dto.appointment.DataAppointmentQueryDTO;
import medical.clinic.API.dto.appointment.ConsultationCancellationDataDTO;
import medical.clinic.API.usecase.AppointmentScheduling;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("appointment")
@SecurityRequirement(name = "bearer-key")
public class AppointmentController {

    @Autowired
    private AppointmentScheduling appointmentScheduling;

    @PostMapping
    @Transactional
    public ResponseEntity appointment(@RequestBody @Valid DataAppointmentDTO data){
        var dto = appointmentScheduling.appointment(data);
        return ResponseEntity.ok(dto);
    }

    @DeleteMapping
    @Transactional
    public ResponseEntity cancel(@RequestBody @Valid ConsultationCancellationDataDTO data) {
        appointmentScheduling.cancel(data);
        return ResponseEntity.noContent().build();
    }
}
