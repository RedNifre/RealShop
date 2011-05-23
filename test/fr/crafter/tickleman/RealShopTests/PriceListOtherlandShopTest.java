
package fr.crafter.tickleman.RealShopTests;

import fr.crafter.tickleman.RealShop.RealPrice;
import fr.crafter.tickleman.RealShop.RealShop;
import fr.crafter.tickleman.RealShop.RealShopPlugin;
import fr.crafter.tickleman.RealShop.pricelookup.PriceListOtherlandShop;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

public class PriceListOtherlandShopTest {
	private RealShopPlugin testPlugin = new RealShopPlugin();
	private RealShop testShop = new RealShop( "testworld", 3, 9, 4, null );
	private PriceListOtherlandShop testList;

	@Before
	public void setUp() {
		this.testList = PriceListOtherlandShop.createInstance( this.testPlugin, this.testShop, "player", null );
	}

	@Test
	public void testPrice() {
		RealPrice price = this.testList.getPrice( "17", 1 );
		// TODO
		assertEquals( 0, 0 );
	}
}
