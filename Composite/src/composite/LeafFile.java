package composite;

public class LeafFile extends AbstractFile {
	private long size;
	
	public LeafFile(String name, int depth, long size) {
		super(name, depth);
		this.size = size;
	}

}
