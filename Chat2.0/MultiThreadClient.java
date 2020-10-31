import java.net.*;
import java.io.*;
import java.util.*;

public class MultiThreadClient implements Runnable {
	// The client socket
	private static Socket clientSocket = null;
	// The output stream
	private static PrintStream os = null;
	// The input stream
	private static DataInputStream is = null;
	
	private static BufferedReader inputLine = null;
	private static boolean closed = false;
	
	private static DatagramSocket server = null;
	private static DatagramPacket receivePacket = null;
	
	public static void main (String[] args) {
		// The default port
		int portNumber = 7000;
		
		// The default host
		String host = "localhost";
		
		byte [] dataReceived = new byte[100];
		
		if (args.length < 2) {
			System.out.println("Usage: java MultiThreadClient <host> <portNumber> \n" + "Now using host=" + host  + ", portNumber=" + portNumber);
		} else {
			host = args[0]; 
			portNumber = Integer.valueOf(args[1]).intValue();
		}
		
		// Open a socket on a given host and port.
		try {
			server = new DatagramSocket(clientSocket.getLocalPort());
			byte [] receiveData=new byte[1024];
			receivePacket=new DatagramPacket(receiveData, receiveData.length);
			
			clientSocket = new Socket(host, portNumber);
			
			inputLine = new BufferedReader(new InputStreamReader(System.in));
			os = new PrintStream(clientSocket.getOutputStream());
			is = new DataInputStream(clientSocket.getInputStream());
		} catch (UnknownHostException e) {
			System.err.println("Don't know about " + host);
		} catch (IOException e) {
			System.err.println(e);
		}
		
		if (clientSocket != null && os != null && is != null) {
			try {
				// Create a thread read from the server
				new Thread(new MultiThreadClient()).start();

				String inp;
				while (!closed) {	
					//System.out.print(">");
					inp = inputLine.readLine();
					if (!inp.startsWith("GET") && (inp != null)) {
						os.println(inp);
					} else if (receivePacket) {
					// ini harusnya if (datagram yg amplop kosong ga null) 
					// dia kirim data dia ke dalem amplop kosong nya
					} else {
						//ini ngecek split command GET tinggal pake tu response 0 sampe 2 buat ngirim amplop kosong
						String response[] = inp.split(" ");
						
						System.out.println("tes" + response[0]);
						System.out.println("tes" + response[1]);
						System.out.println("tes" + response[2]);
					}
				}
				// Close
				os.close();
				is.close();
				clientSocket.close();
			} catch (IOException e) {
				System.err.println("IOException: "+ e);
			}
		}
		
	}
	
	// Create thread to read from the server
	public void run() {
		// Keep reading from socket until Bye from server
		String responseLine;
		try {
			while ((responseLine = is.readLine()) != null) {
		
			  System.out.println(responseLine);
			 
				if (responseLine.indexOf("***Bye") != -1)
					break;
			}
			
			closed = true;
		} catch (IOException e) {
			System.err.println("IOException: "+ e);
		}
	}
} 