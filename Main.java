import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		//verify that the command line argument exists 
				if (args.length == 0 ) {
					System.err.println("Usage Error: the program expects file name as"
							+ " an argument.\n");
					System.exit(1);
				}
				
				//verify that command line argument contains a name of an existing file 
				File file = new File(args[0]); 
				File file2 = new File(args[1]);
				if (!file.exists()){
					System.err.println("Error: the file "+file.getAbsolutePath()+
							" does not exist.\n");
					System.exit(1);
				}
				
				//verifies the file can be read
				if (!file.canRead()){
					System.err.println("Error: the file "+file.getAbsolutePath()+
													" cannot be opened for reading.\n");
					System.exit(1);
				}
				
				//creates list of Species
				Playlist spotifyLib = new Playlist();
				Playlist appleLib = new Playlist();
				
				//open the file for reading using Scanner and CSV
		        try (Scanner scanner2 = new Scanner(file)) {
		            CSV csv = new CSV(scanner2);
		            
		            //gets/skips headers
		            ArrayList<String> headers = csv.getNextRow();
		            
		            //iterate through each row and assign values based on header values
		            ArrayList<String> row = csv.getNextRow();
		            while (row != null) {
		            	try {
			                String name = row.get(0);
			                String ISRC = row.get(5);
			                //create new Species from data and add it to the list
			                spotifyLib.add(new Song(name, ISRC));
			                row = csv.getNextRow();
		            	}
			            
		            	//catches IllegalArgumentException thrown in Species class 
		            	//continues quietly without printing any errors
		            	catch(IllegalArgumentException ex) {
			                row = csv.getNextRow();
		            	}
		            	
		                //catches lines that do not contain the 7 values
		                catch(IndexOutOfBoundsException ex){
		                	row = csv.getNextRow();
		                }
		            }
		        } 
		        
		        
		        //catches if file is unable to be read
		        catch (FileNotFoundException e) {
		            System.err.println("Error: the file " + file.getAbsolutePath() 
		            		+ " cannot be opened for reading.\n");
		            System.exit(1);
		        }
		        try (Scanner scanner = new Scanner(file2)) {
		            CSV csv = new CSV(scanner);
		            
		            //gets/skips headers
		            ArrayList<String> headers = csv.getNextRow();
		            
		            //iterate through each row and assign values based on header values
		            ArrayList<String> row = csv.getNextRow();
		            while (row != null) {
		            	try {
			                String name = row.get(0);
			                String ISRC = row.get(5);
			                
			                //create new Species from data and add it to the list
			                appleLib.add(new Song(name,ISRC));
			                row = csv.getNextRow();
		            	}
			            
		            	//catches IllegalArgumentException thrown in Species class 
		            	//continues quietly without printing any errors
		            	catch(IllegalArgumentException ex) {
			                row = csv.getNextRow();
		            	}
		            	
		                //catches lines that do not contain the 7 values
		                catch(IndexOutOfBoundsException ex){
		                	row = csv.getNextRow();
		                }
		            }
		        } 
		        
		        
		        //catches if file is unable to be read
		        catch (FileNotFoundException e) {
		            System.err.println("Error: the file " + file.getAbsolutePath() 
		            		+ " cannot be opened for reading.\n");
		            System.exit(1);
		        }
		        
		        Collections.sort(spotifyLib);
		        Collections.sort(appleLib);
		        
		        System.out.println(spotifyLib.size()+" Spotify\n"+appleLib.size()+" Apple\n"+(spotifyLib.size()-appleLib.size())+" Diff");
		        
		        spotifyLib.showMissed(appleLib);
		        
	}

}
