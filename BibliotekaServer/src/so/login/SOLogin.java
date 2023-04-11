/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package so.login;

import db.DBBroker;
import domain.AbstractDomainObject;
import domain.Administrator;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import so.AbstractSO;

/**
 *
 * @author Asus
 */
public class SOLogin extends AbstractSO {
    
    Administrator ulogovani;

    @Override
    protected void validate(AbstractDomainObject ado) throws Exception {
        if (!(ado instanceof Administrator)) {
            throw new Exception("Prosledjeni objekat nije instanca klase Administrator!");
        }
    }

    @Override
    protected void execute(AbstractDomainObject ado) throws Exception {

        Administrator a = (Administrator) ado;

        ArrayList<Administrator> administratori
                = (ArrayList<Administrator>) (ArrayList<?>) DBBroker.getInstance().select(ado);

        for (Administrator administrator : administratori) {
            if (administrator.getUsername().equals(a.getUsername())
                    && administrator.getPassword().equals(a.getPassword())) {
                ulogovani = administrator;
                return;
            }
        }

        throw new Exception("Sistem ne moze da pronadje administratora na osnovu unetih podataka.");
        
    }

    public Administrator getUlogovani() {
        return ulogovani;
    }
    
    

}
