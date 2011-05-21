
package fr.crafter.tickleman.RealShop.pricelookup;

import fr.crafter.tickleman.RealPlugin.RealItemStack;
import fr.crafter.tickleman.RealShop.RealPrice;
import fr.crafter.tickleman.RealShop.RealShop;
import fr.crafter.tickleman.RealShop.RealShopPlugin;

public class RealPriceListShopFile extends RealPriceListFile {
	private RealPriceListShopFile( RealShopPlugin plugin, String fileName ) {
		super( plugin, fileName );
	}

	public static RealPriceListShopFile createInstance( RealShopPlugin plugin, RealShop shop, String playerName, RealPriceLookupChain lookupChain ) {
		String shopId = shop.world + ";" + shop.posX + ";" + shop.posY + ";" + shop.posZ;
		return new RealPriceListShopFile( plugin, shopId + ".prices" );
	}

	@Override
	public RealPrice getPrice( String typeIdDamage ) {
		RealPrice price = this.prices.get( typeIdDamage );
		if( (price == null) && typeIdDamage.contains( ":" ) ) {
			// item without damage code price
			Integer typeId = Integer.parseInt( typeIdDamage.split( ":" )[0] );
			Short damage = Short.parseShort( typeIdDamage.split( ":" )[1] );
			// TODO: Might also ask the chain again, see original code
			price = this.prices.get( typeId.toString() );
			if( price != null ) {
				// apply a ratio from the damage amount
				try {
					price.damagedBuy = Math.max(
						0, price.buy - (price.buy * damage / RealItemStack.typeIdMaxDamage( typeId )) );
				} catch( Exception e ) {
				}
				try {
					price.damagedSell = Math.max(
						0, price.sell - (price.sell * damage / RealItemStack.typeIdMaxDamage( typeId )) );
				} catch( Exception e ) {
				}
			}
		}
		return price;
	}
}
