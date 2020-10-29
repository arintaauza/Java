import java.util.Random;


public class Nutritioniste extends Animal{

	public boolean stop = false;
	
	public Nutritioniste(int posX, int posY) {
		super(posX, posY);
		// TODO Auto-generated constructor stub
	}

	public Nutritioniste(int posX, int posY, int dir) {
		super(posX, posY, dir);
		// TODO Auto-generated constructor stub
	}
	
	public void changer_direction () {
        Random rn = new Random();
       // System.out.println("befor dir "+dir);
        int temp = dir;
        int n = rn.nextInt(2);
        dir = ((n* 2) + dir%2 + 1) %4;  
        System.out.println(temp +">>>>>>>>>>>>>>>>><><"+ dir);
       // System.out.println("after dir "+dir);
    }
}
