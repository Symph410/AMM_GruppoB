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
public class UpdateFallitoException  extends RuntimeException {
    
    public UpdateFallitoException(){
        System.out.println("L'operazione di update ha avuto esito negativo");
    }
    
}
