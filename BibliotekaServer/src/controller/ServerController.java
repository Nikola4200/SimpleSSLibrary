/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import domain.Administrator;
import domain.Knjiga;
import domain.Kupac;
import domain.Porudzbina;
import domain.Predmet;
import domain.StavkaPorudzbine;
import domain.Zanr;
import java.util.ArrayList;
import so.administrator.SOGetAllAdministrator;
import so.knjiga.SOGetAllKnjiga;
import so.kupac.SOAddKupac;
import so.kupac.SODeleteKupac;
import so.kupac.SOGetAllKupac;
import so.kupac.SOUpdateKupac;
import so.login.SOLogin;
import so.porudzbina.SOAddPorudzbina;
import so.porudzbina.SODeletePorudzbina;
import so.porudzbina.SOGetAllPorudzbina;
import so.porudzbina.SOUpdatePorudzbina;
import so.predmet.SOAddPredmet;
import so.stavkaPorudzbine.SOGetAllStavkaPorudzbine;
import so.zanr.SOGetAllZanr;

/**
 *
 * @author Asus
 */
public class ServerController {

    private static ServerController instance;

    private ServerController() {
    }

    public static ServerController getInstance() {
        if (instance == null) {
            instance = new ServerController();
        }
        return instance;
    }

    public Administrator login(Administrator administrator) throws Exception {
        SOLogin so = new SOLogin();
        so.templateExecute(administrator);
        return so.getUlogovani();
    }

    public void addKupac(Kupac kupac) throws Exception {
        (new SOAddKupac()).templateExecute(kupac);
    }

    public void addPorudzbina(Porudzbina porudzbina) throws Exception {
        (new SOAddPorudzbina()).templateExecute(porudzbina);
    }

    public void deleteKupac(Kupac kupac) throws Exception {
        (new SODeleteKupac()).templateExecute(kupac);
    }

    public void deletePorudzbina(Porudzbina porudzbina) throws Exception {
        (new SODeletePorudzbina()).templateExecute(porudzbina);
    }

    public void updateKupac(Kupac kupac) throws Exception {
        (new SOUpdateKupac()).templateExecute(kupac);
    }

    public void updatePorudzbina(Porudzbina porudzbina) throws Exception {
        (new SOUpdatePorudzbina()).templateExecute(porudzbina);
    }

    public ArrayList<Administrator> getAllAdministrator() throws Exception {
        SOGetAllAdministrator so = new SOGetAllAdministrator();
        so.templateExecute(new Administrator());
        return so.getLista();
    }

    public ArrayList<Kupac> getAllKupac() throws Exception {
        SOGetAllKupac so = new SOGetAllKupac();
        so.templateExecute(new Kupac());
        return so.getLista();
    }

    public ArrayList<Porudzbina> getAllPorudzbina() throws Exception {
        SOGetAllPorudzbina so = new SOGetAllPorudzbina();
        so.templateExecute(new Porudzbina());
        return so.getLista();
    }

    public ArrayList<Zanr> getAllZanr() throws Exception {
        SOGetAllZanr so = new SOGetAllZanr();
        so.templateExecute(new Zanr());
        return so.getLista();
    }

    public ArrayList<Knjiga> getAllKnjiga() throws Exception {
        SOGetAllKnjiga so = new SOGetAllKnjiga();
        so.templateExecute(new Knjiga());
        return so.getLista();
    }

    public ArrayList<StavkaPorudzbine> getAllStavkaPorudzbine(Porudzbina p) throws Exception {
        SOGetAllStavkaPorudzbine so = new SOGetAllStavkaPorudzbine();
        
        StavkaPorudzbine sp = new StavkaPorudzbine();
        sp.setPorudzbina(p);
        
        so.templateExecute(sp);
        return so.getLista();
    }

    public void addPredmet(Predmet predmet) throws Exception {
        (new SOAddPredmet()).templateExecute(predmet);
    }

}
