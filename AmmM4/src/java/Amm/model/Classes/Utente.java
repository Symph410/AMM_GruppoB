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
public class Utente {
    private String user;
    private String psw;
    private Integer id;
    public Saldo conto;
    
    
    public Utente (){
        user="";
        psw="";
        id= 0;
        conto=new Saldo ();
    }

    /**
     * @return the user
     */
    public String getUser() {
        return user;
    }

    /**
     * @param user the user to set
     */
    public void setUser(String user) {
        this.user = user;
    }

    /**
     * @return the psw
     */
    public String getPsw() {
        return psw;
    }

    /**
     * @param psw the psw to set
     */
    public void setPsw(String psw) {
        this.psw = psw;
    }

    /**
     * @return the id
     */
    public Integer getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the conto
     */
    public Saldo getConto() {
        return conto;
    }
    
    
}
