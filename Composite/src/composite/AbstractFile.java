package composite;

public abstract class AbstractFile {
	protected String name;
	protected int depth;
	
	public AbstractFile(String name, int depth) {
		this.name = name;
		this.depth = depth;
	}

}
