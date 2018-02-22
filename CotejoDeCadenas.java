public class CotejoDeCadenas{
    public static void main(String[] args){

    }

    public static boolean finTexto(char[] T, int j){
        return j < T.length;
    }

    public static int cotejoSimple(char[] P, char[] T, int m){
        int coincide;
        int i, j, k;
        coincide = -1;
        j = 1;
        k = 1;
        i = j;
        while(finTexto(T, j) == false){
            if(k > m){
                coincide = 1;
                break;
            }
            if(T[j] == P[k]){
                j++;
                k++;
            } else {
                int retroceso = k - 1;
                j = j - retroceso;
                k = k - retroceso;
                j++;
                i = j;
            }
        }
        return coincide;
    }

    public static int[] fracaso;

    public static void kmpPrep(char[] P, int m, int[] fracaso){
        int k, s;
        fracaso[1] = 0;
        for(k = 2; k <= m; k++){
            s = fracaso[k-1];
            while(s >= 1){
                if(P[s] == P[k-1])
                    break;
                s = fracaso[s];
            }
            fracaso[k] = s + 1;
        }
    }

    public static int explorKMP(char[] P, char[] T, int m, int[] fracaso){
        int coincide;
        int j, k;
        coincide = -1;
        j = 1;
        k = 1;
        while(finTexto(T, j) == falso){
            if(k > m){
                coincide = j - m;
                break;
            }
            if(k == 0){
                j++;
                k = 1;
            } else if(T[j] == P[k]){
                j++;
                k++;
            } else {
                k = fracaso[k];
            }
        }
        return coincide;
    }

    public static void calcularSaltos(char[] P, int m, int alfa, int[] saltoCar){
        char car;
        int k;
        for(car = 0; car < alfa; car++){
            saltoCar[car] = m;
        }
        for(k = 1; k <= m; k++){
            saltoCar[P[k]] = m - k;
        }
    }

    public static int min(int a, int b){
        if(a < b)
            return a;
        else
            return b;
    }

    public static int max(int a, int b){
        if(a > b)
            return a;
        else
            return b;
    }

    public static void calcularSaltosCotejo(char[] P, int m, int[] saltoCotejo){
        int k, r, s, bajo, desplazar;
        int[] sufijo = new int[m+1];
        for(k = 1; k <= m; k++){
            saltoCotejo[k] = m + 1;
        }
        sufijo[m] = m + 1;
        for(k = m-1; k >= 0; k--){
            s = sufijo[k+1];
            while(s <= m){
                if(P[k+1] == P[s]){
                    break;
                }
                saltoCotejo[s] = min(saltoCotejo[s], s - (k+1));
                s = sufijo[s];
            }
            sufijo[k] = s - 1;
        }
        bajo = 1;
        desplazar = sufijo[0];
        while(desplazar <= m){
            for(k = bajo; k <= desplazar; k++){
                saltoCotejo[k] = min(saltoCotejo[k], desplazar);
            }
            bajo = desplazar + 1;
            desplazar = sufijo[desplazar];
        }
        for(k = 1; k <= m; k++){
            saltoCotejo[k] += (m-k);
        }
    }

    public static int explorBM(char[] P, char[] T, int m, int[] saltoCar, int[] saltoCotejo){
        int coincide;
        int j, k;
        coincide = -1;
        j = m;
        k = m;
        while(finTexto(T, j) == false){
            if(k < 1){
                coincide = j + 1;
                break;
            }
            if(T[j] == P[k]){
                j--;
                k--;
            } else {
                j += max(saltoCar[T[j]], saltoCotejo[k]);
                k = m;
            }
        }
        return coincide;
    }

}