package ordination;

import gui.TypeOrdination;
import org.jspecify.annotations.NullMarked;

import java.time.LocalDate;
import java.util.List;

@NullMarked
public class PN extends Ordination {
    private double antalEnheder;
    private List<LocalDate> datoerAnvendt;

    public PN(LocalDate startDen, LocalDate slutDen, Lægemiddel lægemiddel, double antalEnheder, List<LocalDate> datoerAnvendt) {
        super(startDen, slutDen, lægemiddel);
        this.antalEnheder = antalEnheder;
        this.datoerAnvendt = datoerAnvendt;
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
    return 0;
}

@Override
public double døgnDosis() {
    (antalGangeAnvendt() * antalEnheder) //
}

@Override
public String getType() {
    return TypeOrdination.PN + "";
}
}
