package de.fh_muenster.buecherwelt.buecherwelt;

import de.fh_muenster.buecherwelt.buecherwelt.exceptions.InvalidLoginException;
import de.fh_muenster.buecherwelt.buecherwelt.exceptions.NoSessionException;

/**
 * Created by user on 11.06.15.
 */
public interface MitarbeiterverwaltungService {

    public void login(String benutzername, String passwort) throws InvalidLoginException;

    public void neuenMitarbeiterHinzufuegen(int id, String vorname, String nachname, String plz, String ort, String strasse, int hausnummer, String email, String benutzername, String passwort) throws NoSessionException;

    public void logout() throws NoSessionException;
}
