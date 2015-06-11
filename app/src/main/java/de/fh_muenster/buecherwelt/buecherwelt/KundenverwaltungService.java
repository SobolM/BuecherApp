package de.fh_muenster.buecherwelt.buecherwelt;

import de.fh_muenster.buecherwelt.buecherwelt.exceptions.NoSessionException;

/**
 * Created by user on 11.06.15.
 */
public interface KundenverwaltungService {

    public void logout() throws NoSessionException;

    public void kundeHinzufuegen(int id, String vorname, String nachname, String plz, String ort, String strasse, int hausnummer, String email, String benutzername, String passwort) throws NoSessionException;
}
