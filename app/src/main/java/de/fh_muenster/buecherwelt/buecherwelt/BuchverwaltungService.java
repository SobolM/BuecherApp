package de.fh_muenster.buecherwelt.buecherwelt;

import java.util.Date;

import de.fh_muenster.buecherwelt.buecherwelt.exceptions.NoSessionException;

/**
 * Created by user on 11.06.15.
 */
public interface BuchverwaltungService {

    public void neuesBuchHinzufuegen(int id, String titel, String autor, int erscheinungsjahr, int anzahl) throws NoSessionException;

    public Buch getBuchMitIdEins() throws NoSessionException;
}
