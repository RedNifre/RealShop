
package fr.crafter.tickleman.RealShop.pricelookup;

import fr.crafter.tickleman.RealShop.RealPrice;
import fr.crafter.tickleman.RealShop.RealShop;
import fr.crafter.tickleman.RealShop.RealShopDailyLog;
import fr.crafter.tickleman.RealShop.RealShopPlugin;

public abstract class RealPriceList {
	/**
	 * Daily prices calculation, which always recalculates the prices.
	 * Convenience method for {@link dailyPricesCalculation(RealShopDailyLog, boolean)}.
	 * 
	 * @param dailyLog Log of the day's buying and selling transactions.
	 */
	void dailyPricesCalculation( RealShopDailyLog dailyLog ) {
		dailyPricesCalculation( dailyLog, false );
	}

	/**
	 * Daily price calculation. Takes care of:
	 * <ul>
	 * <li>The last day's transactions log</li>
	 * <li>The last items price</li>
	 * </ul>
	 * <strong>Note:</strong> The default implementation does nothing and can be overridden by subclasses.
	 * 
	 * @param dailyLog Log of the day's buying and selling transactions.
	 * @param simulation Indicates whether the prices recalculation should be actually done or just simulated.
	 */
	void dailyPricesCalculation( RealShopDailyLog dailyLog, boolean simulation ) {
	}

	/**
	 * Gets the price for the given object (ID + damage value, may be used for identification).
	 *
	 * @param typeIdDamage
	 * @return 
	 */
	public abstract RealPrice getPrice( String typeIdDamage, int amount );

	/**
	 * Factory method that creates an instance of the price list.
	 * Any of the given information may be used, as long as available;
	 * If a parameter may not be set this is documented in its description.
	 * If a parameter necessary for instantiating is not set, the factory method should return <code>null</code>.
	 * 
	 * @param plugin The used plugin.
	 * @param shop The shop for which the price list is requested. <em>May be null</em>.
	 * @param playerName The player whose price list is requested.
	 * @param lookupChain The lookup chain used. <em>May be null</em>. Especially, you may pass <code>null</code> for the chain if you use a price list directly.
	 * @return An object extending <code>RealPriceList</code> or <code>null</code>.
	 */
	public static RealPriceList createInstance( RealShopPlugin plugin, RealShop shop, String playerName, RealPriceLookupChain lookupChain ) {
		throw new UnsupportedOperationException( "You must implement this method." );
	}
}
