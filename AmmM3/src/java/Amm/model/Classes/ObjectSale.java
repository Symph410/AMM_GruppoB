/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Amm.model.Classes;

import java.util.Random;

/**
 *
 * @author sym410
 */
public class ObjectSale {
    
    private String name;
    private String url;
    private int quantity;
    private String description;
    private Double price;
    private Integer id;
    private String category;

    
    public ObjectSale(){
        this.name="";
        this.url="";
        this.description="";
        this.quantity=0;
        this.price=0.0;
        this.id=0;
        this.category="";
    }
    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the url
     */
    public String getUrl() {
        return url;
    }

    /**
     * @param url the url to set
     */
    public void setUrl(String url) {
        this.url = url;
    }

    /**
     * @return the quantity
     */
    public Integer getQuantity() {
        return quantity;
    }

    /**
     * @param quantity the quantity to set
     */
    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }
    
    public void setQuantity(String quantity){
        this.quantity=Integer.parseInt(quantity);
    }
    
    /*Metodo comodo se implementerò la possibilità di comprare più
      di una copia dello stesso oggetto per volta*/
    public void buyObject (Integer numObj){
        if (numObj <= quantity)
            quantity-=numObj;
    }

    /**
     * @return the price
     */
    public Double getPrice() {
        return price;
    }

    /**
     * @param price the price to set
     */
    public void setPrice(Double price) {
        this.price = price;
    }
    
    public void setPrice(String price){
        this.price=Double.parseDouble(price);
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
        //faccio in modo che non possa essere impostato un id inferiore a zero,
        //perché oltre a non avere senso, ho effettuato nel codice controlli
        //che impostano l'id a -1 in caso di errore.
        while(id<0){
            Random r= new Random();
            id=r.nextInt();
        }
        this.id = id;  
    }
    
    

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * @param description the description to set
     */
    public void setDescription(String description) {
        this.description = description;
    }
    
    
    
}
