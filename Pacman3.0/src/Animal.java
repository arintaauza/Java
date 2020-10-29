
public abstract class Animal {
	/*
	* la class Animal est une classe abstrait contient 
	* tous les fonctionalites de mouvement 
	* et trois attributs x y et direction
	* tous les autre classe (goinfer nutrisioniste et gateaux missile y heritent
	*/
	protected  int posX  ,posY, vitesse=7;
	/* direction 
	 * Nord = 0
	 * Sud = 2
	 * oeust = 1
	 * est = 3
	 */
	protected int dir ;
	
	public int getDir() {
		return dir;
	}

	public void setDir(int dir) {
		this.dir = dir;
	}

	Panneau mypanneau ;
	
	public Animal(int posX, int posY)
	{
		//System.out.println("+++++++++++++constructeur animal");
		
		this.posX = posX;
		this.posY=posY;
		dir = 3;
	}
	public Animal(int x,int y ,int dir)
	{
		this.posX = x;
		this.posY= y;
		this.dir = dir;
		
	}
	public void move() {
        if (dir == 0) {
            monter();
        } else if (dir == 1) {
            gauche();
        } else if (dir == 2) {
            descendre();
        } else {
            droit();
        }
    }
	
	public int getPosX() {
		return posX;
	}

	public void setPosX(int posX) {
		this.posX = posX;
	}

	public int getPosY() {
		return posY;
	}

	public void setPosY(int posY) {
		this.posY = posY;
	}

	public int getVitesse() {
		return vitesse;
	}

	public void setVitesse(int vitesse) {
		this.vitesse = vitesse;
	}

	public boolean detecterObstacle(){
	// pour detecter les obstacles util pour goinfer et nutr..
		   return true ;
		}

	/* determiner les mouvement droit
	 * droit : tourner a droit
	 * gauche : tourner a gauche 
	 */
	
		public void  droit()
		{
			posX = posX + vitesse;
			//System.out.println("pos X"+posX);
		}
		public void  gauche()
		{
			posX = posX - vitesse;
		}
		
		public void  monter()
		{
			posY = posY - vitesse;
		}
		
		public void  descendre()
		{
			posY = posY + vitesse;
		}
		
}
