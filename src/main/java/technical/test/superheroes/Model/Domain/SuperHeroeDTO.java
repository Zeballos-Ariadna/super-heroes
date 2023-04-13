package technical.test.superheroes.Model.Domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuperHeroeDTO {
    Integer id;
    String name;
    String superPower;
}
