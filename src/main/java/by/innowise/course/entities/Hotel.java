package by.innowise.course.entities;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Set;

@Entity
@Table(name = "hotel")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Hotel implements Serializable {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(min = 2, max = 100, message = "Name must be more than 2 and less than 101 symbol")
    @Column(name = "name")
    private String name;

    @OneToOne(mappedBy = "hotel", cascade = CascadeType.ALL)
    @PrimaryKeyJoinColumn
    private Address address;
//    @OneToMany(mappedBy = "hotel")
//    private Set<Category> categories;
}
