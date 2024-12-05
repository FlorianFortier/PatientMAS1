package com.abernathyclinic.patient.controller;

import com.abernathyclinic.patient.model.Patient;
import com.abernathyclinic.patient.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing patient information.
 * <p>
 * Provides endpoints for creating, updating, retrieving, and deleting patients.
 * </p>
 */
@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    /**
     * Creates a new patient or updates an existing one.
     *
     * @param patient the patient object to create or update.
     * @return a {@link ResponseEntity} containing the created/updated patient, or a 400 Bad Request response if the input is invalid.
     */
    @PostMapping
    public ResponseEntity<Patient> createOrUpdatePatient(@RequestBody Patient patient) {
        if (patient == null) {
            return ResponseEntity.badRequest().build();
        }
        Patient updatedPatient = patientService.saveOrUpdatePatient(patient);
        return ResponseEntity.ok(updatedPatient);
    }

    /**
     * Updates an existing patient's information.
     *
     * @param id      the ID of the patient to update.
     * @param patient the patient object containing updated information.
     * @return a {@link ResponseEntity} containing the updated patient, or a 400 Bad Request response if the input is invalid.
     */
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(@PathVariable Long id, @RequestBody Patient patient) {
        if (patient == null || !id.equals(patient.getId())) {
            return ResponseEntity.badRequest().build();
        }
        Patient updatedPatient = patientService.saveOrUpdatePatient(patient);
        return ResponseEntity.ok(updatedPatient);
    }

    /**
     * Retrieves all patients.
     *
     * @return a {@link ResponseEntity} containing a list of patients, or a 404 Not Found response if no patients are available.
     */
    @GetMapping
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        if (patients.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(patients);
    }

    /**
     * Retrieves a patient by their ID.
     *
     * @param id the ID of the patient to retrieve.
     * @return a {@link ResponseEntity} containing the patient if found, or a 404 Not Found response if the patient does not exist.
     */
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        return patientService.getPatientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    /**
     * Deletes a patient by their ID.
     *
     * @param id the ID of the patient to delete.
     * @return a {@link ResponseEntity} with a 200 OK status if the deletion is successful,
     *         or a 404 Not Found response if the patient does not exist.
     */
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletePatientById(@PathVariable Long id) {
        if (patientService.getPatientById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        patientService.deletePatientById(id);
        return ResponseEntity.ok().build();
    }
}
