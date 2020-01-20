package adapter;

import java.util.Iterator;

public class CollectionLibrary {

	public static <E> String elementsToMultilineString(Iterator<E> iterator) {
		StringBuilder sb = new StringBuilder();
		while(iterator.hasNext()) {
			E e = iterator.next();
			sb.append(e.toString()).append("\n");
		}
		return sb.toString();
	}
}
