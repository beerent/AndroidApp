package subClasses;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;
import java.util.Timer;

public class HomeworkManager {
	private static final int MAX= 25;
	private String [] homeworkList;
	private int size;
	private String homeworkPath;
	private Timer timer;
	
	public HomeworkManager(){
		homeworkPath = "src/subClasses/resources/homework.hmk";
		if(!new File(homeworkPath).exists()) homeworkPath = "resources/homework.hmk";
		timer = new Timer();
	}
	
	public void addAssignment(String newAssignment){
		if(newAssignment.length() >= 1){ 
			setAssignments();
			homeworkList[size] = newAssignment;
			writeAssignments();
		}
	}
	
	private void setAssignments(){
		File f;
		Scanner sc;
		PrintWriter out;
		int count = 0;
		size = 0;
		homeworkList = new String [25];
		try {
			f = new File(homeworkPath);
			sc = new Scanner(f);
			while(sc.hasNextLine()){
				String next = sc.nextLine();
				if(next.substring(0,2).equals("id"))
					homeworkList[count] = next.substring(5 + ("" + count).length());
				else
					homeworkList[count]=next;
				count++;
				size++;
			}
		}catch(Exception e){		
		}		
	}
	
	private void writeAssignments(){ 
		try{
			PrintWriter out = new PrintWriter(new FileWriter(homeworkPath));
			for(int i = 0; i < homeworkList.length; i++){
			if(homeworkList[i] == null) break;
			if(homeworkList[i].length() < 1) continue;
			out.write("id " + i + ": " + homeworkList[i] + "\n");
			}
			out.close();
		}catch(Exception e){
			
		}
	}
	
	public void removeAssignment(String target){
		setAssignments();
		try{
			int id = Integer.parseInt(target);
			if(id<size && id >= 0){
				System.out.println("removing " + homeworkList[id]);
				for(int i = id; i < size; i++){
					System.out.println("replacing " + homeworkList[i] + " with " + homeworkList[i+1]);
					homeworkList[i] = homeworkList[i+1];
				}
			}
			writeAssignments();
		}catch(Exception e){
			for(int i = 0; i < size; i++){
				if(homeworkList[i].equals(target)) removeAssignment("" + i);
			}
		}
		
	}
	
	//TODO
	public void getAssignment(){
		
	}
	
	public String getList(){
		String list = "";
		setAssignments();
		for(int i = 0; i < size; i ++) list +=homeworkList[i] + "\n"; 
		return list;
	}
	
	//method prints the entire list of homework
	//thread sleeps for 5 minutes (hard-coded)
	private void displayHomework(){
		File f;
		Scanner sc;
		while(true){
			try {
				f = new File(homeworkPath);
				sc = new Scanner(f);
				while(sc.hasNextLine()) System.out.println(sc.nextLine());
				sc.close();
		        Thread.sleep(50000);
				for(int i = 0; i < 10; i++) System.out.println("");
			}catch(Exception e){
				System.out.println("displayHomework() error");
			}
		}
	}
	
	//completely clears homework.hwk 
	private void clearHomework(){
		homeworkList = new String [MAX];
		writeAssignments();
	}
	
	//if main is running, displayHomework() is ran
	public static void main(String[] args) {
		HomeworkManager hm = new HomeworkManager();
		hm.displayHomework();
	}
}
