import javax.swing.JFrame;
import javax.swing.text.View;


public class Test  {
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ViewGame f1 = new ViewGame();
		Thread t1 = new Thread(f1);
		t1.start();
		Gateau g1 = new Gateau(10,3);
		
		//SGoinfer g1 = new Goinfer(); 
	}

	

}
