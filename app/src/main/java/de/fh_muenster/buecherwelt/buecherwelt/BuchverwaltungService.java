package de.fh_muenster.buecherwelt.buecherwelt;

import java.util.Date;
import java.util.List;

import de.fh_muenster.buecherwelt.buecherwelt.exceptions.NoSessionException;

/**
 * Created by user on 11.06.15.
 */
public interface BuchverwaltungService {

    public void neuesBuchHinzufuegen(int id, String titel, String autor, int erscheinungsjahr, int anzahl) throws NoSessionException;

    public List<Buch> getAllBuecher() throws NoSessionException;

    public Buch findBuchById(int id) throws NoSessionException;

}
