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
			out.println("done");
		}catch(Exception e){
			System.out.println("socket IO error.");
		}
	}

	private void analyzeNote(String note){
		System.out.println("analyzing " + note );
		note = note.toLowerCase();
		System.out.println("MESSAGE FROM USER: " + note);
		if(note.equals(TERMINATE_KEY))serverOn = false;
		else if(note.contains("get song")){
			Dirtrav dr = new Dirtrav("../../Volumes/Seagate");
			ArrayList<File> alf = dr.traverseGrab("bigfiles", dr.getDir());
			System.out.println("respond with id#, or anything else to cancel.");
			for(int i = 0; i < alf.size(); i++){
				System.out.println("id: #" + i + "| " +alf.get(i).getName());
			}
		}else if(note.contains("download song")){
			//todownload list
		}
		else if(note.contains("hw") || note.contains("homework")){
			if(note.endsWith("returnlist")){
				out.write(new HomeworkManager().getList());
			}else{ 
				addHomeworkAssignment(note);
			}
		}
	}

	public void addHomeworkAssignment(String note){
		new HomeworkManager().addAssignment(note.substring(9));
	}

	public static void main(String[] args) {
		new mainServer();
	}
}