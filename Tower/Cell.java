package Tower;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import Tower.Map.Direction;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Cell.java
//  @ Date : 2014.03.20.
//  @ Author : 
//
//



/**
 * A palya egy egysege.
 */
public class Cell
{
	/**
	 * A cella foglaltsaga.
	 */
	private boolean busy;
	/**
	 * A cellat tartalmazo Map objektum.
	 */
	private Map map;
	/**
	 * A cella szomszedai.
	 * Minden szomszedhoz tartozik egy boolean ertek, amely jelzi, hogy
	 *  tovabb lehet-e lepni a cellara.
	 */
	private HashMap<Direction, Entry<Cell, Boolean>> neighbours;
	/**
	 * Egy cella lehetseges tipusait tartalmazo enumeracio.
	 */
	protected enum CellType {
		Terrain, Road, StartPoint, EndPoint
	};
	/**
	 * A cella tipusa.
	 */
	private CellType type;
	
	/**
	 * Konstruktor.
	 * @param b  A foglaltsag allapota.
	 * @param map  A cellat tartalmazo Map objektum.
	 * @param ct  A cella tipusa.
	 */
	public Cell(boolean b, Map map, CellType ct)
	{
		this.busy = b;
		this.map = map;
		this.neighbours = new HashMap<Map.Direction, Entry<Cell, Boolean>>();
		this.type = ct;
	}
	
	/**
	 * Getter a busy attributumra.
	 * @return  A busy attributum.
	 */
	public boolean getBusy()
	{
		String logString = "Cell.getBusy()";
		Logger.Log(1, logString, this);

		Logger.Log(0, logString, this);
		return this.busy;
	}

	/**
	 * Getter a map attributumra.
	 * @return  A map attributum.
	 */
	public Map getMap()
	{
		return this.map;
	}

		public CellType getCellType()
	{
		String logString = "Cell.getCellType()";
		Logger.Log(1, logString, this);

		Logger.Log(0, logString, this);

		return this.type;
	}

	/**
	 * Getter a neighbours attributumra.
	 * @return  A neighbours attributum.
	 */
	public HashMap<Direction, Entry<Cell, Boolean>> getNeighbours()
	{
		String logString = "Cell.getNeighbours()";
		Logger.Log(1, logString, this);

		Logger.Log(0, logString, this);

		return this.neighbours;
	}

	/**
	 * Visszaadja a cellan talalhato akadalyt.
	 * @return A cellan talalhato akadaly.
	 */
	public Obstacle getObstacle()
	{
		//String logString = "Cell.getObstacle()";
		//Logger.Log(1, logString, this);

		Obstacle returnValue = null;
		ArrayList<Obstacle> obstacles = map.getObstacles();
		for(Obstacle obstacle: obstacles) {
			if(obstacle.getPosition() == this) {
				returnValue = obstacle;
				break;
			}
		}

		//Logger.Log(0, logString, this);
		return returnValue;
	}

	/**
	 * Setter a busy attributumra.
	 * @param b  A busy attributum kivant erteke.
	 */
	public void setBusy(boolean b)
	{
		this.busy = b;
	}

	/**
	 * Setter a neighbours attributumra.
	 * @param b  A neighbours attributum kivant erteke.
	 */
	public void setNeighbours(HashMap<Direction, Entry<Cell, Boolean>> n) {
		this.neighbours = n;
	}
}
