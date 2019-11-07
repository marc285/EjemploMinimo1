import java.util.*;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class GestorImpl implements Gestor {

    //Estructuras del gestor
    private HashMap<String, Usuario> tablaUsuarios = new HashMap<String, Usuario>();
    private LinkedList<Pedido> colaPedidos = new LinkedList<Pedido>();
    private LinkedList<Producto> listaProductos = new LinkedList<Producto>();
    private Logger log = LogManager.getLogger(GestorImpl.class);

    public LinkedList<Producto> productosOrdPrecio() throws NoPedidosException{
        if(chechPedidos()){
            log.error("Error. Cola de pedidos vacía.");
            throw new NoPedidosException();
        }
        else{
            LinkedList<Producto> res = new LinkedList<Producto>();
            this.listaProductos.sort(new Comparator<Producto>() {
                public int compare(Producto prod, Producto t1) {
                    return ((int)(prod.getPrecio() - t1.getPrecio()));
                }
            });
            return res;
        }
    }

    public void anotarPedido(Pedido p) {
        colaPedidos.addLast(p);
    }

    public void servirPedido() throws NoPedidosException{
        if(chechPedidos()){
            log.error("Error. Cola de pedidos vacía.");
            throw new NoPedidosException();
        }
        else {
            Pedido p = this.colaPedidos.removeFirst();

            //----Procesar pedido
            procesarPedido(p);
        }
    }

    public LinkedList<Pedido> pedidosPorUsuario(String idUser) throws NoUsuarioException, NoPedidosException{
        if(tablaUsuarios.get(idUser) == null){
            log.error("Error. No se ha encontrado al usuario.");
            throw new NoUsuarioException();
        }
        else
            if(chechPedidos()) {
                log.error("Error. Cola de pedidos vacía.");
                throw new NoPedidosException();
            }
            else
                return this.tablaUsuarios.get(idUser).getPedidos();
    }

    public LinkedList<Producto> productosOrdVentas() throws NoPedidosException{
        if(chechPedidos()){
            log.error("Error. Cola de pedidos vacía.");
            throw new NoPedidosException();
        }
        else{
            LinkedList<Producto> res = new LinkedList<Producto>();
            this.listaProductos.sort(new Comparator<Producto>() {
                public int compare(Producto prod, Producto t1) {
                    return ((int)(prod.getVentas() - t1.getVentas()));
                }
            });
            return res;
        }
    }




    //Funciones para modificar los atributos
    public void addUsuario(String k, String nom){
        Usuario u = new Usuario(k,nom);
        this.tablaUsuarios.put(k,u);
    }
    public HashMap<String, Usuario> getUsuarios(){
        return this.tablaUsuarios;
    }

    public void addProducto(String nombre, double precio){
        Producto p = new Producto(nombre,precio);
        this.listaProductos.push(p);
    }
    public LinkedList<Producto> getProductos(){
        return this.listaProductos;
    }

    //Funciones auxiliares
    private boolean chechPedidos(){ //Retorna false si la cola de pedidos está vacía
        return this.colaPedidos.size() != 0;
    }

    private void procesarPedido(Pedido p){
        String idusr = p.getIDUsuario();
        //Usuario usuario = pedido.getUser(); ----------------------????
        Usuario usr = this.tablaUsuarios.get(idusr);
        usr.registrarPedido(p);

        for (int i = 0; i < p.getLPsize(); i++){
            LP lp = p.getLP(i);
            String idprod = lp.getIdProducto();
            int num = lp.getNumPedidos();

            //Buscar producto
            for (Producto pr : this.listaProductos) {
                if (pr.getID().equals(idprod))
                    pr.incrementarVentas(num);
            }

        }
    }

    /*private LinkedList<Pedido> checkPedidoPorUsuario(String us){
        LinkedList<Pedido> res = new LinkedList<Pedido>();
        for (Pedido p : this.colaPedidos)
            if (p.getIDUsuario().equals(us))
                res.addLast(p);

        return res;
    }*/
}
