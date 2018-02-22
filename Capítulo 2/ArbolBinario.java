class ArbolBin{
    public static final ArbolBin nil =  null;
    Object raiz;
    ArbolBin subArbolIzq;
    ArbolBin subArbolDer;

    public static ArbolBin construirArbol(Object nuevaRaiz, ArbolBin viejoAI, ArbolBin viejoAD){
        ArbolBin arbol = new ArbolBin();
        arbol.raiz = nuevaRaiz;
        arbol.subArbolDer = viejoAD;
        arbol.subArbolIzq = viejoAI;
        return arbol;
    }

    public static Object raiz(ArbolBin a){
        return a.raiz;
    }

    public static ArbolBin subArbolIzq(ArbolBin a){
        return a.subArbolIzq;
    }

    public static ArbolBin subArbolDer(ArbolBin a){
        return a.subArbolDer;
    }

    public static void recorrerPreOrden(ArbolBin T){
        if(T != ArbolBin.nil){
            System.out.println(raiz(T));
            recorrerPreOrden(subArbolIzq(T));
            recorrerPreOrden(subArbolDer(T));
        }
        return;
    }

    public static void recorrerInOrden(ArbolBin T){
        if(T != ArbolBin.nil){
            recorrerInOrden(subArbolIzq(T));
            System.out.println(raiz(T));
            recorrerInOrden(subArbolDer(T));
        }
        return;
    }

    public static void recorrerPosOrden(ArbolBin T){
        if(T != ArbolBin.nil){
            recorrerPosOrden(subArbolIzq(T));
            recorrerPosOrden(subArbolDer(T));
            System.out.println(raiz(T));
        }
        return;
    }

    public static LceDevuelta calcLce(ArbolBin t){
        LceDevuelta respL, respR;
        LceDevuelta resp = new LceDevuelta();

        if(ArbolBin.raiz(t) == ArbolBin.nil){
            resp.lce = 0;
            resp.numExt = 1;
        } else {
            respL = calcLce(ArbolBin.subArbolIzq(t));
            respR = calcLce(ArbolBin.subArbolDer(t));
            resp.lce = respL.lce + respR.lce + respR.numExt + respL.numExt;
            resp.numExt = respL.numExt + respR.numExt;
        }
        return LceDevuelta;
    }

}

class LceDevuelta{
    int lce;
    int numExt;
}


public class ArbolBinario {
    public static void main(String[] args){
        ArbolBin arbol2 = ArbolBin.construirArbol(2, ArbolBin.nil, ArbolBin.nil);
        ArbolBin arbol3 = ArbolBin.construirArbol(3, ArbolBin.nil, ArbolBin.nil);
        ArbolBin arbol1 = ArbolBin.construirArbol(1, arbol2, arbol3);
        ArbolBin.recorrerInOrden(arbol1);
        ArbolBin.recorrerPosOrden(arbol1);
        ArbolBin.recorrerPreOrden(arbol1);
    }
}