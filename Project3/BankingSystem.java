/*
 * Author: Arnav Joginapally
 * Email: joginapally@wisc.edu
 * Course: CS 300, Fall 2026
 * Assignment: Banking System, Program 3
 * Citations: None
 */

import java.util.ArrayList;

/**
 * Represents a banking system that manages multiple bank accounts and supports its operations
 */
public class BankingSystem {
  private ArrayList<BankAccount> accounts;

  /**
   * Constructs a new BankingSystem instance with an empty list of bank accounts.
   */
  public BankingSystem() {
    accounts = new ArrayList<BankAccount>();
  }

  /**
   * Creates a new bank account with the specified account number, account holder's name, and
   *     initial deposit.
   * @param accountNumber The account number for the new bank account
   * @param name The name of the account holder
   * @param initialDeposit The initial deposit amount for the new bank account
   * @throws InvalidAccountException if the account number is invalid or already exists
   * @throws IllegalArgumentException if the name is null or blank, or the deposit is negative
   */
  public void createAccount(String accountNumber, String name, double initialDeposit)
      throws InvalidAccountException, IllegalArgumentException {
    // Examine each stored account.
    for (BankAccount account : accounts) {
      if (account.getAccountNumber().equals(accountNumber)) {
        throw new InvalidAccountException("Account with this number already exists");
      }
    }

    // Perform the operation and propagate account errors.
    try {
      BankAccount account = new BankAccount(accountNumber, name, initialDeposit);
      accounts.add(account);
    } catch (InvalidAccountException | IllegalArgumentException e) {
      throw e;
    }
  }

  /**
   * Finds and returns the bank account with the specified account number. Throws an exception if
   *     the account is not found.
   * @param accountNumber The account number of the bank account to find
   * @return The bank account with the specified account number
   * @throws InvalidAccountException if the account cannot be found
   */
  public BankAccount findAccount(String accountNumber) throws InvalidAccountException {
    // Examine each stored account.
    for (BankAccount account : accounts) {
      if (account.getAccountNumber().equals(accountNumber)) {
        return account;
      }
    }
    throw new InvalidAccountException("Account not found");
  }

  /**
   * Method to transfer money from one bank account to another.
   * @param fromAccountNum The account number of the account to transfer money from
   * @param toAccountNum The account number of the account to transfer money to
   * @param amount The amount of money to transfer
   * @throws InvalidAccountException if either account cannot be found
   * @throws IllegalArgumentException if the accounts match or the amount is negative
   * @throws InsufficientFundsException if the source account has insufficient funds
   */
  public void transferMoney(String fromAccountNum, String toAccountNum, double amount)
      throws InvalidAccountException, IllegalArgumentException, InsufficientFundsException {
    // Perform the operation and propagate account errors.
    try {
      if (fromAccountNum.equals(toAccountNum)) {
        throw new IllegalArgumentException("Cannot transfer money to the same account");
      }
      // Locate both accounts before updating their balances.
      BankAccount fromAccount = findAccount(fromAccountNum);
      BankAccount toAccount = findAccount(toAccountNum);

      // Withdraw first so insufficient funds prevent the deposit.
      fromAccount.withdraw(amount);
      toAccount.deposit(amount);
    } catch (InvalidAccountException | IllegalArgumentException | InsufficientFundsException e) {
      throw e;
    }
  }

  /**
   * Displays the information of the bank account with the specified account number. Throws an
   *     exception if the account is not found.
   * @param accountNumber The account number of the bank account to display
   * @throws InvalidAccountException if the account cannot be found
   */
  public void displayAccountInfo(String accountNumber) throws InvalidAccountException {
    // Perform the operation and propagate account errors.
    try {
      BankAccount account = findAccount(accountNumber);
      System.out.println(account.toString());
    } catch (InvalidAccountException e) {
      throw new InvalidAccountException("Account not found");
    }
  }

  /**
   * Returns the sum of all account balances.
   * @return the total balance held by the banking system
   */
  public double getTotalBankBalance() {
    double total = 0;
    // Examine each stored account.
    for (BankAccount account : accounts) {
      total += account.getBalance();
    }
    return total;
  }
}
