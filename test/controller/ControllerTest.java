package controller;

import ordination.DagligFast;
import ordination.Lægemiddel;
import ordination.PN;
import ordination.Patient;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static controller.Controller.*;
import static gui.TypeOrdination.PN;
import static org.junit.jupiter.api.Assertions.*;

class ControllerTest {

    Patient PatientVægt20 = new Patient("2", "Hej", 20);
    LocalDate startDato = LocalDate.of(2026, 1, 16);
    LocalDate slutDato = LocalDate.of(2026, 1, 20);
    Lægemiddel Kokain = new Lægemiddel("Kokain", "Linjer", 0.00001, 0.0001, 0.001);
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

        assertDoesNotThrow(() -> {
            anvendOrdinationPN(PN, datoInde);
        });

        assertThrows(IllegalArgumentException.class, () -> {
                    anvendOrdinationPN(PN, datoUde);
                }
        );

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