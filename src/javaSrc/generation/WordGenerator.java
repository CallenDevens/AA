
import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class WordGenerator {

	private static final int WORD_SUM_OF_THE_FILE = 29224;
	/**
	 * Generates collection of wordss from sampling a uniform distribution.
	 * 
	 * @author s3511792
	 */
	/* number of  elements to be generated */
	private Random mRandGen;
	private BufferedReader wordReader = null;
	
	public WordGenerator() {
		//mRandGen = new Random(System.currentTimeMillis());
	} // end of WordGenerator(
		
	/**
	 * Generate 'sampleSize' number of samples, using sampling with replacement.
	 * 
	 * @param sampleSize Number of samples to generate.
	 */
	public ArrayList<String> generateWordSet(int sampleSize) {
		String word = "";
		
		ArrayList<String> samples = new ArrayList<String>();
		
		int counter = 0;
		
		try{
			wordReader = new BufferedReader(new FileReader(new File("dict.txt")));

		
			while(counter < sampleSize){
				double ratio = sampleSize*10000/WORD_SUM_OF_THE_FILE;
				word = wordReader.readLine();
			
				
				if(word  == null){
					wordReader.close();
					wordReader = new BufferedReader(new FileReader(new File("dict.txt")));
				}else{
			
					double random = Math.random()*10000;
					if(random < ratio){
						samples.add(word);
						counter++;
					}
			    }
			}
		}catch(IOException e){
			e.printStackTrace();
		}
		finally{
			if(wordReader != null){
				try {
					wordReader.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		Collections.shuffle(samples);
		return samples;
	} // end of  generateWordSet
	
}//end of WordGenerator
