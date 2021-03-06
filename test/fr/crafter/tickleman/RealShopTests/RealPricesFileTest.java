
package fr.crafter.tickleman.RealShopTests;

import fr.crafter.tickleman.RealShop.RealPrice;
import fr.crafter.tickleman.RealShop.RealShop;
import fr.crafter.tickleman.RealShop.RealShopPlugin;
import fr.crafter.tickleman.RealShop.RealPricesFile;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class RealPricesFileTest {
	private RealShopPlugin testPlugin = new RealShopPlugin();
	private String world = "testworld";
	private int x = 3;
	private int y = 9;
	private int z = 4;
	private RealShop testShop;
	private RealPricesFile testPrices;

	public RealPricesFileTest() {
		this.testPlugin.name = "testplugin";
	}

	@Before
	public void setUp() {
		this.testShop = new RealShop( world, x, y, z, "" );
		this.testPrices = RealPricesFile.getShopPricesFile( testPlugin, testShop );
	}

	@Test
	public void testFileExists() {
		assertTrue( RealPricesFile.shopPricesFileExists( testPlugin, "" + world + ";" + x + ";" + y + ";" + z ) );
	}

	@Test
	public void testFileDoesNotExist() {
		assertFalse( RealPricesFile.shopPricesFileExists( testPlugin, "bla" + world + ";" + x + ";" + y + ";" + z ) );
	}

	@Test
	public void testGetShop() {
		assertNotNull( this.testPrices );
	}

	@Test
	public void testGetPrice() {
		RealPrice price = this.testPrices.getPrice( "nonexistentstuff", null, false );
		assertNull( price );
		price = this.testPrices.getPrice( "somestuff", null, false );
		assertNotNull( price );
		assertEquals( 8, price.buy, 0 );
		assertEquals( 5, price.sell, 0 );
	}
}
