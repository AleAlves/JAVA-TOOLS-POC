package modifiers.domain;

import java.util.ArrayList;
import java.util.List;

public class Directory {

	ArrayList<String> path;

	public Directory() {
		this.path = new ArrayList<String>();
	}

	public ArrayList<String> getPath() {
		return path;
	}

	public void setPath(ArrayList<String> path) {
		this.path = path;
	}
	
	public void addPath(String path) {
		this.path.add(path);
	}
	
	public void clearPaths() {
		this.path.clear();
	}

}
