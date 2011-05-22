
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
		return new RealPrice( getBuyingPrice( typeIdDamage, amount ), getSellingPrice( typeIdDamage, amount ) );
	}

	private double getSellingPrice( String typeIdDamage, int amount ) {
		String category = getCategory( typeIdDamage );
		double catValue = getCategoryValue( category );
		int startAmountInShop = getAmountInShop( typeIdDamage );
		double price = 0;
		for( int amountInShop = startAmountInShop; amountInShop > startAmountInShop - amount; amountInShop-- ) {
			price += sellingFormula( catValue, amountInShop );
		}
		return price;
//		return sellingFormula( catValue, startAmountInShop );
//		return getCategoryValue( category ) / (getAmountInShop( typeIdDamage ) + 1);
	}

	private double sellingFormula( double categoryValue, int amountInShop ) {
		return categoryValue / (amountInShop + 1);
	}

	private double getBuyingPrice( String typeIdDamage, int amount ) {
		String category = getCategory( typeIdDamage );
		double tax = getCategoryTax( category );
		double catValue = getCategoryValue( category );
		int startAmountInShop = getAmountInShop( typeIdDamage );
		double price = 0;
		for( int amountInShop = startAmountInShop; amountInShop < startAmountInShop + amount; amountInShop++ ) {
			price += buyingFormula( tax, catValue, amountInShop );
		}
		return price;
//		return (1 + getCategoryTax( category )) * (getCategoryValue( category ) / getAmountInShop( typeIdDamage ));
	}

	private double buyingFormula( double categoryTax, double categoryValue, int amountInShop ) {
		return (1 + categoryTax) * (categoryValue / amountInShop);

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
