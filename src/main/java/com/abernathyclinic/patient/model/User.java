package com.abernathyclinic.patient.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Setter
@Getter
@Document(collection = "users")  // Spécifie la collection MongoDB
@NoArgsConstructor // Génère le constructeur par défaut
@AllArgsConstructor // Génère un constructeur avec tous les paramètres
public class User {

    @Id
    private String username;  // Utilisé comme identifiant (équivaut à `_id` dans MongoDB)
    private String password;
    private String role;

}
