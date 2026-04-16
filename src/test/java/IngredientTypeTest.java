import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;
import praktikum.IngredientType;

public class IngredientTypeTest {

    @ParameterizedTest
    @EnumSource(IngredientType.class)
    void testIngredientTypeValues(IngredientType type) {

    }
}
