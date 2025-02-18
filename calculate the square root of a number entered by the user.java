import java.util.Scanner;

public class SquareRootCalculator {

    public static void main(String[] args) {
        // Create a scanner object for input
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a number: ");
        String input = scanner.nextLine();

        try {
            // Try to convert the input to a double
            double number = Double.parseDouble(input);

            // Check if the number is negative
            if (number < 0) {
                throw new ArithmeticException("Cannot calculate the square root of a negative number.");
            }

            // Calculate and display the square root
            double squareRoot = Math.sqrt(number);
            System.out.println("The square root of " + number + " is: " + squareRoot);

        } catch (NumberFormatException e) {
            // Handle the case where the input is not a number
            System.out.println("Error: Invalid input. Please enter a valid number.");
        } catch (ArithmeticException e) {
            // Handle the case where the number is negative
            System.out.println("Error: " + e.getMessage());
        } finally {
            // Close the scanner to prevent resource leak
            scanner.close();
        }
    }
}
