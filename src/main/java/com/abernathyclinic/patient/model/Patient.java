package com.abernathyclinic.patient.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.time.LocalDate;

@Document(collection = "patients")
@Data // Génère les getters, setters, toString, equals, hashCode
@NoArgsConstructor // Génère le constructeur par défaut
@AllArgsConstructor // Génère un constructeur avec tous les paramètres
public class Patient {

    @Id
    private String id;
    private String nom;
    private String prenom;
    private LocalDate dateDeNaissance;
    private String genre;
    private String adresse;
    private String telephone;
}