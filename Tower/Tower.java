package Tower;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Tower.java
//  @ Date : 2014.03.20.
//  @ Author : 
//
//




/**
 * Torony.
 */
public class Tower extends Item
{
	/**
	 * Sebzesi ero.
	 */
	private int firePower;
	/**
	 * Egy loves utan ennyi idonek kell eltelnie, 
	 *  hogy a torony ujra lohessen.
	 */
	private int attackSpeed;
	/**
	 * Lotavolsag.
	 */
	private int range;
	/**
	 * Az egyes ellenseg tipusokra vonatkozo bonusz sebzesek.
	 */
	private HashMap<String, Integer> bonusPowers;
	/**
	 * Az az idopont amikor a torony legutoljara lott.
	 */
	private long lastTime;
	/**
	 * A palya referenciaja.
	 */
	private Map map;
	/**
	 * Ennyivel van csokkentve a range attributum erteke az aktiv kod miatt.
	 */
	private int rangeDecreaseByFog;
	/**
	 * Eddig az idopontig lesz aktiv a kod a tornyon.
	 */
	private long fogRemovalTime;
	/**
	 * Igaz, ha van aktiv kod a tornyon.
	 */
	private boolean fogActive;
	/**
	 * Ennyi esellyel lo kettobe a torony egy ellenfelet.
	 * A [0, 1) intervallumba esik.
	 */
	private double sliceShootProbability;

	/**
	 * Konstruktor. Peldanyositashoz ajanlott ezt hasznalni.
	 * Default parameterekkel jon letre a torony. 
	 * @param pos
	 * @param m
	 */
	public Tower(Cell pos, Map m) {
		this(
				10,		// Power
				500,	// AttackSpeed
				1,		// Range
				3,		// Varazskovek maximalis szama
				pos,	// Pozicio
				m,		// Map referencia
				0.1		// Kettobe vagas eselye
				);
	}

	/**
	 * Konstruktor.
	 * @param power  Sebzesi ero.
	 * @param as  A torony lovesei kozott eltelt ido.
	 * @param r  Lotavolsag.
	 * @param mm  A toronyra rakhato varazskovek maximalis szama.
	 * @param pos  A torony pozicioja.
	 * @param map  A palya.
	 * @param slice  Az a valoszniuseg, amely alapjan kettobe lo a torony egy ellenseget.
	 */
	public Tower(int power, int as, int r, int mm, Cell pos, Map map, double slice)
	{
		super(mm, pos);
		this.firePower = power;
		this.attackSpeed = as;
		this.range = r;
		this.bonusPowers = new HashMap<String,Integer>();
		this.lastTime = System.currentTimeMillis();
		this.map = map;
		this.fogActive = false;
		sliceShootProbability = slice;
	}

	/**
	 * Getter a firePower attributumra.
	 * @return  A firePower attributum.
	 */
	public int getFirePower()
	{
		return this.firePower;
	}

	/**
	 * Getter az attackSpeed attributumra.
	 * @return  Az attackSpeed attributum.
	 */
	public int getAttackSpeed()
	{
		return this.attackSpeed;
	}

	/**
	 * Getter a range attributumra.
	 * @return  A range attributum.
	 */
	public int getRange()
	{
		return this.range;
	}

	/**
	 * Getter a lastTime attributumra.
	 * @return  A lastTime attributum.
	 */
	public long getLastTime()
	{
		return this.lastTime;
	}

	/**
	 * Getter a bonusPowers attributumra.
	 * @return  A bonusPowers attributum.
	 */
	public HashMap<String,Integer> getBonusPowers()
	{
		return this.bonusPowers;
	}

	/**
	 * Getter a fogActive attributumra.
	 * @return  A fogActive attributum.
	 */
	public boolean getFogActive() {
		return fogActive;
	}

	/**
	 * Setter a firePower attributumra.
	 * @param power  A firePower attributum kivant erteke.
	 */
	public void setFirePower(int power)
	{
		this.firePower = power;
	}

	/**
	 * Setter az attackSpeed attributumra.
	 * @param as  Az attackSpeed attributum kivant erteke.
	 */
	public void setAttackSpeed(int as)
	{
		this.attackSpeed = as;
	}

	/**
	 * Setter a range attributumra.
	 * @param r  A range attributum kivant erteke.
	 */
	public void setRange(int r)
	{
		this.range = r;
	}

	/**
	 * Setter a lastTime attributumra.
	 * @param lt  A lastTime attributum kivant erteke.
	 */
	public void setLastTime(long lt)
	{
		this.lastTime = lt;
	}

	/**
	 * Setter a bonusPowers attributumra.
	 * @param bonus  A bonusPowers attributum kivant erteke.
	 */
	public void setBonusPowers(HashMap<String,Integer> bonus)
	{
		this.bonusPowers = bonus;
	}

	/**
	 * Setter a fogActive attributumra.
	 * @param bonus  A fogActive attributum kivant erteke.
	 */
	public void setFogActive(boolean fogActive) {
		this.fogActive = fogActive;
	}

	/**
	 * Megvizsgalja, hogy a maxMagicStoneNumber attributum erteke kisebb-e
	 *  mint a magicStones lista hossza, es ha igen akkor a kapott varazsko
	 *  attributumait felhasznalva modositja a torony attributumait
	 *  es true-val ter vissza.
	 * Ellenkezo esetben false-szal ter vissza.
	 */
	public boolean upgrade(MagicStone stone)
	{
		// Csak akkor fejlesztunk, ha meg nem ertuk el a maximalis fejlesztesek szamat
		if (maxMagicStoneNumber < magicStones.size()) {
			// Elrakjuk a kovet az akadalyba
			magicStones.add(stone);

			// Fejlesztjuk a tornyot
			firePower += stone.firePower;
			range += stone.range;
			attackSpeed -= stone.attackSpeed;

			// Modositjuk a bonusz sebzesi eroket
			for (String enemy : stone.bonusPowers.keySet()) {
				// Ha van ilyen ellenseghez tartozo bejegyzes,
				// akkor azt modositjuk
				if (bonusPowers.containsKey(enemy)) {
					bonusPowers.put(
							enemy, 
							bonusPowers.get(enemy) + stone.bonusPowers.get(enemy)
							);
				}
				// Ha nincs, akkor letrehozzuk
				else {
					bonusPowers.put(enemy, stone.bonusPowers.get(enemy));
				}
			}

			return true;
		}

		// Ha nem lehetett fejleszteni akkor visszaterunk false-szal
		return false;

		/*String logString = "Tower.upgrade(stone)";
		Logger.Log(1, logString, this);

		Logger.Log(0, logString, this);
		return true;*/
	}
	
	/**
	 * A torony ralo egy ellenfelre.
	 * Ehhez meghivja a map getEnemysInRange fuggvenyet atadva onmagat. 
	 * Ha nem null erteket kap vissza, akkor egy logika alapjan kivalaszt 
	 *  egy Enemy-t a kapott listabol és meghivja annak a damage fuggvenyet
	 *  atadva a firePower-t, es a bonusPowers attributumok ertekeit. 
	 * Ha a damage fuggveny true-val ter vissza akkor meghivja a map 
	 *  removeEnemy fuggvenyet atadva a kivalasztott Enemy-t.
	 * Aktiv kod eseten leszedi a kodot a toronyrol, ha mar lejart az ideje.
	 */
	public void shoot()
	{
		// Ha van kod a tornyon es lejart az ideje, akkor azt eltavolitjuk
		if (fogActive && System.currentTimeMillis() >= fogRemovalTime ) {
			removeFog();
		}

		// Elkerjuk a maptol a lotavolsagon beluli ellensegeket
		ArrayList<Enemy> enemys = map.getEnemiesInRange(this);

		// Ha van ellenseg lotavolsagon belul, akkor valakire loni fogunk
		// veletlenszeruen
		if (enemys != null) {
			int random = (int)(Math.random() * enemys.size());
			Enemy enemy = enemys.get(random);
			boolean result = enemy.damage(firePower, bonusPowers);

			// Ha meghalt az ellenseg, akkor eltavolitjuk a palyarol
			if (result) {
				map.removeEnemy(enemy);
			}
			// Ha nem halt meg az ellenseg, akkor a tarolt valoszinusegnek
			// megfeleloen kettobe lojuk
			else if (Math.random() < sliceShootProbability) {
				// Felezzuk az eleterot
				enemy.healthPoint /= 2;
				// Lemasoljuk az ellenseget es hozzadjuk a klont a maphoz
				Enemy enemy2 = enemy.clone();
				map.getEnemies().add(enemy2);
			}
		}

		/*
		Enemy enemy;
		boolean isDead = false;
		String answerText;
		Scanner scanner = new Scanner (System.in);

		String logString = "Tower.shoot()";
		Logger.Log(1, logString, this);

		ArrayList<Enemy> enemies;
		enemies = map.getEnemiesInRange(this);

		enemy = enemies.get(0);
		HashMap<String, Integer> hm = new HashMap<String, Integer>();
		hm.put("hobbit", 1);
		enemy.damage(1, hm);

		// Felhasznaloi interakcio
		if(Program.usecaseNumber==10) {
			while(true) {
				System.out.print("Meghalt az ellenseg [igen, nem]: ");
				answerText = scanner.next();
				if(answerText.equals("igen")) {
					isDead=true;
					break;
				} else if(answerText.equals("nem")) {
					isDead=false;
					break;
				} else {
					System.out.println("Helytelen ertek");
					continue;
				}
			}
		}

		if(isDead) {
			map.removeEnemy(enemy);
		}
		Logger.Log(0, logString, this);
		*/
	}

	/**
	 * Aktival egy kodot a toronyra.
	 * @param decrease A range attributumot ennyivel kell csokkenteni.
	 * @param duration A kod ennyi ideig lesz aktiv a tornyon.
	 */
	public void applyFog(int decrease, int duration) {
		// Jelezzuk, hogy van kod a tornyon
		fogActive = true;
		// Beallitjuk azt az idot amikor majd le kell szedni a kodot
		fogRemovalTime = System.currentTimeMillis() + duration;
		// Elmentjuk, hogy mennyivel csokkentettuk a lotavolsagot
		rangeDecreaseByFog = decrease;
		// Csokkentjuk a lotavolsagot
		range -= decrease;
	}

	/**
	 * Leveszi a kodot a toronyrol.
	 */
	public void removeFog() {
		// Jelezzuk, hogy mar nincs kod a tornyon
		fogActive = false;
		// Visszaallitjuk a lotavolsagot
		range += rangeDecreaseByFog;
	}

	/**
	 * Ertesiti a tornyot az ido mulasarol.
	 * Ha a legutolso loves idopontja ota eltelt az attackSpeed-ben
	 *  levo ido, akkor meghivja a shoot fuggvenyt.
	 */
	public void tick()
	{

		long current = System.currentTimeMillis();
		// Ha eljott a loves ideje, akkor lovunk
		// es elmentjuk a loves idopontjat
		if (current - lastTime >= attackSpeed) {
			shoot();
			lastTime = current;
		}
	}
}
