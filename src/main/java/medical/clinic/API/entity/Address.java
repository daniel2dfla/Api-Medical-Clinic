package medical.clinic.API.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import medical.clinic.API.dto.address.AddressDataDTO;

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

    public Address(AddressDataDTO data) {
        this.place = data.place();
        this.neighborhood = data.neighborhood();
        this.zipcode = data.zipcode();
        this.city = data.city();
        this.uf = data.uf();
        this.number = data.number();
        this.complement = data.complement();
    }

    public void updateDataAddress(AddressDataDTO data) {
        if(data.place() != null){
            this.place = data.place();
        }
        if(data.neighborhood() != null){
            this.neighborhood = data.neighborhood();
        }
        if(data.zipcode() != null){
            this.zipcode = data.zipcode();
        }
        if(data.city() != null){
            this.city = data.city();
        }
        if(data.uf() != null){
            this.uf = data.uf();
        }
        if(data.number() != null){
            this.number = data.number();
        }
        if(data.complement() != null){
            this.complement = data.complement();
        }
    }
}
