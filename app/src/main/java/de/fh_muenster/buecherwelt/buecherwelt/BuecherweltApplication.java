package de.fh_muenster.buecherwelt.buecherwelt;

import android.app.Application;

/**
 * Created by user on 11.06.15.
 */
public class BuecherweltApplication extends Application{

    private Kunde kunde;
    private Mitarbeiter mitarbeiter;
    private Buch buch;

    private MitarbeiterverwaltungService mitarbeiterverwaltungService;
    private KundenverwaltungService kundenverwaltungService;
    private BuchverwaltungService buchverwaltungService;

    public BuecherweltApplication() {

        this.mitarbeiterverwaltungService = new MitarbeiterverwaltungServiceImpl();
        this.kundenverwaltungService = new KundenverwaltungServiceImpl();
        this.buchverwaltungService = new BuchverwaltungServiceImpl();
    }

    public Kunde getKunde() {
        return this.kunde;
    }

    public Mitarbeiter getMitarbeiter() {
        return this.mitarbeiter;
    }

    public Buch getBuch() {
        return this.buch;
    }

    public MitarbeiterverwaltungService getMitarbeiterverwaltungService() {
        return this.mitarbeiterverwaltungService;
    }

    public KundenverwaltungService getKundenverwaltungService() {
        return this.kundenverwaltungService;
    }

    public BuchverwaltungService getBuchverwaltungService() {
        return this.buchverwaltungService;
    }

    public void setKunde(Kunde kunde) {
        this.kunde = kunde;
    }

    public void setMitarbeiter(Mitarbeiter mitarbeiter) {
        this.mitarbeiter = mitarbeiter;
    }

    public void setBuch(Buch buch) {
        this.buch = buch;
    }
}
