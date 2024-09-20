package com.abernathyclinic.patient.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import org.springframework.data.mongodb.core.mapping.Unwrapped;

import java.time.LocalDate;

@Document(collection = "patients")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Patient {

    @Id
    private String id;
    private String nom;
    private String prenom;
    private LocalDate dateDeNaissance;
    private String genre;
    @Unwrapped.Nullable
    private String adresse;
    @Unwrapped.Nullable
    private String telephone;
}