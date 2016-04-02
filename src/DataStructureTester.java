

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class DataStructureTester {
	private WordGenerator generator;
	private ArrayList<String> testData;
	
	Map<String, Multiset> dataStructures = new HashMap<String, Multiset>();
	
	public DataStructureTester(int dataScale){
		generator = new WordGenerator();
		testData = generator.generateWordSet(dataScale);
		
		dataStructures.put("BalTreeMultiet"         , new BalTreeMultiset<String>());
		dataStructures.put("HashMultiet"            , new HashMultiset<String>());
		dataStructures.put("LinkedListMultiet"      , new LinkedListMultiset<String>());
		dataStructures.put("SortedLinkedListMultiet", new SortedLinkedListMultiset<String>());
		dataStructures.put("BstMultiset"            , new BstMultiset());

	}
	
	public void test(int initialDataScale, int addScale, int removeScale, int searchScale)throws IllegalArgumentException{
//		int dataScale = testData.size();
		if(removeScale > initialDataScale+ addScale){
			throw new IllegalArgumentException("test scale out of range");
		}
		
		for(int i = 0; i < initialDataScale; i++ ){
	        int size = testData.size();
	        int index = ((int)Math.random()*size+1);
	        
	    	for(Multiset mulset: this.dataStructures.values()){
				mulset.add(testData.get(index));
			}
		}

		testAddAndRemoval(initialDataScale,addScale, removeScale, searchScale);
        
	}
	
	
	private void testAddAndRemoval(int initialDataScale,int addScale, int removeScale, int searchScale) {
		ArrayList<String> addList = new ArrayList<String>();
		ArrayList<String> removeList = new ArrayList<String>();
		ArrayList<String> searchList = new ArrayList<String>();
			
	    for(int i = initialDataScale; i < initialDataScale + addScale; i++ ){
	        int size = testData.size();
	        int index = ((int)Math.random()*size+1);
			addList.add(testData.get(index));
		}
	        
	    for(int i = 0; i < removeScale; i++ ){
	        int size = testData.size();
	        int index = ((int)Math.random()*size+1);
	        	
	        removeList.add(testData.get(index));
		//	entry.getValue().removeOne(testData.get(index));
		}
	    
	    for(int i = 0; i < searchScale; i++ ){
	        int size = testData.size();
	        int index = ((int)Math.random()*size+1);
	        	
	        searchList.add(testData.get(index));
		}
	    
		for(Map.Entry<String, Multiset> entry: this.dataStructures.entrySet()){
			long startTime = System.nanoTime();

			for(String element: addList){
				entry.getValue().add(element);
			}
			
			for(String element: removeList){
				entry.getValue().removeOne(element);
			}
			
			for(String element: searchList){
				entry.getValue().search(element);
			}
	        long endTime = System.nanoTime();
	        
	        System.out.println("Structure: " + entry.getKey() + ", time taken = " + 1000*((double)(endTime - startTime)) / Math.pow(10, 9) + " milliseconds");

		}
		
	}

	public static void main(String [] args){
		DataStructureTester tester = new DataStructureTester (2000);
		tester.test(5000, 1000, 1000,100);
	}

}
