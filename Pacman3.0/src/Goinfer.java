import java.util.Timer;
import java.util.TimerTask;


public class Goinfer extends Animal implements Runnable {

	private int  energie = 100;
	boolean estvivant = true;
	int delay = 5 ; // delay avant que le goinfer perd  res 10 seconds l'energie dicriment 
	
	public Goinfer(int posX, int posY) {
		super(posX, posY);
		// TODO Auto-generated constructor stub
	}
	
	public void  manger(int type)
	{
		System.out.println("manger type"+type);
		switch (type) {
		case 1:// type de gateau 
			energie += 20;
		
			break;

		case 2:// type de gateau 
			// BLALALLALA 
			break;
			
		case 3 : // 
			//bllksd,dkl,d
		default:
			break;
		}
	}

	public int getEnergie() {
		return energie;
	}

	public void setEnergie(int energie) {
		this.energie = energie;
	}

	public boolean estvivant() {
		return estvivant;
	}

	public int getDelay() {
		return delay;
	}

	public void setDelay(int delay) {
		this.delay = delay;
	}
	@Override
	public void run() {
		// TODO Auto-generated method stub
		/*
		 * 
		 */
		
		Timer t = new Timer();
		
		if(estvivant)
		{
			t.schedule(new TimerTask() {
				// on met un compteur chaque 15 second si il mange pas il perde 20 point d'energie
				@Override
				public void run() {
					delay--;
					
					if (delay ==0)
					{
						energie -=20;
						System.out.println(">>>>>>>< energie"+energie);
						delay = 15;
						if (energie <=0)
						{
							
							estvivant = false;
							//System.out.println((" le goinfer est mort"));
						}
					}
				}
			},0, 1000);
				
		}		
	}

	public boolean isEstvivant() {
		return estvivant;
	}

	public void setEstvivant(boolean estvivant) {
		this.estvivant = estvivant;
	}
	
}