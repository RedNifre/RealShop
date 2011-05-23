
package fr.crafter.tickleman.RealShopTests;

import fr.crafter.tickleman.RealPlugin.RealDataValuesFile;
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
	private RealShopPlugin testPlugin;
	private RealShop testShop = new RealShop( "testworld", 3, 9, 4, null );
	private PriceListOtherlandShop testList;

	@Before
	public void setUp() {
		this.testPlugin = new RealShopPlugin();
		this.testPlugin.name = "testplugin";
		this.testPlugin.dataValuesFile = new RealDataValuesFile(this.testPlugin, "dataValues");
		this.testPlugin.dataValuesFile.load();
		this.testList = PriceListOtherlandShop.createInstance( this.testPlugin, this.testShop, "player", null );
	}

	@Test
	public void testPrice() {
		RealPrice price = this.testList.getPrice( "314", 1 );
		assertEquals( 1000000000 / 3, price.sell, 1 );
		assertEquals( 1.5 * (1000000000 / 2), price.buy, 0.0 );
	}
}
