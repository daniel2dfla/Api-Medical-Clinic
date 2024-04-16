package medical.clinic.API.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import medical.clinic.API.dto.patient.PatientDTO;
import medical.clinic.API.dto.patient.UpdateDataPatientsDTO;

@Getter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Patient")
@Table(name = "patients")
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Boolean active;
    private String name;
    private String email;
    private String cpf;
    private String cellphone;

    @Embedded
    private Address address;

    public Patient(PatientDTO data) {

        this.active = true;
        this.name = data.name();
        this.email = data.email();
        this.cellphone = data.cellphone();
        this.cpf = data.cpf();
        this.address = new Address(data.address());
    }

    public void updateData(UpdateDataPatientsDTO data) {
        if (data.name() != null)
            this.name = data.name();

        if (data.cellphone() != null)
            this.cellphone = data.cellphone();

        if (data.address() != null)
            address.updateDataAddress(data.address());
    }

    public void disable() {
        this.active = false;
    }

}