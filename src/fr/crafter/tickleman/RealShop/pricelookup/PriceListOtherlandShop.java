
package fr.crafter.tickleman.RealShop.pricelookup;

import fr.crafter.tickleman.RealPlugin.RealChest;
import fr.crafter.tickleman.RealPlugin.RealInventory;
import fr.crafter.tickleman.RealShop.RealPrice;
import fr.crafter.tickleman.RealShop.RealShop;
import fr.crafter.tickleman.RealShop.RealShopPlugin;

public class PriceListOtherlandShop extends RealPriceList {
	/* Constants for price calculation */
	private final int categoryValue = 1000000000;
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
	public RealPrice getPrice( String typeIdDamage, int amount ) {
		return new RealPrice( getBuyingPrice( typeIdDamage ), getSellingPrice( typeIdDamage ) );
	}

	private double getSellingPrice( String typeIdDamage ) {
		String category = getCategory( typeIdDamage );
		return getCategoryValue( category ) / (getAmountInShop( typeIdDamage ) + 1);
	}

	private double getBuyingPrice( String typeIdDamage ) {
		String category = getCategory( typeIdDamage );
		return (1 + getCategoryTax( category )) * (getCategoryValue( category ) / getAmountInShop( typeIdDamage ));
	}

	private String getCategory( String typeIdDamage ) {
		return "default";
	}

	private int getAmountInShop( String typeIdDamage ) {
		Integer typeId;
		if( typeIdDamage.contains( ":" ) ) {
			// Price of item without damage code
			typeId = Integer.parseInt( typeIdDamage.split( ":" )[0] );
		} else {
			typeId = Integer.parseInt( typeIdDamage );
		}

		RealChest chest = RealChest.create( this.plugin.getServer().getWorld( this.shop.world ), this.shop.posX, this.shop.posY, this.shop.posZ );
		RealInventory inv = RealInventory.create( chest );
		return inv.getAmount( typeId );
	}

	private int getCategoryValue( String category ) {
		return this.categoryValue;
	}

	private double getCategoryTax( String category ) {
		return 0.0;
	}
}
