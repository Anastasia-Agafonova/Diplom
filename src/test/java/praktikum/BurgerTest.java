package praktikum;

import org.junit.Test;
import static org.junit.Assert.*;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class BurgerTest {

    @Test
    public void setBunsShouldSetBun() {
        Burger burger = new Burger();
        Bun bun = new Bun("black bun", 100);

        burger.setBuns(bun);

        assertEquals(bun, burger.bun);
    }

    @Test
    public void addIngredientShouldAddIngredientToList() {
        Burger burger = new Burger();
        Ingredient ingredient = new Ingredient(IngredientType.FILLING, "cutlet", 100);

        burger.addIngredient(ingredient);

        assertEquals(1, burger.ingredients.size());
        assertSame(ingredient, burger.ingredients.get(0));
    }

    @Test
    public void removeIngredientShouldRemoveIngredientFromList() {
        Burger burger = new Burger();
        Ingredient ingredient1 = new Ingredient(IngredientType.FILLING, "cutlet", 100);
        Ingredient ingredient2 = new Ingredient(IngredientType.SAUCE, "hot sauce", 100);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);

        burger.removeIngredient(0);

        assertEquals(1, burger.ingredients.size());
        assertSame(ingredient2, burger.ingredients.get(0));
    }

    @Test
    public void moveIngredientShouldMoveIngredientToNewPosition() {
        Burger burger = new Burger();

        Ingredient ingredient1 = new Ingredient(IngredientType.FILLING, "cutlet", 100);
        Ingredient ingredient2 = new Ingredient(IngredientType.SAUCE, "hot sauce", 100);
        Ingredient ingredient3 = new Ingredient(IngredientType.FILLING, "sausage", 300);

        burger.addIngredient(ingredient1);
        burger.addIngredient(ingredient2);
        burger.addIngredient(ingredient3);

        burger.moveIngredient(0, 2);

        assertSame(ingredient2, burger.ingredients.get(0));
        assertSame(ingredient3, burger.ingredients.get(1));
        assertSame(ingredient1, burger.ingredients.get(2));
    }

    @Test
    public void getPriceShouldReturnCorrectPrice() {
        Burger burger = new Burger();

        Bun bun = mock(Bun.class);
        Ingredient ingredient = mock(Ingredient.class);

        when(bun.getPrice()).thenReturn(100f);
        when(ingredient.getPrice()).thenReturn(100f);

        burger.setBuns(bun);
        burger.addIngredient(ingredient);

        float actualPrice = burger.getPrice();

        assertEquals(300f, actualPrice, 0.001f);
    }

    @Test
    public void getReceiptShouldReturnCorrectReceipt() {
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

        String receipt = burger.getReceipt();

        assertTrue(receipt.contains("black bun"));
        assertTrue(receipt.contains("cutlet"));
        assertTrue(receipt.contains("filling"));
        assertTrue(receipt.contains("300"));
    }
}
