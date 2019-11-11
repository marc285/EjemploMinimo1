public class LP{ //Dupla Pedido-Cantidad
    private int numPedidos;
    private String idProducto;

    public LP(int n, String id) {
        this.numPedidos = n;
        this.idProducto = id;
    }

    public int getNumPedidos(){ return this.numPedidos; }

    public String getIdProducto(){
        return this.idProducto;
    }
}
