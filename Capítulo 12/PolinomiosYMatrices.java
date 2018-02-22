class Complex{
    final double real;
    final double imaginary;

    public Complex(double real, double imaginary){
        this.real = real;
        this.imaginary = imaginary;
    }

    public Complex mas(Complex b){
        double re = this.real + b.real;
        double im = this.imaginary + b.imaginary;
        return new Complex(re, im);
    }

    public Complex menos(Complex b){
        double re = this.real - b.real;
        double im = this.imaginary - b.imaginary;
        return new Complex(re, im);
    }

    public Complex por(Complex b) {
        double re = this.real * b.real - this.imaginary * b.imaginary;
        double im = this.real * b.imaginary + this.imaginary * b.real;
        return new Complex(re, im);
    }

    public Complex escala(double b){
        return new Complex(this.real / b, this.imaginary / b);
    }

    public String toString() {
        if (imaginary == 0) return real + "";
        if (real == 0) return imaginary + "i";
        if (imaginary <  0) return real + " - " + (-imaginary) + "i";
        return real + " + " + imaginary + "i";
    }

}

public class PolinomiosYMatrices{
    public static void main(String[] args){

    }

    public static float poli(float[] a, int n, float x){
        float p, potenciax;
        int i;
        p = a[0];
        potenciax = 1;
        for(i = 1; i <= n; i++){
            potenciax = potenciax * x;
            p += a[i] * potenciax;
        }
        return p;
    }

    public static float poliHorner(float[] a, int n , float x){
        float p;
        int i;
        p = a[n];
        for(i = n-1; i >= 0; i--){
            p = p * x + a[i];
        }
        return p;
    }

    public static Complex[] reducirArreglo(Complex[] entrada, boolean par){
        int i, j;
        if(par)
            i = 0;
        else
            i = 1;
        Complex[] res = new Complex[entrada.length/2 + 1];
        for(j = 0; i < entrada.length; i += 2, j++){
            res[j] = entrada[i];
        }
        return res;
    }

    public static void FFTrecursiva(Complex[] P, int k, int m, Complex[] transformada, Complex[] omega){
        if(k == 0)
            transformada[0] = P[0];
        else {
            int n =(int) Math.pow(2, k);
            Complex[] pares = new Complex[n/2];
            Complex[] impares = new Complex[n/2];            
            Complex xPImpares;
            int j;
            FFTrecursiva(reducirArreglo(P, true), k-1, 2*m, pares, omega);
            FFTrecursiva(reducirArreglo(P, false), k-1, 2*m, impares, omega);
            for(j = 0; j <= Math.pow(2, k-1) - 1; j++){
                xPImpares = omega[m*j].por(impares[j]);
                transformada[j] = impares[j].mas(xPImpares);
                transformada[Math.pow(2, k-1) + j] = impares[j].mas(xPImpares);
            }
        }
    }

    public static int inverso(int t){
        String num = Integer.toBinaryString(t), res = "0";
        for(int i = 0; i < num.length(); i++){
            if(num.charAt(i) == '0')
                res += "1";
            else
                res += "0";
        }
        return Integer.parseInt(res, 2);
    }

    public static void fft(Complex[] P, int k, Complex[] transformada, Complex[] omega){
        int n = (int)Math.pow(2, k);
        int d;
        int num;
        int t;
        int j;
        int m;
        Complex transPrevia, xPImpares;
        for(t = 0; t <= n - 2; t += 2){
            transformada[t] = P[inverso(t)].mas(P[inverso(t+1)]);
            transformada[t+1] = P[inverso(t)].menos(P[inverso(t+1)]);
        }
        m = n/2;
        num = 2;
        for(d = k - 2; d >= 0; d--){
            m = m/2;
            num = 2*num;
            for(t = 0; t <= (Math.pow(2, d) - 1) * num; t += num){
                for(j = 0; j <= (num/2)-1; j++){
                    xPImpares = omega[m*j].por(transformada[t + num/2 + j]);
                    transPrevia = transformada[t + j];
                    transformada[t + j] = transPrevia.mas(xPImpares);
                    transformada[t + num/2 + j] = transPrevia.menos(xPImpares);
                }
            }
        }
    }

    public static void FFTinversa(Complex[] A, int k, Complex[] B, Complex[] omega){
        int n = (int)Math.pow(2, k);
        int i;
        Complex[] transformada = new Complex[n];
        fft(A, k, transformada, omega);
        B[0] = transformada[0].escala(n);
        for(i = 1; i <= n - 1; i++){
            B[i] = transformada[n - 1].escala(n);
        }
    }

}