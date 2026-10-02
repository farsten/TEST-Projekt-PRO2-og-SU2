package ordination;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.*;

import java.time.LocalDate;

class DagligFastTest {

    Lægemiddel methotrexate = new Lægemiddel("Methotrexate", "styk", 0.01, 0.015, 0.02);
    DagligFast dagligFast = new DagligFast(LocalDate.of(2026, 1, 16), LocalDate.of(2026, 1, 20), methotrexate, 2, 2, 6, 2);

    @org.junit.jupiter.api.Test
    void døgnDosis() {
        double døgnDosis = dagligFast.døgnDosis();
        assertEquals(12, døgnDosis);
    }

    @org.junit.jupiter.api.Test
    void samletDosis() {
        double samletDosis = dagligFast.samletDosis();
        assertEquals(60, samletDosis);
    }
}