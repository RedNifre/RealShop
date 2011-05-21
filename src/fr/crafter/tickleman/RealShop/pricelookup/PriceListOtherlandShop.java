
package fr.crafter.tickleman.RealShop.pricelookup;

import fr.crafter.tickleman.RealPlugin.RealChest;
import fr.crafter.tickleman.RealPlugin.RealInventory;
import fr.crafter.tickleman.RealShop.RealPrice;
import fr.crafter.tickleman.RealShop.RealShop;
import fr.crafter.tickleman.RealShop.RealShopPlugin;

public class PriceListOtherlandShop extends RealPriceList {
	/* Constants for price calculation */
	private final double categoryValue = 1000000000;
	/* Other variables */
	private RealShopPlugin plugin;
	private RealShop shop;

	private PriceListOtherlandShop( RealShopPlugin plugin, RealShop shop ) {
		this.plugin = plugin;
		this.shop = shop;
	}

	public static PriceListOtherlandShop createInstance( RealShopPlugin plugin, RealShop shop, String playerName, RealPriceLookupChain lookupChain ) {
		return new PriceListOtherlandShop( plugin, shop );
	}

	@Override
	public RealPrice getPrice( String typeIdDamage ) {
		return new RealPrice( getBuyingPrice( typeIdDamage ), getSellingPrice( typeIdDamage ) );
	}

	private double getSellingPrice( String typeIdDamage ) {
		// Amount of item in this shop
		// Get chest
		// get its inventory
		// Convert typeIdDamage-String to typeId-int
		// call inventory.getAmount(typeId);
		int amount = 0;
		return this.categoryValue / (amount + 1);
	}

	private double getBuyingPrice( String typeIdDamage ) {
		int categoryTax = 0;
		int amount = 0;
		return (1 + categoryTax) * (this.categoryValue / amount);
	}

	private int getAmountInShopOf( String typeIdDamage ) {
		Integer typeId;
		if( typeIdDamage.contains( ":" ) ) {
			// item without damage code price
			typeId = Integer.parseInt( typeIdDamage.split( ":" )[0] );
		} else {
			typeId = Integer.parseInt( typeIdDamage );
		}

		RealChest chest = RealChest.create( this.plugin.getServer().getWorld( this.shop.world ), this.shop.posX, this.shop.posY, this.shop.posZ );
		RealInventory inv = RealInventory.create( chest );
		return inv.getAmount( typeId );
	}
}
