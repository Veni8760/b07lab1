public class Driver {
    public static void main(String [] args) {
    Polynomial p = new Polynomial();
    System.out.println(p.evaluate(3));
    double [] c1 = {6,0,0,5};
    Polynomial p1 = new Polynomial(c1);
    double [] c2 = {0,-2,0,0,-9};
    Polynomial p2 = new Polynomial(c2);
    Polynomial s = p1.add(p2);
    System.out.println("s(0.1) = " + s.evaluate(0.1));
    if(s.hasRoot(1))
    System.out.println("1 is a root of s");
    else
    System.out.println("1 is not a root of s");
    double[] coeffs1 = {0};
    double[] coeffs2 = {0};
    Polynomial p3 = new Polynomial(coeffs1);
    Polynomial p4 = new Polynomial(coeffs2);
    Polynomial result = p3.add(p4);  // Should still be a zero polynomial
    System.out.println(result.evaluate(0));  // Should print 0.
    double[] coeffs3 = {1, 2};       // Represents 1 + 2x
    double[] coeffs4 = {3, 4, 5, 6}; // Represents 3 + 4x + 5x^2 + 6x^3
    Polynomial p5 = new Polynomial(coeffs3);
    Polynomial p6 = new Polynomial(coeffs4);
    Polynomial result2 = p5.add(p6);  // Result should be 4 + 6x + 5x^2 + 6x^3
    for (int i = 0; i < result2.coefficients.length; i++) {
        System.out.println(result2.coefficients[i]);
    }    
    System.out.println(result2.evaluate(1));  // Should print 21.0

    }

}
