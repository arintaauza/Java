import java.net.*;
import java.io.*;

public class ServerTCP {
	// The server socket
	private static ServerSocket serverSocket = null;
	// The client socket
	private static Socket clientSocket = null;
	
	// accept up to maxClient connection
	private static final int maxClientsCount = 10;
	private static final clientThread[] threads = new clientThread[maxClientsCount];
	
	public static void main(String args[]) {
		// The default port number
		int portNumber = 7000;
		if (args.length < 1) {
			System.out.println("Now using " + portNumber);
		} else {
			portNumber = Integer.valueOf(args[0]).intValue();
		}
		
		// Open a server socket on portNumber
		try {
			serverSocket = new ServerSocket(portNumber);
		} catch (IOException e) {
			System.err.println(e);
		}
		
		// Create a client socket for each connection and pass it to a new client thread
		while (true) {
			try {
				clientSocket = serverSocket.accept();
				int i = 0;
				for (i = 0; i < maxClientsCount; i++) {
					if (threads[i] == null) {
						(threads[i]=new clientThread(clientSocket, threads)).start();
						break;
					}
				}
				if (i == maxClientsCount) {
					PrintStream os = new PrintStream(clientSocket.getOutputStream());
					os.println("Server too busy. Try later.");
					os.close();
					clientSocket.close();
				}
			} catch (IOException e) {
				System.err.println(e);
			}
		}
	}
}

class clientThread extends Thread {
	private DataInputStream is = null;
	private PrintStream os = null;
	private Socket clientSocket = null;
	private String name = null;
	private final clientThread[] threads;
	private int maxClientsCount;
	
	public clientThread(Socket clientSocket, clientThread[] threads) {
		this.clientSocket = clientSocket;
		this.threads = threads;
		maxClientsCount = threads.length;
	}
	
	public void run() {
		int maxClientsCount = this.maxClientsCount;
		clientThread[] threads = this.threads;
		
		try {
			is = new DataInputStream(clientSocket.getInputStream());
			os = new PrintStream(clientSocket.getOutputStream());
			os.println("Enter your name: ");
			String name = is.readLine().trim();
			this.name = name;
			os.println("Hello "+ this.name);
			os.println("Your IP address is "+ clientSocket.getRemoteSocketAddress().toString());
			
			
			//String cmd = is.readLine().trim();
			

			while (true) {
				os.println("Enter your command.");
				String line = is.readLine();
				
				if (line.startsWith("LIST")) {
					for (int i = 0; i < maxClientsCount; i++) {
						if (threads[i] !=  null && threads[i] != this) {
							os.println("User " + threads[i].name + " : " + threads[i].clientSocket.getRemoteSocketAddress().toString());
						}
					}
				} else if (line.startsWith("QUIT")) {
					break;
				} 
				
			}
			
			os.println("***Bye "+ name + " ***");
			
			for (int i=0; i < maxClientsCount; i++) {
				if (threads[i] == this) {
					threads[i] = null;
				}
			}
			
			is.close();
			os.close();
		} catch (IOException e) {
		
		}		
	}
}