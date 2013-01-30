//main
package Server;
import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import subClasses.Dirtrav;
import subClasses.HomeworkManager;

public class mainServer {
	private final static int PORT = 10017;
	private ServerSocket server;
	private final String TERMINATE_KEY = "server.close";
	private PrintWriter out;
	private BufferedReader in;

	private boolean serverOn;

	public mainServer() {
		serverOn = true;
		try {
			server = new ServerSocket(PORT);
		} catch (IOException e) {
			e.printStackTrace();
		}
		runServer();
	}

	private void announce(String announcement){
		System.out.println("SERVER: " + announcement);
	}

	private void runServer() {
		Socket socket;
		announce("server on.");
		while (serverOn) {
			try {
				socket = server.accept();
				System.out.println("new connection.");
				newConnection(socket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		announce("server off."); 
	}

	private void newConnection(Socket socket){
		String note;
		try{
			out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			note = in.readLine();
			analyzeNote(note);
		}catch(Exception e){
			System.out.println("socket IO error.");
		}
	}

	private void analyzeNote(String note){
		int op = -1;
		note = note.toLowerCase();
		System.out.println("MESSAGE FROM USER: " + note);
		try{
			op = Integer.parseInt(note.substring(0,1));
			op = Integer.parseInt(note.substring(0,2));
		}catch(Exception e){
			//System.out.println("illegal op code on note : " + note);
			if(note.equals(TERMINATE_KEY))serverOn = false;
			else if(op== -1) {
				return;
			}
		}
		
		if(op == 0){
			if(note.endsWith("returnlist")){
				out.println(new HomeworkManager().getList());
			}else{
			addHomeworkAssignment(note, op);
			out.println("added to Homework list.");
			}	
		}
		else if(op == 1){
			songRequest(note.substring(1));
		}
	}
	
	private void songRequest(String song){
		Dirtrav dr = new Dirtrav("../../Volumes/Seagate/music");
		ArrayList<File> alf = dr.traverseGrab(song, dr.getDir());
		for(int i = 0; i < alf.size(); i++){
			System.out.println("id: #" + i + "| " +alf.get(i).getName());
			System.out.println(dr + "/" + alf.get(i).getName());
		} 
	}

	public void addHomeworkAssignment(String note, int padding){
		int size = (padding + "").length();
		new HomeworkManager().addAssignment(note.substring(size));
	}

	public static void main(String[] args) {
		new mainServer();
	}
}