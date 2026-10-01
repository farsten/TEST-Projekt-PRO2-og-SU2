package storage;

import ordination.Lægemiddel;
import ordination.Patient;
import org.jspecify.annotations.NullMarked;

import java.util.ArrayList;
import java.util.List;

@NullMarked
public class Storage {
    private final List<Patient> patienter = new ArrayList<>();
    private final List<Lægemiddel> lægemidler = new ArrayList<>();

    public List<Patient> getAllePatienter() {
        return List.copyOf(patienter);
    }

    public void storePatient(Patient patient) {
        patienter.add(patient);
    }

    public List<Lægemiddel> getAlleLægemidler() {
        return List.copyOf(lægemidler);
    }

    public void storeLægemiddel(Lægemiddel lægemiddel) {
        lægemidler.add(lægemiddel);
    }
}
