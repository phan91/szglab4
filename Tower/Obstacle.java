package Tower;

import java.util.HashMap;

import javax.swing.plaf.basic.BasicInternalFrameTitlePane.MaximizeAction;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Obstacle.java
//  @ Date : 2014.03.20.
//  @ Author : 
//
//




/**
 * A palyara rakhato objektumokat osszefoglalo absztrakt ososztaly.
 */
public class Obstacle extends Item
{
	/**
	 * A lassitasi szorzo.
	 */
	private double slowRate;
	/**
	 * Az egyes ellenseg tipusokra vonatkozo bonusz lassitasi szorzok.
	 */
	private HashMap<String, Double> bonusSlowRates;

	/**
	 * Konstruktor. Peldanyositashoz ajanlott ezt hasznalni.
	 * Default parameterekkel jon letre az akadaly. 
	 * @param pos
	 */
	public Obstacle(Cell pos) {
		this(
				5,	// Lassitas
				3,		// Varazskovek maximalis szama
				pos
				);
	}

	/**
	 * Konstruktor.
	 * @param sr  A lassitasi szorzo.
	 * @param mm  Az akadalyra rakhato varazskovek maximalis szama.
	 * @param pos  Az akadaly pozicioja.
	 */
	public Obstacle(double sr, int mm, Cell pos)
	{
		super(mm, pos);
		this.slowRate = sr;
		bonusSlowRates = new HashMap<String,Double>();
	}

	/**
	 * Getter a slowRate attributumra.
	 * @return  A slowRate attributum.
	 */
	public double getSlowRate()
	{
		String logString = "Obstacle.getSlowRate()";
		Logger.Log(1, logString, this);

		Logger.Log(0, logString, this);
		return this.slowRate;
	}

	/**
	 * Getter a bonusSlowRates attributumra.
	 * @return  A bonusSlowRates attributum.
	 */
	public HashMap<String,Double> getBonusSlowRates()
	{
		String logString = "Obstacle.getBonusSlowRates()";
		Logger.Log(1, logString, this);

		Logger.Log(0, logString, this);
		return this.bonusSlowRates;
	}

	/**
	 * Setter a slowRate attributumra.
	 * @param b  A slowRate attributum kivant erteke.
	 */
	public void setSlowRate(double sr)
	{
		this.slowRate = sr;
	}

	/**
	 * Setter a bonusSlowRates attributumra.
	 * @param b  A bonusSlowRates attributum kivant erteke.
	 */
	public void setBonusSlowRates(HashMap<String,Double> bonus)
	{
		this.bonusSlowRates = bonus;
	}

	/**
	 * /**
	 * Megvizsgalja, hogy a maxMagicStoneNumber attributum erteke kisebb-e
	 *  mint a magicStones lista hossza, es ha igen akkor a kapott varazsko
	 *  attributumait felhasznalva modositja az akadaly attributumait
	 *  es true-val ter vissza.
	 * Ellenkezo esetben false-szal ter vissza.
	 */
	public boolean upgrade(MagicStone stone)
	{
		// Csak akkor fejlesztunk, ha meg nem ertuk el a maximalis fejlesztesek szamat
		if (maxMagicStoneNumber > magicStones.size()) {
			// Elrakjuk a kovet az akadalyba
			magicStones.add(stone);

			// Noveljuk a lassitasi szorzot
			slowRate *= stone.slowRate;

			// Modositjuk a bonusz lassitasi szorzokat
			for (String enemy : stone.bonusSlowRates.keySet()) {
				// Ha van ilyen ellenseghez tartozo bejegyzes,
				// akkor azt modositjuk
				if (bonusSlowRates.containsKey(enemy)) {
					bonusSlowRates.put(
							enemy, 
							bonusSlowRates.get(enemy) * stone.bonusSlowRates.get(enemy)
							);
				}
				// Ha nincs, akkor letrehozzuk
				else {
					bonusSlowRates.put(enemy, stone.bonusSlowRates.get(enemy));
				}
			}

			return true;
		}

		// Ha nem lehetett fejleszteni akkor visszaterunk false-szal
		return false;

		/*
		String logString = "Obstacle.upgrade(stone)";
		Logger.Log(1, logString, this);

		Logger.Log(0, logString, this);
		return true;
		*/
	}
}
