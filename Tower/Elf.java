package Tower;
import java.util.HashMap;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Elf.java
//  @ Date : 2014.03.20.
//  @ Author : 
//
//




/**
 * Elf ellenseg.
 */
public class Elf extends Enemy
{
	/**
	 * Konstruktor. Peldanyositashoz ajanlott ezt hasznalni.
	 * Default parameterekkel jon letre az elf. 
	 */
	public Elf() {
		this(
				150, // Health
				enemySpeeds.get("elf"), // Aktualis sebesseg
				enemySpeeds.get("elf"), // Eredeti sebesseg
				30 // Varazsero
				);
	}
	
	/**
	 * Konstruktor.
	 * @param hp  eletero.
	 * @param as  Az aktualis sebesseg.
	 * @param os  Az eredeti, lassitas nelkuli sebesseg.
	 * @param m  Az ellenseg halalakor szarumanhoz kerulo varazsero.
	 */
	public Elf(int hp, int as, int os, int m) {
		super(hp, as, os, m);
	}

	public Enemy clone() {
		Elf clone = new Elf(
				healthPoint, 
				actualSpeed, 
				originalSpeed, 
				magic
				);
		clone.lastTime = lastTime;
		clone.isDead = isDead;
		clone.position = position;
		return clone;
	}

	public boolean damage(int power, HashMap<String, Integer> bonus)
	{
		// Csokkentjuk az eleterot
		healthPoint -= power;
		
		// Ha kell, akkor csokkentjuk az eleterot a bonusz sebzessel is
		if (bonus.containsKey("elf")) {
			healthPoint -= bonus.get("elf");
		}

		// Ha az elet 0 ala csokken, akkor az ellenseg meghal
		if (healthPoint <= 0) {
			isDead = true;
		}
		
		return isDead;
	}

	/**
	 * Az ellenseget mozgatja.
	 * Meghivja a moveToNextCell metodust aminek hatasara az ellenseg
	 *  a kovetkezo cellara lep es ha szukseges akkor a sebessege csokken.
	 * Ha a moveToNextCell nem null-lal ter vissza, akkor a kapott
	 *  akadaly bonusSlowRates kollekciojabol kikeresi a Elf-hoz
	 *  tartozo lassitasi erteket es beszorozza vele a speed attributumot.
	 */
	public void move()
	{
		// Leptetjuk az ellenseget
		Obstacle obstacle = moveToNextCell();

		// Ha van az uj cellan akadaly, es az akadalynak van
		// bonusz lassitasa az ellensegre akkor lassitjuk
		if (obstacle != null && obstacle.getBonusSlowRates().containsKey("elf")) {
			actualSpeed *= obstacle.getBonusSlowRates().get("elf");
		}
	}
}
