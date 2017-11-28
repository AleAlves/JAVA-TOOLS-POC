package LogReader;

import java.io.File;
import java.util.List;

interface Files{
	public List<File> walkDir(String path);
}