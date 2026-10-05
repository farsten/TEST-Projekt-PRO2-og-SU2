package ordination;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

class PNTest {

    private Patient patient;
    private Lægemiddel lægemiddel;
    private PN pn;

    @BeforeEach
    void setUp() {
        patient = new Patient("121256-0512", "Jane Jensen", 63.4);
        lægemiddel = new Lægemiddel("Paracetamol", "mL", 1, 1.5, 2);
        pn = new PN(
                LocalDate.of(2025, 9, 1),
                LocalDate.of(2025, 9, 12),
                lægemiddel,
                123.0,
                patient
        );
    }

    @Test
    void anvendDosis_gyldigDato() throws Exception {
        assertEquals(0, pn.antalGangeAnvendt());
        pn.anvendDosis(LocalDate.of(2025, 9, 1));
        assertEquals(1, pn.antalGangeAnvendt());

    }

    @Test
    void anvendDosis_ugyldigDatoKasterException() {
        assertThrows(Exception.class, () -> {
            pn.anvendDosis(LocalDate.of(2025, 8, 31));
        });

    }

    @Test
    void antalGangeAnvendt() throws Exception {
        assertEquals(0,pn.antalGangeAnvendt() );
        pn.anvendDosis(LocalDate.of(2025, 9, 1));
        pn.anvendDosis(LocalDate.of(2025, 9, 4));
        assertEquals(2, pn.antalGangeAnvendt());
    }

    @Test
    void samletDosis() throws Exception {
        assertEquals(0.0, pn.samletDosis(), 0.0001);
        pn.anvendDosis(LocalDate.of(2025, 9, 1));
        assertEquals(123.0, pn.samletDosis(), 0.0001);
        pn.anvendDosis(LocalDate.of(2025, 9, 4));
        assertEquals(246.0, pn.samletDosis(), 0.0001);
    }

    @Test
    void døgnDosis() throws Exception {
        // tom liste
        assertEquals(0.0, pn.døgnDosis(), 0.0001);

        // 1 dag
        pn.anvendDosis(LocalDate.of(2025, 9, 1));
        assertEquals(123.0, pn.døgnDosis(), 0.0001);

        // 2 gange over 4 dage
        pn.anvendDosis(LocalDate.of(2025, 9, 4));
        assertEquals(61.5, pn.døgnDosis(), 0.0001);
    }
}