
package fr.crafter.tickleman.RealShopTests;

import fr.crafter.tickleman.RealShop.pricelookup.RealPriceListFile;
import fr.crafter.tickleman.RealShop.pricelookup.RealPriceListShopFile;
import fr.crafter.tickleman.RealShop.RealPrice;
import fr.crafter.tickleman.RealShop.RealShop;
import fr.crafter.tickleman.RealShop.RealShopPlugin;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RealPriceListShopFileTest {
	private RealShopPlugin testPlugin = new RealShopPlugin();
	private String world = "testworld";
	private int x = 3;
	private int y = 9;
	private int z = 4;
	private RealShop testShop;
	private RealPriceListShopFile testPrices;

	public RealPriceListShopFileTest() {
		this.testPlugin.name = "testplugin";
	}

	@Before
	public void setUp() {
		this.testShop = new RealShop( world, x, y, z, "" );
		this.testPrices = RealPriceListShopFile.createInstance( testPlugin, testShop, "", null );
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testFileExists() {
		assertTrue( RealPriceListFile.pricesFileExists( testPlugin, "" + world + ";" + x + ";" + y + ";" + z ) );
	}

	@Test
	public void testFileDoesNotExist() {
		assertFalse( RealPriceListShopFile.pricesFileExists( testPlugin, "bla" + world + ";" + x + ";" + y + ";" + z ) );
	}

	@Test
	public void testGetShop() {
		assertNotNull( this.testPrices );
	}

	@Test
	public void testGetPrice() {
		RealPrice price = this.testPrices.getPrice( "nonexistentstuff" );
		assertNull( price );
		price = this.testPrices.getPrice( "somestuff" );
		assertNotNull( price );
		assertEquals( 8, price.buy, 0 );
		assertEquals( 5, price.sell, 0 );
	}
}
