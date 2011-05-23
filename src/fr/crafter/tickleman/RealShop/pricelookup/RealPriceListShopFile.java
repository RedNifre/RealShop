
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
		if( shop == null ) {
			return null;
		}
		String shopId = shop.world + ";" + shop.posX + ";" + shop.posY + ";" + shop.posZ;
		return new RealPriceListShopFile( plugin, shopId + ".prices" );
	}
}
