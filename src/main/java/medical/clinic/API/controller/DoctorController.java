package medical.clinic.API.controller;

import jakarta.validation.Valid;
import medical.clinic.API.dto.doctor.DoctorDTO;
import medical.clinic.API.dto.doctor.ListDataDoctorDTO;
import medical.clinic.API.dto.doctor.UpdateDataDoctorDTO;
import medical.clinic.API.entity.Doctor;
import medical.clinic.API.interfaces.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("doctors")
public class DoctorController {

    @Autowired
    private DoctorRepository repository;

    @PostMapping
    @Transactional
    public void register(@RequestBody @Valid DoctorDTO data){
        repository.save(new Doctor(data));

    }

    @GetMapping
    public Page<ListDataDoctorDTO> list(@PageableDefault(size = 10, sort = {"name"}) Pageable pagination){
        return repository.findByActiveTrue(pagination).map(ListDataDoctorDTO::new);
    }

    @PutMapping
    @Transactional
    public void update(@RequestBody @Valid UpdateDataDoctorDTO data){
        var doctor = repository.getReferenceById(data.id());
        doctor.updateData(data);
    }

    @DeleteMapping("/{id}")
    @Transactional
    public void delete(@PathVariable Long id){
        var doctor = repository.getReferenceById(id);
        doctor.disable();
    }
}
