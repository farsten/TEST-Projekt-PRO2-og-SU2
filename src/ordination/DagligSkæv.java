package ordination;

import org.jspecify.annotations.NullMarked;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@NullMarked
public class DagligSkæv extends Ordination{
    private LocalDate startDato;
    private LocalDate slutDato;
    private LocalTime[] tidspunkter;
    private double[] mængde;

    public DagligSkæv(LocalDate startDato, LocalDate slutDato, LocalTime[] klokkeSlet, double[] mængde){

    }

    public void angivDosisPaaKlokkeslet(LocalDateTime tidspunkt, int dosis){

    }
}
