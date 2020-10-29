import java.util.Timer;
import java.util.TimerTask;


public class Missile extends Animal implements Runnable{
	public Missile(int x, int y, int dir) {
		super(x, y, dir);
		// TODO Auto-generated constructor stub
	}
	static int x;
	static int  y;
	static int dir ;
	static int quantite = 100; // par defaut le nombre de missile
static boolean attaquer = false;

static boolean Attaquer() {
	return attaquer;
	
}
static void setAttaquer(boolean attaquer) {
	// attaque on le droit d'attaque
 attaquer = attaquer;
 System.out.println("->>>>>>>>>>>>>>>>>>>>>>><< j ai modifie bolean "+attaquer);
}


static void setX(int x) {
	x = x;
}

static void setY(int y) {
	y = y;
}

static void setDir(String dir) {
	dir = dir;
}
@Override
public void run() {
	// TODO Auto-generated method stub
	
}

	
}
