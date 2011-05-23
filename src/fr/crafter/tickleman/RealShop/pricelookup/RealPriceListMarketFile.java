/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.crafter.tickleman.RealShop.pricelookup;

import fr.crafter.tickleman.RealShop.RealShop;
import fr.crafter.tickleman.RealShop.RealShopPlugin;

public class RealPriceListMarketFile extends RealPriceListFile {
	protected RealPriceListMarketFile( RealShopPlugin plugin ) {
		super( plugin, "market" );
	}

	public static RealPriceListMarketFile createInstance( RealShopPlugin plugin, RealShop shop, String playerName, RealPriceLookupChain lookupChain ) {
		if( pricesFileExists( plugin, "market" ) ) {
			return new RealPriceListMarketFile( plugin );
		} else {
			return null;
		}
	}
}
