
package fr.crafter.tickleman.RealShopTests;

import fr.crafter.tickleman.RealShop.RealShopPlugin;
import fr.crafter.tickleman.RealShop.RealShop;
import fr.crafter.tickleman.RealShop.pricelookup.RealPriceListShopFile;
import fr.crafter.tickleman.RealShop.pricelookup.RealPriceLookupChain;
import fr.crafter.tickleman.RealShop.RealPrice;
import org.junit.Test;
import static org.junit.Assert.*;

public class RealPriceLookupChainTest {
	private String world = "testworld";
	private int x = 3;
	private int y = 9;
	private int z = 4;

	public RealPriceLookupChainTest() {
	}

//	@Test( expected = java.lang.AssertionError.class )
//	public void testEmptyChain() {
//		new RealPriceLookupChain();
//	}
	@Test
	public void testSingleElementChain() {
		RealPriceLookupChain testChain = new RealPriceLookupChain( DoNothingList.class );
		assertNull( testChain.getPrice( "foo", 1, null, null, "player" ) );
	}

	@Test
	public void testSingleElementChain2() {
		RealPriceLookupChain testChain = new RealPriceLookupChain( AddOneList.class );
		RealPrice price = testChain.getPrice( "foo", 1, null, null, "player" );
		assertNotNull( price );
		assertEquals( 3.0, price.buy, 0 );
		assertEquals( 4.5, price.sell, 0 );
	}
//	@Test
//	public void testManyElementsChain() {
//		RealPriceLookupChain testChain = new RealPriceLookupChain( AddOneList.class, AddOneList.class, AddOneList.class, AddOneList.class );
//		RealPrice price = testChain.getPrice( "foo", null, null, "player" );
//		assertNotNull( price );
//		assertEquals( 8, price.buy, 0 );
//		assertEquals( -4, price.sell, 0 );
//
//	}

	@Test
	public void testWithShopFile() {
		RealShopPlugin testPlugin = new RealShopPlugin();
		testPlugin.name = "testplugin";
		RealShop testShop = new RealShop( world, x, y, z, "" );
		RealPriceLookupChain testChain = new RealPriceLookupChain( RealPriceListShopFile.class );
		RealPrice price = testChain.getPrice( "nonexistentstuff", 1, testPlugin, testShop, "player" );
		assertNull( price );
		price = testChain.getPrice( "somestuff", 1, testPlugin, testShop, "player2" );
		assertNotNull( price );
		assertEquals( 8, price.buy, 0 );
		assertEquals( 5, price.sell, 0 );
	}
}