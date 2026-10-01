package ordination;

import org.jspecify.annotations.NullMarked;

import java.time.LocalDate;

@NullMarked
public class PN {
    private double antalEnheder;

    public double getAntalEnheder() {
        return antalEnheder;
    }

    /** Registrer datoen for en anvendt dosis. */
    public boolean anvendDosis(LocalDate dato) {
        // TODO
        return false;
    }

    /** Returner antal gange ordinationen er anvendt. */
    public int antalGangeAnvendt() {
        // TODO
        return -1;
    }
}
