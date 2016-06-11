package Amm.model.Classes;
import java.util.ArrayList;
import java.sql.*;  
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sym410
 */
public class UtenteFactory {

    static UtenteFactory instance= null;
    private static String connectionString;
    ArrayList<Utente> utenti = new ArrayList<>();
    private Connection conn=null;

    private UtenteFactory() {}

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
    
    public void inizializzaDati(){
    try {
            conn = DriverManager.getConnection(getConnectionString(), "sym410", "pswbella");
            Statement stmtBuyer = conn.createStatement();
            Statement stmtSeller= conn.createStatement();

            String queryAcquirente ="select acquirente.id, username, psw, saldo.conto "
                    + "from ACQUIRENTE "
                    + "join SALDO on acquirente.saldo_id = saldo.id";
            
            String queryVenditore= "select venditore.id, username, psw, saldo.conto"
                    + " from VENDITORE "
                    + "join SALDO on venditore.saldo_id= saldo.id";
            
            ResultSet setAcquirente = stmtBuyer.executeQuery(queryAcquirente);
            ResultSet setVenditore = stmtSeller.executeQuery(queryVenditore);
             
            
            while (setVenditore.next()){
                UtenteVenditore u= new UtenteVenditore();
               
                u.setId( setVenditore.getInt("id"));
                u.setUser( setVenditore.getString("username") );
                u.setPsw( setVenditore.getString("psw") );
                u.conto.setConto( setVenditore.getDouble("conto")  );
                utenti.add(u);
            }
            
            while (setAcquirente.next()){    
                UtenteAcquirente u= new UtenteAcquirente();
                
                u.setId( setAcquirente.getInt("id") );
                u.setUser( setAcquirente.getString("username") );
                u.setPsw( setAcquirente.getString("psw") );
                u.conto.setConto( setAcquirente.getDouble("conto") );
                utenti.add(u);
            }
            
            stmtBuyer.close();
            stmtSeller.close();
            conn.close();
            
        } 
        catch (SQLException ex) {
            Logger.getLogger(UtenteFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
    //ritorna la lista di tutti gli utenti venditori
    ArrayList<UtenteVenditore> getUtenteVenditore () {

        ArrayList<UtenteVenditore> venditori = new ArrayList<UtenteVenditore>();

        for (Utente u: utenti)
            if (u instanceof UtenteVenditore) {
                venditori.add( ((UtenteVenditore)u) );
            }
        return venditori;
    }

    //ritorna la lista di tutti gli utenti acquirenti
    ArrayList<UtenteAcquirente> getUtenteAcquirente () {

        ArrayList<UtenteAcquirente> acquirenti= new ArrayList<>();

        for(Utente u: utenti)
            if(u instanceof UtenteAcquirente){
                acquirenti.add ( ((UtenteAcquirente)u) );
            }
        return acquirenti;
    }

    /**
     * @return the connectionString
     */
    public String getConnectionString() {
        return connectionString;
    }

    /**
     * @param connectionString the connectionString to set
     */
    public void setConnectionString(String connectionString) {
        this.connectionString = connectionString;
    }
    
}
