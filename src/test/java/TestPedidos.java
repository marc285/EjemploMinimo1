import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;

public class TestPedidos {
    HashMap<String, Usuario> usuarios = null;
    LinkedList<Pedido> colaPedidos = null;

    @Before
    public void setUp (){
        usuarios = new HashMap<String, Usuario>();
        colaPedidos = new LinkedList<Pedido>();

        Producto chocolatina = new Producto("chocolatina", 0, 1.5);
        Producto cola = new Producto("cocacola", 0, 1);
        Producto BocadilloJamon = new Producto("bocjamon", 0, 3.5);


    }

    @Test
    public void anotarPedidoTest(){

    }

}
