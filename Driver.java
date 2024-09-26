import java.io.File;
import java.io.FileWriter;

public class Driver {
    public static void main(String [] args) {
        Polynomial p1 = new Polynomial();
        System.out.print("p1: "); //Should both be null
        p1.printPolynomial();
        int[] p2Exp = {0, 1, 2, 3, 4};
        double[] p2Coef = {1, 2, -3, 4, -5};
        Polynomial p2 = new Polynomial(p2Coef, p2Exp);
        System.out.print("p2: ");
        p2.printPolynomial();
        int[] p3Exp = {1, 2, 3, 4, 5};
        double[] p3Coef = {1, 2, 3, 4, 5};
        Polynomial p3 = new Polynomial(p3Coef, p3Exp);
        System.out.print("p3: ");
        p3.printPolynomial();
        Polynomial p4 = p2.add(p3);
        System.out.print("p4 (addition of p2 and p3): ");
        p4.printPolynomial();
        System.out.print("Evaluation of p2 with x = 1: ");
        System.out.println(p2.evaluate(1)); //15.0
        int[] p5Exp = {0};
        double[] p5Coef = {1};
        Polynomial p5 = new Polynomial(p5Coef, p5Exp);
        System.out.print("p5: ");
        p5.printPolynomial();
        System.out.print("p5 has root 1: ");
        System.out.println(p5.hasRoot(1));
        System.out.print("p5 has root 2: ");//true
        System.out.println(p5.hasRoot(2));
        System.out.print("p5 has root 4: ");//true
        System.out.println(p1.hasRoot(4)); //false
        Polynomial p6 = p2.multiply(p3);
        System.out.print("p6 (p2 multiplied with p3): ");
        p6.printPolynomial();
        File f = new File("tester.txt");
        p5.saveToFile(f.getAbsolutePath());
        //Polynomial p7 = new Polynomial(f);
        //System.out.print("p7 (polynomial from a file): ");
        //p7.printPolynomial();
    }
}