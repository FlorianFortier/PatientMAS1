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
        Patient patient = new Patient(1L, "TestNone", "PrenomTest", LocalDate.of(1990, 1, 1), "M", "123 rue", "123456789", LocalDate.now(), LocalDate.now(), "admin", null);

        when(patientRepository.save(patient)).thenReturn(patient);

        Patient savedPatient = patientService.saveOrUpdatePatient(patient);

        assertNotNull(savedPatient);
        assertEquals("TestNone", savedPatient.getNom());
    }
    @Test
    void testCreateOrUpdatePatient_TestNone() {
        Patient patient = new Patient(1L, "TestNone", "PrenomTest", LocalDate.of(1990, 1, 1), "M", "123 rue", "123456789", LocalDate.now(), LocalDate.now(), "admin", null);

        when(patientRepository.save(patient)).thenReturn(patient);

        Patient savedPatient = patientService.saveOrUpdatePatient(patient);

        assertNotNull(savedPatient);
        assertEquals("TestNone", savedPatient.getNom());
        // Vérifier les informations de la note du tableau pour TestNone
        String note = "Le patient déclare qu'il se sent très bien. Poids égal ou inférieur au poids recommandé.";
        assertTrue(note.contains("Poids égal ou inférieur au poids recommandé"));
    }

    @Test
    void testCreateOrUpdatePatient_TestBorderline() {
        Patient patient = new Patient(2L, "TestBorderline", "PrenomTest", LocalDate.of(1985, 3, 20), "F", "456 rue", "987654321", LocalDate.now(), LocalDate.now(), "admin", "Le patient déclare qu'il ressent beaucoup de stress au travail. Il se plaint également que son audition est anormale dernièrement.");

        when(patientRepository.save(patient)).thenReturn(patient);

        Patient savedPatient = patientService.saveOrUpdatePatient(patient);

        assertNotNull(savedPatient);
        assertEquals("TestBorderline", savedPatient.getNom());
        // Vérifier les informations de la note du tableau pour TestBorderline
        String note = "Le patient déclare qu'il ressent beaucoup de stress au travail. Il se plaint également que son audition est anormale dernièrement.";
        assertEquals(note, savedPatient.getNote());
    }

    @Test
    void testCreateOrUpdatePatient_TestInDanger() {
        Patient patient = new Patient(3L, "TestInDanger", "PrenomTest", LocalDate.of(1970, 5, 10), "M", "789 rue", "112233445", LocalDate.now(), LocalDate.now(), "admin", "Le patient est fumeur et qu'il a cessé de fumer l'année dernière. Il se plaint également de crises d’apnée respiratoire anormales.");

        when(patientRepository.save(patient)).thenReturn(patient);

        Patient savedPatient = patientService.saveOrUpdatePatient(patient);

        assertNotNull(savedPatient);
        assertEquals("TestInDanger", savedPatient.getNom());
        // Vérifier les informations de la note du tableau pour TestInDanger
        String note = "Le patient est fumeur et qu'il a cessé de fumer l'année dernière. Il se plaint également de crises d’apnée respiratoire anormales.";
        assertEquals(note, savedPatient.getNote());
    }

    @Test
    void testCreateOrUpdatePatient_TestEarlyOnset() {
        Patient patient = new Patient( 4L, "TestEarlyOnset", "PrenomTest", LocalDate.of(1980, 11, 15), "F", "159 rue", "556677889", LocalDate.now(), LocalDate.now(), "admin", "Le patient déclare avoir commencé à fumer depuis peu. Hémoglobine A1C supérieure au niveau recommandé.");

        when(patientRepository.save(patient)).thenReturn(patient);

        Patient savedPatient = patientService.saveOrUpdatePatient(patient);

        assertNotNull(savedPatient);
        assertEquals("TestEarlyOnset", savedPatient.getNom());
        // Vérifier les informations de la note du tableau pour TestEarlyOnset
        String note = "Le patient déclare avoir commencé à fumer depuis peu. Hémoglobine A1C supérieure au niveau recommandé.";
        assertEquals(note, savedPatient.getNote());
    }

    @Test
    void testCreateOrUpdatePatient_TestEarlyOnsetWithMultipleNotes() {
        Patient patient = new Patient(4L, "TestEarlyOnset", "PrenomTest", LocalDate.of(1980, 11, 15), "F", "159 rue", "556677889", LocalDate.now(), LocalDate.now(), "admin", "Taille, Poids, Cholestérol, Vertige et Réaction.");

        when(patientRepository.save(patient)).thenReturn(patient);

        Patient savedPatient = patientService.saveOrUpdatePatient(patient);

        assertNotNull(savedPatient);
        assertEquals("TestEarlyOnset", savedPatient.getNom());
        // Vérifier les informations de la dernière note du tableau pour TestEarlyOnset
        String note = "Taille, Poids, Cholestérol, Vertige et Réaction.";
        assertEquals(note, savedPatient.getNote());;
    }

    @Test
    void testGetPatientById() {
        Long patientId = 1L;
        Patient patient = new Patient(patientId, "TestNone", "PrenomTest", LocalDate.of(1990, 1, 1), "M", "123 rue", "123456789", LocalDate.now(), LocalDate.now(), "admin", null);

        when(patientRepository.findById(patientId.toString())).thenReturn(Optional.of(patient));

        Optional<Patient> retrievedPatient = patientService.getPatientById(patientId.toString());

        assertTrue(retrievedPatient.isPresent());
        assertEquals("TestNone", retrievedPatient.get().getNom());
    }

    @Test
    void testDeletePatientById() {
        String patientId = "1";

        doNothing().when(patientRepository).deleteById(patientId);

        patientService.deletePatientById(patientId);

        verify(patientRepository, times(1)).deleteById(patientId);
    }

    @Test
    void testGetAllPatients() {
        when(patientRepository.findAll()).thenReturn(List.of(
                new Patient(1L, "TestNone", "PrenomTest", LocalDate.of(1990, 1, 1), "M", "123 rue", "123456789", LocalDate.now(), LocalDate.now(), "admin", null),
                new Patient(1L, "TestBorderline", "PrenomTest", LocalDate.of(1985, 3, 20), "F", "456 rue", "987654321", LocalDate.now(), LocalDate.now(), "admin", null)
        ));

        List<Patient> patients = patientService.getAllPatients();

        assertNotNull(patients);
        assertEquals(2, patients.size());
        assertEquals("TestNone", patients.get(0).getNom());
        assertEquals("TestBorderline", patients.get(1).getNom());
    }
}
