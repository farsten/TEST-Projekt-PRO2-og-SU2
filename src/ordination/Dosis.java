package ordination;

import org.jspecify.annotations.NullMarked;

import java.time.LocalTime;

@NullMarked
public class Dosis {
    private LocalTime tid;
    private double antal;

    public Dosis(LocalTime tid, double antal) {
        this.tid = tid;
        this.antal = antal;
    }

    public double getAntal() {
        return antal;
    }

    @Override
    public String toString() {
        return "Kl: " + tid + "   antal:  " + antal;
    }
}
