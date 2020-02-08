package training.springframework.sfgpetclinic.model;

import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.StringJoiner;

@Entity
@Table(name = "visits")
public class Visit extends BaseEntity {

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Column(name = "date")
    private LocalDate date;

    @Column(name = "description")
    private String description;

    @ManyToOne
    @JoinColumn(name = "pet_id")
    private Pet pet;

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    @Override
    public String toString() {
        return new StringJoiner(", ", Visit.class.getSimpleName() + "[", "]")
                .add("date=" + date)
                .add("description='" + description + "'")
                .add("pet=" + pet.getName())
                .toString();
    }
}
