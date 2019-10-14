public class Pedido {
    private Usuario user;
    private Producto product;

    public Pedido(Usuario us, Producto prod){
        this.user = us;
        this.product = prod;
    }

    public Usuario getUsuario(){
        return this.user;
    }
    public Producto getProducto(){
        return this.product;
    }
}
