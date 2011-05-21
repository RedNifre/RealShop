
package fr.crafter.tickleman.RealPluginTests;

import fr.crafter.tickleman.RealPlugin.RealInventory;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class RealInventoryTest {
	private RealInventory testInventory;

	/*
	 * Open questions:
	 * 1. For stacks of the same type but with different damage value (coloured wool),
	 * what do you get as the amount?
	 */
	@Before
	public void setUp() {
//		this.testInventory = RealInventory.create( null );
	}

	@After
	public void tearDown() {
	}

	@Test
	public void testAmount() {
	}
}