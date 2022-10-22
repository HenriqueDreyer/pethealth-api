package com.dreyer.pethealth.api.jpa.entity;

import com.dreyer.pethealth.api.core.domain.entity.Owner;
import lombok.*;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@EqualsAndHashCode
@ToString
@Table(name = "PET", schema = "PETHEALTH")
public class PetEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    Long id;

    @Column(name = "name", nullable = false)
    String name;

    @Column(name = "race", nullable = false)
    String race;

    @Column(name = "species", nullable = false)
    String species;

    @Column(name = "gender", nullable = false)
    String gender;

    @Column(name = "color", nullable = false)
    String color;

    @Column(name = "weight")
    Double weight;

    @Column(name = "birthday")
    LocalDateTime birthday;
}
