/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.crafter.tickleman.RealShopTests;

import java.net.InetSocketAddress;
import java.util.HashSet;
import java.util.List;
import org.bukkit.Achievement;
import org.bukkit.Location;
import org.bukkit.Material;
import org.bukkit.Server;
import org.bukkit.Statistic;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.entity.Arrow;
import org.bukkit.entity.Egg;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;
import org.bukkit.entity.Snowball;
import org.bukkit.entity.Vehicle;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.PlayerInventory;
import org.bukkit.util.Vector;

/**
 * Mock class, implements the player class for testing purposes.
 */
public class MockPlayer implements Player {

	public boolean isOnline() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public String getDisplayName() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public void setDisplayName( String string ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public void setCompassTarget( Location lctn ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public Location getCompassTarget() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public InetSocketAddress getAddress() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public void sendRawMessage( String string ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public void kickPlayer( String string ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public void chat( String string ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public boolean performCommand( String string ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public boolean isSneaking() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public void setSneaking( boolean bln ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public void saveData() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public void loadData() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public void setSleepingIgnored( boolean bln ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public boolean isSleepingIgnored() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public void playNote( Location lctn, byte b, byte b1 ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public void sendBlockChange( Location lctn, Material mtrl, byte b ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public void sendBlockChange( Location lctn, int i, byte b ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public void updateInventory() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public void awardAchievement( Achievement a ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public void incrementStatistic( Statistic ststc ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public void incrementStatistic( Statistic ststc, int i ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public void incrementStatistic( Statistic ststc, Material mtrl ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public void incrementStatistic( Statistic ststc, Material mtrl, int i ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public String getName() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public PlayerInventory getInventory() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public ItemStack getItemInHand() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public void setItemInHand( ItemStack is ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public boolean isSleeping() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public int getSleepTicks() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public int getHealth() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public void setHealth( int i ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public double getEyeHeight() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public double getEyeHeight( boolean bln ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public Location getEyeLocation() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public List<Block> getLineOfSight( HashSet<Byte> hs, int i ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public Block getTargetBlock( HashSet<Byte> hs, int i ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public List<Block> getLastTwoTargetBlocks( HashSet<Byte> hs, int i ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public Egg throwEgg() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public Snowball throwSnowball() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public Arrow shootArrow() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public boolean isInsideVehicle() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public boolean leaveVehicle() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public Vehicle getVehicle() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public int getRemainingAir() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public void setRemainingAir( int i ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public int getMaximumAir() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public void setMaximumAir( int i ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public void damage( int i ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public void damage( int i, Entity entity ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public int getMaximumNoDamageTicks() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public void setMaximumNoDamageTicks( int i ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public int getLastDamage() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public void setLastDamage( int i ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public int getNoDamageTicks() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public void setNoDamageTicks( int i ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public Location getLocation() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public void setVelocity( Vector vector ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public Vector getVelocity() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public World getWorld() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public boolean teleport( Location lctn ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public boolean teleport( Entity entity ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public void teleportTo( Location lctn ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public void teleportTo( Entity entity ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public List<Entity> getNearbyEntities( double d, double d1, double d2 ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public int getEntityId() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public int getFireTicks() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public int getMaxFireTicks() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public void setFireTicks( int i ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public void remove() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public boolean isDead() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public Server getServer() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public Entity getPassenger() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public boolean setPassenger( Entity entity ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public boolean isEmpty() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public boolean eject() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public float getFallDistance() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public void setFallDistance( float f ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public void setLastDamageCause( EntityDamageEvent ede ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public EntityDamageEvent getLastDamageCause() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public void sendMessage( String string ) {
		throw new UnsupportedOperationException( "Not supported yet." );
	}

	public boolean isOp() {
		throw new UnsupportedOperationException( "Not supported yet." );
	}
	
}
