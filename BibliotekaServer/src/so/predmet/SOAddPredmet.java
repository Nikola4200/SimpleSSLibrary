/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.predmet;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Predmet;
import java.util.ArrayList;
import so.AbstractSO;

/**
 *
 * @author Asus
 */
public class SOAddPredmet extends AbstractSO {

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Predmet)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Predmet!");
        }

        Predmet pr = (Predmet) ado;

        ArrayList<Predmet> predmeti = (ArrayList<Predmet>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Predmet predmet : predmeti) {
            if (pr.getNazivPredmeta().equals(predmet.getNazivPredmeta())) {
                throw new Exception("Vec postoji predmet sa tim nazivom!");
            }
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {
        DBBroker.getInstance().insert(ado);
    }
    
}
