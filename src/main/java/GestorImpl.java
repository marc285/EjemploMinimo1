import java.util.*;

import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;

public class GestorImpl implements Gestor {

    //-------------------Estructuras del gestor-----------------------//
    private HashMap<String, Usuario> tablaUsuarios = new HashMap<String, Usuario>();
    private Queue<Pedido> colaPedidos = new LinkedList<Pedido>();
    private List<Producto> listaProductos = new LinkedList<Producto>();
    private Logger log = LogManager.getLogger(GestorImpl.class);

    public LinkedList<Producto> productosOrdPrecio() throws NoProductosException{
        //ORDENADOS ASCENDENTEMENTE
        if(checkProductos()){
            List<Producto> res = this.listaProductos;
            res.sort(new Comparator<Producto>() {
                @Override
                public int compare(Producto p1, Producto p2) {
                    return Double.compare(p1.getPrecio(), p2.getPrecio());
                }
            });
            return (LinkedList<Producto>) res;
        }
        else{
            log.error("Error. Lista de productos vacía.");
            throw new NoProductosException();
        }
    }

    public void anotarPedido(Pedido p) {
        colaPedidos.add(p);
    }

    public void servirPedido() throws NoPedidosException{
        if(chechPedidos()){
            Pedido p = this.colaPedidos.poll();
            log.info("pedido"+p);
            procesarPedido(p);
        }
        else {
            log.error("Error. Cola de pedidos vacía.");
            throw new NoPedidosException();
        }
    }

    public LinkedList<Pedido> pedidosPorUsuario(String idUser) throws NoUsuarioException, NoPedidosException{
        log.info("idUser: "+ idUser);
        if(tablaUsuarios.get(idUser) == null){
            log.error("Error. No se ha encontrado al usuario.");
            throw new NoUsuarioException();
        }
        else
            if(tablaUsuarios.get(idUser).getRegistroPedidos() == null) {
                log.error("Error. Cola de pedidos del usuario vacía.");
                throw new NoPedidosException();
            }
            else
                return this.tablaUsuarios.get(idUser).getRegistroPedidos();
    }

    public LinkedList<Producto> productosOrdVentas() throws NoProductosException{
        //ORDENADOS DESCENDENTEMENTE
        if(checkProductos()){
            List<Producto> res = this.listaProductos;
            res.sort(new Comparator<Producto>() {
                @Override
                public int compare(Producto p1, Producto p2) {
                    return Integer.compare(p2.getVentas(), p1.getVentas());
                }
            });
            return (LinkedList<Producto>) res;
        }
        else{
            log.error("Error. Lista de productos vacía.");
            throw new NoProductosException();
        }
    }

    //-------------------Funciones para modificar los atributos------------------//
    public void addUsuario(String k, String nom){
        Usuario u = new Usuario(k,nom);
        this.tablaUsuarios.put(k,u);
    }
    public HashMap<String, Usuario> getUsuarios(){
        return this.tablaUsuarios;
    }

    public void addProducto(String nombre, double precio) {
        Producto p = new Producto(nombre, precio);
        this.listaProductos.add(p);
    }
    public LinkedList<Producto> getProductos(){
        return (LinkedList<Producto>) this.listaProductos;
    }

    public Producto getProducto(String idProducto) {
        for (Producto p: this.listaProductos) {
            if (p.getID().equals(idProducto)) return p;
        }
        return null;
    }

    //-------------------------Funciones auxiliares------------------------------//
    private boolean chechPedidos(){ //TRUE SI HAY PEDIDOS (NO ESTÁ VACÍA)
        return this.colaPedidos.size() != 0;
    }

    private boolean checkProductos(){ //TRUE SI HAY PRODUCTOS (NO ESTÁ VACÍA)
        return this.listaProductos.size() != 0;
    }

    private void procesarPedido(Pedido p){
        String idusr = p.getIDUsuario();
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
}
