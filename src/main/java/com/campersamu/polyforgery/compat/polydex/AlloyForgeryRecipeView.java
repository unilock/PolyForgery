package com.campersamu.polyforgery.compat.polydex;

import eu.pb4.polydex.api.v1.recipe.AbstractRecipePolydexPage;
import eu.pb4.polydex.api.v1.recipe.PageBuilder;
import eu.pb4.polydex.api.v1.recipe.PolydexEntry;
import eu.pb4.polydex.api.v1.recipe.PolydexPage;
import eu.pb4.sgui.api.elements.GuiElementBuilder;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.recipe.Ingredient;
import net.minecraft.recipe.RecipeEntry;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Style;
import net.minecraft.text.Text;
import net.minecraft.util.Formatting;
import net.minecraft.util.collection.DefaultedList;
import org.jetbrains.annotations.Nullable;
import wraith.alloyforgery.recipe.AlloyForgeRecipe;

import java.util.List;

public class AlloyForgeryRecipeView extends AbstractRecipePolydexPage<AlloyForgeRecipe> {
    public AlloyForgeryRecipeView(RecipeEntry<AlloyForgeRecipe> recipe) {
        super(recipe);
    }

    public static void init() {
        PolydexPage.registerRecipeViewer(AlloyForgeRecipe.class, AlloyForgeryRecipeView::new);
    }

    @Override
    public ItemStack typeIcon(ServerPlayerEntity player) {
        return new GuiElementBuilder(Items.BRICKS)
                .setName(Text.translatable("container.alloy_forgery.rei.title"))
                .setLore(List.of(
                        Text.translatable("container.alloy_forgery.rei.min_tier", recipe.getMinForgeTier()).setStyle(Style.EMPTY.withColor(Formatting.GRAY)),
                        Text.translatable("container.alloy_forgery.rei.fuel_per_tick", recipe.getFuelPerTick()).setStyle(Style.EMPTY.withColor(Formatting.GRAY))
                ))
                .asStack();
    }

    @Override
    public void createPage(@Nullable PolydexEntry entry, ServerPlayerEntity player, PageBuilder builder) {
        DefaultedList<Ingredient> ingredients = recipe.getIngredients();

        for (int i = 0; i < 10; ++i) {
            builder.setIngredient(i % 3 + 2, i / 3 + 1, ingredients.get(i));
        }

        //noinspection deprecation
        builder.setOutput(6, 2, recipe.getOutput());
    }
}
