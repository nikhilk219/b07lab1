public class Polynomial{
    double[] coefficients;

    public Polynomial(){
        this.coefficients = new double[1];
        this.coefficients[0] = 0;
    }

    public Polynomial(double[] coef){
        this.coefficients = new double[coef.length];
        for (int i = 0; i < coef.length; i++){
            this.coefficients[i] = coef[i];
        }
    }

    public Polynomial add(Polynomial adder){
        int maxLength = Math.max(this.coefficients.length, adder.coefficients.length);
        int minLength = Math.min(this.coefficients.length, adder.coefficients.length);
        double[] ret = new double[maxLength];
        int i = 0;
        while (i < minLength){
            ret[i] = this.coefficients[i] + adder.coefficients[i];
            i++;
        }
        if (maxLength == this.coefficients.length){
            while (i < maxLength){
                ret[i] = this.coefficients[i];
                i++;
            }
            return new Polynomial(ret);
        } else{
            while (i < maxLength){
                ret[i] = adder.coefficients[i];
                i++;
            }
            return new Polynomial(ret);
        }
    }

    public double evaluate(double x){
        double total = 0;
        for (int i = 0; i < this.coefficients.length; i++){
            total += this.coefficients[i] * (Math.pow(x, i));
        }
        return total;
    }

    public boolean hasRoot(double val){
        return evaluate(val) == 0;
    }
}