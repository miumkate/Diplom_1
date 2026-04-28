import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import praktikum.IngredientType;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class IngredientTypeTest {

    @ParameterizedTest
    @EnumSource(IngredientType.class)
    public void testIngredientTypeValues(IngredientType type) {
        assertNotNull(type);
    }

    @Test
    public void sauceTest(){
        assertEquals(IngredientType.SAUCE, IngredientType.values()[0]);

    }

    @Test
    public void fillingTest(){
        assertEquals(IngredientType.FILLING, IngredientType.values()[1]);
    }
}
