package subClasses;

import java.io.File;
import java.util.ArrayList;

/*
 *
 * This file is a simple directory traversal utility.
 * 
 */

public class Dirtrav {
	public String directory;
	public File currentDir;
	private ArrayList<File> possibleFiles;
	
	public Dirtrav(String directory){
		this.directory = directory;
		currentDir = new File(System.getProperty("user.home") + "/" + directory);
		System.out.println(currentDir.exists());
		possibleFiles = new ArrayList<File>();
	}
	
	public void traverse(File dir){
		for (File file : dir.listFiles()){
			if (file.isDirectory()){
				System.out.println("DIR: " + file);
				traverse(file);	
			} else {
				System.out.println("FIL: " + file);
			}
			
		}
	}
	
	public void traverseSearch(String target, File dir){
		for (File file : dir.listFiles()){
			if(file.getName().contains(target)) possibleFiles.add(file);
			if (file.isDirectory()){
				traverse(file);	
			} else {
			}
			
		}
	}
	
	public ArrayList<File> traverseGrab(String target, File dir){
		for (File file : dir.listFiles()){
			//System.out.println("viewing: " + file);
			if(file.getName().toLowerCase().contains(target)) possibleFiles.add(file);
			if (file.isDirectory()){
				try{
					traverseGrab(target, file);	
				}catch(Exception e){
					System.out.println("SYSTEM: couldnt search directory " + file.getName());
				}
			}
		}
		return possibleFiles;
	}
	
	public File getDir(){
		return currentDir;
	}
	
	public static void main(String [] args){
		Dirtrav dtrv = new Dirtrav("../../Volumes/Seagate");
		ArrayList<File> af = dtrv.traverseGrab("My Life".toLowerCase(), dtrv.getDir());
		for(int i = 0; i < af.size(); i++){
			System.out.println("FOUND: " + af.get(i));
		}
		System.out.println("found " + af.size() + " files");
	}
}
