public enum Color{
    ROJO(1), NEGRO(0), BLANCO(null);

    private final Integer id;
    Color(int id) { this.id = id; }
    public int getValue() { return id; }
}

public enum Situacion{
    OK(0), RNR(1), NRN(2), RRN(3), NRR(4);

    private final Integer id;    
    Situacion(int id) { this.id = id; }
    public int getValue() { return id; }
}

class Elemento {
    Clave clave;
}

class Clave {
    int valor;
}

static class DevuelveIns{
    public ArbolRojiNegro nuevoArbol;
    public Integer situacion;
}

class ArbolRojiNegro{
    Elemento raiz;
    ArbolRojiNegro subArbolIzq;
    ArbolRojiNegro subArbolDer;
    Integer color;

    public static ArbolRojiNegro nil = null;

    public static Elemento raiz(ArbolRojiNegro arbol){
        return arbol.raiz;
    }

    public static ArbolRojiNegro subarbolIzq(ArbolRojiNegro arbol){
        return arbol.subArbolIzq;
    }

    public static ArbolRojiNegro subarbolDer(ArbolRojiNegro arbol){
        return arbol.subArbolDer;
    }

    public static Color colorDe(ArbolRojiNegro arbol){
        return arbol.color;
    }

    public static void invertirColor(ArbolRojiNegro arbol){
        if(arbol.color == Color.ROJO)
            arbol.color = Color.NEGRO;
        else if (arbol.color == Color.NEGRO)
            arbol.color = Color.ROJO;
    }

    public static Elemento buscarBst(ArbolRojiNegro bst, Clave k){
        Elemento hallado;
        if(bst == ArbolRojiNegro.nil)
            hallado = null;
        else{
            Elemento raiz = ArbolRojiNegro.raiz(bst);
            if(k.valor == raiz.clave.valor)
                hallado = raiz;
            else if(k.valor < raiz.clave.valor)
                hallado = buscarBst(ArbolRojiNegro.subarbolIzq(bst), k);
            else
                hallado = buscarBst(ArbolRojiNegro.subarbolDer(bst), k);               
        }      
        return hallado;  
    }

    public static ArbolRojiNegro insertarArn(ArbolRojiNegro viejoArbolRN, Elemento nuevoNodo){
        DevuelveIns respuesta = ArbolRojiNegro.insArn(viejoArbolRN, nuevoNodo);
        if(respuesta.nuevoArbol.color != Color.NEGRO)
            respuesta.nuevoArbol.color = Color.NEGRO;
        return respuesta.nuevoArbol;
    }

    public static DevuelveIns insArn(ArbolRojiNegro viejoArbolRN, Elemento nuevoNodo){
        DevuelveIns respuesta, respIzq, respDer;
        if(viejoArbolRN == ArbolRojiNegro.nil){
            respuesta = new DevuelveIns();
            ArbolRojiNegro nuevo = new ArbolRojiNegro();
            nuevo.raiz = nuevoNodo;
            nuevo.color = Color.ROJO;
            nuevo.subArbolDer = ArbolRojiNegro.nil;
            nuevo.subArbolIzq = ArbolRojiNegro.nil;
            respuesta.nuevoArbol = nuevo;
            respuesta.situacion = Situacion.NRN;
        } else {
            if(nuevoNodo.clave.valor < viejoArbolRN.raiz.clave.valor){
                respIzq = insArn(viejoArbolRN.subarbolIzq, nuevoNodo);
                respuesta = repararIzq(viejoArbolRN, respIzq);
            } else {
                respDer = insArn(viejoArbolRN.subarbolDer, nuevoNodo);
                respuesta = repararDer(viejoArbolRN, respDer);
            }
        }
        return respuesta;
    }

    public static DevuelveIns repararIzq(ArbolRojiNegro viejoArbol, DevuelveIns respIzq){
        DevuelveIns respuesta = new DevuelveIns();
        if(respIzq.situacion == Situacion.OK){
            respuesta.nuevoArbol = viejoArbol;
            respuesta.situacion = Situacion.OK;
        } else {
            viejoArbol.subArbolIzq = respIzq.nuevoArbol;
            if(respIzq.situacion == Situacion.RNR){
                respuesta.nuevoArbol = viejoArbol;
                respuesta.situacion = Situacion.OK;
            } else if(respIzq.situacion == Situacion.RNR){
                if(viejoArbol.color == Color.NEGRO)
                    respuesta.situacion = Situacion.OK;
                else
                    respuesta.situacion = Situacion.RRN;
                respuesta.nuevoArbol = viejoArbol;
            } else if(ArbolRojiNegro.colorDe(viejoArbol.subArbolDer) == Color.ROJO){
                ArbolRojiNegro.invertirColor(viejoArbol);
                respuesta.nuevoArbol = viejoArbol;
                respuesta.situacion = Situacion.NRN;
            } else {
                respuesta.nuevoArbol = reequilIzq(viejoArbol, respIzq.situacion);
                respuesta.situacion = Situacion.OK;
            }
        }
        return respuesta;
    }

    public static ArbolRojiNegro reequilIzq(ArbolRojiNegro viejoArbol, Integer situacionIzq){
        ArbolRojiNegro L, M, R, LR, RL;
        if(situacionIzq == Situacion.RRN){
            R = viejoArbol;
            M = viejoArbol.subArbolIzq;
            L = M.subArbolIzq;
            RL = M.subArbolDer;
            R.subArbolIzq = RL;
            M.subArbolDer = R;
        } else {
            R = viejoArbol;
            L = viejoArbol.subarbolIzq;
            M = L.subarbolDer;
            LR = M.subarbolIzq;
            RL = M.subarbolDer;
            R.subarbolIzq = RL;
            L.subarbolDer = LR;
            M.subarbolDer = R;
            M.subarbolIzq = L;
        }
        L.color = Color.ROJO;
        R.color = Color.ROJO;
        M.color = Color.NEGRO;
        return M;
    }

    //public static DevuelveIns repararDer(ArbolRojiNegro viejoArbol, DevuelveIns respDer)
    //public static ArbolRojiNegro reequilDer(ArbolRojiNegro viejoArbol, Integer situacionDer)
}