package subClasses;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class HomeworkManager {
	private static final int MAX= 25;
	private String [] homeworkList;
	private int size;
	private String homeworkPath;
	
	public HomeworkManager(){
		homeworkPath = "src/subClasses/resources/homework.hmk";
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
				homeworkList[count] = sc.nextLine();
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
				for(int i = id; i < size; i++){
					homeworkList[i] = homeworkList[i+1];
				}
			}
		}catch(Exception e){
			
		}
		
	}
	
	public void getAssignment(){
		
	}
	
	private void displayHomework(){
		File f;
		Scanner sc;
		try {
			f = new File(homeworkPath);
			sc = new Scanner(f);
			while(sc.hasNextLine()) System.out.println(sc.nextLine());			
		}catch(Exception e){
			
		}
	}
	
	private void clearHomework(){
		
	}
	
	public static void main(String[] args) {
		HomeworkManager hm = new HomeworkManager();
		hm.addAssignment("test 1");
		hm.addAssignment("");
		hm.displayHomework();
	}
}
