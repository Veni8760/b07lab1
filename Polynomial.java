import java.util.HashMap;


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

    //method add
    public Polynomial add(Polynomial polynomial){
        int newMaxLength = Math.max(coefficients.length, polynomial.coefficients.length);

        double [] summedPolynomial = new double[newMaxLength];
        int [] summedexponents = new int[newMaxLength];

        if(coefficients.length < polynomial.coefficients.length){
            for(int i = 0; i< polynomial.coefficients.length; i++){
                boolean addedToArr = false;
                for(int j = 0; j< coefficients.length; i++){
                    if(polynomial.exponents[i] == exponents[j]){
                        if(coefficients[i]+ polynomial.coefficients[j] != 0){
                            summedPolynomial[i] = coefficients[i] + polynomial.coefficients[j];
                            addedToArr = true;
                        }
                        else{
                            addedToArr = true;
                        }
        
                    }
                }
                if(addedToArr == false){
                    summedexponents[i] = polynomial.exponents[i];
                    summedPolynomial[i] = polynomial.coefficients[i];
                }
                
            }

            for(int i = 0; i< coefficients.length; i++){
                boolean x = true;
                for(int j = 0; j< summedexponents.length; i++){
                    if(exponents[i] == summedexponents[j]){
                        x = false;
                    }


                }
                summedPolynomial
            }
        }
        else{
            for(int i = 0; i< coefficients.length; i++){
                boolean addedToArr = false;
                for(int j = 0; j< polynomial.coefficients.length; i++){
                    if(exponents[i] == polynomial.exponents[j]){
                        if(coefficients[i]+ polynomial.coefficients[j] != 0){
                            summedPolynomial[i] = coefficients[i] + polynomial.coefficients[j];
                            addedToArr = true;
                        }
                        else{
                            addedToArr = true;
                        }
                    }
                }
                if(addedToArr == false){
                    summedPolynomial[i] = coefficients[i];
                }
                
            }

            for(int i = 0;)
        }


        return new Polynomial(summedPolynomial, summedexponents);
        
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

