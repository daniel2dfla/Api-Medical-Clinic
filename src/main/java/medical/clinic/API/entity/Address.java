package medical.clinic.API.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import medical.clinic.API.dto.address.addressDataDTO;

@Embeddable
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {
    private String place;
    private String neighborhood;
    private String zipcode;
    private String city;
    private String uf;
    private String number;
    private String complement;

    public Address(addressDataDTO data) {
        this.place = data.place();
        this.neighborhood = data.neighborhood();
        this.zipcode = data.zipcode();
        this.city = data.city();
        this.uf = data.uf();
        this.number = data.number();
        this.complement = data.complement();
    }
}
