package subClasses;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Scanner;

public class HomeworkManager {
	private String homeworkPath;
	
	public HomeworkManager(){
		homeworkPath = "src/subClasses/resources/homework.hmk";
	}
	
	public void addAssignment(String newAssignment){
		File f;
		Scanner sc;
		PrintWriter out;
		int count = 0;
		String [] homeworkList = new String [25];
		try {
			f = new File(homeworkPath);
			sc = new Scanner(f);
			String list = "";
			while(sc.hasNextLine()){
				homeworkList[count] = sc.nextLine();
				count++;
			}
			homeworkList[count] = newAssignment;
			sc.close(); //properly close file
			out = new PrintWriter(new FileWriter(homeworkPath));
			for(int i = 0; i < count-1; i++){
				
			}
			out.close();
		} catch (IOException e) {
			System.err.println("ERROR IN: addAssignment(String newAssignment)");
		}
	}
	
	public void removeAssignment(){
		
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
	public static void main(String[] args) {
		HomeworkManager hm = new HomeworkManager();
		hm.addAssignment("test 1");
		hm.addAssignment("test 2");
		hm.displayHomework();
	}
}
