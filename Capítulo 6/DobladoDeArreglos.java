class Doblado{
    Object[] elementos;
    int indice;

    public Doblado(){
        this.elementos = new Object[10];
        this.indice = -1;
    }

    public Doblado(int tamano){
        this.elementos = new Object[tamano];
        this.indice = -1;
    }

    public void push(Object val){
        if(this.indice >= this.elementos.length-1)
            doblarArreglo(this);
        if(this.indice < 0)
            this.indice = 0;
        this.elementos[this.indice] = val;
        this.indice++;
    }

    public Object pop(){
        Object res;
        if(this.indice - 1 > -1){
            res = this.elementos[this.indice];
            this.elementos[this.indice] = null;
            this.indice--;
            return res;
        }
        return null;
    }

    public Object top(){
        if(this.indice - 1 > -1){
            return this.elementos[this.indice];
        } else {
            return null;
        }
    }

    public void doblarArreglo(Doblado conjunto){
        int nuevaLongitud = 2*conjunto.elementos.length;
        Object[] nuevosElementos = new Object[nuevaLongitud];
        for(int i = 0; i < conjunto.elementos.length; i++){
            nuevosElementos[i] = conjunto.elementos[i];
        }
        conjunto.elementos = nuevosElementos;
    }

    public String toString(){
        String res = "";
        for(int i = 0; i < this.indice; i++){
            res += this.elementos[i]+" ";
        }
        return res;
    }

}

public class DobladoDeArreglos{
    public static void main(String[] args){
        Doblado arreglo = new Doblado(2);
        arreglo.insertar(2);
        arreglo.insertar(4);
        arreglo.insertar(7);
        System.out.println(arreglo);
        arreglo.remover();
        System.out.println(arreglo);
    }
}