/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package domain;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

/**
 *
 * @author Asus
 */
public class Predmet extends AbstractDomainObject {

    private Long predmetID;
    private String nazivPredmeta;
    private int brojESPB;

    public Predmet(Long predmetID, String nazivPredmeta, int brojESPB) {
        this.predmetID = predmetID;
        this.nazivPredmeta = nazivPredmeta;
        this.brojESPB = brojESPB;
    }

    public Predmet() {
    }
    
    @Override
    public String nazivTabele() {
        return " Predmet ";
    }

    @Override
    public String alijas() {
        return " pr ";
    }

    @Override
    public String join() {
        return "";
    }

    @Override
    public ArrayList<AbstractDomainObject> vratiListu(ResultSet rs) throws SQLException {
        ArrayList<AbstractDomainObject> lista = new ArrayList<>();

        while (rs.next()) {
            Predmet pr = new Predmet(rs.getLong("PredmetId"),
            rs.getString("nazivPredmeta"),rs.getInt("brojESPB"));

            lista.add(pr);
        }

        rs.close();
        return lista;
    }

    @Override
    public String koloneZaInsert() {
        return " (NazivPredmeta, brojESPB) ";
    }

    @Override
    public String vrednostZaPrimarniKljuc() {
        return " PredmetId = " + predmetID;
    }

    @Override
    public String vrednostiZaInsert() {
        return "'" + nazivPredmeta + "', '" + brojESPB + "'";
    }

    @Override
    public String vrednostiZaUpdate() {
        return "";
    }

    @Override
    public String uslov() {
        return "";
    }

    public String getNazivPredmeta() {
        return nazivPredmeta;
    }

    public void setNazivPredmeta(String nazivPredmeta) {
        this.nazivPredmeta = nazivPredmeta;
    }

    public int getBrojESPB() {
        return brojESPB;
    }

    public void setBrojESPB(int brojESPB) {
        this.brojESPB = brojESPB;
    }

    public Long getPredmetID() {
        return predmetID;
    }

    public void setPredmetID(Long predmetID) {
        this.predmetID = predmetID;
    }
    
}
