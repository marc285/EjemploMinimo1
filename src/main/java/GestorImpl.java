import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class GestorImpl implements Gestor {

    //Tabla de Hash de los Usuarios y cola (lista encadenada) de pedidos
    private HashMap<String, Usuario> usuarios = new HashMap<String, Usuario>();
    private LinkedList<Pedido> colaPedidos = new LinkedList<Pedido>();

    public List<Producto> productosOrdPrecio() throws NoPedidosException{

        return;
    };

    public void anotarPedido(Pedido p){
        if(checkUsuario(p.getUsuario())){
            //↓ log
            System.out.println(p.getUsuario().getID()+ ": usuario ya registrado anteriormente. No se volverá a registrar");
        }
        else //Registramos al usuario en la tabla si no esta ya registrado
            usuarios.put(p.getUsuario().getID(), p.getUsuario());

        //colaPedidos.addLast(p);
        //----Incrementar numventas----//
    };

    public Pedido servirPedido() throws NoPedidosException{
        return colaPedidos.removeFirst();
    };

    public List<Pedido> pedidosPorUsuario(String idUser) throws NoUsuarioException, NoPedidosException{

    };

    public List<Producto> productosOrdVentas() throws NoPedidosException{

    };

    //Funciones
    private boolean checkUsuario(Usuario u){ //Retorna true si ya está registrado el usuario
        return usuarios.get(u.getID()) != null;
    }

}
