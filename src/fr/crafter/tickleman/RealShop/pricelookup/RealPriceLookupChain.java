
package fr.crafter.tickleman.RealShop.pricelookup;

import fr.crafter.tickleman.RealShop.RealPrice;
import fr.crafter.tickleman.RealShop.RealShop;
import fr.crafter.tickleman.RealShop.RealShopDailyLog;
import fr.crafter.tickleman.RealShop.RealShopPlugin;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

public class RealPriceLookupChain {
	private List<Class<? extends RealPriceList>> priceListClasses;
	private List<RealPriceList> priceListInstances;
	private Map<String, Set<RealPriceList>> askedLists;

	public RealPriceLookupChain( Class<? extends RealPriceList>... priceListClasses ) {
		assert (priceListClasses.length > 0);
		this.priceListClasses = new LinkedList<Class<? extends RealPriceList>>();
		this.priceListClasses.addAll( Arrays.asList( priceListClasses ) );
	}

	/* Declared private so outside code can't create invalid instances */
	private RealPriceLookupChain() {
	}

	/**
	 * The RealPriceLookupChain calls the prices calculation method on all the price lists it contains.
	 * @see fr.crafter.tickleman.RealShop.pricelookup.RealPriceList#dailyPricesCalculation(fr.crafter.tickleman.RealShop.RealShopDailyLog, boolean) dailyPricesCalculation(RealShopDailyLog, boolean)
	 */
	/*@Override
	void dailyPricesCalculation( RealShopDailyLog dailyLog, boolean simulation ) {
	for( RealPriceList priceList : this.priceLists ) {
	priceList.dailyPricesCalculation( dailyLog, simulation );
	}
	}*/
	public RealPrice getPrice( String typeIdDamage, RealShopPlugin plugin, RealShop shop, String playerName ) {
		// If this gets called in the prototype chain, create an instance and relay price lookup to it.
		if( this.priceListInstances == null ) {
			RealPriceLookupChain chain = createChain( plugin, shop, playerName );
			return chain.getPrice( typeIdDamage, plugin, shop, playerName );
		}
		// From here on: Code for the chain with price list instances.
		RealPrice price = null;
		/* Ask price lists if one contains the price. */
		for( RealPriceList priceList : this.priceListInstances ) {

			/* Make sure that no list is asked for the same thing twice.
			 * If this happens, no list contained the price, so we can end the loop here */
			if( this.askedLists.containsKey( typeIdDamage ) ) {
				if( this.askedLists.get( typeIdDamage ).contains( priceList ) ) {
					break;
				} else {
					this.askedLists.get( typeIdDamage ).add( priceList );
				}
			} else {
				Set<RealPriceList> listSet = new HashSet<RealPriceList>();
				listSet.add( priceList );
				this.askedLists.put( typeIdDamage, listSet );
			}

			price = priceList.getPrice( typeIdDamage );
			if( price != null ) {
				break;
			}
		}
		return price;
	}

	private RealPriceLookupChain createChain( RealShopPlugin plugin, RealShop shop, String playerName ) {
		RealPriceLookupChain chain = new RealPriceLookupChain();
		List<RealPriceList> priceLists = new LinkedList<RealPriceList>();
		for( Class<? extends RealPriceList> priceListClass : this.priceListClasses ) {
			Method factoryMethod = null;
			try {
				factoryMethod = priceListClass.getMethod( "createInstance", RealShopPlugin.class, RealShop.class, String.class, RealPriceLookupChain.class );
			} catch( NoSuchMethodException ex ) {
				Logger.getLogger( RealPriceLookupChain.class.getName() ).log( Level.SEVERE, null, ex );
			} catch( SecurityException ex ) {
				Logger.getLogger( RealPriceLookupChain.class.getName() ).log( Level.SEVERE, null, ex );
			}
			try {
				// Factory method may return null if not all required information was available, and we don't want null in the list.
				RealPriceList priceList = (RealPriceList) factoryMethod.invoke( null, plugin, shop, playerName, chain );
				if( null != priceList ) {
					priceLists.add( priceList );
				}
			} catch( IllegalAccessException ex ) {
				Logger.getLogger( RealPriceLookupChain.class.getName() ).log( Level.SEVERE, null, ex );
			} catch( IllegalArgumentException ex ) {
				Logger.getLogger( RealPriceLookupChain.class.getName() ).log( Level.SEVERE, null, ex );
			} catch( InvocationTargetException ex ) {
				Logger.getLogger( RealPriceLookupChain.class.getName() ).log( Level.SEVERE, null, ex );
			}
		}
		chain.priceListInstances = priceLists;
		chain.askedLists = new HashMap<String, Set<RealPriceList>>();
		return chain;
	}
}
