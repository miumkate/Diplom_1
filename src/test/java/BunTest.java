import org.junit.jupiter.api.Test;
import praktikum.Bun;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class BunTest {

    @Test
    public void getBunPriceTest(){
        int bunBrice = 213;
        Bun newBun = new Bun("Зерновая булочка",bunBrice);
        assertEquals(bunBrice,newBun.getPrice());
    }

    @Test
    public void getBunNameTest(){
        String bunName = "Булочка овсяная";
        Bun newBun = new Bun(bunName,341);
        assertEquals(bunName,newBun.getName());
    }
}
