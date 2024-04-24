package medical.clinic.API.controller;

import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import medical.clinic.API.dto.doctor.DoctorDTO;
import medical.clinic.API.dto.doctor.ListDataDoctorDTO;
import medical.clinic.API.dto.doctor.UpdateDataDoctorDTO;
import medical.clinic.API.dto.doctor.DetailDataDoctorDTO;
import medical.clinic.API.entity.Doctor;
import medical.clinic.API.interfaces.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;


@RestController
@RequestMapping("doctors")
@SecurityRequirement(name = "bearer-key")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public ResponseEntity register(@RequestBody @Valid DoctorDTO data, UriComponentsBuilder uriBilder){
        var doctor = new Doctor(data);
        repository.save(doctor);

        var uri =uriBilder.path("/doctors/{id}").buildAndExpand(doctor.getId()).toUri();

        return ResponseEntity.created(uri).body(new DetailDataDoctorDTO(doctor));
    }

    @GetMapping
    public ResponseEntity<Page<ListDataDoctorDTO>> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination){
        var page = repository.findByActiveTrue(pagination).map(ListDataDoctorDTO::new);
        return ResponseEntity.ok(page);
    }

    @PutMapping
    @Transactional
    public ResponseEntity update(@RequestBody @Valid UpdateDataDoctorDTO data){
        var doctor = repository.getReferenceById(data.id());
        doctor.updateData(data);

        return ResponseEntity.ok(new DetailDataDoctorDTO(doctor));
    }

    @DeleteMapping("/{id}")
    @Transactional
    public ResponseEntity delete(@PathVariable Long id){
        var doctor = repository.getReferenceById(id);
        doctor.disable();

        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}")
    public ResponseEntity detail(@PathVariable Long id){
        var doctor = repository.getReferenceById(id);

        return ResponseEntity.ok(new DetailDataDoctorDTO(doctor));
    }
}
