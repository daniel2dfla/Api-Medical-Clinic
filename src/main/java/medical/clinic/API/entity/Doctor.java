package medical.clinic.API.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import medical.clinic.API.dto.doctor.DoctorDTO;
import medical.clinic.API.dto.doctor.SpecialtyDTO;
import medical.clinic.API.dto.doctor.UpdateDataDoctorDTO;

@Table(name = "doctors")
@Entity(name = "Doctor")
@Getter
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
public class Doctor {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String email;
    private String cellphone;
    private String crm;

    @Enumerated(EnumType.STRING)
    private SpecialtyDTO specialty;

    @Embedded
    private Address address;

    private Boolean active;

    public Doctor(DoctorDTO data) {
        this.active = true;
        this.name = data.name();
        this.email = data.email();
        this.cellphone = data.cellphone();
        this.crm = data.crm();
        this.specialty = data.specialty();
        this.address = new Address(data.address());
    }

    public void updateData(UpdateDataDoctorDTO data) {
        if(data.name() != null){
            this.name = data.name();
        }
        if(data.cellphone() != null){
            this.cellphone = data.cellphone();
        }
        if(data.address() != null){
            this.address.updateDataAddress(data.address());
        }
    }

    public void disable() {
        this.active = false;
    }
}
