public class Recursion{
    public static void main(String[] args){

    }

    public static int cicloFact(int n){
        int k, f, fnueva, knueva;
        k = 1;
        f = 1;
        while(k <= n){
            fnueva = f*k;
            knueva = k+1;
            k = knueva;
            f = fnueva;
        }
        return f;
    }

    public static int fact(int n){
        return factRec(n, 1, 1);
    }

    public static int factRec(int n, int k, int f){
        int resp;
        if(k < n)
            resp = f;
        else{
            int fnueva = f*k;
            int knueva = k+1;
            resp = factRec(n, knueva, fnueva);
        }
        return resp;
    }

    public static int mcd(int m, int n){
        int resp, nMenos;
        if(m==0)
            resp = n;
        else if(m > n)
            resp = mcd(n, m);
        else{
            nMenos = n-m;
            resp = mcd(m, nMenos);
        }
        return resp;
    }
}