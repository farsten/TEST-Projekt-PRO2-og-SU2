package controller;

import ordination.Lægemiddel;
import ordination.Patient;
import org.junit.jupiter.api.Test;
import storage.Storage;

import java.time.LocalDate;

import static controller.Controller.opretPNOrdination;
import static org.junit.jupiter.api.Assertions.assertEquals;

class ControllerTest {
    private final Storage storage = new Storage();
    Patient PatientVægt20 = new Patient("2", "Hej", 20);
    Patient PatientVægt100 = new Patient("3", "Gug", 100);
    Patient PatientVægt140 = new Patient("100", "Stor mand", 140);
    LocalDate startDato = LocalDate.of(2026, 1, 16);
    LocalDate slutDato = LocalDate.of(2026, 1, 20);
    Lægemiddel Kokain = new Lægemiddel("Kokain", "Linjer", 0.00001, 0.0001, 0.001);
    Lægemiddel heroin = new Lægemiddel("Heroin", "Ske", 0.02, 0.06, 0.1);


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