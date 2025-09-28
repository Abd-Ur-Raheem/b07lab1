import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;
import java.io.FileWriter;
import java.io.IOException;

public class Polynomial {
    private double [] non_zerocoef;
    private int [] exponents;
    
    //point 2: zero poly constructor
    public Polynomial() {
         non_zerocoef = new double[0];
         exponents = new int[0];
        }
        
    //point 3: double array constructor
    public Polynomial(double [] coef) {
            int len = 0;
            for (double c : coef){
                     if (c != 0)
                         len++;
                }
            
            non_zerocoef = new double[len];
            exponents = new int[len];
            
            int idx = 0;
            int i=0;
            while (i < coef.length) {
                if (coef[i] != 0){
                    non_zerocoef[idx] = coef[i];
                    exponents[idx] = i;
                    idx++;
                }
                i++;
            }
        }
        
    //point 4: method to add two polys
    public Polynomial add(Polynomial Arg){
        int maxexp = 0;
        for (int e : exponents) {
            if (e > maxexp) maxexp=e;
            }
            
        for (int e : Arg.exponents) {
            if (e > maxexp) maxexp=e;
            }
            
        double[] full_non_zero = new double[maxexp + 1];
        double[] full_other = new double [maxexp + 1];
        
        int i = 0;
        while(i < non_zerocoef.length) {
            full_non_zero[exponents[i]] = non_zerocoef[i];
            i++;
            }
        int j = 0;
        while (j < Arg.non_zerocoef.length){
            full_other[Arg.exponents[i]] = Arg.non_zerocoef[i];
            j++;
            }
        
        double[] result = new double[maxExp + 1];
        for (int i = 0; i <= maxExp; i++) {
            result[i] = full_non_zero[i] + full_other[i];
        }
        
        return new Polynomial(result);
        }
    
    //point 5: evaluate method for poly
    public double evaluate (double x){
        double result = 0;
        for (int i =0; i < non_zerocoef.length; i++){
            result += non_zerocoef[i] * Math.pow(x,exponents[i]);
            }
        return result;
        }
    //point 6: hasroot method
    public boolean hasRoot(double x){
            return evaluate(x) == 0;
        }
    
    public Polynomial multiply(Polynomial other) {
            int maxsize = this.non_zerocoef.length * other.non_zerocoef.length;
            double [] tempCoeffs = new double[maxsize];
            int[] tempExps = new int[maxSize];
            int count = 0;
            
            for (int i = 0; i<this.non_zerocoef.length; i++) {
                for (int j = 0; j<other.non_zerocoef.length; j++) {
                    double coeff= this.non_zerocoef[i] * other.non_zerocoef[j];
                    int exp = this.exponents[i] + other.exponents[j];
                    
                    int index = find_same_exp(tempExps, count, exp)
                    if (index = i) {
                           tempCoeffs[index] += coeff; 
                        } else {
                            tempCoeffs[count] = coeff;
                            tempExps[count] = exp;
                            count++;
                        }
                    }
                }
                
                double [] NewCoeffs = new double [count];
                int [] NewExp = new int [count];
                for (int i = 0; i < count; i++) {
                        NewCoeffs[i] = tempCoeffs[i];
                        NewExp[i] = tempExps[i];
                    }
                return new Polynomial(NewCoeffs, NewExp);
        }
    
    private int find_same_exp(int[] exps, int size, int exp) {
        for (int i = 0; i < size; i++) {
            if (exps[i] == exp) {
                return i;
            }
        }
        return -1;
    
    }
    
    public Polynomial(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        if (sc.hasNextLine()) {
            String line = sc.nextLine();
    
            line = line.replace("-", "+-");
            
            String[] terms = line.split("\\+");
    
            non_zerocoef = new double[terms.length];
            exponents = new int[terms.length];
            int count = 0;
    
            for (String term : terms) {
                double coef;
                int exp;
    
                if (term.contains("x")) {
                    String[] parts = term.split("x"); 
    
                    if (parts[0].equals("") || parts[0].equals("+")) {
                        coef = 1.0;
                    } else if (parts[0].equals("-")) {
                        coef = -1.0;
                    } else {
                        coef = Double.parseDouble(parts[0]); 
                    }
    
                    if (parts.length == 1 || parts[1].equals("")) {
                        exp = 1;
                    } else {
                        exp = Integer.parseInt(parts[1]);
                    }
                } else {
                    coef = Double.parseDouble(term); 
                    exp = 0;
                }
    
                non_zerocoef[count] = coef;
                exponents[count] = exp;
                count++;
            }
    
            
            if (count < terms.length) {
                double[] newCoef = new double[count];
                int[] newExp = new int[count];
                for (int i = 0; i < count; i++) {
                    newCoef[i] = non_zerocoef[i];
                    newExp[i] = exponents[i];
                }
                non_zerocoef = newCoef;
                exponents = newExp;
            }
        }
        sc.close();
    }
    
    public void saveToFile(File f) throws IOException {
        FileWriter writer = new FileWriter(f);
        StringBuilder sb = new StringBuilder();
    
        for (int i = 0; i < non_zerocoef.length; i++) {
            double coef = non_zerocoef[i];
            int exp = exponents[i];

    
            if (i > 0) { 
                if (coef > 0) {
                    sb.append("+");
                } else {
                    sb.append("-");
                }
            } else {
                if (coef < 0) sb.append("-");
            }
    
            double absCoef = Math.abs(coef);
    
            if (exp == 0) {
                sb.append(absCoef);
            } else if (exp == 1) {
                if (absCoef == 1.0) {
                    sb.append("x");
                } else {
                    sb.append(absCoef).append("x");
                }
            } else {
                if (absCoef == 1.0) {
                    sb.append("x").append(exp);
                } else {
                    sb.append(absCoef).append("x").append(exp);
                }
            }
        }
    
        writer.write(sb.toString());
        writer.close();
    }
}