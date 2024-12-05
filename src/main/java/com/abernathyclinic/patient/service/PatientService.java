package com.abernathyclinic.patient.service;

import com.abernathyclinic.patient.model.Patient;
import com.abernathyclinic.patient.repository.PatientRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service class for managing patient operations.
 * <p>
 * This service provides methods for creating, updating, retrieving, and deleting patients.
 * All operations are transactional to ensure data consistency.
 * </p>
 */
@Transactional
@Service
public class PatientService {

    private final PatientRepository patientRepository;

    /**
     * Constructs a new {@code PatientService} with the specified {@link PatientRepository}.
     *
     * @param patientRepository the repository used for managing patient data.
     */
    public PatientService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    /**
     * Saves or updates a patient in the database.
     *
     * @param patient the patient object to save or update.
     * @return the saved or updated {@link Patient} object.
     */
    public Patient saveOrUpdatePatient(Patient patient) {
        return patientRepository.save(patient);
    }

    /**
     * Retrieves all patients from the database.
     *
     * @return a list of {@link Patient} objects.
     */
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    /**
     * Retrieves a patient by their ID.
     *
     * @param id the ID of the patient to retrieve.
     * @return an {@link Optional} containing the {@link Patient} object if found, or empty if not.
     */
    public Optional<Patient> getPatientById(Long id) {
        return patientRepository.findById(id);
    }

    /**
     * Deletes a patient by their ID.
     *
     * @param id the ID of the patient to delete.
     */
    public void deletePatientById(Long id) {
        patientRepository.deleteById(id);
    }
}
