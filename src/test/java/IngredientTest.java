import org.junit.jupiter.api.Test;
import praktikum.Ingredient;
import praktikum.IngredientType;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class IngredientTest {

    @Test
    public void typeIngredientTest(){
        IngredientType filling = IngredientType.FILLING;
        Ingredient ingredient = new Ingredient(filling, "Сальчичон", 100);
        assertEquals(filling, ingredient.getType(), "Ожидается "+filling);
    }

    @Test
    public void nameIngredientTest(){
        String sauce = "Бальзамический уксус";
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, sauce, 20);
        assertEquals(sauce, ingredient.getName(), "Ожидается "+sauce);
    }

    @Test
    public void priceIngredientTest(){
        int price = 50;
        Ingredient ingredient = new Ingredient(IngredientType.SAUCE, "Медово-Ореховый соус", price);
        assertEquals(price, ingredient.getPrice(), "Ожидается "+price);
    }
}
