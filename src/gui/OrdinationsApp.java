package gui;

import javafx.application.Application;
import ordination.Lægemiddel;
import ordination.Patient;
import org.jspecify.annotations.NullMarked;

import java.time.LocalDate;
import java.time.LocalTime;

import static controller.Controller.*;

@NullMarked
public class OrdinationsApp {
    void main() {
        initStorage();
        Application.launch(StartVindue.class);
    }

    public static void initStorage() {
        Patient jane = opretPatient("121256-0512", "Jane Jensen", 63.4);
        Patient finn = opretPatient("070985-1153", "Finn Madsen", 83.2);
        opretPatient("050972-1233", "Hans Jørgensen", 89.4);
        opretPatient("011064-1522", "Ulla Nielsen", 59.9);
        Patient ib = opretPatient("090149-2529", "Ib Hansen", 87.7);

        Lægemiddel acetylsalicylsyre = opretLægemiddel(
            "Acetylsalicylsyre", "styk", 0.1, 0.15, 0.16
        );
        Lægemiddel paracetamol = opretLægemiddel(
            "Paracetamol", "mL", 1, 1.5, 2
        );
        Lægemiddel fucidin = opretLægemiddel(
            "Fucidin", "styk", 0.025, 0.025, 0.025
        );
        opretLægemiddel(
            "Methotrexate", "styk", 0.01, 0.015, 0.02
        );

        opretPNOrdination(
            LocalDate.parse("2025-09-01"), LocalDate.parse("2025-09-12"),
            123, jane, paracetamol
        );
        opretPNOrdination(
            LocalDate.parse("2025-09-12"), LocalDate.parse("2025-09-14"),
            3, jane, acetylsalicylsyre
        );
        opretPNOrdination(
            LocalDate.parse("2025-09-20"), LocalDate.parse("2025-09-25"),
            5, ib, fucidin
        );
        opretPNOrdination(
            LocalDate.parse("2025-09-01"), LocalDate.parse("2025-09-12"),
            123, jane, paracetamol
        );

        opretDagligFastOrdination(
            LocalDate.parse("2025-09-10"), LocalDate.parse("2025-09-12"),
            2, 0, 1, 0, finn, fucidin
        );

        LocalTime[] kl = {
            LocalTime.parse("12:00"), LocalTime.parse("12:40"),
            LocalTime.parse("16:00"), LocalTime.parse("18:45")
        };
        double[] an = {0.5, 1, 2.5, 3};
        opretDagligSkævOrdination(
            LocalDate.parse("2025-09-21"), LocalDate.parse("2025-09-24"),
            kl, an, finn, fucidin
        );
    }
}
