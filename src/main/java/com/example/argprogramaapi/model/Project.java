package com.example.argprogramaapi.model;


import lombok.*;

import javax.persistence.*;
import java.util.Date;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
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
