import java.util.List;

public interface Gestor {
    public List<Producto> productosOrdPrecio() throws NoPedidosException;
    public void anotarPedido(Pedido p);
    public Pedido servirPedido() throws NoPedidosException;
    public List<Pedido> pedidosPorUsuario(String idUser) throws NoUsuarioException , NoPedidosException;
    public List<Producto> productosOrdVentas() throws NoPedidosException;
}