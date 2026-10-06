package controller;

import ordination.*;
import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;
import storage.Storage;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

@NullMarked
public abstract class Controller {
    private static Storage storage = new Storage();

    public static void setStorage(Storage storage) {
        Controller.storage = storage;
    }

    /**
     * Opret og returner en PN ordination.
     * Hvis startDato er efter slutDato, kastes en IllegalArgumentException.
     * Pre: antal > 0.
     */
    public static PN opretPNOrdination(
            LocalDate startDato, LocalDate slutDato, double antal,
            Patient patient, @Nullable Lægemiddel lægemiddel
    ) {
        PN pn = new PN(startDato, slutDato, lægemiddel, antal, patient);
        patient.addOrdination(pn);
        return pn;
    }

    /**
     * Opret og returner en DagligFast ordination.
     * Hvis startDato er efter slutDato, kastes en IllegalArgumentException.
     * Pre: morgenAntal, middagAntal, aftenAntal, natAntal er alle >= 0.
     */
    public static void opretDagligFastOrdination(
            LocalDate startDato, LocalDate slutDato,
            double morgenAntal, double middagAntal, double aftenAntal, double natAntal,
            Patient patient, @Nullable Lægemiddel lægemiddel
    ) {
        DagligFast dagligFast = new DagligFast(startDato, slutDato, lægemiddel, morgenAntal, middagAntal, aftenAntal, natAntal);
        patient.addOrdination(dagligFast);
    }


    /**
     * Opret og returner en DagligSkæv ordination.
     * Hvis startDato er efter slutDato, kastes en IllegalArgumentException.
     * Hvis antallet af elementer i klokkeSlet og antalEnheder er forskellige,
     * kastes en IllegalArgumentException.
     * Pre: I antalEnheder er alle tal >= 0.
     */
    public static void opretDagligSkævOrdination(
            LocalDate startDen, LocalDate slutDen, LocalTime[] klokkeSlet, double[] antalEnheder,
            Patient patient, @Nullable Lægemiddel lægemiddel
    ) {
        DagligSkæv dagligSkæv = new DagligSkæv(startDen, slutDen, lægemiddel, klokkeSlet, antalEnheder);
        patient.addOrdination(dagligSkæv);
    }

    /**
     * Tilføj en dato for anvendelse af PN ordinationen.
     * Hvis datoen ikke er indenfor ordinationens gyldighedsperiode,
     * kastes en IllegalArgumentException.
     */
    public static void anvendOrdinationPN(PN ordination, LocalDate dato) throws Exception {
        if (dato.isBefore(ordination.getStartDato()) || dato.isAfter(ordination.getSlutDato())) {
            throw new IllegalArgumentException();
        } else {
            ordination.anvendDosis(dato);
        }
    }

    /**
     * Returner den anbefalede dosis pr. døgn til patienten af lægemidlet.
     * (Den anbefalede dosis afhænger af patientens vægt.)
     */
    public static double anbefaletDosisPrDøgn(Patient patient, Lægemiddel lægemiddel) {
        if (patient.getVægt() < 25) {
            return patient.getVægt() * lægemiddel.getAntalPrKgPrDøgnLet();
        } else if (patient.getVægt() > 120) {
            return patient.getVægt() * lægemiddel.getAntalPrKgPrDøgnTung();
        } else {
            return patient.getVægt() * lægemiddel.getAntalPrKgPrDøgnNormal();
        }
    }

    /**
     * Returner antal ordinationer af lægemidlet for patienter med vægt i vægtintervallet.
     */
    public static int antalOrdinationerPrVægtPrLægemiddel(
            double vægtStart, double vægtSlut, Lægemiddel lægemiddel
    ) {
        int count = 0;
        for (Patient p : storage.getAllePatienter()) {
            if (p.getVægt() >= vægtStart && p.getVægt() <= vægtSlut) {
                for (Ordination o : p.getOrdinationer()) {
                    try {
                        if (lægemiddel.equals(o.getLægemiddel())) {
                            count++;
                        }
                    } catch (NullPointerException nullPointerException) {
                        throw new NullPointerException();
                    }
                }
            }
        }
        return count;
    }

    public static List<Patient> getAllePatienter() {
        return storage.getAllePatienter();
    }

    public static List<Lægemiddel> getAlleLægemidler() {
        return storage.getAlleLægemidler();
    }

    public static Patient opretPatient(String cpr, String navn, double vægt) {
        Patient p = new Patient(cpr, navn, vægt);
        storage.storePatient(p);
        return p;
    }

    public static Lægemiddel opretLægemiddel(
            String navn, String enhed,
            double enhedPrKgPrDøgnLet, double enhedPrKgPrDøgnNormal, double enhedPrKgPrDøgnTung
    ) {
        Lægemiddel lm = new Lægemiddel(
                navn, enhed,
                enhedPrKgPrDøgnLet, enhedPrKgPrDøgnNormal, enhedPrKgPrDøgnTung
        );
        storage.storeLægemiddel(lm);
        return lm;
    }
}
