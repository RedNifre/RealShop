
package fr.crafter.tickleman.RealShopTests;

import fr.crafter.tickleman.RealShop.pricelookup.RealPriceLookupChain;
import fr.crafter.tickleman.RealShop.RealPrice;
import org.junit.Test;
import static org.junit.Assert.*;

public class RealPriceLookupChainTest {
	public RealPriceLookupChainTest() {
	}

//	@Test( expected = java.lang.AssertionError.class )
//	public void testEmptyChain() {
//		new RealPriceLookupChain();
//	}

	@Test
	public void testSingleElementChain() {
		RealPriceLookupChain testChain = new RealPriceLookupChain( DoNothingList.class );
		assertNull( testChain.getPrice( "foo", null, null, "player" ) );
	}

	@Test
	public void testSingleElementChain2() {
		RealPriceLookupChain testChain = new RealPriceLookupChain( AddOneList.class );
		RealPrice price = testChain.getPrice( "foo", null, null, "player" );
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
}