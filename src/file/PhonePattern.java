package file;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public enum PhonePattern {

    // +7 812 123-4567
    PATTERN1("\\+[0-9]{1}\\s[0-9]{3}\\s[0-9]{3}-[0-9]{4}", true, true),
    // +7 812 123-45-67
    PATTERN2("\\+[0-9]{1}\\s[0-9]{3}\\s[0-9]{3}-[0-9]{2}-[0-9]{2}", true, true),
    // +7 812 1234567
    PATTERN3("\\+[0-9]\\s[0-9]{3}\\s[0-9]{7}", true, true),
    // +7 (812) 123-4567
    PATTERN4("\\+[0-9]{1}\\s\\([0-9]{3}\\)\\s[0-9]{3}-[0-9]{4}", true, true),
    // +7 (812) 123-45-67
    PATTERN5("\\+[0-9]{1}\\s\\([0-9]{3}\\)\\s[0-9]{3}-[0-9]{2}-[0-9]{2}", true, true),
    // +7 (812) 1234567
    PATTERN6("\\+[0-9]{1}\\s\\([0-9]{3}\\)\\s[0-9]{7}", true, true),
    // +7812123-4567
    PATTERN7("\\+[0-9]{7}-[0-9]{4}", true, true),
    // +7812123-45-67
    PATTERN8("\\+[0-9]{7}-[0-9]{2}-[0-9]{2}", true, true),
    // +78121234567
    PATTERN9("\\+[0-9]{11}", true, true),
    // +7812 123-4567
    PATTERN10("\\+[0-9]{4}\\s[0-9]{3}-[0-9]{4}", true, true),
    // +7812 123-45-67
    PATTERN11("\\+[0-9]{4}\\s[0-9]{3}-[0-9]{2}-[0-9]{2}", true, true),
    // +7812 1234567
    PATTERN12("\\+[0-9]{4}\\s[0-9]{7}", true, true),
    // 1-812-123-4567
    PATTERN13("[0-9]{1}-[0-9]{3}-[0-9]{3}-[0-9]{4}", true, true),
    // 1-812-123-45-67
    PATTERN14("[0-9]{1}-[0-9]{3}-[0-9]{3}-[0-9]{2}-[0-9]{2}", true, true),
    // 1-812-1234567
    PATTERN15("[0-9]{1}-[0-9]{3}-[0-9]{7}", true, true),
    // (812) 123-4567
    PATTERN16("\\([0-9]{3}\\)\\s[0-9]{3}-[0-9]{4}", false, true),
    // (812) 123-45-67
    PATTERN17("\\([0-9]{3}\\)\\s[0-9]{3}-[0-9]{2}-[0-9]{2}", false, true),
    // (812) 1234567
    PATTERN18("\\([0-9]{3}\\)\\s[0-9]{7}", false, true),
    // 812 123-4567
    PATTERN19("[0-9]{3}\\s[0-9]{3}-[0-9]{4}", false, true),
    // 812 123-45-67
    PATTERN20("[0-9]{3}\\s[0-9]{3}-[0-9]{2}-[0-9]{2}", false, true),
    // 812 1234567
    PATTERN21("[0-9]{3}\\s[0-9]{7}", false, true),
    // 812123-4567
    PATTERN22("[0-9]{6}-[0-9]{4}", false, true),
    // 812123-45-67
    PATTERN23("[0-9]{6}-[0-9]{2}-[0-9]{2}", false, true),
    // 8121234567
    PATTERN24("[0-9]{10}", false, true),
    // 812-123-4567
    PATTERN25("[0-9]{3}-[0-9]{3}-[0-9]{4}", false, true),
    // 812-123-45-67
    PATTERN26("[0-9]{3}-[0-9]{3}-[0-9]{2}-[0-9]{2}", false, true),
    // 812-1234567
    PATTERN27("[0-9]{3}-[0-9]{7}", false, true),
    // 123-4567
    PATTERN28("[0-9]{3}-[0-9]{4}", false, false),
    // 123-45-67
    PATTERN29("[0-9]{3}-[0-9]{2}-[0-9]{2}", false, false),
    // 1234567
    PATTERN30("[0-9]{7}", false, false);

    private String regex;
    private boolean hasCountryCode;
    private boolean hasCityCode;
    
    PhonePattern(String regex, boolean hasCountryCode, boolean hasCityCode) {
	this.regex = regex;
	this.hasCountryCode = hasCountryCode;
	this.hasCityCode = hasCityCode;
    }
    
    public static Optional<PhonePattern> getPattern(String phoneNumber) {
	
	return Arrays.stream(PhonePattern.values())
		.filter(pattern -> phoneNumber.matches(pattern.regex))
		.findFirst();
    }
        
    public static List<String> match(PhonePattern expression, String text) {

	List<String> result = new ArrayList<>();
	
	Pattern pattern = Pattern.compile(expression.regex);
	Matcher matcher = pattern.matcher(text);
	
	while(matcher.find()) {
	    result.add(matcher.group());	    
	}

	return result;
    }
    
    public String getRegex() {
        return regex;
    }

    public boolean hasCountryCode() {
        return hasCountryCode;
    }

    public boolean hasCityCode() {
        return hasCityCode;
    }    
    
}
