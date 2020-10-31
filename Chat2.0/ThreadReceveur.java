/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author hafizbudif
 */
//A

import java.net.*;
import java.io.*;

public class ThreadReceveur extends Thread{
    int port;
    String adresse;

    ThreadReceveur(int port, String addr){
	this.port = port;
	this.adresse = addr;
    }


    public void run() {
	try{
	    DatagramSocket socketClient = new DatagramSocket();
	    InetAddress IPAddress = InetAddress.getByName(adresse); 
	    byte[] sendData = new byte[100];
	    byte[] receiveData = new byte[100];
	    DatagramPacket sendPacket = new DatagramPacket(sendData, sendData.length, IPAddress, port);
	    socketClient.send(sendPacket);
	    DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
	    socketClient.receive(receivePacket);
			
	    String donne = new String(receivePacket.getData());
	    String res="";
	    for(int i=0;i<donne.length();i++)
		if(donne.charAt(i)!=Character.MIN_VALUE)
		    res+=donne.charAt(i);
	    donne = res;
	    //if(!Client.myDatas.contains(donne)) Client.myDatas.add(donne);
	    socketClient.close();

	}catch (UnknownHostException e) { System.err.println("adresse"+e); 
	}catch (IOException e) { System.err.println(e); }
    }


}
