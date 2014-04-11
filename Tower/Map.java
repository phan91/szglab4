package Tower;
import java.util.ArrayList;
import java.util.Scanner;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Map.java
//  @ Date : 2014.03.20.
//  @ Author : 
//
//




/**
 * A palyat reprezentalo osztaly. 
 * Tarolja a palyan talalhato objektumokat es o ertesit mindenkit az ido mulasarol.
 */
public class Map
{
	/**
	 * Az iranyokat tarolo enumeracio.
	 */
	public enum Direction {
		North, West, South, East
	};
	/**
	 * A jatek lehetseges kimeneteleit tarolo enumeracio.
	 */
	public enum GameResult {
		Win, Loose
	};
	/**
	 * A palyan szereplo cellak szomszedainak maximalis szama.
	 */
	private int neighbourNumber;
	/**
	 * Az az idopont, amikor legutoljara ellenseget adtak a palyahoz.
	 */
	private long lastEnemyAddedTime;
	/**
	 * Az aktualis kor kezdetenek idopontja.
	 */
	private long roundStartedTime;
	/**
	 * A palyan levo cellak.
	 */
	private ArrayList<Cell> cells;
	/**
	 * A jatekhoz tartozo kor leiroja.
	 */
	private Round round;
	/**
	 * A palyan szereplo ellensegek listaja.
	 */
	private ArrayList<Enemy> enemies;
	/**
	 * A palyan szereplo akadalyok listaja.
	 */
	private ArrayList<Obstacle> obstacles;
	/**
	 * A palyan szereplo tornyok listaja.
	 */
	private ArrayList<Tower> towers;
	/**
	 * Szaruman.
	 */
	private Saruman saruman;
	/**
	 * Ennyi idonkent kerul kod egy toronyra.
	 */
	private int fogApplianceTime;
	/**
	 * Ekkor kerult utoljara kod egy toronyra.
	 */
	private long lastFog;
	/**
	 * A kod ennyivel csokkenti egy torony lotavolsagat.
	 */
	private final static int fogDecreason = 1;
	/**
	 * A kod ennyi ideig csokkenti egy torony lotavolsagat.
	 */
	private final static int fogDuration = 4000;

	/**
	 * Ennyi idonkent ertesulnek az objektumok az ido mulasarol.
	 */
	private final int dt = 50;
	
	/**
	 * Konstruktor.
	 * @param neighbour  A palyan szereplo cellak szomszedainak maximalis szama.
	 * @param lastEnemy  Az az idopont, amikor legutoljara ellenseget adtak a palyahoz.
	 * @param rt  Az aktualis kor kezdetenek idopontja.
	 */
	public Map(int neighbour, long lastEnemy, long rt) 
	{
		this.neighbourNumber = neighbour;
		this.lastEnemyAddedTime = lastEnemy;
		this.roundStartedTime = rt;
		
		this.cells = new ArrayList<Cell>();
		this.enemies = new ArrayList<Enemy>();
		this.obstacles = new ArrayList<Obstacle>();
		this.towers = new ArrayList<Tower>();
		this.round = new Round(1,1,1,1,1,1);
	}

	/**
	 * Getter a neighbourNumber attributumra.
	 * @return  A neighbourNumber attributum.
	 */
	public int getNeighbourNumber()
	{
		return this.neighbourNumber;
	}

	/**
	 * Getter a lastEnemyAddedTime attributumra.
	 * @return  A lastEnemyAddedTime attributum.
	 */
	public long getLastEnemyAddedTime()
	{
		return this.lastEnemyAddedTime;
	}

	/**
	 * Getter a roundStartedTime attributumra.
	 * @return  A roundStartedTime attributum.
	 */
	public long getRoundStartedTime()
	{
		return this.roundStartedTime;
	}

	/**
	 * Getter a cells attributumra.
	 * @return  A cells attributum.
	 */
	public ArrayList<Cell> getCells()
	{
		return this.cells;
	}

	/**
	 * Getter az enemies attributumra.
	 * @return  Az enemies attributum.
	 */
	public ArrayList<Enemy> getEnemies()
	{
		return this.enemies;
	}

	/**
	 * Getter a towers attributumra.
	 * @return  A towers attributum.
	 */
	public ArrayList<Tower> getTowers()
	{
		return this.towers;
	}

	/**
	 * Getter az obstacles attributumra.
	 * @return  Az obstacles attributum.
	 */
	public ArrayList<Obstacle> getObstacles()
	{
		String logString = "Map.getObstacles()";
		Logger.Log(1, logString, this);

		Logger.Log(0, logString, this);

		return this.obstacles;
	}

	/**
	 * Getter a fogDecreason attributumra.
	 * @return  A fogDecreason attributum.
	 */
	public static int getFogdecreason() {
		return fogDecreason;
	}

	/**
	 * Getter a fogDuration attributumra.
	 * @return  A fogDuration attributum.
	 */
	public static int getFogduration() {
		return fogDuration;
	}
	
	/**
	 * Setter a neighbourNumber attributumra.
	 * @param b  A neighbourNumber attributum kivant erteke.
	 */
	public void setNeighbourNumber(int neighbour)
	{
		this.neighbourNumber = neighbour;
	}

	/**
	 * Setter a lastEnemyAddedTime attributumra.
	 * @param b  A lastEnemyAddedTime attributum kivant erteke.
	 */
	public void setLastEnemyAddedTime(long lastEnemy)
	{
		this.lastEnemyAddedTime = lastEnemy;
	}

	/**
	 * Setter a roundStartedTime attributumra.
	 * @param b  A roundStartedTime attributum kivant erteke.
	 */
	public void setRoundStartedTime(long rt)
	{
		this.roundStartedTime = rt;
	}

	/**
	 * Setter a cells attributumra.
	 * @param b  A cells attributum kivant erteke.
	 */
	public void setCells(ArrayList<Cell> cell)
	{
		this.cells = cell;
	}

	/**
	 * Setter az enemies attributumra.
	 * @param b  Az enemies attributum kivant erteke.
	 */
	public void setEnemies(ArrayList<Enemy> enemy)
	{
		this.enemies = enemy;
	}

	/**
	 * Setter a towers attributumra.
	 * @param b  A towers attributum kivant erteke.
	 */
	public void setTowers(ArrayList<Tower> tower)
	{
		this.towers = (ArrayList<Tower>) tower;
	}

	/**
	 * Setter az obstacles attributumra.
	 * @param b  Az obstacles attributum kivant erteke.
	 */
	public void setObstacles(ArrayList<Obstacle> obstacle)
	{
		this.obstacles = obstacle;
	}

	public void setSaruman(Saruman s){
		this.saruman = s;
	}

	/**
	 * Letrehoz egy Enemy leszarmazottat a parameterkent kapott 
	 *  ertekeknek megfeleloen, es az enemies listahoz adja. 
	 * Ehhez a megfelelo Enemy leszarmazott osztaly konstruktorat hivja meg.
	 * @param type  Az ellenseg tipusa.
	 * @param pos  Az ellenseg pozicioja.
	 */
	public void addEnemy(String type, Cell pos)
	{
		String logString = "Map.addEnemy(type, position)";
		Logger.Log(1, logString, this);
		Enemy enemy;
		
		if(type.equals("human")) {
			enemy = new Human(100, 5, 5, 30);
			Logger.AddName(enemy, "HumanID");
		} else if(type.equals("elf")) {
			enemy = new Elf(100, 5, 5, 30);
			Logger.AddName(enemy, "ElfID");
		} else if(type.equals("dwarf")) {
			enemy = new Dwarf(100, 5, 5, 30);
			Logger.AddName(enemy, "DwarfID");
		} else { // it's a hobbit
			enemy = new Hobbit(100, 5, 5, 30);
			Logger.AddName(enemy, "HobbitID");
		}

		enemy.setPosition(pos);
		enemies.add(enemy);
		Logger.Log(0, logString, this);
	}

	/**
	 * Noveli Saruman varazserejet a changeMagicPowerBy metoduson keresztul, 
	 *  felhasznalva a kapott enemy magic attributumat. 
	 * Vegul az enemy-t eltavolitja az enemies listabol.
	 * @param enemy  Az eltavolitando ellenseg.
	 */
	public void removeEnemy(Enemy enemy)
	{
		String logString = "Map.removeEnemy(enemy)";
		Logger.Log(1, logString, this);

		saruman.changeMagicPowerBy(1);
		enemies.remove(enemy);

		Logger.Log(0, logString, this);
	}

	/**
	 * Hozzaadja a paramterkent kapott tornyot a towers listahoz.
	 * @param tower  A palyahoz adando torony.
	 */
	protected void addTower(Tower tower) {
		String logString = "Map.addTower(tower)";
		Logger.Log(1, logString, this);

		towers.add(tower);

		Logger.Log(0, logString, this);
	}

	/**
	 * Hozzaadja a paramterkent kapott akadalyt az obstacles listahoz.
	 * @param obstacle  A palyahoz adando akadaly.
	 */
	protected void addObstacle(Obstacle obstacle) {
		String logString = "Map.addObstacle(obstacle)";
		Logger.Log(1, logString, this);

		obstacles.add(obstacle);

		Logger.Log(0, logString, this);
	}

	/**
	 * Visszaadja a kapott torony lotavolsagan beluli ellenfelek listajat. 
	 * Ehhez vegigmegy az enemies listan, es ha olyan ellenfelet talal 
	 *  benne, aki a torony lotavolsagan belul van akkor azt hozzafuzi
	 *  a visszaadando listahoz. 
	 * Ha nem talal ilyen ellenfelet akkor null-t ad vissza.
	 * @param tower  A torony akinek a hatotavolsagan beluli ellenfeleket kell visszaadni.
	 * @return  A kapott torony lotavolsagan beluli ellenfelek listaja.
	 *  Ha nincs ellenfel a torony lotavolsagan belul akkor null-t ad vissza.
	 */
	public ArrayList<Enemy> getEnemiesInRange(Tower tower)
	{
		String logString = "Map.getEnemiesInRange(tower)";
		Logger.Log(1, logString, this);
		ArrayList<Enemy> enemyList = new ArrayList<Enemy>();

		tower.getPosition();
		for(Enemy enemy: enemies) {
			enemy.getPosition();
			enemyList.add(enemy);
		}
		Logger.Log(0, logString, this);
		return enemyList;
	}

	/**
	 * A palyan levo ellensegeket, es tornyokat ertesiti az ido mulasarol.
	 * Ehhez a towers es enemies listakban tarolt objektumok tick fuggvenyet hivja meg. 
	 * Idonkenkent karbantartja az aktualis kort, hozzad ellenfeleket a palyahoz es
	 *  kodoket rak a tornyokra. 
	 * Minden iteracio vegen ellenorzi, hogy veget ert-e a jatek.
	 */
	public GameResult simulateWorld()
	{
		String logString = "Map.simulateWorld()";
		Logger.Log(1, logString, this);

		boolean isEndPoint = false;
		String answerText;
		Scanner scanner = new Scanner (System.in);

		while(true) {
			for(Enemy enemy: enemies) {
				enemy.getPosition().getCellType();

				// Felhasznaloi interakcio
				if(Program.usecaseNumber==2) {
					while(true) {
						System.out.print("Celmezore lepett [igen, nem]: ");
						answerText = scanner.next();
						if(answerText.equals("igen")) {
							isEndPoint=true;
							break;
						} else if(answerText.equals("nem")) {
							isEndPoint=false;
							break;
						} else {
							System.out.println("Helytelen ertek");
							continue;
						}
					}
				}
			}

			if(isEndPoint) {
					break;
			}
		}
		Logger.Log(0, logString, this);
		return null;
	}
}
