import javax.swing.*;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class GestorImpl implements Gestor {

    //Tabla de Hash de los Usuarios y cola (lista encadenada) de pedidos
    private HashMap<String, Usuario> usuarios = new HashMap<String, Usuario>();
    private LinkedList<Pedido> colaPedidos = new LinkedList<Pedido>();

    public LinkedList<Producto> productosOrdPrecio() throws NoPedidosException{
        if(chechPedidos())
            throw new NoPedidosException();
        else{
            LinkedList<Producto> res = new LinkedList<Producto>();
            //SORT
            return res;
        }
    }

    public void anotarPedido(Pedido p){
        if(checkUsuario(p.getUsuario().getID())){
            //--   ↓ log
            System.out.println(p.getUsuario().getID()+ ": usuario ya registrado anteriormente. No se volverá a registrar");
        }
        else { //Registramos al usuario en la tabla si no esta ya registrado
            usuarios.put(p.getUsuario().getID(), p.getUsuario());
            //colaPedidos.addLast(p);
            //----Incrementar numventas----//
        }
    }

    public Pedido servirPedido() throws NoPedidosException{
        if(chechPedidos())
            throw new NoPedidosException();
        else
            return this.colaPedidos.removeFirst();
    }

    public LinkedList<Pedido> pedidosPorUsuario(String idUser) throws NoUsuarioException, NoPedidosException{
        if(!checkUsuario(idUser)){
            throw new NoUsuarioException();
        }
        else
            if(chechPedidos())
                throw new NoPedidosException();
            else
                return checkPedidoPorUsuario(idUser);
    }

    public LinkedList<Producto> productosOrdVentas() throws NoPedidosException{
        if(chechPedidos())
            throw new NoPedidosException();
        else{
            LinkedList<Producto> res = new LinkedList<Producto>();
            //SORT
            return res;
        }
    }



    //Funciones complementarias
    private boolean checkUsuario(String idUser){ //Retorna true si ya está registrado el usuario
        return this.usuarios.get(idUser) != null;
    }

    private boolean chechPedidos(){ //Retorna false si la cola de pedidos está vacía
        return this.colaPedidos.size() != 0;
    }

    private LinkedList<Pedido> checkPedidoPorUsuario(String us){
        LinkedList<Pedido> res = new LinkedList<Pedido>();
        for (Pedido p : this.colaPedidos) {
            if (p.getUsuario().getID().equals(us))
                res.addLast(p);
        }
        return res;
    }
}
