package fr.crafter.tickleman.RealShop;

import fr.crafter.tickleman.RealPlugin.RealItemStack;
import fr.crafter.tickleman.RealShop.pricelookup.RealPriceLookupChain;

//######################################################################### RealShopTransactionLine
public class RealShopTransactionLine extends RealItemStack 
{

	public String comment; 

	private double unitPrice;

	private double totalPrice;
	
	//--------------------------------------------------------------------- RealShopTransactionLine
	public RealShopTransactionLine(RealItemStack itemStack, RealPrice price)
	{
		super(itemStack.getTypeId(), itemStack.getAmount(), itemStack.getDurability());
		if (price == null) {
			unitPrice = 0;
			totalPrice = 0;
		} else {
//			unitPrice = ((getAmount() < 0) ? price.getSell() : price.getBuy());
			totalPrice = ((getAmount() < 0) ? price.getSell() : price.getBuy());
			unitPrice = totalPrice / Math.abs( getAmount() );
		}
	}

	//-------------------------------------------------------------------------------- getLinePrice
	public double getLinePrice()
	{
//		return Math.ceil((double)100 * getUnitPrice() * (double)getAmount()) / (double)100;
		return totalPrice;
	}

	//-------------------------------------------------------------------------------- getUnitPrice
	public double getUnitPrice()
	{
		return unitPrice;
	}

}
