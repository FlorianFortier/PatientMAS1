package com.abernathyclinic.patient.model;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.hibernate.proxy.HibernateProxy;

import java.time.LocalDate;
import java.util.Objects;

@Getter
@Setter
@ToString
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
    @Column()
    private LocalDate lastModified;

    @CreationTimestamp // Gère automatiquement la date de création
    @Column(nullable = false, updatable = false) // Non nullable, non modifiable après la création
    private LocalDate createdAt;

    @Column()
    private String whoLastModified;

    @Override
    public final boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Patient patient = (Patient) o;
        return Objects.equals(id, patient.id);
    }

    @Override
    public final int hashCode() {
        return Objects.hash(getId());
    }
}
