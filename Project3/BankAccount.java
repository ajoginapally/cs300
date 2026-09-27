public class BankAccount {
  private String accountNumber;
  private double balance;
  private String accountHolderName;

  // Constructor - ADD: Validation and exception throwing
  public BankAccount(
    String accountNumber,
    String accountHolderName,
    double initialBalance
  ) throws InvalidAccountException, IllegalArgumentException {
    // TODO: Validate accountNumber is exactly 8 digits (8 numbers in String)(throw
    // InvalidAccountException if not)
    // TODO: Validate accountHolderName is not null or empty (throw
    // IllegalArgumentException if invalid)
    // TODO: Validate initialBalance is not negative (throw IllegalArgumentException
    // if negative)

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

    this.accountNumber = accountNumber;
    this.accountHolderName = accountHolderName;
    this.balance = initialBalance;
  }

  // ADD: throws declaration and validation
  public void deposit(double amount) throws IllegalArgumentException {
    if (amount < 0) {
      throw new IllegalArgumentException("Deposit amount cannot be negative");
    }
    balance += amount;
  }

  // ADD: throws declaration and validation
  public void withdraw(double amount) throws IllegalArgumentException, InsufficientFundsException {
    if (amount < 0) {
      throw new IllegalArgumentException("Withdrawal amount cannot be negative");
    }
    if (amount > balance) {
      throw new InsufficientFundsException("Insufficient funds for withdrawal");
    }
    balance -= amount;
  }

  public double getBalance() {
    return balance;
  }

  public String getAccountNumber() {
    return accountNumber;
  }

  public String getAccountHolderName() {
    return accountHolderName;
  }

  public String toString() {
    return String.format(
      "Account: %s, Holder: %s, Balance: $%.2f",
      accountNumber,
      accountHolderName,
      balance
    );
  }
}