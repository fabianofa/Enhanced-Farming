package com.mrbysco.enhancedfarming.datagen.data;

import com.google.gson.JsonObject;
import com.mrbysco.enhancedfarming.init.FarmingRegistry;
import com.mrbysco.enhancedfarming.init.conditions.CropToSeedCondition;
import com.mrbysco.enhancedfarming.init.conditions.RakeEnabledCondition;
import net.minecraft.data.CachedOutput;
import net.minecraft.data.PackOutput;
import net.minecraft.data.recipes.FinishedRecipe;
import net.minecraft.data.recipes.RecipeCategory;
import net.minecraft.data.recipes.RecipeProvider;
import net.minecraft.data.recipes.ShapedRecipeBuilder;
import net.minecraft.data.recipes.ShapelessRecipeBuilder;
import net.minecraft.resources.ResourceLocation;
import net.minecraft.tags.ItemTags;
import net.minecraft.tags.TagKey;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.Items;
import net.minecraft.world.item.alchemy.PotionUtils;
import net.minecraft.world.item.alchemy.Potions;
import net.minecraft.world.level.ItemLike;
import net.minecraftforge.common.Tags;
import net.minecraftforge.common.crafting.ConditionalRecipe;
import net.minecraftforge.common.crafting.StrictNBTIngredient;
import net.minecraftforge.registries.RegistryObject;
import org.jetbrains.annotations.Nullable;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.function.Consumer;

public class FarmingRecipeProvider extends RecipeProvider {
	public FarmingRecipeProvider(PackOutput packOutput) {
		super(packOutput);
	}

	@Override
	protected void buildRecipes(Consumer<FinishedRecipe> consumer) {
		generateSapling(consumer, FarmingRegistry.APPLE_SAPLING_ITEM, Items.OAK_SAPLING, "fruits/apple");
		generateSapling(consumer, FarmingRegistry.LEMON_SAPLING_ITEM, Items.OAK_SAPLING, "fruits/lemon");
		generateSapling(consumer, FarmingRegistry.ORANGE_SAPLING_ITEM, Items.OAK_SAPLING, "fruits/orange");
		generateSapling(consumer, FarmingRegistry.CHERRY_SAPLING_ITEM, Items.OAK_SAPLING, "fruits/cherry");
		generateSapling(consumer, FarmingRegistry.PEAR_SAPLING_ITEM, Items.OAK_SAPLING, "fruits/pear");
		generateSapling(consumer, FarmingRegistry.BANANA_SAPLING_ITEM, Items.JUNGLE_SAPLING, "fruits/banana");
		generateSapling(consumer, FarmingRegistry.AVOCADO_SAPLING_ITEM, Items.ACACIA_SAPLING, "fruits/avocado");
		generateSapling(consumer, FarmingRegistry.MANGO_SAPLING_ITEM, Items.OAK_SAPLING, "fruits/mango");
		generateSapling(consumer, FarmingRegistry.OLIVE_SAPLING_ITEM, Items.ACACIA_SAPLING, "vegetables/olive");

		generatePie(consumer, FarmingRegistry.APPLE_PIE, "sugar", "eggs");
		generatePie(consumer, FarmingRegistry.BANANA_PIE, "sugar", "eggs", "milk");
		generatePie(consumer, FarmingRegistry.CHERRY_PIE, "sugar", "eggs");
		generatePie(consumer, FarmingRegistry.GRAPE_PIE, "sugar", "eggs");
		generatePie(consumer, FarmingRegistry.LEMON_PIE, "sugar", "eggs");
		generatePie(consumer, FarmingRegistry.PEAR_PIE, "sugar", "eggs");
		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, FarmingRegistry.BACON_AND_EGG_PIE.get())
				.requires(createTag("raw_beef"))
				.requires(createTag("eggs"))
				.requires(createTag("dough/wheat"))
				.unlockedBy("has_beef", has(createTag("raw_beef")))
				.unlockedBy("has_egg", has(createTag("eggs")))
				.unlockedBy("has_dough", has(createTag("dough/wheat")))
				.save(consumer, FarmingRegistry.BACON_AND_EGG_PIE.getId().withPrefix("pie/"));

		generateJuice(consumer, FarmingRegistry.APPLE_JUICE, "fruits/apple");
		generateJuice(consumer, FarmingRegistry.LEMONADE, "fruits/lemon");
		generateJuice(consumer, FarmingRegistry.ORANGE_JUICE, "fruits/orange");
		generateJuice(consumer, FarmingRegistry.CHERRY_JUICE, "fruits/cherry");
		generateJuice(consumer, FarmingRegistry.PEAR_JUICE, "fruits/pear");
		generateJuice(consumer, FarmingRegistry.BANANA_JUICE, "fruits/banana");
		generateJuice(consumer, FarmingRegistry.GRAPE_JUICE, "fruits/grape");
		generateJuice(consumer, FarmingRegistry.MANGO_JUICE, "fruits/mango");
		generateJuice(consumer, FarmingRegistry.PINEAPPLE_JUICE, "fruits/pineapple");

		generateSmoothie(consumer, FarmingRegistry.SMOOTHIE_APPLE, "fruits/apple");
		generateSmoothie(consumer, FarmingRegistry.SMOOTHIE_BANANA, "fruits/banana");
		generateSmoothie(consumer, FarmingRegistry.SMOOTHIE_CHERRY, "fruits/cherry");
		generateSmoothie(consumer, FarmingRegistry.SMOOTHIE_CUCUMBER, "vegetables/cucumber");
		generateSmoothie(consumer, FarmingRegistry.SMOOTHIE_GRAPE, "fruits/grapes");
		generateSmoothie(consumer, FarmingRegistry.SMOOTHIE_LEMON, "fruits/lemon");
		generateSmoothie(consumer, FarmingRegistry.SMOOTHIE_MANGO, "fruits/mango");
		generateSmoothie(consumer, FarmingRegistry.SMOOTHIE_ORANGE, "fruits/orange");
		generateSmoothie(consumer, FarmingRegistry.SMOOTHIE_PEAR, "fruits/pear");
		generateSmoothie(consumer, FarmingRegistry.SMOOTHIE_PINEAPPLE, "fruits/pineapple");

		generateSeed(consumer, FarmingRegistry.TOMATO_SEEDS, FarmingRegistry.TOMATO.get());
		generateSeed(consumer, FarmingRegistry.CUCUMBER_SEEDS, FarmingRegistry.CUCUMBER.get());
		generateSeed(consumer, FarmingRegistry.AUBERGINE_SEEDS, FarmingRegistry.AUBERGINE.get());
		generateSeed(consumer, FarmingRegistry.GRAPE_SEEDS, FarmingRegistry.GRAPES.get());
		generateSeed(consumer, FarmingRegistry.PINEAPPLE_SEEDS, FarmingRegistry.PINEAPPLE.get());
		generateSeed(consumer, FarmingRegistry.CORN_SEEDS, FarmingRegistry.CORN.get());
		generateSeed(consumer, FarmingRegistry.ONION_SEEDS, FarmingRegistry.ONION.get());
		generateSeed(consumer, FarmingRegistry.GARLIC_SEEDS, FarmingRegistry.GARLIC.get());
		generateSeed(consumer, FarmingRegistry.LETTUCE_SEEDS, FarmingRegistry.LETTUCE.get());

		generateRake(consumer, FarmingRegistry.WOODEN_RAKE, ItemTags.PLANKS);
		generateRake(consumer, FarmingRegistry.STONE_RAKE, ItemTags.STONE_TOOL_MATERIALS);
		generateRake(consumer, FarmingRegistry.IRON_RAKE, Tags.Items.INGOTS_IRON);
		generateRake(consumer, FarmingRegistry.GOLD_RAKE, Tags.Items.INGOTS_GOLD);
		generateRake(consumer, FarmingRegistry.DIAMOND_RAKE, Tags.Items.GEMS_DIAMOND);
	}

	private void generateJuice(Consumer<FinishedRecipe> consumer, RegistryObject<Item> juice, String tag) {
		TagKey<Item> itemTag = createTag(tag);
		ItemStack waterBottle = PotionUtils.setPotion(new ItemStack(Items.POTION), Potions.WATER);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, juice.get())
				.requires(StrictNBTIngredient.of(waterBottle))
				.requires(itemTag)
				.unlockedBy("has_item", has(itemTag))
				.save(consumer, juice.getId().withPrefix("juice/"));
	}

	private void generateSmoothie(Consumer<FinishedRecipe> consumer, RegistryObject<Item> juice, String tag) {
		TagKey<Item> itemTag = createTag(tag);

		ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, juice.get())
				.requires(FarmingRegistry.MILK_BOTTLE.get())
				.requires(itemTag)
				.requires(Items.SNOWBALL)
				.unlockedBy("has_milk_bottle", has(FarmingRegistry.MILK_BOTTLE.get()))
				.unlockedBy("has_item", has(itemTag))
				.unlockedBy("has_snowball", has(Items.SNOWBALL))
				.save(consumer, juice.getId().withPrefix("smoothie/"));
	}

	private void generatePie(Consumer<FinishedRecipe> consumer, RegistryObject<Item> juice, String... tags) {
		List<TagKey<Item>> itemTags = Arrays.stream(tags).map(tag -> createTag(tag)).toList();

		ShapelessRecipeBuilder builder = ShapelessRecipeBuilder.shapeless(RecipeCategory.FOOD, juice.get())
				.requires(FarmingRegistry.FLOUR.get())
				.unlockedBy("has_flour", has(FarmingRegistry.FLOUR.get()));
		for (TagKey<Item> itemTag : itemTags) {
			builder = builder.requires(itemTag)
					.unlockedBy("has_" + itemTag.location().getPath().replace(":", "_"), has(itemTag));
		}
		builder.save(consumer, juice.getId().withPrefix("pie/"));
	}

	private void generateSapling(Consumer<FinishedRecipe> consumer, RegistryObject<Item> newSapling, ItemLike sapling, String tag) {
		TagKey<Item> itemTag = createTag(tag);
		ConditionalRecipe.builder()
				.addCondition(new CropToSeedCondition())
				.addRecipe(ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, newSapling.get())
						.requires(sapling)
						.requires(itemTag)
						.unlockedBy("has_sapling", has(sapling))
						.unlockedBy("has_item", has(itemTag))::save
				).build(consumer, newSapling.getId().withPrefix("sapling/"));
	}

	private void generateSeed(Consumer<FinishedRecipe> consumer, RegistryObject<Item> seed, ItemLike item) {
		ConditionalRecipe.builder()
				.addCondition(new CropToSeedCondition())
				.addRecipe(ShapelessRecipeBuilder.shapeless(RecipeCategory.MISC, seed.get())
						.requires(item)
						.unlockedBy("has_item", has(item))::save
				).build(consumer, seed.getId().withPrefix("seed/"));
	}

	private void generateRake(Consumer<FinishedRecipe> consumer, RegistryObject<Item> rake, TagKey<Item> material) {
		ConditionalRecipe.builder()
				.addCondition(new RakeEnabledCondition())
				.addRecipe(
						ShapedRecipeBuilder.shaped(RecipeCategory.TOOLS, rake.get())
								.pattern("X X")
								.pattern("XSX")
								.pattern(" S ")
								.define('X', material)
								.define('S', Tags.Items.RODS_WOODEN)
								.unlockedBy("has_material", has(material))
								.unlockedBy("has_stick", has(Tags.Items.RODS_WOODEN))
								::save
				).build(consumer, rake.getId().withPrefix("rake/"));
	}

	private TagKey<Item> createTag(String name) {
		return ItemTags.create(new ResourceLocation("forge", name));
	}

	@Override
	protected @Nullable CompletableFuture<?> saveAdvancement(CachedOutput output, FinishedRecipe finishedRecipe, JsonObject advancementJson) {
		return null;
	}
}