/*
 * Author: Arnav Joginapally
 * Email: joginapally@wisc.edu
 * Course: CS 300, Fall 2026
 * Assignment: Banking System, Program 3
 * Citations: None
 */

/**
 * This contains test cases to comprehensively test the BankAccount and BankingSystem
 * classes.
 * @author Arnav Joginapally
 */
public class BankingSystemTester {
  /**
   * Runs all tests and prints whether they passed.
   * @param args command-line arguments
   */
  public static void main(String[] args) {
    if (allTests()) {
      System.out.println("All tests passed.");
    } else {
      System.out.println("At least one test failed.");
    }
  }

  /**
   * Runs every test method.
   * @return true if all tests pass, false otherwise
   */
  public static boolean allTests() {
    boolean allPassed = true;

    // BankAccount constructor tests
    allPassed &= testBankAccountConstructorValid();
    allPassed &= testBankAccountConstructorInvalidAccountNumber();
    allPassed &= testBankAccountConstructorNullName();
    allPassed &= testBankAccountConstructorEmptyName();
    allPassed &= testBankAccountConstructorNegativeBalance();

    // BankAccount deposit tests
    allPassed &= testDepositValid();
    allPassed &= testDepositNegativeAmount();

    // BankAccount withdraw tests
    allPassed &= testWithdrawValid();
    allPassed &= testWithdrawNegativeAmount();
    allPassed &= testWithdrawInsufficientFunds();

    // BankingSystem createAccount tests
    allPassed &= testCreateAccountValid();
    allPassed &= testCreateAccountDuplicate();

    // BankingSystem findAccount tests
    allPassed &= testFindAccountValid();
    allPassed &= testFindAccountNotFound();

    // BankingSystem transferMoney tests
    allPassed &= testTransferMoneyValid();
    allPassed &= testTransferMoneyNegativeAmount();
    allPassed &= testTransferMoneySameAccount();
    allPassed &= testTransferMoneyFromAccountNotFound();
    allPassed &= testTransferMoneyToAccountNotFound();
    allPassed &= testTransferMoneyInsufficientFunds();

    // BankingSystem displayAccountInfo tests
    allPassed &= testDisplayAccountInfoValid();
    allPassed &= testDisplayAccountInfoNotFound();

    return allPassed;
  }

  // =================== BankAccount Constructor Tests ===================

  /**
   * Tests that a valid account can be constructed.
   * @return true if the expected behavior occurs, false otherwise
   */
  public static boolean testBankAccountConstructorValid() {
    System.out.print("testBankAccountConstructorValid ");
    // Exercise the scenario and check the result or expected exception.
    try {
      new BankAccount("12345678", "John Doe", 100.0);
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  /**
   * Tests that an invalid account number is rejected.
   * @return true if the expected behavior occurs, false otherwise
   */
  public static boolean testBankAccountConstructorInvalidAccountNumber() {
    System.out.print("testBankAccountConstructorInvalidAccountNumber ");

    // Exercise the scenario and check the result or expected exception.
    try {
      BankAccount account = new BankAccount("1234", "John Doe", 100.0);
      System.out.println("FAIL");
      return false;
    } catch (InvalidAccountException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  /**
   * Tests that a null account holder name is rejected.
   * @return true if the expected behavior occurs, false otherwise
   */
  public static boolean testBankAccountConstructorNullName() {
    System.out.print("testBankAccountConstructorNullName ");
    // Exercise the scenario and check the result or expected exception.
    try {
      new BankAccount("12345678", null, 100.0);
      System.out.println("FAIL");
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  /**
   * Tests that an empty account holder name is rejected.
   * @return true if the expected behavior occurs, false otherwise
   */
  public static boolean testBankAccountConstructorEmptyName() {
    System.out.print("testBankAccountConstructorEmptyName ");

    // Exercise the scenario and check the result or expected exception.
    try {
      new BankAccount("12345678", "", 100.0);
      System.out.println("FAIL");
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  /**
   * Tests that a negative initial balance is rejected.
   * @return true if the expected behavior occurs, false otherwise
   */
  public static boolean testBankAccountConstructorNegativeBalance() {
    System.out.print("testBankAccountConstructorNegativeBalance ");

    // Exercise the scenario and check the result or expected exception.
    try {
      new BankAccount("12345678", "John Doe", -100.0);
      System.out.println("FAIL");
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  // =================== BankAccount Deposit Tests ===================

  /**
   * Tests that a valid deposit increases the balance.
   * @return true if the expected behavior occurs, false otherwise
   */
  public static boolean testDepositValid() {
    System.out.print("testDepositValid ");
    // Exercise the scenario and check the result or expected exception.
    try {
      BankAccount account = new BankAccount("12345678", "John Doe", 100.0);
      double originalBalance = account.getBalance();
      account.deposit(50.0);
      if (account.getBalance() == originalBalance + 50.0) {
        System.out.println("PASS");
        return true;
      } else {
        System.out.println("FAIL");
        return false;
      }
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  /**
   * Tests that a negative deposit is rejected.
   * @return true if the expected behavior occurs, false otherwise
   */
  public static boolean testDepositNegativeAmount() {
    System.out.print("testDepositNegativeAmount ");

    // Exercise the scenario and check the result or expected exception.
    try {
      BankAccount account = new BankAccount("12345678", "John Doe", 100.0);
      account.deposit(-50.0);
      System.out.println("FAIL");
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  // =================== BankAccount Withdraw Tests ===================

  /**
   * Tests that a valid withdrawal decreases the balance.
   * @return true if the expected behavior occurs, false otherwise
   */
  public static boolean testWithdrawValid() {
    System.out.print("testWithdrawValid ");
    // Exercise the scenario and check the result or expected exception.
    try {
      BankAccount account = new BankAccount("12345678", "John Doe", 100.0);
      double originalBalance = account.getBalance();
      account.withdraw(30.0);
      if (account.getBalance() == originalBalance - 30.0) {
        System.out.println("PASS");
        return true;
      } else {
        System.out.println("FAIL");
        return false;
      }
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  /**
   * Tests that a negative withdrawal is rejected.
   * @return true if the expected behavior occurs, false otherwise
   */
  public static boolean testWithdrawNegativeAmount() {
    System.out.print("testWithdrawNegativeAmount ");

    // Exercise the scenario and check the result or expected exception.
    try {
      BankAccount account = new BankAccount("12345678", "John Doe", 100.0);
      account.withdraw(-30.0);
      System.out.println("FAIL");
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  /**
   * Tests that a withdrawal exceeding the balance is rejected.
   * @return true if the expected behavior occurs, false otherwise
   */
  public static boolean testWithdrawInsufficientFunds() {
    System.out.print("testWithdrawInsufficientFunds ");
    // Exercise the scenario and check the result or expected exception.
    try {
      BankAccount account = new BankAccount("12345678", "John Doe", 100.0);
      account.withdraw(150.0);
      System.out.println("FAIL");
      return false;
    } catch (InsufficientFundsException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  // =============== BankingSystem CreateAccount Tests ===============

  /**
   * Tests that a valid account is added to the system.
   * @return true if the expected behavior occurs, false otherwise
   */
  public static boolean testCreateAccountValid() {
    System.out.print("testCreateAccountValid ");
    // Exercise the scenario and check the result or expected exception.
    try {
      BankingSystem system = new BankingSystem();
      system.createAccount("12345678", "Jane Smith", 200.0);
      BankAccount account = system.findAccount("12345678");
      if (account != null && account.getBalance() == 200.0) {
        System.out.println("PASS");
        return true;
      } else {
        System.out.println("FAIL");
        return false;
      }
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  /**
   * Tests that a duplicate account number is rejected.
   * @return true if the expected behavior occurs, false otherwise
   */
  public static boolean testCreateAccountDuplicate() {
    System.out.print("testCreateAccountDuplicate ");

    // Exercise the scenario and check the result or expected exception.
    try {
      BankingSystem system = new BankingSystem();
      system.createAccount("12345678", "Jane Smith", 200.0);
      system.createAccount("12345678", "John Doe", 150.0);
      System.out.println("FAIL");
      return false;
    } catch (InvalidAccountException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  // =============== BankingSystem FindAccount Tests ===============

  /**
   * Tests that an existing account can be found.
   * @return true if the expected behavior occurs, false otherwise
   */
  public static boolean testFindAccountValid() {
    System.out.print("testFindAccountValid ");
    // Exercise the scenario and check the result or expected exception.
    try {
      BankingSystem system = new BankingSystem();
      system.createAccount("12345678", "Jane Smith", 200.0);
      BankAccount account = system.findAccount("12345678");
      if (account != null && account.getAccountNumber().equals("12345678")) {
        System.out.println("PASS");
        return true;
      } else {
        System.out.println("FAIL");
        return false;
      }
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  /**
   * Tests that a missing account causes the expected exception.
   * @return true if the expected behavior occurs, false otherwise
   */
  public static boolean testFindAccountNotFound() {
    System.out.print("testFindAccountNotFound ");

    // Exercise the scenario and check the result or expected exception.
    try {
      BankingSystem system = new BankingSystem();
      system.createAccount("12345678", "Jane Smith", 200.0);
      BankAccount account = system.findAccount("87654321");
      System.out.println("FAIL");
      return false;
    } catch (InvalidAccountException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  // =============== BankingSystem TransferMoney Tests ===============

  /**
   * Tests that a valid transfer updates both balances.
   * @return true if the expected behavior occurs, false otherwise
   */
  public static boolean testTransferMoneyValid() {
    System.out.print("testTransferMoneyValid ");
    // Exercise the scenario and check the result or expected exception.
    try {
      BankingSystem system = new BankingSystem();
      system.createAccount("12345678", "Alice", 300.0);
      system.createAccount("87654321", "Bob", 100.0);

      system.transferMoney("12345678", "87654321", 50.0);

      BankAccount fromAccount = system.findAccount("12345678");
      BankAccount toAccount = system.findAccount("87654321");

      if (fromAccount.getBalance() == 250.0 && toAccount.getBalance() == 150.0) {
        System.out.println("PASS");
        return true;
      } else {
        System.out.println("FAIL");
        return false;
      }
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  /**
   * Tests that a negative transfer is rejected.
   * @return true if the expected behavior occurs, false otherwise
   */
  public static boolean testTransferMoneyNegativeAmount() {
    System.out.print("testTransferMoneyNegativeAmount ");

    // Exercise the scenario and check the result or expected exception.
    try {
      BankingSystem system = new BankingSystem();
      system.createAccount("12345678", "Alice", 300.0);
      system.createAccount("87654321", "Bob", 100.0);

      system.transferMoney("12345678", "87654321", -50.0);
      System.out.println("FAIL");
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  /**
   * Tests that a transfer to the same account is rejected.
   * @return true if the expected behavior occurs, false otherwise
   */
  public static boolean testTransferMoneySameAccount() {
    System.out.print("testTransferMoneySameAccount ");
    // Exercise the scenario and check the result or expected exception.
    try {
      BankingSystem system = new BankingSystem();
      system.createAccount("12345678", "Alice", 300.0);

      system.transferMoney("12345678", "12345678", 50.0);
      System.out.println("FAIL");
      return false;
    } catch (IllegalArgumentException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  /**
   * Tests that a missing source account is rejected.
   * @return true if the expected behavior occurs, false otherwise
   */
  public static boolean testTransferMoneyFromAccountNotFound() {
    System.out.print("testTransferMoneyFromAccountNotFound ");
    // Exercise the scenario and check the result or expected exception.
    try {
      BankingSystem system = new BankingSystem();
      system.createAccount("87654321", "Bob", 100.0);

      system.transferMoney("99999999", "87654321", 50.0);
      System.out.println("FAIL");
      return false;
    } catch (InvalidAccountException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  /**
   * Tests that a missing destination account is rejected.
   * @return true if the expected behavior occurs, false otherwise
   */
  public static boolean testTransferMoneyToAccountNotFound() {
    System.out.print("testTransferMoneyToAccountNotFound ");
    // Exercise the scenario and check the result or expected exception.
    try {
      BankingSystem system = new BankingSystem();
      system.createAccount("12345678", "Alice", 300.0);

      system.transferMoney("12345678", "99999999", 50.0);
      System.out.println("FAIL");
      return false;
    } catch (InvalidAccountException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  /**
   * Tests that a transfer exceeding the source balance is rejected.
   * @return true if the expected behavior occurs, false otherwise
   */
  public static boolean testTransferMoneyInsufficientFunds() {
    System.out.print("testTransferMoneyInsufficientFunds ");

    // Exercise the scenario and check the result or expected exception.
    try {
      BankingSystem system = new BankingSystem();
      system.createAccount("12345678", "Alice", 300.0);
      system.createAccount("87654321", "Bob", 100.0);

      system.transferMoney("12345678", "87654321", 500.0);
      System.out.println("FAIL");
      return false;
    } catch (InsufficientFundsException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  // =============== BankingSystem DisplayAccountInfo Tests ===============

  /**
   * Tests that an existing account can be displayed.
   * @return true if the expected behavior occurs, false otherwise
   */
  public static boolean testDisplayAccountInfoValid() {
    System.out.print("testDisplayAccountInfoValid ");
    // Exercise the scenario and check the result or expected exception.
    try {
      BankingSystem system = new BankingSystem();
      system.createAccount("12345678", "Test User", 500.0);
      system.displayAccountInfo("12345678"); // Should not throw exception
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }

  /**
   * Tests that displaying a missing account causes the expected exception.
   * @return true if the expected behavior occurs, false otherwise
   */
  public static boolean testDisplayAccountInfoNotFound() {
    System.out.print("testDisplayAccountInfoNotFound ");

    // Exercise the scenario and check the result or expected exception.
    try {
      BankingSystem system = new BankingSystem();
      system.displayAccountInfo("99999999");
      System.out.println("FAIL");
      return false;
    } catch (InvalidAccountException e) {
      System.out.println("PASS");
      return true;
    } catch (Exception e) {
      System.out.println("FAIL");
      return false;
    }
  }
}
