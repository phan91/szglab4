package Tower;
import java.util.HashMap;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Human.java
//  @ Date : 2014.03.20.
//  @ Author : 
//
//




/**
 * Ember ellenseg.
 */
public class Human extends Enemy
{
	/**
	 * Konstruktor.
	 * @param hp  eletero.
	 * @param as  Az aktualis sebesseg.
	 * @param os  Az eredeti, lassitas nelkuli sebesseg.
	 * @param m  Az ellenseg halalakor szarumanhoz kerulo varazsero.
	 * @param lt  Az az idopont amikor az ellenseg legutoljara lepett.
	 */
	public Human(int hp, int as, int os, int m, long lt) {
		super(hp, as, os, m, lt);
	}

	public Enemy clone() {
		Human clone = new Human(
				healthPoint, 
				actualSpeed, 
				originalSpeed, 
				magic, 
				lastTime
				);
		clone.isDead = isDead;
		clone.position = position;
		return clone;
	}
	
	public boolean damage(int power, HashMap<String, Integer> bonus)
	{
		// Csokkentjuk az eleterot
		healthPoint -= power;
		
		// Ha kell, akkor csokkentjuk az eleterot a bonusz sebzessel is
		if (bonus.containsKey("human")) {
			healthPoint -= bonus.get("human");
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
	 * Ha a moveToNextCell nem null-al ter vissza, akkor a kapott
	 *  akadaly bonusSlowRates kollekciojabol kikeresi a Human-hoz
	 *  tartozo lassitasi erteket es beszorozza vele a speed attributumot.
	 */
	public void move()
	{
		// Leptetjuk az ellenseget
		Obstacle obstacle = moveToNextCell();
		
		// Ha van az uj cellan akadaly, es az akadalynak van
		// bonusz lassitasa az ellensegre akkor lassitjuk
		if (obstacle != null && obstacle.getBonusSlowRates().containsKey("human")) {
			actualSpeed *= obstacle.getBonusSlowRates().get("human");
		}
	}
}
