import java.util.Scanner;

abstract class Cake{
   String name;
   String[] topping;
   String[] toppingOrder;
   double priceSmall;
   double priceMedium; 
   double priceBig;
   int size;
   int quantity;

   Cake(String name){this.name = name;}

   abstract void setCake(String[] topping, double priceSmall, double priceMedium, double priceBig);         

   void orderCake(String[] toppingOrder, int quantity, int size){
      this.toppingOrder = toppingOrder;
      this.quantity = quantity;
      this.size = size;
   }

   double getSizePrice(int size){
      double invalidSize = 0;

      if(size == 1)
         return priceSmall;
      else if(size == 2)
         return priceMedium;
      else if(size == 3)
         return priceBig;
      else{
         System.out.println("Invalid Size!");
         return invalidSize = 0;}
   }

   double getTotalPrice(){
      double totalPrice = getSizePrice(size) * quantity + (toppingOrder.length * 10);
      return totalPrice;
   }

   String getSize(int size){
      String cakeSize = "";
      if(size == 1){
         cakeSize = "Small";
         return cakeSize;
      }
      else if(size == 2){
         cakeSize = "Medium";
         return cakeSize;
      }
      else if(size == 3){
         cakeSize = "Big";
         return cakeSize;
      }
      else{
         cakeSize = "Invalid size";
         return cakeSize;
      }
   }

   void printCake(){
      System.out.println("--------------------------------");
      System.out.println("            Cake Menu           ");
      System.out.println("--------------------------------");
      System.out.println(name + " with available toppings: ");
      for(int i = 0; i < topping.length; i++)
         System.out.println(i+1 + ") " + topping[i]);
      
      System.out.println("\nPrice:");
      System.out.println("[1] Small : RM" + priceSmall);
      System.out.println("[2] Medium: RM" + priceMedium);
      System.out.println("[3] Big   : RM" + priceBig);
   }
   void printOrder(){
      System.out.println("\nCake Order detail: ");
      System.out.println("--------------------------------------");
      System.out.print("Topping : ");
      for(int i = 0; i < toppingOrder.length; i++)
         System.out.print(i+1 + ") " + toppingOrder[i] + " ");
      System.out.println("\nSize\t: " + getSize(size));
      System.out.println("--------------------------------------");
      System.out.println("Total Price: RM" + getTotalPrice());
      System.out.println("--------------------------------------");
   }
}

class BlackForest extends Cake{
   BlackForest(String name){
      super(name);
   }

   void setCake(String[] topping, double priceSmall, double priceMedium, double priceBig){
      this.topping = topping;
      this.priceSmall = priceSmall;
      this.priceMedium = priceMedium;
      this.priceBig = priceBig;
   }
}

public class TestCake1{
   public static void main(String args[]){
      Cake c = new BlackForest("BlackForest");
      String[] topping = {"Chocolate", "Cherries", "Whipped Cream"};
      c.setCake(topping, 45.00, 65.00, 80.00);
      c.printCake();

      String[] order = {"Chocolate", "Cherries"};
      c.orderCake(order, 1, 2);
      c.printOrder();
   }
}
