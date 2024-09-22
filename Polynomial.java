public class Polynomial {
    //fields
    double [] coefficients;

    //methods

    //no-arguement constructor
    public Polynomial() {
        coefficients = new double[1];
        coefficients[0] = 0;
    }
    

    //constructor
    public Polynomial(double[] coefficients) {
        this.coefficients = coefficients;
    }
    

    //method add
    public Polynomial add(Polynomial polynomial){
        int newMaxLength = Math.max(coefficients.length, polynomial.coefficients.length);
        int minLength = Math.min(coefficients.length, polynomial.coefficients.length);
        double [] summedPolynomial = new double[newMaxLength];
    
        for (int i =0; i < minLength; i++){
            summedPolynomial[i] = coefficients[i] + polynomial.coefficients[i]; 
        }
        for(int i =minLength; i< newMaxLength; i++){
            if (i < coefficients.length) {
                summedPolynomial[i] = coefficients[i];
            } else {
                summedPolynomial[i] = polynomial.coefficients[i];
            }
        }
        return new Polynomial(summedPolynomial);
    }

    //method evaluate
    public double evaluate(double x){
        double value = 0;
        for(int i =0; i< coefficients.length; i++){
            value = value + coefficients[i] * Math.pow(x, i);
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
    
}

