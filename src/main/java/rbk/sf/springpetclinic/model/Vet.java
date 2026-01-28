package rbk.sf.springpetclinic.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import rbk.sf.springpetclinic.util.PropertyComparator;

import java.util.*;

@Entity
@Table(name = "vets")
public class Vet extends Person{

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "vet_specialities",
            joinColumns = @JoinColumn(name = "vet_id"),
            inverseJoinColumns = @JoinColumn(name = "specialty_id"))
    private Set<Specialty> specialities = new HashSet<>();

    @JsonIgnore
    protected Set<Specialty> getSpecialtiesInternal(){
        if(this.specialities == null){
            this.specialities = new HashSet<>();
        }
        return specialities;
    }

    @JsonIgnore
    protected void setSpecialtiesInternal(Set<Specialty> specialities){
        this.specialities = specialities;
    }

    public List<Specialty> getSpecialities() {
        List<Specialty> sortedSpecialties = new ArrayList<>(getSpecialtiesInternal());
        PropertyComparator.sort(sortedSpecialties);
        return Collections.unmodifiableList(sortedSpecialties);
    }

    public void setSpecialities(Set<Specialty> specialities) {
        this.specialities = specialities;
    }

    public void addSpecialty(Specialty specialty){
        getSpecialtiesInternal().add(specialty);
    }

    public void clearSpecialties(){
        getSpecialtiesInternal().clear();
    }
    public int getNoOfSpecialties(){
        return getSpecialtiesInternal().size();
    }
}
