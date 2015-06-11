package de.fh_muenster.buecherwelt.buecherwelt;

/**
 * Created by user on 11.06.15.
 */
public class Mitarbeiter {

    private int id;
    private String vorname;
    private String nachname;
    private String plz;
    private String ort;
    private String strasse;
    private int hausnummer;
    private String email;
    private String benutzername;
    private String passwort;

    public Mitarbeiter(int id, String vorname, String nachname, String plz, String ort, String strasse, int hausnummer, String email, String benutzername, String passwort) {
        this.id = id;
        this.vorname = vorname;
        this.nachname = nachname;
        this.plz = plz;
        this.ort = ort;
        this.strasse = strasse;
        this.hausnummer = hausnummer;
        this.email = email;
        this.benutzername = benutzername;
        this.passwort = passwort;
    }

    public int getId() {
        return id;
    }

    public String getVorname() {
        return vorname;
    }

    public String getNachname() {
        return nachname;
    }

    public String getOrt() {
        return ort;
    }

    public String getStrasse() {
        return strasse;
    }

    public int getHausnummer() {
        return hausnummer;
    }

    public String getEmail() {
        return email;
    }

    public String getBenutzername() {
        return benutzername;
    }

    public String getPasswort() {
        return passwort;
    }

    public String getPlz() {
        return plz;
    }
}
