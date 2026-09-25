/*
 * Author: Arnav Joginapally
 * Course: CS 300, Fall 2026
 * Assignment: Shopping Cart, Program 1
 * Email: joginapally@wisc.edu
 * Citations: None
 */

/**
 * This class contains utility methods for managing a shopping cart, including
 * adding and removing items, calculating costs, and retrieving item
 * information.
 */
public class CartUtilities {

  /**
   * 
   * @param cart
   * @param cartSize
   * @param description
   * @return index of the item in the cart if found, otherwise -1
   */
  public static int indexOfItem(String[][] cart, int cartSize, String description) {

    for (int i = 0; i < cartSize; i++) {
      // checks if the item at the current index matches the description
      if (cart[i][0].equals(description)) { 
        return i;
      } else if (cart[i][0] == null) {
        continue;
      }
    }

    return -1;
  }

  /**
   * 
   * @param cart
   * @param cartSize
   * @param description
   * @return the new cartsize after adding the item to the cart if there is space,
   *         otherwise returns the original cartsize
   */
  public static int addItemToCart(String[][] cart, int cartSize, String description) {

    for (int i = 0; i < cartSize; i++) {
      if (cart[i][0].equals(description)) { 
        // checks if the item is already in the cart                      
        int quantity = Integer.parseInt(cart[i][1]);
        quantity++;
        cart[i][1] = Integer.toString(quantity);
        return cartSize;
      }
    }

    if (cartSize < cart.length) {          
      cart[cartSize] = new String[] { description, "1" }; 
      // adds the item to the cart with a quantity of 1
      cartSize++;
    }

    return cartSize;
  }

  /**
   * 
   * @param cart
   * @param cartSize
   * @param index
   * @return the new cartsize after removing the item from the cart if the index
   *         is valid, otherwise returns the original cartsize
   */
  public static int removeItemFromCart(String[][] cart, int cartSize, int index) {

    if (index < 0 || index >= cartSize) {
      return cartSize; // Invalid index, return original cart size
    } else {
      for (int i = index; i < cartSize - 1; i++) {
        // Shifts every index to the left, overwriting the item at the specified index
        cart[i][0] = cart[i + 1][0]; 
        cart[i][1] = cart[i + 1][1];
      }
      cart[cartSize - 1] = null; 
      // adds an additional null value to the end of the cart 
      cartSize--; // decreasing cart size by 1 to reflect the removal of an item
    }

    return cartSize;
  }

  /**
   * 
   * @param inventory
   * @param costs
   * @param description
   * @return the cost of the item if found, otherwise -1
   * 
   */
  public static int getCostOfItem(String[] inventory, int[] costs, String description) {

    for (int i = 0; i < inventory.length; i++) { 
      // iterate through the inventory array                                   
      if (inventory[i].equals(description)) { 
        // checks if the item at the current index matches the description
        return costs[i]; 
      }
    }
    return -1;
  }

  /**
   * 
   * @param cart
   * @param cartSize
   * @param inventory
   * @param costs
   * @return the total cost of all items in the cart if all items are present in
   *         inventory
   */
  public static int getTotalCost(String[][] cart, int cartSize, String[] inventory, int[] costs) {
    int totalSum = 0; // initialize total sum to 0

    for (int i = 0; i < cartSize; i++) {
      String description = cart[i][0]; 
      int quantity = Integer.parseInt(cart[i][1]);
      int cost = getCostOfItem(inventory, costs, description);

      if (cost != -1) { 
        // check if the item is present in inventory
        totalSum += cost * quantity;
      }
    }

    return totalSum;
  }

}