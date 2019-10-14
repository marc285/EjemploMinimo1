public class Producto {
    private String idProduct;
    private int numventas;
    private double precio;

    public Producto(String id, int vent, double prec){
        this.idProduct = id;
        this.numventas = vent;
        this.precio = prec;
    }

    public String getID(){
        return this.idProduct;
    }
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
