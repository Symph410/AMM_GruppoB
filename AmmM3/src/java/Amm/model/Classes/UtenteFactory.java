/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Amm.model.Classes;
import java.util.ArrayList;

/**
 *
 * @author sym410
 */
public class UtenteFactory {

    static UtenteFactory instance= null;
    ArrayList<Utente> utenti = new ArrayList<Utente>();

    private UtenteFactory() {
        /*Dati fittizi...*/
        Utente[] utentiRegistrati= new Utente[3];

        utentiRegistrati[0]= new UtenteVenditore();
        utentiRegistrati[1]= new UtenteAcquirente();
        utentiRegistrati[2]= new UtenteAcquirente();

        utentiRegistrati[0].setUser("Seller1");
        utentiRegistrati[0].setPsw("psw1");
        utentiRegistrati[0].setId(1);
        utentiRegistrati[0].conto.setConto(100.0);
        
        utentiRegistrati[1].setUser("Buyer1");
        utentiRegistrati[1].setPsw("psw2");
        utentiRegistrati[1].setId(2);
        utentiRegistrati[1].conto.setConto(27.50);

        utentiRegistrati[2].setUser("Buyer2");
        utentiRegistrati[2].setPsw("psw3");
        utentiRegistrati[2].setId(3);
        utentiRegistrati[2].conto.setConto(170.50);
        

        for (int i=0; i < utentiRegistrati.length; i++)
            utenti.add(utentiRegistrati[i]);

    }

    synchronized public static UtenteFactory singleton () {
        if(instance==null)
            instance= new UtenteFactory();

        return instance;
    }

    public ArrayList<Utente> getUtenteList (){
        return utenti;
    }

    Utente getUtenteById (Integer id) {

        for (Utente u: utenti){
            if(u.getId().equals(id))
                return u;
        }
        return null;
    }

    ArrayList<UtenteVenditore> getUtenteVenditore () {

        ArrayList<UtenteVenditore> venditori = new ArrayList<UtenteVenditore>();

        for (Utente u: utenti)
            if (u instanceof UtenteVenditore) {
                venditori.add( ((UtenteVenditore)u) );
            }
        return venditori;
    }

    ArrayList<UtenteAcquirente> getUtenteAcquirente () {

        ArrayList<UtenteAcquirente> acquirenti= new ArrayList<UtenteAcquirente>();

        for(Utente u: utenti)
            if(u instanceof UtenteAcquirente){
                acquirenti.add ( ((UtenteAcquirente)u) );
            }
        return acquirenti;
    }
}
