import java.io.*;
import java.util.HashMap;
import java.util.Scanner;

public class Polynomial{
    double[] coefficients;
    int[] exponenets;

    public Polynomial(){
        this.coefficients = null;
        this.exponenets = null;
    }

    public Polynomial(double[] coef, int[] expo){
        this.coefficients = new double[coef.length];
        this.exponenets = new int[expo.length];
        for (int i = 0; i < coef.length; i++){
            this.coefficients[i] = coef[i];
            this.exponenets[i] = expo[i];
        }
    }

    public Polynomial(File file){
        String polynomial = "";
        try{
            Scanner scanner = new Scanner(file);
            if (scanner.hasNextLine()) {
                polynomial = scanner.nextLine();
            }
            scanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Invalid File");
        }
        polynomial = polynomial.replace("-", "+-");
        String[] terms = polynomial.split("\\+");
        int length = terms.length;
        if (terms[0].isBlank()){
            length--;
        }
        double[] newC = new double[length];
        int[] newE = new int[length];
        int i = 0;
        for (String term: terms){
            if (term.isBlank()) {
                continue;
            } else if (terms.length == 1){
                newC = null;
                newE = null;
                break;
            }
            String[] coefAndExpo = term.split("x");
            if (coefAndExpo.length != 1){
                newC[i] = Double.parseDouble(coefAndExpo[0]);
                newE[i] = Integer.parseInt(coefAndExpo[1]);
            } else{
                newC[i] = Double.parseDouble(coefAndExpo[0]);
                newE[i] = 0;
            }
            i++;
        }
        this.coefficients = newC;
        this.exponenets = newE;
    }

    public Polynomial add(Polynomial addPoly){
        if (this.coefficients == null && addPoly.coefficients == null){
            return new Polynomial();
        } else if (this.coefficients == null){
            return new Polynomial(addPoly.coefficients, addPoly.exponenets);
        } else if (addPoly.coefficients == null){
            return new Polynomial(this.coefficients, this.exponenets);
        }
        HashMap<Integer, Double> expToCoef = new HashMap<>();
        for (int i = 0; i < this.exponenets.length; i++){
            expToCoef.put(this.exponenets[i], this.coefficients[i]);
        }
        for (int j = 0; j < addPoly.exponenets.length; j++){
            int exponent = addPoly.exponenets[j];
            double coefficient = addPoly.coefficients[j];
            if (expToCoef.containsKey(exponent)){
                double newCoef = coefficient + expToCoef.get(exponent);
                if (newCoef == 0){
                    expToCoef.remove(exponent);
                } else{
                    expToCoef.put(exponent, newCoef);
                }
            } else{
                expToCoef.put(exponent, coefficient);
            }
        }
        int[] newExponents = new int[expToCoef.size()];
        double[] newCoefficients = new double[expToCoef.size()];
        int i = 0;
        for (int exp: expToCoef.keySet()){
            newExponents[i] = exp;
            newCoefficients[i] = expToCoef.get(exp);
            i++;
        }
        return new Polynomial(newCoefficients, newExponents);
    }

    public double evaluate(double x){
        if (this.coefficients == null){
            return 0;
        }
        double total = 0;
        for (int i = 0; i < this.coefficients.length; i++){
            total += (this.coefficients[i] * Math.pow(x, this.exponenets[i]));
        }
        return total;
    }

    public boolean hasRoot(double val) {
        if (this.coefficients == null){
            return false;
        }
        return evaluate(val) == 0;
    }

    public Polynomial multiply(Polynomial multPoly){
        if (this.coefficients == null && multPoly.coefficients == null){
            return new Polynomial();
        } else if (this.coefficients == null){
            return new Polynomial(multPoly.coefficients, multPoly.exponenets);
        } else if (multPoly.coefficients == null){
            return new Polynomial(this.coefficients, this.exponenets);
        }
        HashMap<Integer, Double> expToCoef= new HashMap<>();
        for (int i = 0; i < this.coefficients.length; i++){
            for (int j = 0; j < multPoly.coefficients.length; j++){
                double coef = this.coefficients[i] * multPoly.coefficients[j];
                int exp = this.exponenets[i] + multPoly.exponenets[j];
                if (expToCoef.containsKey(exp)){
                    coef += expToCoef.get(exp);
                    if (coef == 0){
                        expToCoef.remove(exp);
                    } else{
                        expToCoef.put(exp, coef);
                    }
                } else{
                    expToCoef.put(exp, coef);
                }
            }
        }
        int[] newExponents = new int[expToCoef.size()];
        double[] newCoefficients = new double[expToCoef.size()];
        int i = 0;
        for (int exp: expToCoef.keySet()){
            newExponents[i] = exp;
            newCoefficients[i] = expToCoef.get(exp);
            i++;
        }
        return new Polynomial(newCoefficients, newExponents);
    }

    public void saveToFile(String file){
        String output = "";
        if (this.coefficients != null){
            for (int i = 0; i < this.coefficients.length; i++){
                if (this.exponenets[i] == 0){
                    output = output + this.coefficients[i];
                } else if (this.coefficients[i] < 0 || i == 0){
                    output = output + this.coefficients[i] + "x" + this.exponenets[i];
                } else {
                    output = output + "+" + this.coefficients[i] + "x" + this.exponenets[i];
                }
            }
        }
        try{
            FileWriter writer = new FileWriter(file);
            writer.write(output);
            writer.close();
        } catch (IOException e) {
            System.out.println("Invalid file");
            return;
        }
    }
    public void printPolynomial(){
        if (this.coefficients == null){
            System.out.println();
            return;
        }
        for (int i = 0; i < this.coefficients.length; i++){
            System.out.print(this.coefficients[i] + "x^" + this.exponenets[i] + " ");
        }
        System.out.println();
    }

}