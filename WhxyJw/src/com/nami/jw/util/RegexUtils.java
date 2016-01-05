package com.nami.jw.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegexUtils {
	
	public static String getInfo(String content, String regex){
		String result = "";
		Pattern p = Pattern.compile(regex);
		Matcher matcher = p.matcher(content);
		for(int i=1; i<=matcher.groupCount(); i++){
			result = matcher.group(i).toString();
		}
		return result;
	}
	
}
