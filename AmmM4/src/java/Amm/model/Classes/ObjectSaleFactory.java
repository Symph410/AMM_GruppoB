/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Amm.model.Classes;

import java.util.List;
import java.util.ArrayList;
import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sym410
 */
public class ObjectSaleFactory {

    private static ObjectSaleFactory instance = null;
    private static String connectionString;
    private List<ObjectSale> myList = new ArrayList<>();
    private Connection conn;
    private Integer loggedSellerId; //utile per transazione nel database

    private ObjectSaleFactory() {}

    synchronized public static ObjectSaleFactory singleton() {
        if (instance == null) {
            instance = new ObjectSaleFactory();
        }

        return instance;
    }
    
    public ObjectSale getObjectSaleById(Integer id) {
        for (ObjectSale L : myList  ) {
            if (L.getId().equals(id)) {
                return L;
            }
        }

        return null;
    }
    
    //funzione comoda per ritornare tutti gli oggetti attualmente in vendita
    public List<ObjectSale> getSellingObjectList() {
        try {
            ArrayList<ObjectSale> objSellingList =new ArrayList<>();
            conn = DriverManager.getConnection(connectionString, "sym410", "pswbella");
            Statement stmtItems = conn.createStatement();
            String query = "select * from OBJECTSALE";
           
            ResultSet itemSet = stmtItems.executeQuery(query);

            while (itemSet.next()) {
                ObjectSale ob = new ObjectSale();
                ob.setId(itemSet.getInt("id"));
                ob.setName(itemSet.getString("nome"));
                ob.setUrl(itemSet.getString("url"));
                ob.setQuantity(itemSet.getInt("quantity"));
                ob.setDescription(itemSet.getString("description"));
                ob.setPrice(itemSet.getDouble("price"));
                ob.setCategory(itemSet.getString("category"));
                objSellingList.add(ob);
            }
            
            stmtItems.close();
            conn.close();
            
            return objSellingList;
            
        } catch (SQLException ex) {
            Logger.getLogger(UtenteFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return null;
    }

    public List<ObjectSale> getObjectSaleByCategory(String category) {

        List<ObjectSale> tmp = new ArrayList<ObjectSale>();

        for (ObjectSale L : getSellingObjectList()) {
            if (L.getCategory().equals(category)) {
                tmp.add(L);
            }
        }
        return tmp;
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
    
    //inserisco tutti gli oggetti presenti nel DB nella mia lista
    public void inizializzaDati() {
        try {
            conn = DriverManager.getConnection(connectionString, "sym410", "pswbella");
            Statement stmtItems = conn.createStatement();
            String query = "select * from OBJECTSALE";
            Integer currPos = 0;
            ResultSet itemSet = stmtItems.executeQuery(query);

            while (itemSet.next()) {
                myList.add(new ObjectSale());
                myList.get(currPos).setId(itemSet.getInt("id"));
                myList.get(currPos).setName(itemSet.getString("nome"));
                myList.get(currPos).setUrl(itemSet.getString("url"));
                myList.get(currPos).setQuantity(itemSet.getInt("quantity"));
                myList.get(currPos).setDescription(itemSet.getString("description"));
                myList.get(currPos).setPrice(itemSet.getDouble("price"));
                myList.get(currPos).setCategory(itemSet.getString("category"));
                currPos++;
            }

            stmtItems.close();
            conn.close();

        } catch (SQLException ex) {
            Logger.getLogger(UtenteFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void addItem(ObjectSale ob) {
        try {
            conn = DriverManager.getConnection(connectionString, "sym410", "pswbella");
            PreparedStatement stmtInsert = conn.prepareStatement("insert into objectsale (id, nome, url, quantity, description, price, category, venditore_id) "
                    + " VALUES (default, ?, ?, ?, ?, ?, ?, ?)");
            
            stmtInsert.setString(1, ob.getName());
            stmtInsert.setString(2, ob.getUrl());
            stmtInsert.setInt(3, ob.getQuantity());
            stmtInsert.setString(4, ob.getDescription());
            stmtInsert.setDouble(5, ob.getPrice());
            stmtInsert.setString(6, ob.getCategory());
            stmtInsert.setInt(7, loggedSellerId);

            int rows = stmtInsert.executeUpdate();
            myList=getSellingObjectList();
            if (rows != 1) {
                throw new UpdateFallitoException();
            }

            stmtInsert.close();
            conn.close();


        } catch (SQLException ex) {
            Logger.getLogger(ObjectSaleFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void updateItem(ObjectSale ob) {
        try {
            conn = DriverManager.getConnection(connectionString, "sym410", "pswbella");
            PreparedStatement stmtUpdate = conn.prepareStatement("update OBJECTSALE set id = ?, nome= ?, url=?, quantity=?, description=?, price=?, category=?"
                    + " WHERE id = ?");
            

            stmtUpdate.setInt(1, ob.getId());
            stmtUpdate.setString(2, ob.getName());
            stmtUpdate.setString(3, ob.getUrl());
            stmtUpdate.setInt(4, ob.getQuantity());
            stmtUpdate.setString(5, ob.getDescription());
            stmtUpdate.setDouble(6, ob.getPrice());
            stmtUpdate.setString(7, ob.getCategory());
            stmtUpdate.setInt(8, ob.getId());
            
            int rows = stmtUpdate.executeUpdate();
            myList= getSellingObjectList();

            if (rows != 1) {
                throw new UpdateFallitoException();
            }

            stmtUpdate.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ObjectSaleFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void deleteItem(int id) {
        try {
            conn = DriverManager.getConnection(connectionString, "sym410", "pswbella");
            PreparedStatement stmtDelete = conn.prepareStatement("delete FROM OBJECTSALE where ID = ?");
            stmtDelete.setInt(1, id);
            myList.remove(getObjectSaleById(id));
            stmtDelete.executeUpdate();

            conn.commit();
            stmtDelete.close();
            conn.close();
        } catch (SQLException ex) {
            Logger.getLogger(ObjectSaleFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Integer getLoggedSellerId() {
        return loggedSellerId;
    }

    public void setLoggedSellerId(Integer loggedSellerId) {
        this.loggedSellerId = loggedSellerId;
    }

    public boolean transazione(Utente u, ObjectSale ob) {
        try {
            Connection connTransaction = DriverManager.getConnection(connectionString, "sym410", "pswbella");
            connTransaction.setAutoCommit(false);

            ob.buyObject(1); //diminuisco la quantità nella lista
            updateItem(ob); //rendo effettivo il cambiamento nel database

            if (!u.conto.subMoney(ob.getPrice())) { //se l'utente non ha abbastanza soldi... rollback.
                ob.addQuantity(1); //ripristino la mia lista allo stato precedente
                connTransaction.rollback();
                return false;
            }

            //sottraggo i soldi all'utente e finalizzo nel db
            String queryAcquirente = "update saldo set conto = ? "
                    + "where id= ?";
            PreparedStatement stmtAcquirente = connTransaction.prepareStatement(queryAcquirente);
            stmtAcquirente.setDouble(1, u.conto.getConto());
            stmtAcquirente.setInt(2, u.getId());
            
            if (stmtAcquirente.executeUpdate() != 1){ //se c'è un problema nel sottrarre soldi al cliente... rollback.
                ob.addQuantity(1);
                connTransaction.rollback();   
                return false;
            }
            //aggiungo i soldi al conto del venditore
            String queryVenditoreId = "select venditore_id from objectsale "
                    + "where id= ?";

            PreparedStatement stmtVenditoreID = connTransaction.prepareStatement(queryVenditoreId);
            stmtVenditoreID.setInt(1, ob.getId());
            ResultSet venditore_idSet = stmtVenditoreID.executeQuery();
            Integer venditoreId = null;

            if (venditore_idSet.next()) {
                venditoreId = venditore_idSet.getInt("venditore_id");
            }

            if (venditoreId == null) {
                ob.addQuantity(1);
                connTransaction.rollback();
            }
            Utente currSeller = UtenteFactory.singleton().getUtenteById(venditoreId);
            currSeller.conto.addMoney(ob.getPrice());

            String queryVenditore = "update saldo set conto = ? "
                    + "where id= ?";
            PreparedStatement transaction = connTransaction.prepareStatement(queryVenditore);
            transaction.setDouble(1, currSeller.conto.getConto());
            transaction.setInt(2, venditoreId);

            if(transaction.executeUpdate() != 1){  
                ob.addQuantity(1);
                connTransaction.rollback();
                return false;
            }

            connTransaction.commit();
            connTransaction.setAutoCommit(true);
            stmtAcquirente.close();
            stmtVenditoreID.close();
            transaction.close();
            connTransaction.close();

            return true;

        } catch (SQLException ex) {
            Logger.getLogger(ObjectSaleFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
    
    //ritorna la lista degli oggetti in vendita del venditore attualmente loggato come tale
    public ArrayList<ObjectSale> getSellerItems() {
        try {
            ArrayList<ObjectSale> sellerItems = new ArrayList<>();
            Connection conn = DriverManager.getConnection(connectionString, "sym410", "pswbella");
            String query = "select * from objectsale "
                    + "where venditore_id= ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, loggedSellerId);
            ResultSet res = stmt.executeQuery();

            while (res.next()) {
                ObjectSale ob = new ObjectSale();
                ob.setId(res.getInt("id"));
                ob.setName(res.getString("nome"));
                ob.setUrl(res.getString("url"));
                ob.setQuantity(res.getInt("quantity"));
                ob.setDescription(res.getString("description"));
                ob.setPrice(res.getDouble("price"));
                ob.setCategory(res.getString("category"));
                sellerItems.add(ob);
            }

            return sellerItems;
        } catch (SQLException ex) {
            Logger.getLogger(ObjectSaleFactory.class.getName()).log(Level.SEVERE, null, ex);
        }

        return null;
    }

}
