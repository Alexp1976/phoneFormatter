package main;

import java.io.IOException;
import java.util.HashSet;
import java.util.Set;

import file.DirectoryReader;
import file.FileReader;

public class PhoneApp {

    public static void main(String[] args) throws Exception {
	
	if (args == null || args.length == 0) {
	    System.out.print("Please inform the root directory to read the files\n\n");
	    return;
	}		
	
	Set<String> phoneList = new HashSet<>();
	
	DirectoryReader directoryReader = new DirectoryReader();
	directoryReader.listFiles(args[0]);
	
	FileReader fileReader = new FileReader();
	
	directoryReader.getFiles().forEach(e -> {
	    try {
		fileReader.readFile(e);
		phoneList.addAll(fileReader.getPhoneList());
	    } catch (IOException ex) {
		ex.printStackTrace();
	    }
	});
	
	phoneList.stream().sorted().forEach(System.out::println);

    }

}
