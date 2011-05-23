
package fr.crafter.tickleman.RealShop.pricelookup;

import fr.crafter.tickleman.RealShop.RealPrice;
import fr.crafter.tickleman.RealShop.RealShop;
import fr.crafter.tickleman.RealShop.RealShopPlugin;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PriceListOtherlandShop extends RealPriceList {
	/* Constants for price calculation */
	private final int categoryValue = 1000000000;
	/* Other variables */
	private RealShopPlugin plugin;
	private RealShop shop;
	private Map<String, String> itemCategories = new HashMap<String, String>();
	private Map<String, Double> categoryTaxes = new HashMap<String, Double>();

	private PriceListOtherlandShop( RealShopPlugin plugin, RealShop shop ) {
		this.plugin = plugin;
		this.shop = shop;
		load();
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
		String typeName = this.plugin.dataValuesFile.getName( typeIdDamage ).toLowerCase();
		String category = this.itemCategories.get( typeName );
		// Not found: Try again without damage value
//		if(category == null)
//			category = this.itemCategories.get( typeIdDamage );
		if( category == null ) {
			category = "default";
		}
		return category;
	}

	private int getAmountInShop( String typeIdDamage ) {
		return 2;
//		Integer typeId;
//		if( typeIdDamage.contains( ":" ) ) {
//			// Price of item without damage code
//			typeId = Integer.parseInt( typeIdDamage.split( ":" )[0] );
//		} else {
//			typeId = Integer.parseInt( typeIdDamage );
//		}
//
//		RealChest chest = RealChest.create( this.plugin.getServer().getWorld( this.shop.world ), this.shop.posX, this.shop.posY, this.shop.posZ );
//		RealInventory inv = RealInventory.create( chest );
//		return inv.getAmount( typeId );
	}

	private int getCategoryValue( String category ) {
		return this.categoryValue;
	}

	private double getCategoryTax( String category ) {
		Double tax = this.categoryTaxes.get( category );
		if( tax == null ) {
			tax = 0d;
		}
		return tax;
	}

	/* Specification files */
	private void load() {
		Scanner fileScanner;
		/* Parse associations item -> category */
		try {
			fileScanner = new Scanner( new File( "plugins/" + plugin.name + "/" + "ItemCategoryDefinitions.txt" ) );
			this.itemCategories.clear();
			while( fileScanner.hasNextLine() ) {
				String line = fileScanner.nextLine();
				String[] values = line.split( ":" );
				if( values.length != 2 ) {
					this.plugin.log.warning( "Item category definitions file has invalid format!" );
				} else {
					this.itemCategories.put( values[0].toLowerCase(), values[1].toLowerCase() );
				}
			}
		} catch( FileNotFoundException ex ) {
			Logger.getLogger( PriceListOtherlandShop.class.getName() ).log( Level.SEVERE, null, ex );
		}

		/* Parse associations category -> tax */
		try {
			fileScanner = new Scanner( new File( "plugins/" + plugin.name + "/" + "CategoryTaxDefinitions.txt" ) );
			this.categoryTaxes.clear();
			while( fileScanner.hasNextLine() ) {
				String line = fileScanner.nextLine();
				String[] values = line.split( ":" );
				if( values.length != 2 ) {
					this.plugin.log.warning( "Category taxes definitions file has invalid format!" );
				} else {
					this.categoryTaxes.put( values[0].toLowerCase(), Double.valueOf( values[1] ) );
				}
			}
		} catch( FileNotFoundException ex ) {
			Logger.getLogger( PriceListOtherlandShop.class.getName() ).log( Level.SEVERE, null, ex );
		}
	}
}
