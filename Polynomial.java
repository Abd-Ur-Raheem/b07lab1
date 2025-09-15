public class Polynomial {
    private double [] coefficients;
    
    //point 2: zero poly constructor
    public Polynomial() {
         coefficients = new double[1];
         coefficients[0] = 0;
        }
        
    //point 3: double array constructor
    public Polynomial(double [] coef) {
            coefficients = new double[coef.length];
            for (int i=0; i<coef.length; i++) {
                coefficients[i] = coef[i];
                }
        }
        
    //point 4: method to add two polys
    public Polynomial add(Polynomial Arg){
        int maxlen = Math.max(coefficients.length, Arg.coefficients.length);
        double [] result = new double[maxlen];
            
        int minlen = Math.min(coefficients.length, Arg.coefficients.length);
        for (int i=0; i<minlen; i++){
            result[i] = coefficients[i] + Arg.coefficients[i];
            }
            
        if(coefficients.length > minlen){
            for (int i = minlen; i < coefficients.length; i++) {
                result[i] = coefficients[i];
                }
            } else if(Arg.coefficients.length > minlen){
                for (int i = minlen; i < Arg.coefficients.length; i++) {
                result[i] = Arg.coefficients[i];
                }
            }
        
        return new Polynomial(result);
        
        }
    
    //point 5: evaluate method for poly
    public double evaluate (double x){
        double result = 0;
        for (int i =0; i < coefficients.length; i++){
            result += coefficients[i] * Math.pow(x,i);
            }
        return result;
        }
    //point 6: hasroot method
    public boolean hasRoot(double x){
            return evaluate(x)==0;
        }
    
    }