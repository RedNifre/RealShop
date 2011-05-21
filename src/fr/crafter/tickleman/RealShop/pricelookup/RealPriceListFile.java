
package fr.crafter.tickleman.RealShop.pricelookup;

import fr.crafter.tickleman.RealPlugin.RealDataValuesFile;
import fr.crafter.tickleman.RealPlugin.RealTools;
import fr.crafter.tickleman.RealShop.RealPrice;
import fr.crafter.tickleman.RealShop.RealShopPlugin;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.StringTokenizer;

public abstract class RealPriceListFile extends RealPriceList {
	/** master plugin */
	protected final RealShopPlugin plugin;
	/** stored file name */
	protected final String fileName;
	/** prices list : typeId[:damageId] => RealPrice(buy, sell) */
	protected Map<String, RealPrice> prices = new HashMap<String, RealPrice>();

	protected RealPriceListFile( RealShopPlugin plugin, String fileName ) {
		this.plugin = plugin;
		this.fileName = fileName;
		load();
	}

	public static boolean pricesFileExists( RealShopPlugin plugin, String shopId ) {
		return RealTools.fileExists( "plugins/" + plugin.name + "/" + shopId + ".prices.txt" );
	}

	public final void load() {
		boolean willSave = false;
		RealTools.renameFile(
			"plugins/" + plugin.name + "/" + fileName + ".cfg",
			"plugins/" + plugin.name + "/" + fileName + ".txt" );
		if( (fileName.equals( "market" ))
			&& !RealTools.fileExists( "plugins/" + plugin.name + "/" + fileName + ".txt" ) ) {
			RealTools.extractDefaultFile( plugin, fileName + ".txt" );
			willSave = true;
		}
		try {
			prices.clear();
			BufferedReader reader = new BufferedReader(
				new FileReader( "plugins/" + plugin.name + "/" + fileName + ".txt" ) );
			String buffer;
			StringTokenizer line;
			String typeIdDamage;
			RealPrice price;
			while( (buffer = reader.readLine()) != null ) {
				line = new StringTokenizer( buffer, ";" );
				if( line.countTokens() >= 3 ) {
					try {
						typeIdDamage = line.nextToken().trim();
						price = new RealPrice(
							Double.parseDouble( line.nextToken().trim() ),
							Double.parseDouble( line.nextToken().trim() ) );
						prices.put( typeIdDamage, price );
					} catch( Exception e ) {
						// when some values are not number, then ignore
					}
				}
			}
			reader.close();
		} catch( Exception e ) {
			if( fileName.equals( "market" ) ) {
				plugin.log.severe( "Needs plugins/" + plugin.name + "/" + fileName + ".txt file" );
			}
		}
		if( willSave ) {
			save();
		}
	}

	public final void save() {
		try {
			BufferedWriter writer = new BufferedWriter(
				new FileWriter( "plugins/" + plugin.name + "/" + fileName + ".txt" ) );
			writer.write( "#item:dm;buy;sell;name\n" );
			Iterator<String> iterator = prices.keySet().iterator();
			while( iterator.hasNext() ) {
				String typeIdDamage = iterator.next();
				RealPrice price = prices.get( typeIdDamage );
				writer.write(
					typeIdDamage + ";"
					+ price.buy + ";"
					+ price.sell + ";"
					+ plugin.dataValuesFile.getName( typeIdDamage )
					+ "\n" );
			}
			writer.flush();
			writer.close();
		} catch( Exception e ) {
			plugin.log.severe( "Could not save plugins/" + plugin.name + "/" + fileName + ".txt file" );
		}
		// Save all current values (including calculated prices) into currentValues.txt
		if( fileName.equals( "market" ) ) {
			try {
				RealDataValuesFile dataValues = new RealDataValuesFile( plugin, "dataValues" ).load();
				BufferedWriter writer = new BufferedWriter(
					new FileWriter( "plugins/" + plugin.name + "/currentValues.txt" ) );
				writer.write( "#item:dm;buy;sell;name\n" );
				for( String typeIdDamage : dataValues.getIds() ) {
					// TODO: Include lookup from recipe
					RealPrice price = getPrice( typeIdDamage );
					if( price != null ) {
						writer.write(
							typeIdDamage + ";"
							+ price.buy + ";"
							+ price.sell + ";"
							+ plugin.dataValuesFile.getName( typeIdDamage )
							+ "\n" );
					} else {
						writer.write(
							typeIdDamage + ";0;0;"
							+ plugin.dataValuesFile.getName( typeIdDamage )
							+ "\n" );
					}
				}
				plugin.log.debug( "END" );
				writer.flush();
				writer.close();
			} catch( Exception e ) {
				plugin.log.error( "Could not save plugins/" + plugin.name + "/dataValues.txt file" );
				plugin.log.error( e.getMessage() );
				plugin.log.error( e.getStackTrace().toString() );
			}
		}
	}
}
