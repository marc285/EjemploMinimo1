import java.util.LinkedList;
import java.util.List;

public class Usuario {
    private String idUsuario;
    private String nombre;
    private List<Pedido> registro = null; //Historico de pedidos del usuario

    public Usuario (String id, String nom) {
        this.idUsuario = id;
        this.nombre = nom;
        registro = new LinkedList<Pedido>();
    }

    public String getID(){
        return this.idUsuario;
    }
    public String getNombre(){
        return this.nombre;
    }

    public void registrarPedido(Pedido p){ registro.add(p); }
    public LinkedList<Pedido> getRegistroPedidos(){ return (LinkedList<Pedido>) this.registro; }

}
