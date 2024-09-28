import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;

public class Polynomial {
    //fields
    double [] coefficients;
    int [] exponents;

    //methods

    //no-arguement constructor
    public Polynomial() {
        coefficients = null;
        exponents = null;
    }

    //constructor
    public Polynomial(double [] coefficients, int [] exponents ){
        this.coefficients = coefficients;
        this.exponents = exponents;
    }

    //constructor that takes one arguement file
//public Polynomial(File file)
public Polynomial(File file) {
    try {
        Scanner input = new Scanner(file);
        ArrayList<Double> scannedCoefficients = new ArrayList<>();
        ArrayList<Integer> scannedExponents = new ArrayList<>();

        while (input.hasNextLine()) {
            String line = input.nextLine();
            String[] terms = line.split("(?=[+-])");  // Split by + or - signs, keeping the signs with the terms
            
            for (String term : terms) {
                term = term.trim();

                if (term.contains("x")) {
                    double coefficient;
                    int exponent;

                    if (term.equals("x")) {
                        // Case where the term is exactly "x", which means coefficient = 1 and exponent = 1
                        coefficient = 1.0;
                        exponent = 1;
                    } else if (term.equals("-x")) {
                        // Case where the term is "-x", which means coefficient = -1 and exponent = 1
                        coefficient = -1.0;
                        exponent = 1;
                    } else {
                        // Case where 'x' is present with coefficient and exponent
                        String[] parts = term.split("x");

                        // Parse coefficient (left side of 'x')
                        if (parts[0].isEmpty()) {
                            coefficient = 1.0;  // `x2` is equivalent to `1x2`
                        } else if (parts[0].equals("-")) {
                            coefficient = -1.0;  // `-x2` is equivalent to `-1x2`
                        } else {
                            coefficient = Double.parseDouble(parts[0]);  // Explicit coefficient
                        }

                        // Parse exponent (right side of 'x')
                        if (parts.length > 1 && !parts[1].isEmpty()) {
                            exponent = Integer.parseInt(parts[1]);  // Explicit exponent (like x2)
                        } else {
                            exponent = 1;  // If no number after 'x', it's `x^1`
                        }
                    }

                    scannedCoefficients.add(coefficient);
                    scannedExponents.add(exponent);

                } else {
                    //constant term (no 'x')
                    double coefficient = Double.parseDouble(term);
                    scannedCoefficients.add(coefficient);
                    scannedExponents.add(0);  // Constant term has exponent 0
                }
            }
        }
        input.close();

        Double [] finalCoefficents  = scannedCoefficients.toArray(new Double[0]);
        Integer [] finalExponents = scannedExponents.toArray(new Integer[0]);

        // Convert Double[] and Integer[] to double[] and int[]
        double[] primitiveFinalCoefficients = new double[finalCoefficents.length];
        for (int i = 0; i < finalCoefficents.length; i++) {
            primitiveFinalCoefficients[i] = finalCoefficents[i];
        }

        int[] primitiveFinalExponents = new int[finalExponents.length];
        for (int i = 0; i < finalExponents.length; i++) {
            primitiveFinalExponents[i] = finalExponents[i];
        }
        
        this.coefficients = primitiveFinalCoefficients;
        this.exponents = primitiveFinalExponents;

    } catch (FileNotFoundException e) {
        e.printStackTrace();
    }
}

 // Add method for adding two polynomials
    public Polynomial add(Polynomial polynomial) {
        ArrayList<Double> newCoefficients = new ArrayList<>();
        ArrayList<Integer> newExponents = new ArrayList<>();

        // Add comon terms from calling object polynomial and the arguemebt polynomial
        for (int i = 0; i < coefficients.length; i++) {
            boolean added = false;
            for (int j = 0; j < polynomial.coefficients.length; j++) {
                // If the exponents are the same add the coefficients
                if (this.exponents[i] == polynomial.exponents[j]) {
                    double newCoefficient = coefficients[i] + polynomial.coefficients[j];
                    // If the new coefficient is not zero add it to the new polynomial
                    if (newCoefficient != 0) {
                        newCoefficients.add(newCoefficient);
                        newExponents.add(this.exponents[i]);
                    }
                    added = true;
                    break;
                }
            }
            // If the term was not added add it to the new polynomial
            if (!added) {
                newCoefficients.add(this.coefficients[i]);
                newExponents.add(this.exponents[i]);
            }
        }

        // Add terms from the other polynomial that were not in the calling object
        for (int j = 0; j < polynomial.coefficients.length; j++) {
            boolean exists = false;
            for (int i = 0; i < coefficients.length; i++) {
                if (polynomial.exponents[j] == exponents[i]) {
                    exists = true;
                    break;
                }
            }
            // If the term was not in the calling object add it to the new polynomial
            if (!exists) {
                newCoefficients.add(polynomial.coefficients[j]);
                newExponents.add(polynomial.exponents[j]);
            }
        }

        // Convert lists to arrays
        Double[] summedCoefficients = newCoefficients.toArray(new Double[0]);
        Integer[] summedExponents = newExponents.toArray(new Integer[0]);

        // Convert Double[] and Integer[] to double[] and int[]
        double[] primitiveSummedCoefficients = new double[summedCoefficients.length];
        for (int i = 0; i < summedCoefficients.length; i++) {
            primitiveSummedCoefficients[i] = summedCoefficients[i];
        }

        int[] primitiveSummedExponents = new int[summedExponents.length];
        for (int i = 0; i < summedExponents.length; i++) {
            primitiveSummedExponents[i] = summedExponents[i];
        }

        return new Polynomial(primitiveSummedCoefficients, primitiveSummedExponents);
    }

    //method evaluate
    public double evaluate(double x){
        double value = 0;
        for(int i =0; i< coefficients.length; i++){
            value = value + coefficients[i] * Math.pow(x, exponents[i]);
        }

        return value;
    }

    //method hasRoot
    public boolean hasRoot(double x){
        if(this.evaluate(x) == 0){
        return true;
    }
    return false;
    }
    

    public Polynomial multiply(Polynomial polynomial){
        ArrayList<Double> multipliedCoefficents = new ArrayList<>();
        ArrayList<Integer> multipliedExponents = new ArrayList<>(); 
        ArrayList<Double> simplifiedCoefficents = new ArrayList<>();
        ArrayList<Integer> simplifiedExponents = new ArrayList<>(); 

        

        for(int i = 0; i< coefficients.length; i++){
            for(int j = 0; j<polynomial.coefficients.length; j++){
                double newCoefficient = coefficients[i] * polynomial.coefficients[j];
                int newExponent = exponents[i] + polynomial.exponents[j];
                multipliedCoefficents.add(newCoefficient);
                multipliedExponents.add(newExponent);
            }
        }
        //Simplify the redundent exponents
        for(int i = 0; i< multipliedExponents.size(); i ++){
            if(!simplifiedExponents.contains(multipliedExponents.get(i))) {
                simplifiedExponents.add(multipliedExponents.get(i));
            }
        }

        //Simpifly the polynomial.
        for(int i = 0; i<  simplifiedExponents.size(); i++){
            double newCoefficient = 0;
            for(int j = 0; j<  multipliedExponents.size(); j++){
                if(simplifiedExponents.get(i) ==  multipliedExponents.get(j)){
                    newCoefficient += multipliedCoefficents.get(j);
                }
            }
            simplifiedCoefficents.add(newCoefficient);
        }

        // Convert lists to arrays
        Double[] finalCoefficients = simplifiedCoefficents.toArray(new Double[0]);
        Integer[] finalExponents = simplifiedExponents.toArray(new Integer[0]);

        // Convert Double[] and Integer[] to double[] and int[]
        double[] primitiveSummedCoefficients = new double[finalCoefficients.length];
        for (int i = 0; i < finalCoefficients.length; i++) {
            primitiveSummedCoefficients[i] = finalCoefficients[i];
        }

        int[] primitiveSummedExponents = new int[finalExponents.length];
        for (int i = 0; i < finalExponents.length; i++) {
            primitiveSummedExponents[i] = finalExponents[i];
        }

        return new Polynomial(primitiveSummedCoefficients, primitiveSummedExponents);
    }

    public void saveToFile(String filename) {
        try {
            PrintStream output = new PrintStream(filename);
            for (int i = 0; i < coefficients.length; i++) {
                if (i == 0) {
                    output.print(coefficients[i]);
                    if (exponents[i] != 0) {
                        output.print("x");
                        if (exponents[i] != 1) {
                            output.print(exponents[i]);
                        }
                    }
                } else {
                    if (coefficients[i] > 0) {
                        output.print("+");
                        output.print(coefficients[i]);
                    } else {
                        output.print(coefficients[i]);  // The negative sign is already part of the coefficient
                    }
    
                    if (exponents[i] != 0) {
                        output.print("x");
                        if (exponents[i] != 1) {
                            output.print(exponents[i]);
                        }
                    }
                }
            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    


}

