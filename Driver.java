import java.io.File;
import java.io.FileNotFoundException;

import java.util.Scanner;

public class Driver {
    public static void main(String[] args) {
        // Test 1: Simple polynomial evaluation
        double[] c1 = {6, 5}; // 6 + 5x^3
        int[] e1 = {0, 3};    // Corresponding exponents
        Polynomial p1 = new Polynomial(c1, e1);
        System.out.println("p1(2) = " + p1.evaluate(2));  // Expected output: 46.0 (6 + 5*8)

        // Test 2: Another polynomial evaluation
        double[] c2 = {-6, -5}; // -6 - 5x^3 (opposite of p1)
        int[] e2 = {0, 3};      // Corresponding exponents
        Polynomial p2 = new Polynomial(c2, e2);
        System.out.println("p2(1) = " + p2.evaluate(1));  // Expected output: -11.0 (-6 - 5)

        // Test 3: Adding two polynomials where terms cancel out (zero coefficients)
        Polynomial s = p1.add(p2); // Result should be 0 (terms cancel out)
        System.out.println("s(1) = " + s.evaluate(1));  // Expected output: 0.0
        System.out.println("s size = " + s.coefficients.length);  // Expected output: 0 (no terms in the result)

        // Test 4: Adding polynomials with some terms canceling out but some non-zero terms
        double[] c3 = {1, 2, 3}; // 1 + 2x + 3x^2
        int[] e3 = {0, 1, 2};    // Corresponding exponents
        Polynomial p3 = new Polynomial(c3, e3);
        
        double[] c4 = {-1, -2, 4}; // -1 - 2x + 4x^2 (cancels first two terms of p3)
        int[] e4 = {0, 1, 2};      // Corresponding exponents
        Polynomial p4 = new Polynomial(c4, e4);

        Polynomial s2 = p3.add(p4); // Result: 7x^2 (all other terms cancel out)
        System.out.println("s2(3) = " + s2.evaluate(3));  // Expected output: 63.0
        System.out.println("s2 size = " + s2.coefficients.length);  // Expected output: 1 (only one term left)

        // Test case 5: Multiply two 3rd-degree polynomials
        double[] coeffsA = {2, -1, 3, 4}; // Polynomial: 2x^3 - x^2 + 3x + 4
        int[] expsA = {3, 2, 1, 0};       // Exponents for Polynomial A: cubic, quadratic, linear, constant

        double[] coeffsB = {1, 5, 2};     // Polynomial: x^2 + 5x + 2
        int[] expsB = {2, 1, 0};          // Exponents for Polynomial B: quadratic, linear, constant

        Polynomial pA = new Polynomial(coeffsA, expsA);
        Polynomial pB = new Polynomial(coeffsB, expsB);

        Polynomial result = pA.multiply(pB);
        System.out.println("Test 5 (2x^3 - x^2 + 3x + 4) * (x^2 + 5x + 2): ");
        printPolynomial(result);

        // Test case 6: Multiply a 4th-degree polynomial with a 2nd-degree polynomial
        double[] coeffsC = {1, 2, 1, 5};  // Polynomial: x^4 + 2x^3 + x^2 + 5
        int[] expsC = {4, 3, 2, 0};       // Exponents for Polynomial C: quartic, cubic, quadratic, constant

        double[] coeffsD = {3, 6};        // Polynomial: 3x^2 + 6
        int[] expsD = {2, 0};             // Exponents for Polynomial D: quadratic, constant

        Polynomial pC = new Polynomial(coeffsC, expsC);
        Polynomial pD = new Polynomial(coeffsD, expsD);

        result = pC.multiply(pD);
        System.out.println("Test 6 (x^4 + 2x^3 + x^2 + 5) * (3x^2 + 6): ");
        printPolynomial(result);

        // Test case 7: Multiply two polynomials with five terms each
        double[] coeffsE = {3, 7, 2, 1, 4}; // Polynomial: 3x^4 + 7x^3 + 2x^2 + x + 4
        int[] expsE = {4, 3, 2, 1, 0};      // Exponents for Polynomial E: quartic, cubic, quadratic, linear, constant

        double[] coeffsF = {2, 3, 5, 1};    // Polynomial: 2x^3 + 3x^2 + 5x + 1
        int[] expsF = {3, 2, 1, 0};         // Exponents for Polynomial F: cubic, quadratic, linear, constant

        Polynomial pE = new Polynomial(coeffsE, expsE);
        Polynomial pF = new Polynomial(coeffsF, expsF);

        result = pE.multiply(pF);
        System.out.println("Test 7 (3x^4 + 7x^3 + 2x^2 + x + 4) * (2x^3 + 3x^2 + 5x + 1): ");
        printPolynomial(result);

        // Test case 8: Multiply two polynomials with large coefficients
        double[] coeffsG = {100, -50, 25};  // Polynomial: 100x^2 - 50x + 25
        int[] expsG = {2, 1, 0};            // Exponents for Polynomial G: quadratic, linear, constant

        double[] coeffsH = {10, -20};       // Polynomial: 10x - 20
        int[] expsH = {1, 0};               // Exponents for Polynomial H: linear, constant

        Polynomial pG = new Polynomial(coeffsG, expsG);
        Polynomial pH = new Polynomial(coeffsH, expsH);

        result = pG.multiply(pH);
        System.out.println("Test 8 (100x^2 - 50x + 25) * (10x - 20): ");
        printPolynomial(result);

        // Test case 9: Complex polynomials with mixed exponents and large coefficients
        double[] coeffsQ = {15, -3, 4, 20, -7, 9};  // Polynomial: 15x^10 - 3x^5 + 4x^7 + 20x^2 - 7x^3 + 9
        int[] expsQ = {10, 5, 7, 2, 3, 0};         // Exponents are mixed

        double[] coeffsR = {8, 2, -5, 12, 6, 1};   // Polynomial: 8x^6 + 2x^8 - 5x^3 + 12x^4 + 6x + 1
        int[] expsR = {6, 8, 3, 4, 1, 0};          // Exponents are mixed up

        Polynomial pQ = new Polynomial(coeffsQ, expsQ);
        Polynomial pR = new Polynomial(coeffsR, expsR);

        Polynomial result2 = pQ.multiply(pR);
        System.out.println("Test 9 (15x^10 - 3x^5 + 4x^7 + 20x^2 - 7x^3 + 9) * (8x^6 + 2x^8 - 5x^3 + 12x^4 + 6x + 1): ");
        printPolynomial(result2);

        // Define some test coefficients and exponents
        double[] coefficients = {6, -5, 7};
        int[] exponents = {0, 1, 7};

        // Create a Polynomial object (assuming you have an appropriate constructor)
        Polynomial polynomial = new Polynomial(coefficients, exponents);

        // Call the saveToFile method to save the polynomial to a file
        polynomial.saveToFile("polynomial_output.txt");

        // Confirm by reading and printing the content of the file (optional)
        try {
            File file = new File("polynomial_output.txt");
            Scanner scanner = new Scanner(file);
            while (scanner.hasNextLine()) {
                System.out.println(scanner.nextLine());
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }       
    
        //Now we will see if the constructor works 
        // Create a Polynomial object from a file
        File newFile = new File("polynomial_output.txt");
        Polynomial n = new Polynomial(newFile);
        System.out.println("Polynomial from file:");
        printPolynomial(n);

    }

     // Helper method to print the polynomial
     private static void printPolynomial(Polynomial p) {
        double[] bcoefficients = p.coefficients;
        int[] bexponents = p.exponents;

        for (int i = 0; i < bcoefficients.length; i++) {
            if (i > 0 && bcoefficients[i] > 0) {
                System.out.print("+");
            } else if (i > 0 && bcoefficients[i] < 0) {
                System.out.print("-");
                bcoefficients[i] = -bcoefficients[i]; // Make the coefficient positive for printing
            }
            System.out.print(bcoefficients[i] + "x^" + bexponents[i]);
        }
        System.out.println();
    }
    
}
