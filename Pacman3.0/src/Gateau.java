import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;


public class Gateau extends Thread implements Runnable{

	private int ttl = 7000; // dure de vie de gateau
	/*
	 * type de gateau 1 --> gateaux donne l'energie
	 * 2--> gateau donne l'arme 
	 * 3-> gateau qui stop les nut..
	 */
	private int type = 1; 
	private int x = 400;
	private int y = 400;
	
	private boolean afficher = false;
	
	// true on affiche le gateux sinon on le masque
	public boolean isAfficher() {
		return afficher;
	}

	public void setAfficher(boolean afficher) {
		this.afficher = afficher;
	}

	public int getTtl() {
		return ttl;
	}

	
	// time to live la dure d'affichage de gateur est decrement chaque second
	public void setTtl(int ttl) {
		this.ttl = ttl;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Gateau(int ttl,int type )
	{
		this.ttl = ttl;
		this.type = type;
	}
	  public boolean isFilled(int x, int y) {
	        boolean filled = false;
	        for (int i = x; i <= x+40; i++) {
	            for (int j = y; j <= y+40; j++) {
	               try {
	            	   if (Panneau.mat_panneau[i][j] == 1) {
		                    filled = true;
		                    break;
		                }
				} catch (Exception e) {
					// TODO: handle exception
				}
	            	
	            }
	        }
	        return filled;
	    }
	public void go()
	{
		while(true)
		{
		
			try {

				 do {
	                    x = (int) (Math.random()*460) + 20;
	                    y =(int)(Math.random()*460)+ 20;
	                } while (isFilled(x,y));

				
				Thread.sleep(3000);// la dure avant qu'on affiche un gateau 
				afficher=true;
				
				Thread.sleep(4000); // la dure d'affichage de  gateux
				afficher=false;
				
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
	
	boolean afficher()
	{
		return afficher;
	}
	
	// masquer le gateau
	void masquer()
	{
		afficher=false;
	}
	
	public void run() {
		go();
	}
	
	
}
