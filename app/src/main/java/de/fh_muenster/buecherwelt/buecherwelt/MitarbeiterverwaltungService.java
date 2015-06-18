package de.fh_muenster.buecherwelt.buecherwelt;

import java.util.List;

import de.fh_muenster.buecherwelt.buecherwelt.exceptions.InvalidLoginException;
import de.fh_muenster.buecherwelt.buecherwelt.exceptions.NoSessionException;

/**
 * Created by user on 11.06.15.
 */
public interface MitarbeiterverwaltungService {

    public Mitarbeiter mitarbeiterLogin(String benutzername, String passwort) throws InvalidLoginException;

    public void neuenMitarbeiterHinzufuegen(int id, String vorname, String nachname, String plz, String ort, String strasse, int hausnummer, String email, String benutzername, String passwort) throws NoSessionException;

    public void logout() throws NoSessionException;

    public List<Mitarbeiter> getAllMitarbeiter() throws NoSessionException;

    public Mitarbeiter getMitarbeiter() throws NoSessionException;
}
