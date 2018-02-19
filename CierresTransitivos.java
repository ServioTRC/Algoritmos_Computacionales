import java.util.BitSet;

class MatrizDeBits{
    BitSet[] bitArray;
    
    public static MatrizDeBits crear(int n){
        MatrizDeBits res = new MatrizDeBits();
        res.bitArray = new BitSet[n+1];
        for(int i = 1; i <= n; i++){
            res.bitArray[i] = new BitSet(n+1);
        }
        return res;
    }

    public static void set(MatrizDeBits res, int row, int col, boolean value){
        res.bitArray[row].set(col, value);
    }

    public static boolean get(MatrizDeBits res, int row, int col){
        return res.bitArray[row].get(col);
    }

    public static void ORporBits(BitSet I, BitSet K, int n){
        I.or(K);
    }

    public static int segBits(BitSet B, int segmento, int tamano){
        long value = 0L;
        for (int i = (j-1)*t+1; i <= segmento*tamano; ++i) {
            value += bits.get(i) ? (1L << i) : 0L;
        }
        return (int)value;
    }
}

public class CierresTransitivos{
    public static void main(String[] args){
        
    }

    //Valores de matrices inician en el indice 1
    public static void cierreTransitivoSimple(boolean[][] A, int n, boolean[][] R){
        int i, j, k;
        for(i = 1; i <= n; i++){
            for(j = 1; j <= n; j++){
                if(i==j)
                    R[i][j] = true;
                else
                    R[i][j] = A[i][j];
            }            
        }
        boolean cambio, temp;
        do{
            cambio = false;
            for(i = 1; i <= n; i++){
                for(j = 1; j <= n; j++){
                    for(k = 1; k <= n; k++){
                        temp = R[i][j];
                        R[i][j] = R[i][j] || (R[i][k] && R[k][j]);
                        if(!cambio)
                            cambio = (temp != R[i][j]);
                    }
                }
            }
        } while(cambio);
    }

    public static void cierreTransitivo(boolean[][] A, int n, boolean[][] R){
        int i, j, k;
        for(i = 1; i <= n; i++){
            for(j = 1; j <= n; j++){
                if(i==j)
                    R[i][j] = true;
                else
                    R[i][j] = A[i][j];
            }            
        }
        for(k = 1; k <= n; k++){
            for(i = 1; i <= n; i++){
                for(j = 1; j <= n; j++){
                    R[i][j] = R[i][j] || (R[i][k] && R[k][j]);
                }
            }
        }
    }

    public static void matricesBitsWarshall(MatrizDeBits A, int n, MatrizDeBits R){
        int i, k;
        boolean temp;
        for(i = 1; i <= n; i++){
            for(k = 1; k <= n; k++){
                if(i==k)
                    MatrizDeBits.set(R, i, k, true);
                else
                    MatrizDeBits.set(R, i, k, MatrizDeBits.get(A, i, k));
            }
        }
        for(k = 1; k <= n; k++){
            for(i = 1; i <= n; i++){
                if(MatrizDeBits.get(A, i, k) == true)
                    ORporBits(R.bitArray[i], R.bitArray[j], n);
            }
        }
    }

    public static double min(double a, double b){
        if(a <= b)
            return a;
        else
            return b;
    }

    public static void caminosMasCortosTodosPares(double[][] W, int n, double[][] D){
        int i, j, k;
        for(i = 1; i <= n; i++){
            for(j = 1; j <= n; j++){
                D[i][j] = W[i][j];
            }
        }
        for(k = 1; k <= n; k++){
            for(i = 1; i <= n; i++){
                for(j = 1; j <= n; j++){
                    D[i][j] = min(D[i][j], D[i][k]+D[k][j]);
                }
            }
        }
    }

    public static void kronrod(MatrizDeBits A, MatrizDeBits B, int n, MatrizDeBits C){
        int t, g, i, j, k;
        Double temp;
        temp = Math.floor(Math.log(n) / Math.log(2));
        t = temp.intValue();
        temp = Math.ceil(n/t);
        g = temp.intValue();
        MatrizDeBits uniones = MatrizDeBits.crear(n);
        for(i = 1; i <= n; i++){
            for(k = 1; k <= n; k++){
                MatrizDeBits.set(C, i, k, false);
                MatrizDeBits.set(uniones, i, k, false);
            }
        }
        
        for(j = 1; j <= g; j++){
            for(i = 1; i <= n; i++){
                MatrizDeBits.set(uniones, 0, i, false);
            }
            for(k = 0; k <= t - 1; k++){
                for(i = 0; i <= (int)(Math.pow(2, k)) - 1; i++){
                    uniones.bitArray[i+(int)Math.pow(2, k)] = uniones.bitArray[i];
                    MatrizDeBits.ORporBits(uniones.bitArray[i+(int)Math.pow(2, k)], B.bitArray[j*t-k], n);
                }  
            }
            for(i = 1; i <= n; i++){
                MatrizDeBits.ORporBits(C.bitArray[i], MatrizDeBits.segBits(A.bitArray[i], j, t), n);
            }
        }
    }

}