/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Amm.model.Classes;

/**
 *
 * @author sym410
 */
public class Saldo {
    
    private Double conto;

    public Saldo () {
        this.conto=0.0;
    }
    
    public void addMoney (Double val){
        this.setConto((Double) (this.conto + val));
    }
    
    //se l'operazione di sottrazione va a buon fine sottraggo la somma e ritorno true, altrimenti false
    public boolean subMoney (Double val){
        
        if(val<0) //se un utente inserisce un valore negativo ne ottengo il valore assoluto  
            val= Math.abs(val);

        if ( val <= conto  ) {
            this.setConto((Double) (this.getConto() - val));
            return true;
        }
        return false;
   }
    
    public Double getConto() {
        return conto;
    }

    public void setConto(Double conto) {
        this.conto = conto;
    }
    
}
