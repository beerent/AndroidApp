import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

public class mainServer {
	private final static int PORT = 10017;
	private ServerSocket server;

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

	private void runServer() {
		Socket socket;
		while (serverOn) {
			try {
				socket = server.accept();
				newConnection(socket);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private void newConnection(Socket socket){
		PrintWriter out;
		BufferedReader in;
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
	
	public void analyzeNote(String note){
		System.out.println(note);		
	}

	public static void main(String[] args) {
		new mainServer();
	}
}
