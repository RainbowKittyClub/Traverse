package com.terraformersmc.traverse.boat;

import com.terraformersmc.terraform.boat.api.item.TerraformBoatItemHelper;
import com.terraformersmc.traverse.Traverse;
import net.minecraft.resources.Identifier;
import net.minecraft.world.item.Item;
import net.minecraft.world.level.storage.loot.providers.number.ints.ContextIntProviders;

public class TraverseBoats {
	public static final Identifier FIR = Identifier.fromNamespaceAndPath(Traverse.MOD_ID, "fir");
	public static final Item FIR_BOAT = TerraformBoatItemHelper.registerBoatItem(FIR, boatProperties(), false, false);
	public static final Item FIR_CHEST_BOAT = TerraformBoatItemHelper.registerBoatItem(FIR, boatProperties(), true, false);

	public static void register() {
	}

	private static Item.Properties boatProperties() {
		return new Item.Properties().cookingFuel(ContextIntProviders.COOKING_TIME_BOATS).stacksTo(1);
	}
}
