package solid.lsp.unmodifiableList;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

import org.junit.Test;

public class TestLSP {

	@Test
	public void deleteFromList() {
		String item = "orange";
		
		List<String> modifiableList = new ArrayList<String>();
		modifiableList.add(item);
		modifiableList.add(item);
		
		assertEquals(2, modifiableList.size());
		removeAll(modifiableList, item);
		assertEquals(0, modifiableList.size());
	}
	
	@Test(expected = UnsupportedOperationException.class)
	public void deleteFromUnmodifiableList() {
		String item = "orange";
		
		List<String> modifiableList = new ArrayList<String>();
		modifiableList.add(item);
		modifiableList.add(item);
		
		List<String> unmodifiableList = Collections.unmodifiableList(modifiableList);
		assertEquals(2, unmodifiableList.size());
		// we expect UnsupportedOperationException to be thrown after calling removeAll()
		removeAll(unmodifiableList, item);
	}
	
	@Test
	public void deleteFromModifiableList() {
		String item = "orange";
		
		List<String> modifiableList = new ArrayList<String>();
		modifiableList.add(item);
		modifiableList.add(item);
		
		List<String> unmodifiableList = Collections.unmodifiableList(modifiableList);
		assertEquals(2, unmodifiableList.size());
		removeAll(modifiableList, item);
		assertEquals(2, unmodifiableList.size());
	}
	
	private static void removeAll(Collection<String> list, String item){
		while(list.contains(item)){
			list.remove(item);
		}
	}
}
