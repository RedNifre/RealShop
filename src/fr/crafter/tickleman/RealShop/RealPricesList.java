
package fr.crafter.tickleman.RealShop;

public interface RealPricesList {

	void dailyPricesCalculation( RealShopDailyLog dailyLog );

	/**
	 * Daily price calculation
	 * Takes care of :
	 * - the last day transactions log
	 * - the last items price
	 */
	void dailyPricesCalculation( RealShopDailyLog dailyLog, boolean simulation );

	/**
	 * Calculate Price using crafting recipes
	 * - returns null if no price for any component
	 * - recurse if necessary
	 * recipe format : typeId[*mulQty][/divQty][+...][=resQty]
	 * recipe samples :
	 * - stick (typeId=280) : 5*2=4 : 2 wooden planks gives you 4 sticks
	 * - diamond hoe (typeId=293) : 280*2+264*2 : 2 sticks and 2 diamonds give you 1 diamond hoe
	 */
	RealPrice fromRecipe( String typeIdDamage, RealPricesFile marketFile );

	/**
	 * Gets the price for the given object (ID + damage value used for identification).
	 * First, the price is looked up in this prices file. If no price is available here,
	 * the price is looked up in the given market file or calculated from the object's recipe.
	 *
	 * @param typeIdDamage
	 * @param marketFile Prices file used for fallback lookup.
	 * @return
	 */
	RealPrice getPrice( String typeIdDamage, RealPricesFile marketFile );

	/**
	 * Gets the price for the given object (ID + damage value used for identification).
	 * First, the price is looked up in this prices file. If no price is available here,
	 * the price is looked up in the given market file.
	 * If desired, the price may also be calculated from the object's recipe.
	 *
	 * @param typeIdDamage
	 * @param marketFile Prices file used for fallback lookup.
	 * @param recipe Calculate price from recipe if it's not predefined?
	 * @return
	 */
	RealPrice getPrice( String typeIdDamage, RealPricesFile marketFile, boolean recipe );

	RealPricesFile load();

	void save();
	
}
