/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Amm.model.Classes;
import java.util.List;
import java.util.ArrayList;

/**
 *
 * @author sym410
 */
public class ObjectSaleFactory {

    static ObjectSaleFactory instance=null;
    List<ObjectSale> myList= new ArrayList<>();


    private ObjectSaleFactory(){

        ObjectSale[] items= new ObjectSale[5];

        for(int i=0; i<items.length; i++)
            items[i]= new ObjectSale ();

        //setto i nomi fittizi
        items[0].setName("Ugin foil 1sted");
        items[1].setName("Bionicle lev kah");
        items[2].setName("Nave in fiammiferi");
        items[3].setName("Gundam Damashii");
        items[4].setName("Portadeck swamp");

        //url
        items[0].setUrl("DatiStatici/Images/ugin.jpg");
        items[1].setUrl("DatiStatici/Images/Bionicle.jpg");
        items[2].setUrl("DatiStatici/Images/caravella.jpg");
        items[3].setUrl("DatiStatici/Images/Gundam.jpg");
        items[4].setUrl("DatiStatici/Images/protector.png");
        
        //quantità disponibili
        items[0].setQuantity(5);
        items[1].setQuantity(10);
        items[2].setQuantity(1);
        items[3].setQuantity(5);
        items[4].setQuantity(10);
        
        //descrizioni
        items[0].setDescription("Carta Ugin da FOE, condizione mint");
        items[1].setDescription("Bionicle prima edizione, 2005");
        items[2].setDescription("Nave in fiammiferi fatta a mano");
        items[3].setDescription("Modellino gundam dashii, importato dal Giappone");
        items[4].setDescription("Porta carte in plastica rinforzata");

        //price
        items[0].setPrice(17.40);
        items[1].setPrice(7.50);
        items[2].setPrice(50.00);
        items[3].setPrice(25.00);
        items[4].setPrice(10.0);

        //category
        items[0].setCategory("TCG");
        items[1].setCategory("Action Figure");
        items[2].setCategory("Modellismo");
        items[3].setCategory("Action Figure");
        items[4].setCategory("Gadget");

        //id
        items[0].setId(1);
        items[1].setId(2);
        items[2].setId(3);
        items[3].setId(4);
        items[4].setId(5);
         

        for (int i=0; i< items.length; i++)
            myList.add(items[i]);


    }

    synchronized public static ObjectSaleFactory singleton () {
        if(instance==null)
            instance= new ObjectSaleFactory();

        return instance;
    }

     public ObjectSale getObjectSaleById (Integer id){
        for (ObjectSale L : myList) {
            if (L.getId().equals(id))
                return L;
        }

        return null;
     }

     public List<ObjectSale>  getSellingObjectList() {
         return myList;
     }
     
     public List<ObjectSale> getObjectSaleByCategory(String category) {

         List<ObjectSale> tmp= new ArrayList<ObjectSale> ();

         for ( ObjectSale L : myList){
            if (L.getCategory().equals(category))
               tmp.add(L);
         }
         return tmp;
     }


}
