package medical.clinic.API.controller;

import jakarta.validation.Valid;
import medical.clinic.API.dto.doctor.DoctorDTO;
import medical.clinic.API.dto.doctor.ListDataDoctorDTO;
import medical.clinic.API.entity.Doctor;
import medical.clinic.API.interfaces.DoctorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public List<ListDataDoctorDTO> listar(){
        return repository.findAll().stream().map(ListDataDoctorDTO::new).toList();
    }
}
