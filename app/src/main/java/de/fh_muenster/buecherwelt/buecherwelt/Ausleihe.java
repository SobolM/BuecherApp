package de.fh_muenster.buecherwelt.buecherwelt;

import java.util.Date;

/**
 * Created by user on 20.06.15.
 */
public class Ausleihe {

    private int id;

    private String leihdatum;
    private int kundenId;
    private int buchId;


    public Ausleihe(int id, String leihdatum, int kundenId, int buchId) {
        this.id = id;
        this.leihdatum = leihdatum;
        this.kundenId = kundenId;
        this.buchId = buchId;
    }

    public Ausleihe(int id, int kundenId, int buchId) {
        this.id = id;

        this.kundenId = kundenId;
        this.buchId = buchId;
    }


    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLeihdatum() {
        return this.leihdatum;
    }

    public int getKundenId() {
        return this.kundenId;
    }

    public int getBuchId() {
        return this.buchId;
    }

    public void setLeihdatum(String leihdatum) {
        this.leihdatum = leihdatum;
    }

    public void setKundenId(int kundenId) {
        this.kundenId = kundenId;
    }

    public void setBuchId(int buchId) {
        this.buchId = buchId;
    }
}
