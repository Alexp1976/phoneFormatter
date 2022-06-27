package file;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class DirectoryReader {
    
    private List<String> files = new ArrayList<>();

    public void listFiles(String rootDirectory) {
	
	File directory = new File(rootDirectory);
	
	File[] fileList = directory.listFiles();
	
	if (fileList != null) {
	    for (File file : fileList) {
		if (file.isFile() && isTextFile(file.getAbsolutePath())) {		    
		    files.add(file.getPath());
		} else if (file.isDirectory()) {
		    listFiles(file.getAbsolutePath());
		}
	    }
	}
    }
    
    private boolean isTextFile(String file) {
	
	return file.substring(file.lastIndexOf(".") + 1).equals("txt");
    }
    
    public List<String> getFiles() {
	return files;
    }

    public void setFiles(List<String> files) {
	this.files = files;
    }
    
    
}
