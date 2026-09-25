/*
 * Author: Arnav Joginapally
 * Course: CS 300, Fall 2026
 * Assignment: Shopping Cart, Program 1
 * Email: joginapally@wisc.edu
 * Citations: None
 */



/**
 * Contains testing methods for each method in CartUtilities.
 * Each testing method returns true when all its test cases pass, otherwise false.
 */
public class CartUtilitiesTester {


  
  // no need for testing method header comments as these are self-explanatory.
  public static boolean testIndexOfItem() {
    String[][] cart = { {"cheese", "1"},
                        {"apple", "5"},
                        {"bread", "1"},
                          null, null};
    int normalResult = CartUtilities.indexOfItem(cart, 3, "apple"); 
    //testing if the method correctly returns the index of an item in the cart
    if (normalResult != 1) {
      return false;
    }

    int edgeResult = CartUtilities.indexOfItem(cart, 3, "banana"); 
    //testing if the method correctly returns -1 when the item is not in the cart
    if (edgeResult != -1) {
      return false;
    }

    return true;
  }

  public static boolean testAddItemToCart() {
    String[][] cart = { {"cheese", "1"},
                        {"apple", "5"},
                        {"bread", "1"},
                          null, null};
    int newSize = CartUtilities.addItemToCart(cart, 3, "egg"); 
    //testing if the method correctly adds an item to the cart when there is space
    if (newSize != 4 
    || !cart[3][0].equals("egg") 
    || !cart[3][1].equals("1")) {
      return false;
    }

    String[][] cart2 = { {"cheese", "1"},
                        {"apple", "5"},
                        {"bread", "1"},
                        null, null};
    int sameSize = CartUtilities.addItemToCart(cart2, 3,"apple"); 
    //testing if the method correctly increases the quantity of an item already in the cart
    if (sameSize != 3 || !cart2[1][1].equals("6")) {
      return false;
    }

    return true;
  }

  public static boolean testRemoveItemFromCart() {
    String[][] cart2 = { {"cheese", "1"},
                        {"apple", "5"},
                        {"bread", "1"},
                        null, null};

    int newSize = CartUtilities.removeItemFromCart(cart2, 3, 1); 
    //testing if the method correctly removes the item at index 1 and shifts the remaining items to the left
    if (newSize != 2 
      || !cart2[1][0].equals("bread") 
      || !cart2[1][1].equals("1") 
      || cart2[2] != null) {
      return false;
    }

    String[][] cart3 = { {"cheese", "1"},
                        {"apple", "5"},
                        {"bread", "1"},
                        null, null};
    newSize = CartUtilities.removeItemFromCart(cart3, 3, 5); 
    //testing if the method handles an invalid index correctly
    if (newSize != 3 
      || !cart3[1][0].equals("apple") 
      || !cart3[0][0].equals("cheese") 
      || !cart3[2][0].equals("bread")) {
      return false;
    }

    return true;
  }

  public static boolean testGetCostOfItem() {
    String[] inventory = {"apple", "bread", "cheese"};
    int[] costs = {1, 3, 5};

    int normalResult = CartUtilities.getCostOfItem(inventory, costs, "cheese"); 
    //testing if the method correctly returns the cost of an item in the inventory
    if (normalResult != 5) {
      return false;
    }

    int edgeResult = CartUtilities.getCostOfItem(inventory, costs, "egg"); 
    //testing if the method correctly returns -1 when the item is not in the inventory
    if (edgeResult != -1) {
      return false;
    }

    return true;
  }

  public static boolean testGetTotalCost() {
    String[][] cart2 = { {"cheese", "1"},
                        {"apple", "5"},
                        {"bread", "1"},
                        null, null};
    String[] inventory = {"apple", "bread", "cheese"};
    int[] costs = {1, 3, 5};

    if (CartUtilities.getTotalCost(cart2, 3, inventory, costs) != 13) { 
      //testing if the method correctly calculates the total cost of all items in the cart
      return false;
    }

    cart2 = new String[][] { {"cheese", "1"},
                        {"apple", "5"},
                        {"milk", "1"},
                        null, null};

    if (CartUtilities.getTotalCost(cart2, 3, inventory, costs) != 10) { 
      //testing if the method correctly returns -1 when an item in the cart is not in the inventory
      return false;
    }
    return true;
  }

  public static void main(String[] args) {
    System.out.println("=== CART UTILITIES TESTER ===");

    boolean allPass = true, testPass = true;

    System.out.println("testIndexOfItem():");
    testPass = testIndexOfItem();
    System.out.println("\t" + (testPass ? "PASS" : "FAIL"));

    allPass &= testPass;

    System.out.println("testAddItemToCart():");
    testPass = testAddItemToCart();
    System.out.println("\t" + (testPass ? "PASS" : "FAIL"));

    allPass &= testPass;

    System.out.println("testRemoveItemFromCart():");
    testPass = testRemoveItemFromCart();
    System.out.println("\t" + (testPass ? "PASS" : "FAIL"));

    allPass &= testPass;

    System.out.println("testGetCostOfItem():");
    testPass = testGetCostOfItem();
    System.out.println("\t" + (testPass ? "PASS" : "FAIL"));

    allPass &= testPass;

    System.out.println("testGetTotalCost():");
    testPass = testGetTotalCost();
    System.out.println("\t" + (testPass ? "PASS" : "FAIL"));

    allPass &= testPass;

    if (allPass) {
      System.out.println("\nCONGRATULATIONS! All of your tests passed.");
    }
  }

}