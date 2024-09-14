package com.abernathyclinic.patient.controller;


import com.abernathyclinic.patient.model.Patient;
import com.abernathyclinic.patient.service.PatientService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/patients")
@RequiredArgsConstructor
public class PatientController {

    private PatientService patientService;
    // Create or update a person
    @PostMapping
    public Patient createOrUpdatePatient(@RequestBody Patient patient) {
        return patientService.saveOrUpdatePatient(patient);
    }

    // Get all persons
    @GetMapping
    public List<Patient> getAllPatients() {
        return patientService.getAllPatients();
    }

    // Get person by ID
    @GetMapping("/{id}")
    public Patient getPatientById(@PathVariable String id) {
        return patientService.getPatientById(id).orElse(null);
    }

    // Delete person by ID
    @DeleteMapping("/{id}")
    public Void deletePatientById(@PathVariable String id) {
        return patientService.deletePatientById(id);
    }
}