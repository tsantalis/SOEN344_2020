package adapter;
import java.util.Iterator;
import java.util.StringTokenizer;

public class TokenizerToIteratorAdapter implements Iterator<String> {

	private StringTokenizer tokenizer;
	public TokenizerToIteratorAdapter(StringTokenizer tokenizer) {
		this.tokenizer = tokenizer;
	}

	@Override
	public boolean hasNext() {
		return false;
	}

	@Override
	public String next() {
		return null;
	}

}
