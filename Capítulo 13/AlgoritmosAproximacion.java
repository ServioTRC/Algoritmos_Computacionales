import java.util.Arrays;

public class AlgoritmosAproximacion{
    public static void main(String[] args){

    }

    public static int factor(int n){
        int factor = 0;
        for(j = 2; j < n; j++){
            if(n % j == 0){
                factor = j;
                break; 
            }
        }
        return factor;
    }

    public static void llenadoFFD(float[] sucesion, int n, int[] cajon){
        float[] usado = new float[n+1];
        int i, j;
        for(i = 0; i < n+1; i++){
            usado[i] = 0.0;
        }
        Arrays.sort(sucesion);
        Collections.reverse(Arrays.asList(sucesion));
        for(i = 1; i <= n; i++){
            for(j = 1; j <= n; j++){
                if(usado[j] + sucesion[i] <= 1.0){
                    cajon[i] = j;
                    usado[j] += sucesion[i];
                    break;
                }
            }
        }
    }

    
}