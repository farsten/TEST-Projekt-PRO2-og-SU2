package ordination;

import org.jspecify.annotations.NullMarked;

@NullMarked
public class Lægemiddel {
    private String navn;
    private String enhed;
    private double antalPrKgPrDøgnLet;    // faktor hvis patients vægt < 25 kg
    private double antalPrKgPrDøgnNormal; // faktor hvis 25 kg <= patient vægt <= 120 kg
    private double antalPrKgPrDøgnTung;   // faktor hvis patients vægt > 120 kg

    public Lægemiddel(
        String navn, String enhed,
        double antalPrKgPrDøgnLet, double antalPrKgPrDøgnNormal, double antalPrKgPrDøgnTung
    ) {
        this.navn = navn;
        this.enhed = enhed;
        this.antalPrKgPrDøgnLet = antalPrKgPrDøgnLet;
        this.antalPrKgPrDøgnNormal = antalPrKgPrDøgnNormal;
        this.antalPrKgPrDøgnTung = antalPrKgPrDøgnTung;
    }

    public String getEnhed() {
        return enhed;
    }

    public double getAntalPrKgPrDøgnLet() {
        return antalPrKgPrDøgnLet;
    }

    public double getAntalPrKgPrDøgnNormal() {
        return antalPrKgPrDøgnNormal;
    }

    public double getAntalPrKgPrDøgnTung() {
        return antalPrKgPrDøgnTung;
    }

    @Override
    public String toString() {
        return navn;
    }
}
