package com.abernathyclinic.patient.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity // Indique que cette classe est une entité JPA
@Table(name = "patients") // Nom de la table dans la base de données
public class Patient {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // Auto-incrémentation pour l'ID
    private Long id;

    @Column(nullable = false) // Non nullable
    private String nom;

    @Column(nullable = false) // Non nullable
    private String prenom;

    @Column(nullable = false) // Non nullable
    private LocalDate dateDeNaissance;

    @Column(nullable = false) // Non nullable
    private String genre;

    @Column(nullable = true) // Optionnel
    private String adresse;

    @Column(nullable = true) // Optionnel
    private String telephone;

    @UpdateTimestamp // Gère automatiquement la date de dernière modification
    @Column(nullable = false) // Non nullable
    private LocalDate lastModified;

    @CreationTimestamp // Gère automatiquement la date de création
    @Column(nullable = false, updatable = false) // Non nullable, non modifiable après la création
    private LocalDate createdAt;

    @Column(nullable = false) // Non nullable
    private String whoLastModified;

    @Column(columnDefinition = "TEXT", nullable = false) // Si les notes peuvent être longues, non nullable
    private String note;

}
