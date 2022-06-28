package file;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class FileProcessor {

    private List<String> phoneList = new ArrayList<String>();
    private String content;
    
    public void readFile(String file) throws IOException {
	
	BufferedReader reader = new BufferedReader(new FileReader(new File(file)));
	content = reader.lines().collect(Collectors.joining(System.lineSeparator()));
	
	FormatPhone formatter = new FormatPhone();
	
	for (PhonePattern pattern : PhonePattern.values()) {
	    PhonePattern.match(pattern, content).forEach(item -> {
		// remove special characters from matched text in order to format it
		String phoneDigits = item.replaceAll("[^0-9]", "");
		try {
		    formatter.formatDisplayNumber(PhonePattern.getPattern(item).get(), phoneDigits);
		} catch (ParseException e) {		    
		    e.printStackTrace();
		}
		// remove text matching the pattern to avoid duplicates
		content = content.replaceAll(pattern.getRegex(), "");
	    });
	    phoneList.addAll(formatter.getPhoneList());
	}
		
	phoneList = formatter.getPhoneList();
	
    }

    public List<String> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<String> phoneList) {
        this.phoneList = phoneList;
    }


}
