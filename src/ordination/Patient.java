package ordination;

import org.jspecify.annotations.NullMarked;

import java.util.ArrayList;
import java.util.List;

@NullMarked
public class Patient {
    private final String cprNr;
    private final String navn;
    private final double vægt;
    List<Ordination> ordinationer = new ArrayList<>();

    public Patient(String cprNr, String navn, double vægt) {
        this.cprNr = cprNr;
        this.navn = navn;
        this.vægt = vægt;
    }

    public double getVægt() {
        return vægt;
    }

    public void addOrdination(Ordination ordination){
        ordinationer.add(ordination);
    }

    @Override
    public String toString() {
        return navn + "  " + cprNr;
    }

    public String getCprNr() {
        return cprNr;
    }

    public String getNavn() {
        return navn;
    }

    public List<Ordination> getOrdinationer() {
        return ordinationer;
    }
}
