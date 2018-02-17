public enum Color{
    BLANCO(0), GRIS(1), NEGRO(2);

    private final Integer id;
    Color(int id) { this.id = id; }
    public int getValue() { return id; }
}

class ListaInt{
    int elemento;
    ListaInt siguiente;
    public static final ListaInt nil = null;
    
    public static int primero(ListaInt l){
        return l.elemento;
    }

    public static ListaInt cons(int nuevoElemento, ListaInt listaVieja){
        ListaInt nuevaL = new ListaInt();
        nuevaL.elemento = nuevoElemento;
        nuevaL.siguiente = listaVieja;
        return nuevaL;
    }

    public static ListaInt resto(ListaInt listaVieja){
        ListaInt nuevaL = new ListaInt();
        if(listaVieja.siguiente != ListaInt.nil){
            nuevaL.elemento = ListaInt.primero(listaVieja.siguiente);
            nuevaL.siguiente = listaVieja.siguiente.siguiente;
        } else{
            nuevaL = ListaInt.nil;
        }
        return nuevaL;
    }

    public static ListaInt insertar1(int nuevoElemento, ListaInt listaVieja){
        ListaInt listaNueva;
        if(listaVieja == ListaInt.nil)
            listaNueva = ListaInt.cons(nuevoElemento, listaVieja);
        else {
            int antiguoPrimero = ListaInt.primero(listaVieja);
            if(nuevoElemento == antiguoPrimero)
                listaNueva = ListaInt.cons(nuevoElemento, listaVieja);
            else {
                ListaInt antiguoResto = ListaInt.resto(listaVieja);
                ListaInt nuevoResto = insertar1(nuevoElemento, antiguoResto);
                listaNueva = ListaInt.cons(antiguoPrimero, nuevoResto);
            }
        }
        return listaNueva;
    }

    public static void imprimirLista(ListaInt lista){
        while(lista != ListaInt.nil){
            System.out.println(ListaInt.primero(lista));
            lista = ListaInt.resto(lista);
        }
    }
}

class PilaInt{
    Integer[] arreglo;
    int tamano;
    int indice;
    boolean vacio;

    public static PilaInt crear(int tamano){
        PilaInt pila = new Pila();
        pila.arreglo = new int[tamano];
        for(int i = 0; i < tamano; i++){
            pila.arreglo[i] = null;
        }
        pila.tamano = tamano;
        pila.indice = 0;
        pila.vacio = true;
        return pila;
    }

    public static int top(PilaInt pila){
        if(pila.arreglo[pila.indice] != null)
            return pila.arreglo[pila.indice];
        else
            return null;
    }

    public static void push(PilaInt pila, Integer num){
        if(pila.indice < pila.tamano){
            pila.arreglo[pila.indice+1] = num;
            pila.indice++;
            pila.vacio = false;
        }
    }

    public static void pop(PilaInt pila){
        if(pila.indice == 0){
            pila.arreglo[pila.indice] = null;
            pila.vacio = true;
        } else {
            pila.arreglo[pila.indice] = null;
            pila.indice--;
        }
    }

    public static boolean estaVacia(PilaInt pila){
        return pila.vacio;
    }

}

public class PrimeroEnProfundidad{

    public static void main(Strings[] args){

    }

    public static void componentesConectados(ListaInt[] verticesAdya, int n, int[] cc){
        int[] color = new int[n+1];
        int v;
        for(v = 0; v < n+1; v++){
            color[v] = Color.BLANCO;
        }
        for(v = 1; v <= n; v++){
            if(color[v] == Color.BLANCO)
                ccDFS(verticesAdya, color, v, v, cc);
        }
        return;
    }

    public static void ccDFS(ListaInt[] verticesAdya, int[] color, int v, int numCC, int[] cc){
        int w;
        ListaInt adyaRest;
        color[v] = Color.GRIS;
        cc[v] = numCC;
        adyaRest = verticesAdya[v];
        while(adyaRest != ListaInt.nil){
            w = ListaInt.primero(adyaRest);
            if(color[w] == Color.BLANCO){
                ccDFS(verticesAdya, color, W, numCC, cc);
            }
            adyaRest = ListaInt.resto(adyaRest);
        }
        color[v] = Color.NEGRO;
        return;
    }

    public static int barridoDFS(ListaInt[] verticesAdya, int n){
        int respuesta, i;
        int[] color = new int[n];
        for(i = 0; i < n; i++){
            color[i] = Color.BLANCO;
        }
        for(i = 0; i < n; i++){
            if(color[i] == Color.BLANCO){
                int vResp = dfs(verticesAdya, color, i);
            }
        }
        return respuesta;
    }

    public static int dfs(ListaInt[] verticesAdya, int[] color, int v){
        int w;
        ListaInt adyaRest;
        int respuesta;
        color[v] = Color.GRIS;
        adyaRest = verticesAdya[v];
        while(adyaRest != ListaInt.nil){
            w = ListaInt.primero(adyaRest);
            if(color[w] == Color.BLANCO){
                int wResp = dfs(verticesAdya, color, w);
            } else {

            }
            adyaRest = ListaInt.resto(adyaRest);
        }
        color[v] = Color.NEGRO;
        return respuesta;
    }

    public static int[] topo;
    public static int numTopo;

    public static void barridoDFSTopoligicoInverso(ListaInt[] verticesAdya, int n){
        int respuesta, i;
        int[] color = new int[n];
        topo = new int[n];
        for(i = 0; i < n; i++){
            color[i] = Color.BLANCO;
            topo[i] = 0;
        }
        for(i = 0; i < n; i++){
            if(color[i] == Color.BLANCO){
                int vResp = dfsTopologicoInverso(verticesAdya, topo, color, i);
            }
        }
        return;
    }

    public static void dfsTopologicoInverso(ListaInt[] verticesAdya, int[] color, int[] topo,int v){
        int w;
        ListaInt adyaRest;
        int respuesta;
        color[v] = Color.GRIS;
        adyaRest = verticesAdya[v];
        while(adyaRest != ListaInt.nil){
            w = ListaInt.primero(adyaRest);
            if(color[w] == Color.BLANCO){
                int wResp = dfs(verticesAdya, color, w);
            } else {

            }
            adyaRest = ListaInt.resto(adyaRest);
            numTopo++;
            topo[v] = numTopo;
        }
        color[v] = Color.NEGRO;
        return;
    }

    public static int[] duracion;
    public static int[] depCrit;
    public static int[] ttm;

    public static void barridoDFSCritico(ListaInt[] verticesAdya, int n){
        int respuesta, i;
        int[] color = new int[n];
        duracion = new int[n];
        depCrit = new int[n];
        ttm = new int[n];
        for(i = 0; i < n; i++){
            color[i] = Color.BLANCO;
            duracion[i] = 0;
            depCrit[i] = 0;
            ttm[i] = 0;
        }
        for(i = 0; i < n; i++){
            if(color[i] == Color.BLANCO){
                int vResp = dfsCritico(verticesAdya, color, i);
            }
        }
        return;
    }

    public static void dfsCritico(ListaInt[] verticesAdya, int[] color, int v){
        int w, tim;
        ListaInt adyaRest;
        int respuesta;
        color[v] = Color.GRIS;
        tim = 0;
        depCrit[v] = -1;
        adyaRest = verticesAdya[v];
        while(adyaRest != ListaInt.nil){
            w = ListaInt.primero(adyaRest);
            if(color[w] == Color.BLANCO){
                int wResp = dfs(verticesAdya, color, w);
                if(ttm[w] >= tim){
                    tim = ttm[w];
                    depCrit[v] = w;
                }
            } else {
                if(ttm[w] >= tim){
                    tim = ttm[w];
                    depCrit[v] = w;
                }
            }
            adyaRest = ListaInt.resto(adyaRest);
        }
        ttm[v] = tim + duracion[v];
        color[v] = Color.NEGRO;
        return;
    }

    public static void componentesFuertes(ListaInt[] verticesAdya, int n, int[] cfc){
        PilaInt pilaTerminar = PilaInt.crear(n);
        pilaTerminar = barridoDFSComponentesFuertes(verticesAdya, n, pila);
        // calcular transAdya
        barridoDfsT(transAdya, n, pilaTerminar, cfc);
        return;
    }

    public static PilaInt barridoDFSComponentesFuertes(ListaInt[] verticesAdya, int n, PilaInt pila){
        int respuesta, i;
        int[] color = new int[n];
        for(i = 0; i < n; i++){
            color[i] = Color.BLANCO;
        }
        for(i = 0; i < n; i++){
            if(color[i] == Color.BLANCO){
                pila = dfsComponentesFuertes(verticesAdya, color, i, pila);
            }
        }
        return pila;
    }

    public static PilaInt dfsComponentesFuertes(ListaInt[] verticesAdya, int[] color, int v, PilaInt pilaTerminar){
        int w;
        ListaInt adyaRest;
        int respuesta;
        color[v] = Color.GRIS;
        adyaRest = verticesAdya[v];
        while(adyaRest != ListaInt.nil){
            w = ListaInt.primero(adyaRest);
            if(color[w] == Color.BLANCO){
                int wResp = dfs(verticesAdya, color, w);
            } else {

            }
            adyaRest = ListaInt.resto(adyaRest);
        }
        PilaInt.push(pila, v);
        color[v] = Color.NEGRO;
        return pilaTerminar;
    }

    public static void barridoDFST(ListaInt[] transAdya, int n, PilaInt pilaTerminar, int[] cfc){
        int[] color = new int[n];
        for(int i = 0; i < n; i++){
            color[i] = Color.BLANCO;
        }
        while(PilaInt.estaVacia(pilaTerminar)){
            int v = PilaInt.top(pilaTerminar);
            PilaInt.pop(pilaTerminar);
            if(color[v] == Color.BLANCO){
                cfc = dfsT(transAdya, color, v, v, cfc);
            }
        }
        return;
    }

    public static int[] dfsT(ListaInt[] verticesAdya, int[] color, int v, int lider, int[] cfc){
        int w;
        ListaInt adyaRest;
        int respuesta;
        color[v] = Color.GRIS;
        cfc[v] = lider;
        adyaRest = verticesAdya[v];
        while(adyaRest != ListaInt.nil){
            w = ListaInt.primero(adyaRest);
            if(color[w] == Color.BLANCO){
                int wResp = dfs(verticesAdya, color, w);
            } 
            adyaRest = ListaInt.resto(adyaRest);
        }
        color[v] = Color.NEGRO;
        return cfc;
    }

    public static int dfsNoDirigido(ListaInt[] verticesAdya, int[] color, int v, int p){
        int w;
        ListaInt adyaRest;
        int respuesta;
        color[v] = Color.GRIS;
        adyaRest = verticesAdya[v];
        while(adyaRest != ListaInt.nil){
            w = ListaInt.primero(adyaRest);
            if(color[w] == Color.BLANCO){
                int wResp = dfs(verticesAdya, color, w);
            } else if (color[w] == Color.GRIS && w != p){

            }
            adyaRest = ListaInt.resto(adyaRest);
        }
        color[v] = Color.NEGRO;
        return respuesta; 
    }

    //Vertices de Adyacencia iniciando en el arreglo desde la posición 1
    public static int tiempo;
    public static Integer[] tiempoDescubrir;
    public static PilaInt pilaAristas;

    public static void bicomponentes(ListaInt[] verticesAdya, int n){
        int v;
        int[] color = new int[n+1];
        tiempoDescubrir = new Integr[n+1];
        for(v = 0; v < n+1; v++){
            color[v] = Color.BLANCO;
            tiempoDescubrir[v] = null;
        }
        tiempo = 0;
        pilaAristas = PilaInt.crear(n);
        for(v = 1; v <= n; v++){
            if(color[v] == Color.BLANCO)
                bicompDFS(verticesAdya, color, v, -1);
        }
        return;
    }

    public static int bicompDFS(ListaInt[] verticesAdya, int[] color, int v, int p){
        int w;
        ListaInt adyaRest;
        int retro;
        color[v] = Color.GRIS;
        tiempo++;
        tiempoDescubrir[v] = tiempo;
        retro = tiempoDescubrir[v];
        adyaRest = verticesAdya[v];
        while(adyaRest != ListaInt.nil){
            w = ListaInt.primero(adyaRest);
            if(color[w] == Color.BLANCO){
                //PilaInt.push(pila, vw);
                int wRetro = bicompDFS(verticesAdya, color, w, v);
                if(wRetro >= tiempoDescubrir[v]){

                }
                retro = min(retro, wRetro);
            } else if(color[w] == Color.GRIS && w != p){
                //PilaInt.push(pila, vw);
                retro = min(tiempoDescubrir[w], retro);
            }
            adyaRest = PilaInt.resto(adyaRest);
        }
        tiempo++;
        tiempoDescubrir[v] = tiempo;
        color[v] = Color.NEGRO;
        return retro;
    }


}