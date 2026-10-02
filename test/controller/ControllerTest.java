package controller;

import ordination.DagligFast;
import ordination.Lægemiddel;
import ordination.Patient;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static controller.Controller.opretPNOrdination;
import static gui.TypeOrdination.PN;
import static org.junit.jupiter.api.Assertions.*;

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
    void anbefaletDosisPrDøgn() {
    }

    @Test
    void antalOrdinationerPrVægtPrLægemiddel() {
    }

    @Test
    void opretPatient() {
    }

    @Test
    void opretLægemiddel() {
    }
}