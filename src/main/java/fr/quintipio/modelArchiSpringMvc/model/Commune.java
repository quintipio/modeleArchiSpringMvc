package fr.quintipio.modelArchiSpringMvc.model;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@EqualsAndHashCode(of="id")
@Entity
@Table(name="COMMUNE")
@Getter
@Setter
@NoArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@ToString(of = {"libelle"} )
public class Commune {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;

    @NotNull
    @Size(max = 5)
    String code_insee;

    @NotNull
    @Size(max = 5)
    String code_postal;

    @NotNull
    String libelle;

}
