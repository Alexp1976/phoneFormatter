package file;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

public class FileReader {

    private List<String> phoneList = new ArrayList<String>();
    
    public void readFile(String file) throws IOException {
	
	InputStream input = new FileInputStream(file);
	
	try {
	    InputStreamReader reader = new InputStreamReader(input);
	    BufferedReader bufferedReader = new BufferedReader(reader);
	    String phoneNumber = bufferedReader.readLine();
	    FormatPhone formatter = new FormatPhone();
	    while (phoneNumber != null) {
		String phoneDigits = phoneNumber.replaceAll("[^0-9]", "");
		
		if (PhonePattern.getPattern(phoneNumber).isPresent()) {
		    formatter.formatDisplayNumber(PhonePattern.getPattern(phoneNumber).get(), phoneDigits, phoneNumber);	    
		}
		
		phoneNumber = bufferedReader.readLine();
	    }
	    phoneList = formatter.getPhoneList();
	    bufferedReader.close();
	} catch (Exception e) {
	    e.printStackTrace();
	} 
    }

    public List<String> getPhoneList() {
        return phoneList;
    }

    public void setPhoneList(List<String> phoneList) {
        this.phoneList = phoneList;
    }


}
