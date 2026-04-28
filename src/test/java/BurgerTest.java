import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import praktikum.*;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class BurgerTest {

    private Burger burger;
    private Bun bun;
    private Ingredient ingredient;
    private Ingredient ingredient1;

    @BeforeEach
    public void prepareBurger(){
        burger = new Burger();
        bun = Mockito.mock(Bun.class);
        ingredient  = Mockito.mock(Ingredient.class);
        ingredient1 = Mockito.mock(Ingredient.class);
    }

    @Test
    public void setBunTest(){
        burger.setBuns(bun);
        assertEquals(burger.bun,bun,"Ожидается совпадение булочек   " + bun);
    }

    @Test
    public void createBurgerWithDatabaseTest(){
        Database database = new Database();
        burger.setBuns(database.availableBuns().get(0));
        burger.addIngredient(database.availableIngredients().get(0));
        assertEquals(burger.bun,database.availableBuns().get(0),"Не совпали замоканные булочки.... ");
    }

    @Test
    public void createBurgerWithMockDatabaseTest(){
        Database database = Mockito.mock(Database.class);
        List<Ingredient> mockIngredients = new ArrayList<>();
        mockIngredients.add(new Ingredient(IngredientType.SAUCE, "mustard sauce", 111));
        Mockito.when(database.availableIngredients()).thenReturn(mockIngredients);

        List<Bun> mockBuns = new ArrayList<>();
        mockBuns.add(new Bun("seed bun", 222));
        Mockito.when(database.availableBuns()).thenReturn(mockBuns);
        burger.setBuns(database.availableBuns().get(0));
        burger.addIngredient(database.availableIngredients().get(0));
        assertEquals(burger.bun,database.availableBuns().get(0),"Не совпали замоканные булочки.... ");
    }

    @Test
    public void addNegativeIngredientTest(){
        burger.setBuns(null);
        burger.addIngredient(null);
        assertThrows(NullPointerException.class, () ->{
            burger.getPrice();
        });
    }

    @Test
    public void removeIngredientTest(){
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient1);
        int before = burger.ingredients.size();
        burger.removeIngredient(0);
        int after = burger.ingredients.size();
        assertNotEquals(before,after,"Удаление не произошло.");
    }

    @Test
    public void moveIngredientTest() {
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient1);
        Ingredient before = burger.ingredients.get(0);
        burger.moveIngredient(0, 1);
        Ingredient after = burger.ingredients.get(1);
        assertEquals(before,after,"Перемещение не произошло.");
    }

    @Test
    public void getReceiptTest(){
        float expectedPrice = 458F;
        String bunName = "Самая свежая булочка";
        String cheese = "Сыр чеддер";
        String ketchup = "Кетчуп";

        burger.setBuns(bun);
        burger.addIngredient(ingredient);
        burger.addIngredient(ingredient1);

        Mockito.when(bun.getName()).thenReturn(bunName);
        Mockito.when(ingredient.getType()).thenReturn(IngredientType.FILLING);
        Mockito.when(ingredient.getName()).thenReturn(cheese);
        Mockito.when(ingredient1.getType()).thenReturn(IngredientType.SAUCE);
        Mockito.when(ingredient1.getName()).thenReturn(ketchup);
        Mockito.when(burger.getPrice()).thenReturn(expectedPrice);


        String[] linesArray = burger.getReceipt().split("\n");

        assertTrue(linesArray[0].contains
                (String.format("(==== %s ====)",
                        bunName)));

        assertTrue(linesArray[1].contains
                (String.format("= %s %s =",
                        IngredientType.FILLING.toString().toLowerCase(),
                        cheese)));

        assertTrue(linesArray[2].contains
                (String.format("= %s %s =",
                        IngredientType.SAUCE.toString().toLowerCase(),
                        ketchup)));

        assertTrue(linesArray[3].contains
                (String.format("(==== %s ====)",
                        bunName)));

        assertTrue(linesArray[4].contains(""));


        assertTrue(linesArray[5].contains
                (String.format("Price: %f",
                        expectedPrice)));
    }

    @Test
    public void getPriceTest(){
        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        Mockito.when(bun.getPrice()).thenReturn(100F);
        Mockito.when(ingredient.getPrice()).thenReturn(20F);
        assertTrue(burger.getPrice() > 0,"Прайс должен быть положительным числом");
    }
}