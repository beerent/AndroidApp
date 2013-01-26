package com.example.android.skeletonapp;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import android.annotation.SuppressLint;
import android.os.AsyncTask;
import android.widget.Toast;

public class sender extends AsyncTask<String, Void, String> {
	private Socket socket;
	private PrintWriter out;
	private BufferedReader in;
	private SkeletonActivity sk;
	
	public sender(SkeletonActivity sk){
		this.sk = sk;
	}
	
	protected String doInBackground(String... message) {
		try {
			socket = new Socket("bdubdolla.dyndns.tv", 10017);
			PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
			in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			out.println("test");
			String response = in.readLine();
			sk.mEditor.setText(response);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return "complete";
	}
	
	protected void onPostExecute(String response) {
		Toast.makeText(sk.getApplicationContext(), response, Toast.LENGTH_LONG).show();
	}
}
