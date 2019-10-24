import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class TestPedidos {

    public GestorImpl gestor = null;
    public Pedido p = null;

    @Before
    public void setUp (){
        gestor = new GestorImpl();

        gestor.addUsuario("12345","Toni");

        gestor.addProducto("chocolatina",1);
        gestor.addProducto("cocacola",1.5);
        gestor.addProducto("bocadillo_jamon", 3.5);
        gestor.addProducto("zumo_naranja", 1.2);

        p = new Pedido("12345");
        p.addLP(3, "chocolatina");
        p.addLP(1, "zumo_naranja");
        p.addLP(1, "bocadillo_jamon");

    }

    @Test
    public void anotarPedidoTest() {
        gestor.anotarPedido(p);

        //Comprobar que se ha hecho bien el pedido
        Assert.assertEquals("12345", p.getIDUsuario());
        Assert.assertEquals("chocolatina", p.getLP(0).getIdProducto());
        Assert.assertEquals("bocadillo_jamon", p.getLP(p.getLPsize() - 1).getIdProducto());

        //Comprobamos que se han introducido bien los datos en las estructuras del gestor
        Assert.assertEquals("Toni", gestor.getUsuarios().get("12345").getID());

    }

    @Test
    public void servirPedidoTest(){
        Assert.assertEquals(4, gestor.getProductos().getFirst().getVentas());
        Assert.assertEquals(1, gestor.getProductos().getLast().getVentas());
    }

    @Test
    public void ordenarProdPrecioTest() throws NoPedidosException {
        LinkedList<Producto> prueba = gestor.productosOrdPrecio();
        Assert.assertEquals("bocadillo_jamon", prueba.pop().getID());
    }

    //@Test(expected = NoPedidosException.class)


}
