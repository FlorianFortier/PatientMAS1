package com.abernathyclinic.patient.service;

import com.abernathyclinic.patient.model.Patient;
import com.abernathyclinic.patient.repository.PatientRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PatientService {

    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    private final PatientRepository patientRepository;

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
    public void deletePatientById(String id) {
        patientRepository.deleteById(id);
    }
}

