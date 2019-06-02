/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingmachinetest;


/**
 *
 * @author 曾馨慧
 */
public class Drink {
    String Name;
    String Manufacturer;
    int Quantity;
    int Price;
    
    // getter
    public String getName(){  // get the name
        return this.Name;
    }
    public String getManufacturer(){  // get the Manufacturer
        return this.Manufacturer;
    }
      public int getQuantity(){  // get the Quantity
        return this.Quantity;
    }
      public int getPrice(){  // get the Price
        return this.Price;
    }
    //setter
    public void setName(String name){
      Name=name;
    }
   public void setManufacturer(String manufacturer){
      Manufacturer=manufacturer;
    }
    public void setQuantity(int quantity){
      Quantity=quantity;
    }
    public void setPrice(int price){
      Price=price;
    }
    
}// end of class Drink
