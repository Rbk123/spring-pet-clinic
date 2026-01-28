package rbk.sf.springpetclinic.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.NotEmpty;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "owners")
public class Owner extends Person{
    @Column(name = "address")
    @NotEmpty
    private String address;

    @Column(name="city")
    @NotEmpty
    private String city;

    @Column(name = "telephone")
    @NotEmpty
    @Digits(fraction = 0, integer = 10)
    private Integer telephone;

   @OneToMany(cascade = CascadeType.ALL, mappedBy = "owner", fetch = FetchType.EAGER)
   @JoinColumn(name = "owner_id")
    private Set<Pet> pets = new HashSet<>();

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public Integer getTelephone() {
        return telephone;
    }

    public void setTelephone(Integer telephone) {
        this.telephone = telephone;
    }

    public Set<Pet> getPets() {
        return pets;
    }

    public void setPets(Set<Pet> pets) {
        this.pets = pets;
    }

    public void addPet(Pet pet){
        if(pets == null){
            pets = new HashSet<>();
        }
        pets.add(pet);
        pet.setOwner(this);
    }

    public Pet getPet(String name){
        return pets.stream().filter(pet -> pet.getName().equalsIgnoreCase(name)).findFirst().orElse(null);
    }

    public Pet getPet(Integer id){
        return pets.stream().filter(pet -> pet.getId()!=null && pet.getId().equals(id)).findFirst().orElse(null);
    }
}
