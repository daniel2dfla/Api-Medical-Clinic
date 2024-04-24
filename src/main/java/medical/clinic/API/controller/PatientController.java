package medical.clinic.API.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import medical.clinic.API.dto.patient.ListDataPatientDTO;
import medical.clinic.API.dto.patient.PatientDTO;
import medical.clinic.API.dto.patient.UpdateDataPatientsDTO;
import medical.clinic.API.entity.Patient;
import medical.clinic.API.interfaces.PatientRepository;
import medical.clinic.API.dto.patient.DetailDataPatientDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
@RequestMapping("patients")
@SecurityRequirement(name = "bearer-key")
public class PatientController {

    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid PatientDTO data, UriComponentsBuilder uriBuilder){
        var patient = new Patient(data);
        repository.save(patient);

        var uri = uriBuilder.path("/patients/{id}").buildAndExpand(patient.getId()).toUri();
        return ResponseEntity.created(uri).body(new DetailDataPatientDTO(patient));
    }

    @GetMapping
    public ResponseEntity<Page<ListDataPatientDTO>> list(@PageableDefault(page = 0, size = 10, sort = {"name"}) Pageable pagination){
        var page = repository.findByActiveTrue(pagination).map(ListDataPatientDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UpdateDataPatientsDTO data) {
        var patient = repository.getReferenceById(data.id());
        patient.updateData(data);

        return ResponseEntity.ok(new DetailDataPatientDTO(patient));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity  remover(@PathVariable Long id) {
        var patient = repository.getReferenceById(id);
        patient.disable();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity datail(@PathVariable Long id) {
        var patient = repository.getReferenceById(id);
        return ResponseEntity.ok(new DetailDataPatientDTO(patient));
    }
}
