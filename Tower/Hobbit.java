package Tower;
import java.util.HashMap;
import java.util.Scanner;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Hobbit.java
//  @ Date : 2014.03.20.
//  @ Author : 
//
//




/**
 * Hobbit ellenseg.
 */
public class Hobbit extends Enemy
{
	/**
	 * Konstruktor. Peldanyositashoz ajanlott ezt hasznalni.
	 * Default parameterekkel jon letre a hobbit. 
	 */
	public Hobbit() {
		this(
				100, // Health
				enemySpeeds.get("hobbit"), // Aktualis sebesseg
				enemySpeeds.get("hobbit"), // Eredeti sebesseg
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
	public Hobbit(int hp, int as, int os, int m) {
		super(hp, as, os, m);
	}

	public Enemy clone() {
		Hobbit clone = new Hobbit(
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
		/*String logString = "Hobbit.damage(power, bonus)";
		Logger.Log(1, logString, this);

		Logger.Log(0, logString, this);
		return true;*/

		// Csokkentjuk az eleterot
		healthPoint -= power;

		// Ha kell, akkor csokkentjuk az eleterot a bonusz sebzessel is
		if (bonus.containsKey("hobbit")) {
			healthPoint -= bonus.get("hobbit");
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
	 *  akadaly bonusSlowRates kollekciojabol kikeresi a Hobbit-hoz
	 *  tartozo lassitasi erteket es beszorozza vele a speed attributumot.
	 */
	public void move()
	{
		// Leptetjuk az ellenseget
		Obstacle obstacle = moveToNextCell();
		
		// Ha van az uj cellan akadaly, es az akadalynak van
		// bonusz lassitasa az ellensegre akkor lassitjuk
		if (obstacle != null && obstacle.getBonusSlowRates().containsKey("hobbit")) {
			actualSpeed *= obstacle.getBonusSlowRates().get("hobbit");
		}
				
		/*String logString = "Hobbit.move()";
		Logger.Log(1, logString, this);

		String answerText;
		boolean isSlowed = false;
		boolean isEndPoint = false;
		Obstacle obstacle;
		Cell cell;
		Scanner scanner = new Scanner (System.in);

		// Kivalasztjuk a kovetkezo cellat, amire lepni fog az ellenseg
		cell = this.getPosition().getNeighbours().get(0);

		this.setPosition(cell);

		cell.getBusy();

		// Felhasznaloi interakcio
		if(Program.usecaseNumber==4) {
			while(true) {
				System.out.print("Akadalyra lepett [igen, nem]: ");
				answerText = scanner.next();
				if(answerText.equals("igen")) {
					isSlowed=true;
					break;
				} else if(answerText.equals("nem")) {
					isSlowed=false;
					break;
				} else {
					System.out.println("Helytelen ertek");
					continue;
				}
			}
		}

		if(isSlowed) {
			obstacle = cell.getObstacle();
			obstacle.getSlowRate();
			obstacle.getBonusSlowRates();
			this.setActualSpeed(1);
		} else {
			this.setActualSpeed(this.getOriginalSpeed());
		}

		Logger.Log(0, logString, this);
		*/
	}
}
