import java.util.LinkedList;
import java.util.List;

public class Pedido {
    private String iduser;
    private List<LP> lps = null;

    public Pedido(String idUser){
        this.iduser = idUser;
        this.lps = new LinkedList<LP>();
    }

    public String getIDUsuario(){
        return this.iduser;
    }

    public void addLP(int n, String idprod){
        LP lp = new LP (n, idprod);
        this.lps.add(lp);
    }

    public LP getLP(int i){
        return this.lps.get(i);
    }

    public int getLPsize(){
        return this.lps.size();
    }
}
