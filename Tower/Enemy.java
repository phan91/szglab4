package Tower;
import java.util.HashMap;

//
//
//  Generated by StarUML(tm) Java Add-In
//
//  @ Project : Untitled
//  @ File Name : Enemy.java
//  @ Date : 2014.03.20.
//  @ Author : 
//
//




public abstract class Enemy
{
	private int healthPoint;
	private int actualSpeed;
	private int originalSpeed;
	private int magic;
	private boolean isDead;
	private long lastTime;
	public static HashMap<String, Integer> enemySpeeds;
	private Cell position;

	//Constructor
	public Enemy(int hp, int as, int os, int m, long lt)
	{
		this.healthPoint = hp;
		this.actualSpeed = as;
		this.originalSpeed = os;
		this.magic = m;
		this.isDead = false;
		this.lastTime = lt;
		this.enemySpeeds = new HashMap<String,Integer>();
	}

	//Getter
	public int getHealthPoint()
	{
		return this.healthPoint;
	}

	public int getActualSpeed()
	{
		String logString = "Enemy.getActualSpeed()";
		Logger.Log(1, logString, this);

		Logger.Log(0, logString, this);

		return this.actualSpeed;
	}

	public int getOriginalSpeed()
	{
		String logString = "Enemy.getOriginalSpeed()";
		Logger.Log(1, logString, this);

		Logger.Log(0, logString, this);

		return this.originalSpeed;
	}

	public int getMagic()
	{
		return this.magic;
	}

	public long getLastTime()
	{
		return this.lastTime;
	}

	public boolean getIsDead()
	{
		return this.isDead;
	}

	public Cell getPosition()
	{
		return this.position;
	}

	//Setter
	public void setHealthPoint(int hp)
	{
		this.healthPoint = hp;
	}

	public void setActualSpeed(int as)
	{
		String logString = "Enemy.setActualSpeed(as)";
		Logger.Log(1, logString, this);

		this.actualSpeed = as;

		Logger.Log(0, logString, this);
	}

	public void setOriginalSpeed(int os)
	{
		this.originalSpeed = os;
	}

	public void setMagic(int m)
	{
		this.magic = m;
	}

	public void setLastTime(long lt)
	{
		this.lastTime = lt;
	}

	public void setIsDead(boolean dead)
	{
		this.isDead = dead;
	}

	public void setPosition(Cell pos)
	{
		String logString = "Enemy.setPosition(position)";
		Logger.Log(1, logString, this);
		this.position = pos;
		Logger.Log(0, logString, this);
	}

	public abstract boolean damage(int power, HashMap<String , Integer> bonus);

	public void tick()
	{
	}

	public abstract void move();
}
