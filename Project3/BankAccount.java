/*
 * Author: Arnav Joginapally
 * Email: joginapally@wisc.edu
 * Course: CS 300, Fall 2026
 * Assignment: Banking System, Program 3
 * Citations: none
 */

/**
 * Represents a bank account with basic operations such as deposit, withdraw, and balance
 *     information
 */
public class BankAccount {
  private String accountNumber;
  private double balance;
  private String accountHolderName;

  /**
   * BankAccount Constructor
   * @param accountNumber Bank Account num
   * @param accountHolderName Account holder's name
   * @param initialBalance Initial balance for the account
   * @throws InvalidAccountException if the account number is null or not eight digits
   * @throws IllegalArgumentException if the name is null or blank, or the balance is negative
   */
  public BankAccount(String accountNumber, String accountHolderName, double initialBalance)
      throws InvalidAccountException, IllegalArgumentException {
    // Validate accountNumber
    if (accountNumber == null || !accountNumber.matches("\\d{8}")) {
      throw new InvalidAccountException("Account number must be exactly 8 digits");
    }

    // Validate accountHolderName
    if (accountHolderName == null || accountHolderName.trim().isEmpty()) {
      throw new IllegalArgumentException("Account holder name cannot be null or empty");
    }

    // Validate initialBalance
    if (initialBalance < 0) {
      throw new IllegalArgumentException("Initial balance cannot be negative");
    }

    // Initialize the account fields.
    this.accountNumber = accountNumber;
    this.accountHolderName = accountHolderName;
    this.balance = initialBalance;
  }

  /**
   * This Method deposits the specified amount into the bank account.
   * @param amount Amount to deposit
   * @throws IllegalArgumentException if the deposit amount is negative
   */
  public void deposit(double amount) throws IllegalArgumentException {
    // Reject negative amounts before changing the balance.
    if (amount < 0) {
      throw new IllegalArgumentException("Deposit amount cannot be negative");
    }

    // Apply the deposit.
    balance += amount;
  }

  /**
   * This Method withdraws the specified amount from the bank account.
   * @param amount Amount to withdraw
   * @throws IllegalArgumentException if the withdrawal amount is negative
   * @throws InsufficientFundsException if the amount exceeds the balance
   */
  public void withdraw(double amount) throws IllegalArgumentException, InsufficientFundsException {
    // Reject negative amounts before changing the balance.
    if (amount < 0) {
      throw new IllegalArgumentException("Withdrawal amount cannot be negative");
    }

    // Ensure the account has enough funds.
    if (amount > balance) {
      throw new InsufficientFundsException("Insufficient funds for withdrawal");
    }

    // Apply the withdrawal.
    balance -= amount;
  }

  /**
   * This Method returns the current balance of the bank account.
   * @return The current balance of the bank account.
   */
  public double getBalance() {
    return balance;
  }

  /**
   * Returns the account number of the bank account.
   * @return The account number of the bank account.
   */
  public String getAccountNumber() {
    return accountNumber;
  }

  /**
   * Returns the account holder's name of the bank account.
   * @return The account holder's name of the bank account.
   */
  public String getAccountHolderName() {
    return accountHolderName;
  }

  /**
   * Returns a string representation of the bank account.
   * @return the account number, holder name, and formatted balance
   * @see java.lang.Object#toString()
   */
  public String toString() {
    return String.format("Account: %s, Holder: %s, Balance: $%.2f",
        accountNumber, accountHolderName, balance);
  }
}
