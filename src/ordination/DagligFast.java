package ordination;

import org.jspecify.annotations.NullMarked;
import org.jspecify.annotations.Nullable;

import java.time.LocalDate;
import java.time.LocalTime;

@NullMarked
public class DagligFast extends Ordination{
    // Array der har alle tider på dagen
    private final Dosis[] doser = new Dosis[4];

    public DagligFast(LocalDate startDen, LocalDate slutDen, @Nullable Lægemiddel lægemiddel, double morgen, double middag, double aften, double nat) {
        super(startDen,slutDen,lægemiddel);
        tidsbedømmer(morgen, middag, aften, nat);
    }

    private void tidsbedømmer(double morgen, double middag, double aften, double nat) {
        doser[0] = new Dosis(LocalTime.of(8, 0), morgen);
        doser[1] = new Dosis(LocalTime.of(12, 0), middag);
        doser[2] = new Dosis(LocalTime.of(18, 0), aften);
        doser[3] = new Dosis(LocalTime.of(23, 0), nat);
    }

    public Dosis[] getDoser() {
        return doser;
    }
    @Override
    public double døgnDosis() {
        double total = 0;
        for (Dosis d : doser) {
            if (d != null) {
                total += d.getAntal();
            }
        }
        return total;
    }

    @Override
    public double samletDosis() {
        return døgnDosis() * antalDage();
    }

    @Override
    public String getType() {
        return "Daglig Fast";
    }

    @Override
    public @Nullable Lægemiddel getLægemiddel() {
        return super.getLægemiddel();
    }

}