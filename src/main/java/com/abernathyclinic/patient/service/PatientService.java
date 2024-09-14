package com.abernathyclinic.patient.service;

import com.abernathyclinic.patient.model.Patient;
import com.abernathyclinic.patient.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    // Create or Update a person
    public Patient saveOrUpdatePatient(Patient person) {
        return patientRepository.save(person);
    }

    // Retrieve all persons
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    // Retrieve a person by ID
    public Optional<Patient> getPatientById(String id) {
        return patientRepository.findById(id);
    }

    // Delete a person by ID
    public Void deletePatientById(String id) {
        patientRepository.deleteById(id);
        return null;
    }
}

