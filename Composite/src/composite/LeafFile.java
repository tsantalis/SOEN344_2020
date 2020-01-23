package composite;

public class LeafFile extends AbstractFile {
	private long size;
	
	public LeafFile(String name, int depth, long size) {
		super(name, depth);
		this.size = size;
	}

	@Override
	public String ls() {
		StringBuilder sb = new StringBuilder();
		sb.append(printTabs()).append(getName()).append("\n");
		return sb.toString();
	}

	@Override
	public long size() {
		return size;
	}
}
