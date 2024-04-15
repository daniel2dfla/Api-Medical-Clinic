package medical.clinic.API.controller;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import medical.clinic.API.dto.patient.ListDataPatientDTO;
import medical.clinic.API.dto.patient.PatientDTO;
import medical.clinic.API.dto.patient.UpdateDataPatientsDTO;
import medical.clinic.API.entity.Patient;
import medical.clinic.API.interfaces.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("patients")

public class PatientController {

    @Autowired
    private PatientRepository repository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid PatientDTO data){
        repository.save(new Patient(data));
    }

    @GetMapping
    public Page<ListDataPatientDTO> list(@PageableDefault(page = 0, size = 10, sort = {"name"}) Pageable pagination){
        return repository.findByActiveTrue(pagination).map(ListDataPatientDTO::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid UpdateDataPatientsDTO data) {
        var patient = repository.getReferenceById(data.id());
        patient.updateData(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void remover(@PathVariable Long id) {
        var patient = repository.getReferenceById(id);
        patient.disable();
    }
}
