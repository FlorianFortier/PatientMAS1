package com.abernathyclinic.patient.controller;

import com.abernathyclinic.patient.model.Patient;
import com.abernathyclinic.patient.service.PatientService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private final PatientService patientService;

    // Create or update a patient
    @PostMapping
    @Operation(summary = "Create or update a patient", description = "Creates a new patient or updates an existing one")
    @ApiResponse(responseCode = "200", description = "Patient created or updated successfully")
    @ApiResponse(responseCode = "400", description = "Invalid patient details provided")
    public ResponseEntity<Patient> createOrUpdatePatient(@RequestBody Patient patient) {
        return ResponseEntity.ok(patientService.saveOrUpdatePatient(patient));
    }

    // Get all patients
    @GetMapping
    @Operation(summary = "Get all patients", description = "Retrieves a list of all patients")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved list of patients")
    @ApiResponse(responseCode = "404", description = "No patients found")
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = patientService.getAllPatients();
        if (patients.isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        return ResponseEntity.ok(patients);
    }

    // Get patient by ID
    @GetMapping("/{id}")
    @Operation(summary = "Get patient by ID", description = "Retrieves a patient by their unique ID")
    @ApiResponse(responseCode = "200", description = "Successfully retrieved the patient")
    @ApiResponse(responseCode = "404", description = "Patient not found")
    public ResponseEntity<Patient> getPatientById(@PathVariable String id) {
        return patientService.getPatientById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.status(HttpStatus.NOT_FOUND).build());
    }

    // Delete patient by ID
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete patient by ID", description = "Deletes a patient by their unique ID")
    @ApiResponse(responseCode = "200", description = "Patient deleted successfully")
    @ApiResponse(responseCode = "404", description = "Patient not found")
    public ResponseEntity<Void> deletePatientById(@PathVariable String id) {
        if (patientService.getPatientById(id).isEmpty()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
        patientService.deletePatientById(id);
        return ResponseEntity.ok().build();
    }
}
