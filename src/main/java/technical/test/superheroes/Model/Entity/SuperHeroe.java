package technical.test.superheroes.Model.Entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.*;
@Entity
@Table(name = "super_heroe")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SuperHeroe {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name", columnDefinition = "VARCHAR(100)")
    private String name;

    @Column(name = "super_power", columnDefinition = "VARCHAR(200)")
    private String superPower;
}
