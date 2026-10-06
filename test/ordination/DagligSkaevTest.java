package ordination;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;

import static org.junit.jupiter.api.Assertions.*;

class DagligSkaevTest {
    Lægemiddel acetylsalicylsyre = new Lægemiddel("Acetylsalicylsyre", "styk", 0.1, 0.15, 0.16);
    DagligSkæv dagligSkæv = new DagligSkæv(LocalDate.of(2026, 1, 1), LocalDate.of(2026, 2, 1), acetylsalicylsyre, new LocalTime[]{LocalTime.of(12, 15), LocalTime.of(16, 30), LocalTime.of(20, 45)}, new double[]{2, 2, 6});

    @Test
    void samletDosis() {
        double samletDosis = dagligSkæv.samletDosis();
        assertEquals(320, samletDosis);
    }

    @Test
    void døgnDosis() {
        double døgnDosis = dagligSkæv.døgnDosis();
        assertEquals(10, døgnDosis);
    }
}