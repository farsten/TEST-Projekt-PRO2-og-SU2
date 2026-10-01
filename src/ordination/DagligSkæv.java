package ordination;

import org.jspecify.annotations.NullMarked;

import java.time.LocalDate;
import java.time.LocalTime;

@NullMarked
public class DagligSkæv extends Ordination{
    private LocalTime[] tidspunkter;
    private double[] mængde;
    Patient patient;

    public DagligSkæv(LocalDate startDato, LocalDate slutDato, Lægemiddel lægemiddel, LocalTime[] tidspunkter, double[] mængde, Patient patient){
        super(startDato, slutDato, lægemiddel);
        this.tidspunkter = tidspunkter;
        this.mængde = mængde;
        this.patient = patient;
    }

    public double samletDosis(){
        return super.antalDage() * døgnDosis();
    }

    public double døgnDosis(){
        double dagligMængde = 0;
        for (double m : mængde) {
            dagligMængde += m;
        }
        return dagligMængde;
    }

    public String getType(){
        return "Daglig skæv";
    }

    public LocalTime[] getTidspunkter(){
        return tidspunkter;
    }
}
