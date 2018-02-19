class IdDicc{
    Integer id;
}

class Dicc{

    Object[] objetos;
    IdDicc[] ids;
    int tamano;

    public static Dicc crear(int tamano){
        Dicc dic = new Dicc();
        dic.tamano = tamano;
        dic.objetos = new Object[tamano];
        dic.ids = new IdDicc[tamano];
        for(int i = 0; i < tamano; i++){
            dic.objetos[i] = null;
            dic.ids[i] = null;
        }
        return dic;
    }

    public static boolean miembro(Dicc dic, IdDicc id){
        for(int i = 0; i < dic.tamano; i++){
            if(dic.ids[i] == null)
                return false;
            else if(dic.ids[i].id == id.id)
                return true;
        }
        return false;
    }

    public static Object recuperar(Dicc dic, IdDicc id){
        for(int i = 0; i < dic.tamano; i++){
            if(dic.ids[i] == null)
                return null;
            else if(dic.ids[i].id == id.id)
                return dic.objetos[i];
        }
        return null;
    }

    public static void almacenar(Dicc dic, IdDicc id, Object info){
        for(int i = 0; i < dic.tamano; i++){
            if(dic.ids[i] == null){
                dic.ids[i] = id;
                dic.objetos[i] = info;
            }
        }
    }
}

public class Diccionario{
    public static void main(String[] args){

    }
}