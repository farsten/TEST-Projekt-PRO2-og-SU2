package controller;

import ordination.DagligFast;
import ordination.Lægemiddel;
import ordination.Patient;
import org.junit.jupiter.api.Test;
import storage.Storage;

import java.time.LocalDate;

import static controller.Controller.opretPNOrdination;
import static gui.TypeOrdination.PN;
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
    void anbefaletDosisPrDøgn() {
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
    void opretPatient() {
    }

    @Test
    void opretLægemiddel() {
    }
}