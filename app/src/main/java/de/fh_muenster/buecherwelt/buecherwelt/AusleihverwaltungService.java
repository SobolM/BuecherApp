package de.fh_muenster.buecherwelt.buecherwelt;

import java.util.Date;
import java.util.List;

import de.fh_muenster.buecherwelt.buecherwelt.exceptions.NoSessionException;

/**
 * Created by user on 20.06.15.
 */
public interface AusleihverwaltungService {

    public List<Ausleihe> getAllAusleihen()throws NoSessionException;

    public void neuesAusleiheHinzufuegen(int id, Date leihdatum, int kundenId, int buchId) throws NoSessionException;

    public List<Ausleihe> getAusleihenByKundenId(int id) throws NoSessionException;
}
