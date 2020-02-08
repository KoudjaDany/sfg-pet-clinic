package training.springframework.sfgpetclinic.model;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.StringJoiner;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
@Table(name = "types")
public class PetType extends BaseEntity{

    @Column(name ="name")
    private String name;


    @Override
    public String toString() {
        return new StringJoiner(", ", PetType.class.getSimpleName() + "[", "]")
                .add("name='" + name + "'")
                .toString();
    }
}
