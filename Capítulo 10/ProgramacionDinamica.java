class Dicc{

    Object[] objetos;
    Integer[] ids;
    int tamano;

    public static Dicc crear(int tamano){
        Dicc dic = new Dicc();
        dic.tamano = tamano;
        dic.objetos = new Object[tamano];
        dic.ids = new Integer[tamano];
        for(int i = 0; i < tamano; i++){
            dic.objetos[i] = null;
            dic.ids[i] = null;
        }
        return dic;
    }

    public static boolean miembro(Dicc dic, Integer id){
        for(int i = 0; i < dic.tamano; i++){
            if(dic.ids[i] == null)
                return false;
            else if(dic.ids[i] == id)
                return true;
        }
        return false;
    }

    public static Object recuperar(Dicc dic, Integer id){
        for(int i = 0; i < dic.tamano; i++){
            if(dic.ids[i] == null)
                return null;
            else if(dic.ids[i] == id)
                return dic.objetos[i];
        }
        return null;
    }

    public static void almacenar(Dicc dic, Integer id, Object info){
        for(int i = 0; i < dic.tamano; i++){
            if(dic.ids[i] == null){
                dic.ids[i] = id;
                dic.objetos[i] = info;
            }
        }
    }
}

public class ProgramacionDinamica{
    public static void main(String[] args){

    }

    public static int fiboArreglo(int n){
        int[] f = new int[n+1];
        f[0] = 0;
        f[1] = 1;
        for(int i = 2; i <= n; i++){
            f[i] = f[i-2] + f[i-1];
        }
        return f[n];
    }

    public static int envoltFibPD(int n){
        Dicc soln = Dicc.crear(n);
        return fibPD(soln, n);
    }

    public static int fibPD(Dicc soln, int k){
        int fib, f1, f2;
        if(k < 2)
            fib = k;
        else {
            if(Dicc.miembro(soln, k-1) == false)
                f1 = fibPD(soln, k-1);
            else 
                f1 = Dicc.recuperar(soln, k-1);
            if(Dicc.miembro(soln, k-2) == false)
                f1 = fibPD(soln, k-2);
            else 
                f1 = Dicc.recuperar(soln, k-2);
            fib = f1 + f2;
        }
        Dicc.almacenar(soln, k, fib);
        return fib;
    }

    public static int[][] ultimo;
    public static int[] ordenMulti;

    public static float ordenMatrices(int[] dim, int n, int[] ordenMulti){
        ultimo = new int[n+1][n+1];
        double[][] costo = new double[n+1][n+1];
        int bajo, alto, k, mejorUltimo;
        double mejorCosto;

        for(bajo = n-1; bajo >= 1; bajo--){
            for(alto = bajo+1; alto <= n; alto++){
                if(alto - bajo == 1){
                    mejorCosto = 0;
                    mejorUltimo = -1;
                } else {
                    mejorCosto = Double.MAX_VALUE;
                }
                for(k = bajo+1; k <= alto-1; k++){
                    double a = costo[bajo][k];
                    double b = costo[k][alto];
                    double c = costoMult(dim[bajo], dim[k], dim[alto]);
                    if(a + b + c < mejorCosto){
                        mejorCosto = a + b + c;
                        mejorUltimo = k;
                    }
                }
                costo[bajo][alto] = mejorCosto;
                ultimo[bajo][alto] = mejorUltimo;
            }
        }
        envoltExtraerOrden(n, ultimo, ordenMult);
        return costo[0][n];
    }

    public static double costoMult(double Dizq, double Dmed, double Dder){
        return Dizq*Dmed*Dder;
    }

    public static int ordenMultSiguiente;
    
    public static void envoltExtraerOrden(int n, int[][] ultimo, int[] ordenMulti){
        ordenMultSiguiente = 0;
        extraerOrden(0, n, ultimo, ordenMulti);
    }

    public static void extraerOrden(int bajo, int alto, int[][] ultimo, int[] ordenMulti){
        int k;
        if(alto - bajo > 1){
            k = ultimo[bajo][alto];
            extraerOrden(bajo, k, ultimo, ordenMulti);
            extraerOrden(k, alto, ultimo, ordenMulti);
            ordenMulti[ordenMultSiguiente] = k;
            ordenMultSiguiente++;
        }
    }
}