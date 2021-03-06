package com.android.AssistantApp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.widget.Toast;

@SuppressLint("NewApi")
public class sender extends AsyncTask<String, Void, String> {
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	private SkeletonActivity sk;
	
	public sender(SkeletonActivity sk){
		this.sk = sk;
	}
	
	protected String doInBackground(String... message) {
		String response = "initial";
		try {
			socket = new Socket("bdubdolla.dyndns.tv", 10017);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out.println(message[0]); //send note with op code in front to server. 
			response = in.readLine(); //read response from server. 
			response = analyzeResponse(response); //analyze response from server. 	
		} catch (UnknownHostException e) {
			return "fail UHE";
		} catch (IOException e) {
			return "fail IOE";
		}		
		return response; //response from server printed on android screen.
	}
	
	private String analyzeResponse(String response) {
		return response;
	}

	protected void onPostExecute(String response) {
		Toast.makeText(sk.getApplicationContext(), response, Toast.LENGTH_LONG).show();
	}
}
