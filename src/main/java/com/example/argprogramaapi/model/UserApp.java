package com.example.argprogramaapi.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UserApp {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(nullable = false, updatable = false)
    private Long id;

    private String name;

    private String lastName;

    private String title;

    private String description;

    private String imageUrl;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Experience> experiences;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Education> educations;

    @OneToMany(fetch = FetchType.LAZY)
    private List<Skill> skills;

}
