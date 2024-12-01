package com.abernathyclinic.patient.service;

import com.abernathyclinic.patient.model.Patient;
import com.abernathyclinic.patient.repository.PatientRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PatientServiceTest {

    @Mock
    private PatientRepository patientRepository;

    @InjectMocks
    private PatientService patientService;

    @Test
    void testCreateOrUpdatePatient() {
        Patient patient = new Patient(1L, "TestNone", "PrenomTest", LocalDate.of(1990, 1, 1), "M", "123 rue", "123456789", LocalDate.now(), LocalDate.now(), "admin");

        when(patientRepository.save(patient)).thenReturn(patient);

        Patient savedPatient = patientService.saveOrUpdatePatient(patient);

        assertNotNull(savedPatient);
        assertEquals("TestNone", savedPatient.getNom());
    }
    @Test
    void testCreateOrUpdatePatient_TestBorderline() {
        Patient patient = new Patient(2L, "TestBorderline", "PrenomTest", LocalDate.of(1985, 3, 20), "F", "456 rue", "987654321", LocalDate.now(), LocalDate.now(), "admin");

        when(patientRepository.save(patient)).thenReturn(patient);

        Patient savedPatient = patientService.saveOrUpdatePatient(patient);

        assertNotNull(savedPatient);
        assertEquals("TestBorderline", savedPatient.getNom());
    }
    @Test
    void testCreateOrUpdatePatient_TestInDanger() {
        Patient patient = new Patient(3L, "TestInDanger", "PrenomTest", LocalDate.of(1970, 5, 10), "M", "789 rue", "112233445", LocalDate.now(), LocalDate.now(), "admin");

        when(patientRepository.save(patient)).thenReturn(patient);

        Patient savedPatient = patientService.saveOrUpdatePatient(patient);

        assertNotNull(savedPatient);
        assertEquals("TestInDanger", savedPatient.getNom());
    }
    @Test
    void testCreateOrUpdatePatient_TestEarlyOnset() {
        Patient patient = new Patient(4L, "TestEarlyOnset", "PrenomTest", LocalDate.of(1980, 11, 15), "F", "159 rue", "556677889", LocalDate.now(), LocalDate.now(), "admin");

        when(patientRepository.save(patient)).thenReturn(patient);

        Patient savedPatient = patientService.saveOrUpdatePatient(patient);

        assertNotNull(savedPatient);
        assertEquals("TestEarlyOnset", savedPatient.getNom());
    }
    @Test
    void testGetPatientById() {
        long patientId = 1L;
        Patient patient = new Patient(patientId, "TestNone", "PrenomTest", LocalDate.of(1990, 1, 1), "M", "123 rue", "123456789", LocalDate.now(), LocalDate.now(), "admin");

        when(patientRepository.findById(Long.valueOf(Long.toString(patientId)))).thenReturn(Optional.of(patient));

        Optional<Patient> retrievedPatient = patientService.getPatientById(Long.valueOf(Long.toString(patientId)));

        assertTrue(retrievedPatient.isPresent());
        assertEquals("TestNone", retrievedPatient.get().getNom());
    }
    @Test
    void testDeletePatientById() {
        String patientId = "1";

        doNothing().when(patientRepository).deleteById(Long.valueOf(patientId));

        patientService.deletePatientById(Long.valueOf(patientId));

        verify(patientRepository, times(1)).deleteById(Long.valueOf(patientId));
    }

    @Test
    void testGetAllPatients() {
        when(patientRepository.findAll()).thenReturn(List.of(
                new Patient(1L, "TestNone", "PrenomTest", LocalDate.of(1990, 1, 1), "M", "123 rue", "123456789", LocalDate.now(), LocalDate.now(), "admin"),
                new Patient(1L, "TestBorderline", "PrenomTest", LocalDate.of(1985, 3, 20), "F", "456 rue", "987654321", LocalDate.now(), LocalDate.now(), "admin")
        ));

        List<Patient> patients = patientService.getAllPatients();

        assertNotNull(patients);
        assertEquals(2, patients.size());
        assertEquals("TestNone", patients.get(0).getNom());
        assertEquals("TestBorderline", patients.get(1).getNom());
    }
}
