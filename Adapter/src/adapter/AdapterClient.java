package adapter;

import java.util.Iterator;
import java.util.StringTokenizer;

public class AdapterClient {
	
	public static void main(String args[]) {
		printWordsLineByLine("This is a wonderful day");
	}
	
	private static void printWordsLineByLine(String input) {
		StringTokenizer tokenizer = new StringTokenizer(input);
		
		//the iterator should iterate over the elements of the tokenizer
		Iterator<String> iterator = new TokenizerToIteratorAdapter(tokenizer);
		
		//function elementsToString() takes as input an Iterator
		String output = CollectionLibrary.elementsToMultilineString(iterator);
		System.out.print(output);
	}
}
