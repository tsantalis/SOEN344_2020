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
		return tokenizer.hasMoreTokens();
	}

	@Override
	public String next() {
		return tokenizer.nextToken();
	}

}
