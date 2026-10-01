package ordination;

import gui.TypeOrdination;
import org.jspecify.annotations.NullMarked;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

@NullMarked
public class PN extends Ordination {
    private double antalEnheder;
    private List<LocalDate> datoerAnvendt = new ArrayList<>();
    private double samletAntalEnheder;
    private Patient patient;

    public PN(LocalDate startDen, LocalDate slutDen, Lægemiddel lægemiddel, double antalEnheder, Patient patient) {
        super(startDen, slutDen, lægemiddel);
        this.antalEnheder = antalEnheder;
        this.patient = patient;
    }

    public double getAntalEnheder() {
        return antalEnheder;
    }

    /**
     * Registrer datoen for en anvendt dosis.
     */
    public void anvendDosis(LocalDate dato) throws Exception {
        if (dato.isBefore(this.getStartDato()) || dato.isAfter(this.getSlutDato())) {
            throw new Exception("Datoen er ude fra slut og start dato");
        } else datoerAnvendt.add(dato);
    }

    /**
     * Returner antal gange ordinationen er anvendt.
     */
    public int antalGangeAnvendt() {
        return datoerAnvendt.size();
    }

    @Override
    public double samletDosis() {
        return samletAntalEnheder + antalEnheder;
    }

    @Override
    public double døgnDosis() {
        return (antalGangeAnvendt() * antalEnheder) / ((int) ChronoUnit.DAYS.between(datoerAnvendt.getFirst(), datoerAnvendt.getLast()) + 1);
    }

    @Override
    public String getType() {
        return TypeOrdination.PN + "";
    }
}
