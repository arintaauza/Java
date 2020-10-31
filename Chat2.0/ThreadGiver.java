/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hafizbudif
 */
//B

import java.net.*;
import java.io.*;


public class ThreadGiver implements Runnable{
    int port;
    public ThreadGiver(int p){
	port = p;
    }
    
    public void run(){
	try{
	    DatagramSocket server = new DatagramSocket(port);
	    //server.setSoTimeout(2000);
	    byte [] dataReceived = new byte[100];
	    byte[] sendData ;
	    while(true){
		try{
		    DatagramPacket rcvPacket = new DatagramPacket(dataReceived, dataReceived.length);
		    server.receive(rcvPacket);

		    InetAddress IPAddress = rcvPacket.getAddress();//ngambil IP nya A
		    int port = rcvPacket.getPort();//ngambil port A
		    String data = "Data B";
		    sendData = new byte[data.length()];
		    sendData = data.getBytes();
		    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
		    server.send(sendPacket);
		}
		catch(SocketTimeoutException e){
		    continue;
		}
	    }
	    //server.close();
	    
	}
	catch(IOException exn){
	    
	}
	}
}

