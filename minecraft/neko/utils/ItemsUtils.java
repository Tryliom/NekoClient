package neko.utils;

import net.minecraft.item.Item;
import net.minecraft.util.ResourceLocation;

public class ItemsUtils {
	public static Item getRegisteredItem(String p_i1293_1_){
		return (Item)Item.itemRegistry.getObject(new ResourceLocation(p_i1293_1_));
	}
	
	public static final Item Lead = getRegisteredItem("lead"); 
	public static final Item Milk_Bucket = getRegisteredItem("milk_bucket");
	
}
