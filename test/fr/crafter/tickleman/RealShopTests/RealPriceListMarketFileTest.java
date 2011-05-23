/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.crafter.tickleman.RealShopTests;

import fr.crafter.tickleman.RealShop.RealPrice;
import fr.crafter.tickleman.RealShop.RealShop;
import fr.crafter.tickleman.RealShop.RealShopPlugin;
import fr.crafter.tickleman.RealShop.pricelookup.RealPriceListMarketFile;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RealPriceListMarketFileTest {
	private RealShopPlugin testPlugin = new RealShopPlugin();
	private String world = "testworld";
	private int x = 3;
	private int y = 9;
	private int z = 4;
	private RealShop testShop;
	private RealPriceListMarketFile testFile;

	@Before
	public void setUp() {
		this.testPlugin.name = "testplugin";
		this.testShop = new RealShop( world, x, y, z, "" );
		this.testFile = RealPriceListMarketFile.createInstance( testPlugin, this.testShop, "player", null );
	}

	@Test
	public void testNotNull() {
		assertNotNull( this.testFile );
	}

	@Test
	public void testPrices() {
		RealPrice price = this.testFile.getPrice( "14", 1 );
		assertEquals( 300, price.buy, 0 );
		assertEquals( 270, price.sell, 0 );

		price = this.testFile.getPrice( "6", 4 );
		assertEquals( 5 * 4, price.buy, 0 );
		assertEquals( 4 * 4, price.sell, 0 );
	}
}
