

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
//words3 58695
//words4 29224
public class WordsShriker {
	
	
	private BufferedReader wordReader;
	private PrintWriter writer;
	public WordsShriker(){
		try{
			wordReader= new BufferedReader(new FileReader(new File("words3.txt")));
			writer = new PrintWriter(new FileWriter(new File("words4.txt")));
		}catch(IOException e){
			e.printStackTrace();
		}
	}
	
	public void genenrateNewWordSet(){
		int count = 0;
		String word = "";
		try {
			while((word = wordReader.readLine()) != null){
				if(Math.random() < 0.5){
					writer.println(word);
					count++;
				}
			}
			
			wordReader.close();
			writer.close();
			System.out.println("get " + count + " words");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally{

		}
	}
	
	
	public static void main(String [] args){
		WordsShriker ws = new WordsShriker();
		ws.genenrateNewWordSet();
	}


}
