package controller;

import ordination.Lægemiddel;
import ordination.Patient;
import org.junit.jupiter.api.Test;
import storage.Storage;

import java.time.LocalDate;
import java.time.LocalTime;

import static controller.Controller.*;
import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {
    private final Storage storage = new Storage();
    Patient PatientVægt20 = new Patient("2", "Hej", 20);
    Patient PatientVægt100 = new Patient("3", "Gug", 100);
    Patient PatientVægt140 = new Patient("100", "Stor mand", 140);
    LocalDate startDato = LocalDate.of(2026, 1, 16);
    LocalDate slutDato = LocalDate.of(2026, 1, 20);
    Lægemiddel Kokain = new Lægemiddel("Kokain", "Linjer", 0.00001, 0.0001, 0.001);
    Lægemiddel heroin = new Lægemiddel("Heroin", "Ske", 0.02, 0.06, 0.1);
    Lægemiddel acetylsalicylsyre = new Lægemiddel("Acetylsalicylsyre", "styk", 0.1, 0.15, 0.16);
    Lægemiddel paracetamol = new Lægemiddel("Paracetamol", "mL", 1, 1.5, 2);
    Lægemiddel methotrexate = new Lægemiddel("Methotrexate", "styk", 0.01, 0.015, 0.02);
    double[] antalEnheder = new double[2];
    LocalTime[] klokkeSlet = new LocalTime[2];

    @Test
    public void constructPNOrdination() {
        opretPNOrdination(startDato, slutDato, 3, PatientVægt20, Kokain);

        assertEquals("Hej", PatientVægt20.getNavn());
        assertEquals("2", PatientVægt20.getCprNr());
        assertEquals(20, PatientVægt20.getVægt());
    }

    @Test
    void opretDagligFastOrdination() {
        Controller.opretDagligFastOrdination(startDato, slutDato, 2, 1, 1, 2, PatientVægt100, heroin);

        assertEquals("Gug", PatientVægt100.getNavn());
        assertEquals("3", PatientVægt100.getCprNr());
        assertEquals(100, PatientVægt100.getVægt());
    }

    @Test
    void constructDagligSkævOrdination() {
        opretDagligSkævOrdination(startDato, slutDato, klokkeSlet, antalEnheder, PatientVægt20, Kokain);

        assertEquals(2, klokkeSlet.length);
        assertEquals(2, antalEnheder.length);
        assertEquals("Hej", PatientVægt20.getNavn());
        assertEquals("2", PatientVægt20.getCprNr());
        assertEquals(20, PatientVægt20.getVægt());
    }
    @Test
    void useOrdinationPN() {
        ordination.PN PN = opretPNOrdination(startDato, slutDato, 3, PatientVægt20, Kokain);
        LocalDate datoInde = LocalDate.of(2026, 1, 17);
        LocalDate datoUde = LocalDate.of(2026, 5, 17);

        assertDoesNotThrow(() -> anvendOrdinationPN(PN, datoInde));

        assertThrows(IllegalArgumentException.class, () -> anvendOrdinationPN(PN, datoUde)
        );

    }

    @Test
    void anbefaletDosisPrDøgn_under25kg() {
        // under 25 kg
        Patient letPatientNul = new Patient("2", "let", 0);
        Patient letPatient = new Patient("2", "let", 20);
        Patient letPatientGrænse = new Patient("2", "let", 24);

        double resultat = Controller.anbefaletDosisPrDøgn(letPatient, acetylsalicylsyre);
        assertEquals(2, resultat, 0.000001);

        double resultatGrænse = Controller.anbefaletDosisPrDøgn(letPatientGrænse, paracetamol);
        assertEquals(24, resultatGrænse, 0.000001);

        double resultatNul = Controller.anbefaletDosisPrDøgn(letPatientNul, paracetamol);
        assertEquals(0, resultatNul, 0.000001);
    }


    @Test
    void anbefaletDosisPrDøgn_mellem25og120kg() {
      // mellem 25 kg og 70 kg
        Patient normalPatient = new Patient("3", "Normal", 60);
        Patient normalPatientNedreGrænse = new Patient("3", "Normal", 25);
        Patient normalPatientØvreGrænse = new Patient("3", "Normal", 120);

        double resultat = Controller.anbefaletDosisPrDøgn(normalPatient, acetylsalicylsyre);
        assertEquals(9, resultat, 0.000001);

        double resultatNedreGrænse = Controller.anbefaletDosisPrDøgn(normalPatientNedreGrænse, methotrexate);
        assertEquals(0.375, resultatNedreGrænse, 0.0000000001);

        double resultatØvreGrænse = Controller.anbefaletDosisPrDøgn(normalPatientØvreGrænse, methotrexate);
        assertEquals(1.8, resultatØvreGrænse, 0.00001);
    }

    @Test
    void anbefaletDosisPrDøgn_over120kg() {
        // over 120 kg
        Patient tungPatient = new Patient("4", "Tung", 145);
        Patient tungPatientGrænse = new Patient("4", "Tung", 121);

        double resultat = Controller.anbefaletDosisPrDøgn(tungPatient, acetylsalicylsyre);
        assertEquals(23.2, resultat, 0.000001);

        double resultatGrænse = Controller.anbefaletDosisPrDøgn(tungPatientGrænse, acetylsalicylsyre);
        assertEquals(19.36, resultatGrænse, 0.000001);
    }


    @Test
    void antalOrdinationerPrVægtPrLægemiddel() {
        Controller.setStorage(storage);
        storage.storePatient(PatientVægt100);
        storage.storePatient(PatientVægt20);
        storage.storePatient(PatientVægt140);

        Controller.opretDagligFastOrdination(startDato, slutDato, 2, 1, 1, 2, PatientVægt100, heroin);
        Controller.opretDagligFastOrdination(startDato, slutDato, 1, 1, 8, 1, PatientVægt20, Kokain);
        Controller.opretDagligFastOrdination(startDato, slutDato, 1, 8, 8, 1, PatientVægt140, Kokain);

        int antalKok = Controller.antalOrdinationerPrVægtPrLægemiddel(10, 150, Kokain);
        int antalHero = Controller.antalOrdinationerPrVægtPrLægemiddel(90, 110, heroin);

        assertEquals(2, antalKok);
        assertEquals(1, antalHero);
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