import java.io.File;
import java.io.IOException;

public class Driver {
    public static void main(String [] args) throws IOException {
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
            
        // multiply() 
        Polynomial m = p1.multiply(p2); 
        System.out.println("m(2) = " + m.evaluate(2)); 
        // evaluate() 
        System.out.println("p1(2) = " + p1.evaluate(2)); 
        System.out.println("p2(2) = " + p2.evaluate(2)); 
        // file constructor 
        File inputFile = new File("poly.txt"); 
        Polynomial p3 = new Polynomial(inputFile); 
        System.out.println("p3(2) = " + p3.evaluate(2)); 
        // saveToFile 
        File outFile = new File("saved_poly.txt"); 
        p1.saveToFile(outFile); 
        System.out.println("p1 saved to saved_poly.txt"); 
        // reload saved file 
        Polynomial p4 = new Polynomial(outFile); 
        System.out.println("Reloaded p4(2) = " + p4.evaluate(2));
        }
    }