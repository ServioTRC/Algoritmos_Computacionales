public enum Situacion{
    NoVisto(0), DeArbol(1), DeBorde(2);

    private final Integer id;
    Situacion(int id) { this.id = id; }
    public int getValue() { return id; }
}

class InfoArista{
    int a;
    double peso;

    @Override
    public boolean equals(Object obj){
        if (obj == null) 
            return false;
        InfoArista temp = (InfoArista) obj;
        return ((this.a == temp.a)&&(this.peso == temp.peso));
    }
}

class CPMin{
    int numVertices, numCP;
    int minVertice;
    double oo;
    int[] situacion;
    int[] padre;
    double[] pesoBorde;

    public static CPMin crear(int n, int[] situacion, int[] padre, double[] pesoBorde){
        CPMin cp = new CPMin();
        cp.padre = padre;
        cp.pesoBorde = pesoBorde;
        cp.situacion = situacion;
        for(int i = 1; i <= n; i++){
            situacion[i] = Situacion.NoVisto;
        }
        cp.numVertices = n;
        cp.numCP = 0;
        cp.minVertice = -1;
        cp.oo = Double.POSITIVE_INFINITY;
        return cp;
    }

    public static void insertar(CPMin cp, int v, int nuevoPadre, double nuevoP){
        cp.padre[v] = nuevoPadre;
        cp.pesoBorde[v] = nuevoP;
        cp.situacion[v] = Situacion.DeBorde;
        cp.minVertice = -1;
        cp.numCP++;
        return;
    }

    public static void decrementarClave(CPMin cp, int v, int nuevoPadre, double nuevoP){
        cp.padre[v] = nuevoPadre;
        cp.pesoBorde[v] = nuevoP;
        cp.minVertice = -1;
        return;
    }

    public static void borrarMin(CPMin cp){
        int viejoMin = CPMin.obtenerMin(cp);
        cp.situacion[viejoMin] = Situacion.DeArbol;
        cp.minVertice = -1;
        cp.numCP--;
        return;
    }

    public static boolean estaVacia(CPMin cp){
        return(cp.numCP == 0);
    }

    public static double obtenerPrioridad(CPMin cp, int v){
        return cp.pesoBorde[v];
    }

    public static int obtenerMin(CPMin cp){
        if(cp.minVertice == -1){
            CPMin.hallarMin(cp);
        }
        return cp.minVertice;
    }

    public static void hallarMin(CPMin cp){
        int v;
        double pesoMin;
        pesoMin = cp.oo;
        for(v = 1; v <= cp.numVertices; v++){
            if(cp.situacion[v] == Situacion.DeBorde){
                if(cp.pesoBorde[v] < pesoMin){
                    cp.minVertice = v;
                    pesoMin = cp.pesoBorde[v];
                }
            }
        }
        return;
    }

}

class ListaAristas{
    InfoArista arista;
    ListaAristas siguiente;
    public static final ListaAristas nil = null;
    
    public static InfoArista primero(ListaAristas l){
        return l.arista;
    }

    public static ListaAristas cons(InfoArista nuevoElemento, ListaAristas listaVieja){
        ListaAristas nuevaL = new ListaAristas();
        nuevaL.arista = nuevoElemento;
        nuevaL.siguiente = listaVieja;
        return nuevaL;
    }

    public static ListaAristas resto(ListaAristas listaVieja){
        ListaAristas nuevaL = new ListaAristas();
        if(listaVieja.siguiente != ListaAristas.nil){
            nuevaL.arista = ListaAristas.primero(listaVieja.siguiente);
            nuevaL.siguiente = listaVieja.siguiente.siguiente;
        } else{
            nuevaL = ListaAristas.nil;
        }
        return nuevaL;
    }

    public static ListaAristas insertar1(InfoArista nuevoElemento, ListaAristas listaVieja){
        ListaAristas listaNueva;
        if(listaVieja == ListaAristas.nil)
            listaNueva = ListaAristas.cons(nuevoElemento, listaVieja);
        else {
            InfoArista antiguoPrimero = ListaAristas.primero(listaVieja);
            if(nuevoElemento.equals(antiguoPrimero))
                listaNueva = ListaAristas.cons(nuevoElemento, listaVieja);
            else {
                ListaAristas antiguoResto = ListaAristas.resto(listaVieja);
                ListaAristas nuevoResto = insertar1(nuevoElemento, antiguoResto);
                listaNueva = ListaAristas.cons(antiguoPrimero, nuevoResto);
            }
        }
        return listaNueva;
    }

    public static void imprimirLista(ListaAristas lista){
        while(lista != ListaAristas.nil){
            System.out.println(ListaAristas.primero(lista));
            lista = ListaAristas.resto(lista);
        }
    }
}

public class ArbolAbarcanteMinimo{
    public static void main(String[] args){

    }

    public static CPMin cp;

    public static void primMST(ListaAristas[] infoAdya, int n, int s, int[] padre, double[] pesoBorde){
        int[] situacion = new int[n+1];
        cp = CPMin.crear(n, situacion, padre, pesoBorde);
        CPMin.insertar(cp, s, -1, 0);
        while(CPMin.estaVacia(cp) == false){
            int v = CPMin.obtenerMin(cp);
            CPMin.borrarMin(cp);
            CPMin.actualizarBorde(cp, infoAdya[v], v);
        }
    }

    public static void actualizarBorde(CPMin cp, ListaAristas infoAdyaDeV, int v){
        ListaAristas adyaRest;
        adyaRest = infoAdyaDeV;
        while(adyaRest != ListaAristas.nil){
            InfoArista infoW = ListaAristas.primero(adyaRest);
            int w = infoW.a;
            double nuevoPeso = infoW.peso;
            if(cp.situacion[w] == Situacion.NoVisto){
                CPMin.insertar(cp, w, v, nuevoPeso);
            } else if(cp.situacion[w] == Situacion.DeBorde){
                if(nuevoPeso < CPMin.obtenerPrioridad(cp, w))
                    CPMin.decrementarClave(cp, w, v, nuevoPeso);
            }
            adyaRest = ListaAristas.resto(adyaRest);
        }
        return;
    }

    public static void caminosMasCortos(ListaAristas[] infoAdya, int n, int s, int[] padre, double[] pesoBorde){
        int[] situacion = new int[n+1];
        cp = CPMin.crear(n, situacion, padre, pesoBorde);
        CPMin.insertar(cp, s, -1, 0);
        while(CPMin.estaVacia(cp) == false){
            int v = CPMin.obtenerMin(cp);
            CPMin.borrarMin(cp);
            actualizarBordeDij(cp, infoAdya[v], v);
        }
    }

    public static void actualizarBordeDij(CPMin cp, ListaAristas infoAdyaDeV, int v){
        double miDist = cp.pesoBorde[v];
        ListaAristas adyaRest;
        adyaRest = infoAdyaDeV;
        while(adyaRest != ListaAristas.nil){
            InfoArista infoW = ListaAristas.primero(adyaRest);
            int w = infoW.a;
            double nuevaDist = infoW.peso + miDist;
            if(cp.situacion[w] == Situacion.NoVisto){
                CPMin.insertar(cp, w, v, nuevoPeso);
            } else if(cp.situacion[w] == Situacion.DeBorde){
                if(nuevoPeso < CPMin.obtenerPrioridad(cp, w))
                    CPMin.decrementarClave(cp, w, v, nuevoPeso);
            }
            adyaRest = ListaAristas.resto(adyaRest);
        }
        return;
    }

}