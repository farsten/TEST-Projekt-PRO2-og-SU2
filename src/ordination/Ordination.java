package ordination;

import org.jspecify.annotations.NullMarked;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@NullMarked
public abstract class Ordination {
    private LocalDate startDato;
    private LocalDate slutDato;

    public LocalDate getStartDato() {
        return startDato;
    }

    public LocalDate getSlutDato() {
        return slutDato;
    }

    /**
     * Returner antal dage mellem startdato og slutdato
     * (begge dage inklusive).
     */
    public int antalDage() {
        return (int) ChronoUnit.DAYS.between(startDato, slutDato) + 1;
    }

    @Override
    public String toString() {
        return startDato.toString();
    }

    /** Returner den totale dosis, der er givet i den periode, ordinationen er gyldig. */
    public abstract double samletDosis();

    /** Returner den gennemsnitlige dosis givet per dag. */
    public abstract double døgnDosis();

    /** Returner ordinations typen som en String (f.eks. "PN"). */
    public abstract String getType();
}
