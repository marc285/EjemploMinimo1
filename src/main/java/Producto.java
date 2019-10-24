public class Producto {
    private String idProducto;
    private int numventas;
    private double precio;

    public Producto(String id, double prec){
        this.idProducto = id;
        this.numventas = 0;
        this.precio = prec;
    }

    public String getID(){ return this.idProducto; }
    public int getVentas(){
        return this.numventas;
    }
    public double getPrecio(){
        return this.precio;
    }


    public void incrementarVentas(int n){//
        this.numventas = this.numventas + n;
    }

}
