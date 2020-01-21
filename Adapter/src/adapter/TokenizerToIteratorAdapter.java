package adapter;
import java.util.Iterator;

public class TokenizerToIteratorAdapter implements Iterator<String> {

	@Override
	public boolean hasNext() {
		return false;
	}

	@Override
	public String next() {
		return null;
	}

}
