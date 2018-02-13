class Elemento {
    Clave clave;
}

class Clave {
    int valor;
}

class Hash{

    Elemento[] arreglo;
    boolean[] borrados;
    int tamano;
    int ocupados;
    double llenado;

    public Hash(int tamano, double llenado){
        int i;
        this.arreglo = new Elemento[tamano];
        this.borrados = new boolean[tamano];
        for(i = 0; i < tamano; i++){
            this.borrados[i] = false;
            this.arreglo[i] = null;
        }
        this.tamano = tamano;
        this.ocupados = 0;
        this.llenado = llenado;
    }

    private int codigoDisp(Clave K){
        return K.valor % tamano;
    }

    private int increHash(Clave K){
        return K.valor % tamano;
    }

    public void insertarElemento(Elemento el){
        int clave = codigoDisp(el.clave);
        int incremento = increHash(el.clave);
        while(this.arreglo[clave] != null){
            clave += incremento;
        } 
        this.arreglo[clave] = el;
        this.ocupados++;
        if(this.ocupados >= (this.tamano*this.llenado)){
            doblarArreglo();
        }
    }

    private void doblarArreglo(){
        Elemento[] tempDatos = new Elemento[this.tamano*2];
        boolean[] tempBorrados = new boolean[this.tamano*2];
        int i;
        for(i = 0; i < this.tamano*2; i++){
            tempDatos[i] = null;
            tempBorrados[i] = false;
        }
        for(i = 0; i < this.tamano; i++){
            tempDatos[i] = this.arreglo[i];
            tempBorrados[i] = this.borrados[i];
        }
        this.arreglo = tempDatos;
        this.borrados = tempBorrados;
    }

    public Elemento hallarHash(Clave k){
        int clave = codigoDisp(K);
        int claveInicio = clave;
        int incremento = increHash(K);
        do {
            if(this.arreglo[clave].clave.valor == k.valor){
                return this.arreglo[clave];
            } else if(this.borrados[clave]){
                clave += incremento;
            } else{
                break;
            }
        } while(clave != claveInicio);
        return null;
    }

    public boolean eliminarHash(Clave k){
        int clave = codigoDisp(K);
        int claveInicio = clave;
        int incremento = increHash(K);
        do {
            if(this.arreglo[clave].clave.valor == k.valor){
                this.arreglo[clave] = null;
                this.borrados[clave] = true;
                return true;
            } else if(this.borrados[clave]){
                clave += incremento;
            } else{
                break;
            }
        } while(clave != claveInicio);
        return false;
    }
}

public class ArregloHash{
    public static void main(String[] args){

    }
}