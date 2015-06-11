package de.fh_muenster.buecherwelt.buecherwelt;

import java.util.Date;

/**
 * Created by user on 11.06.15.
 */
public class Buch {

    int id;
    String titel;
    String autor;
    Date erscheinungsjahr;
    int anzahl;

    public Buch(int id, String titel, String autor, Date erscheinungsjahr, int anzahl){
        super();
        this.id = id;
        this.titel = titel;
        this.autor = autor;
        this.erscheinungsjahr = erscheinungsjahr;
        this. anzahl = anzahl;
    }

    public String getTitel() {
        return titel;
    }

    public String getAutor() {
        return autor;
    }

    public int getId() {
        return id;
    }

    public Date getErscheinungsjahr() {
        return erscheinungsjahr;
    }

    public int getAnzahl() {
        return anzahl;
    }
}