
package fr.crafter.tickleman.RealShopTests;

import fr.crafter.tickleman.RealShop.RealPrice;
import fr.crafter.tickleman.RealShop.RealShop;
import fr.crafter.tickleman.RealShop.RealShopPlugin;
import fr.crafter.tickleman.RealShop.pricelookup.RealPriceList;
import fr.crafter.tickleman.RealShop.pricelookup.RealPriceLookupChain;

public class AddOneList extends RealPriceList {
	@Override
	public RealPrice getPrice( String typeIdDamage, int amount ) {
		return new RealPrice( 3.0 * amount, 4.5 * amount );
	}

	public static RealPriceList createInstance( RealShopPlugin plugin, RealShop shop, String playerName, RealPriceLookupChain lookupChain ) {
		return new AddOneList();
	}
}