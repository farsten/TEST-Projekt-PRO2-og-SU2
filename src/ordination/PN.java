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
        return antalGangeAnvendt() * antalEnheder;
    }

    @Override
    public double døgnDosis() {
        if (datoerAnvendt.isEmpty()) return 0;

        LocalDate min = datoerAnvendt.getFirst();
        LocalDate max = min;
        for (LocalDate d : datoerAnvendt) {
            if (d.isBefore(min)) min = d;
            if (d.isAfter(max)) max = d;
        }

        return samletDosis() / (ChronoUnit.DAYS.between(min, max) + 1);
    }

    @Override
    public String getType() {
        return TypeOrdination.PN + "";
    }

}
