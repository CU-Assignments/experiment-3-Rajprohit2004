import java.util.Scanner;

class InvalidPinException extends Exception {
    public InvalidPinException(String message) {
        super(message);
    }
}

class InsufficientBalanceException extends Exception {
    public InsufficientBalanceException(String message) {
        super(message);
    }
}

class ATM {
    private String pin = "1234"; // Correct PIN for the user
    private double balance = 5000; // Initial balance

    // Method to simulate withdrawal
    public void withdraw(String enteredPin, double withdrawalAmount) throws InvalidPinException, InsufficientBalanceException {
        // Check if entered PIN is correct
        if (!enteredPin.equals(pin)) {
            throw new InvalidPinException("Error: Invalid PIN entered.");
        }

        // Check if the balance is sufficient for the withdrawal
        if (withdrawalAmount > balance) {
            throw new InsufficientBalanceException("Error: Insufficient balance. Current Balance: " + balance);
        }

        // If all checks pass, proceed with the withdrawal
        balance -= withdrawalAmount;
        System.out.println("Withdrawal Successful. Remaining Balance: " + balance);
    }

    // Method to display the current balance
    public void displayBalance() {
        System.out.println("Current Balance: " + balance);
    }
}

public class ATMWithdrawalSystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ATM atm = new ATM(); // Create ATM instance

        // Ask user for PIN
        System.out.print("Enter PIN: ");
        String enteredPin = scanner.nextLine();

        // Ask for withdrawal amount
        System.out.print("Enter withdrawal amount: ");
        double withdrawalAmount = scanner.nextDouble();

        // Display the balance before any operation
        System.out.println("Before Transaction:");
        atm.displayBalance();

        try {
            // Try to perform the withdrawal
            atm.withdraw(enteredPin, withdrawalAmount);
        } catch (InvalidPinException e) {
            System.out.println(e.getMessage());
        } catch (InsufficientBalanceException e) {
            System.out.println(e.getMessage());
        } finally {
            // Always show the remaining balance
            System.out.println("After Transaction:");
            atm.displayBalance();
        }

        // Close the scanner to avoid resource leaks
        scanner.close();
    }
}

