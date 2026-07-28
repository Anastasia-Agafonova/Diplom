package praktikum;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

import java.util.Arrays;
import java.util.Collection;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(Parameterized.class)
public class BurgerReceiptTest {

    private final String expectedText;

    public BurgerReceiptTest(String expectedText) {
        this.expectedText = expectedText;
    }

    @Parameterized.Parameters(name = "Receipt should contain: {0}")
    public static Collection<Object[]> getReceiptData() {
        return Arrays.asList(new Object[][]{
                {"black bun"},
                {"cutlet"},
                {"filling"},
                {"300"}
        });
    }

    @Test
    public void getReceiptShouldContainExpectedText() {
        Burger burger = new Burger();

        Bun bun = mock(Bun.class);
        Ingredient ingredient = mock(Ingredient.class);

        when(bun.getName()).thenReturn("black bun");
        when(bun.getPrice()).thenReturn(100f);

        when(ingredient.getName()).thenReturn("cutlet");
        when(ingredient.getType()).thenReturn(IngredientType.FILLING);
        when(ingredient.getPrice()).thenReturn(100f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        assertTrue(burger.getReceipt().contains(expectedText));
    }
}
