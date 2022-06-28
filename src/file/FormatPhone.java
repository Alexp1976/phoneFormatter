package file;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.swing.text.MaskFormatter;

public class FormatPhone {

    private List<String> phoneList = new ArrayList<>();
    
    public void formatDisplayNumber(PhonePattern pattern, String phoneDigit) throws ParseException {
	
	MaskFormatter formatter = new MaskFormatter("+# (###) ###-####");
	formatter.setValueContainsLiteralCharacters(false);
	StringBuilder phoneFormatted = new StringBuilder();
	phoneFormatted.append(checkCountryCode(pattern));
	phoneFormatted.append(checkCityCode(pattern));
	phoneFormatted.append(phoneDigit);
	
	phoneList.add(formatter.valueToString(phoneFormatted.toString()));

    }

    public List<String> getPhoneList() {
        return phoneList;
    }
    
    private String checkCountryCode(PhonePattern pattern) {
	
	return PhonePattern.valueOf(pattern.name()).hasCountryCode() ? "" : "7";
    }
    
    private String checkCityCode(PhonePattern pattern) {
	
	return PhonePattern.valueOf(pattern.name()).hasCityCode() ? "" : "812";
    }
}
