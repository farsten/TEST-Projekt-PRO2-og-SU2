package ordination;

import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DagligSkævTest {
    Lægemiddel acetylsalicylsyre = new Lægemiddel("Acetylsalicylsyre", "styk", 0.1, 0.15, 0.16);
    DagligSkæv dagligSkæv = new DagligSkæv(LocalDate.of(2026, 1, 1), LocalDate.of(2026, 2, 1), acetylsalicylsyre, new LocalTime[]{LocalTime.of(12, 15), LocalTime.of(16, 30), LocalTime.of(20, 45)}, new double[]{2, 2, 6});

    @Test
    void samletDosis() {
    }

    @Test
    void døgnDosis() {
    }
}