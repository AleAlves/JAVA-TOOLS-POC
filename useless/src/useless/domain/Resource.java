package useless.domain;

import java.io.File;

public class Resource {

	private File file;
	private String path;
	private String name;
	private boolean notFound;
	private ResourceType resourceType;

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		while(name.contains(".")) {
			name = name.substring(0, name.lastIndexOf('.'));
		}
		this.name = name;
	}

	public boolean isNotFound() {
		return notFound;
	}

	public void setNotFound(boolean notFound) {
		this.notFound = notFound;
	}

	public ResourceType getResourceType() {
		return resourceType;
	}

	public void setResourceType(ResourceType resourceType) {
		this.resourceType = resourceType;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

}
