package com.example.argprogramaapi.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Project {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private String description;

    private String urlRepo;

    private String urlDemo;

    @OneToOne(cascade = CascadeType.ALL)
    private Image image;

    @Temporal(TemporalType.DATE)
    private Date startDate;

}
