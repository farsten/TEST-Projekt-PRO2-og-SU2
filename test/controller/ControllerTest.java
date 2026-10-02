package controller;

import ordination.Lægemiddel;
import ordination.Patient;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static controller.Controller.opretPNOrdination;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ControllerTest {

    Patient PatientVægt20 = new Patient("2", "Hej", 20);
    LocalDate startDato = LocalDate.of(2026, 1, 16);
    LocalDate slutDato = LocalDate.of(2026, 1, 20);
    Lægemiddel Kokain = new Lægemiddel("Kokain", "Linjer", 0.00001, 0.0001, 0.001);


    @Test
    public void constructPNOrdination() {
        opretPNOrdination(startDato, slutDato, 3, PatientVægt20, Kokain);

        assertEquals("Hej", PatientVægt20.getNavn());
        assertEquals("2", PatientVægt20.getCprNr());
        assertEquals(20, PatientVægt20.getVægt());

    }

    @Test
    void opretDagligFastOrdination() {
    }

    @Test
    void opretDagligSkævOrdination() {
    }

    @Test
    void anvendOrdinationPN() {
    }

    @Test
    void anbefaletDosisPrDøgn_under25kg() {
        // under 25 kg
        Patient patientVægt20 = new Patient("2", "let", 20);
        double resultat = Controller.anbefaletDosisPrDøgn(patientVægt20, Kokain);
        assertEquals(0.0002, resultat, 0.000001);
    }

    @Test
    void anbefaletDosisPrDøgn_mellem25og120kg() {
      // mellem 25 kg og 70 kg
        Patient normalPatient = new Patient("3", "Normal", 70);
        double resultat = Controller.anbefaletDosisPrDøgn(normalPatient, Kokain);
        assertEquals(0.007, resultat, 0.000001);
    }

    @Test
    void anbefaletDosisPrDøgn_over120kg() {
        // over 120 kg
        Patient tungPatient = new Patient("4", "Tung", 130);
        double resultat = Controller.anbefaletDosisPrDøgn(tungPatient, Kokain);
        assertEquals(0.13, resultat, 0.000001);
    }


    @Test
    void antalOrdinationerPrVægtPrLægemiddel() {
    }

    @Test
    void constructPatient() {
        assertEquals("Hej", PatientVægt20.getNavn());
    }

    @Test
    void constructLægemiddel() {
        assertEquals("Linjer", Kokain.getEnhed());
    }
}