package com.nogoon.examples.mail;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.junit.Assert;
import org.junit.Test;

public class HeaderTest {

	@Test
	public void parserTest() {

		String test = "ABC: dabcdf\n" 
				+ "BCD: asdfasdfasd\n" 
				+ " asdfasdfasd\n" 
				+ " asdfasdfasd\n"
				+ "ABCD: sdf asdfasdfasd \n"
				+ "ABCD1:";

		System.out.println(getHeaderMap(test));

	}
	
	@Test
	public void stringBuilderTest() {
		StringBuilder builder = new StringBuilder();

		builder.append("abc");
		Assert.assertEquals(3, builder.length());

		builder.delete(0, builder.length());
		Assert.assertEquals(0, builder.length());

	}

	public Map<String, String> getHeaderMap(String headerString) {

		Pattern keyValuePattern = Pattern.compile("^(.*):(.*)$", Pattern.DOTALL);

		String[] splitedStr = headerString.split("\n");
		System.out.println(Arrays.toString(splitedStr));

		Map<String, String> resultMap = new HashMap<String, String>();

		Matcher matcher = null;
		StringBuilder keyValue = new StringBuilder();
		String temp = null;
		boolean kvDone = false;

		for (int i = 0; i <= splitedStr.length; i++) {
			
			// 마지막 라인의 처리를 위하여 강제로 종료처리
			if (i == splitedStr.length) {
				kvDone = true;
			}
			else {
				
				// K/V 쌍인지 패턴 검증
				matcher = keyValuePattern.matcher(splitedStr[i]);

				// K/V 형태가 맞을 경우
				if (matcher.matches()) {
					// 기존 K/V 문자열이 존재하면 현 라인을 임시로 저장하고, K/V 처리를 하도록 값 셋팅
					// 아닐 경우, K/V 문자열에 저장
					if (keyValue.length() > 0) {
						temp = splitedStr[i];
						kvDone = true;
					}
					else {
						keyValue.append(splitedStr[i]);
					}
				}
				// K/V 형태가 아닐 경우 개행문자를 포합하여 저장함
				else {
					keyValue.append("\n").append(splitedStr[i]);
				}
			}
			
			// 실제 K/V 쌍의 문자열 처리
			if (kvDone) {
				matcher = keyValuePattern.matcher(keyValue);
				if (matcher.matches() && matcher.groupCount() == 2) {
					resultMap.put(matcher.group(1), matcher.group(2));
				}
				
				// 초기화 (K/V 문자열, 상태값)
				keyValue.delete(0, keyValue.length());
				keyValue.append(temp);
				kvDone = false;
			}
		}

		return resultMap;

	}
	
	@Test
	public void from() {
		String test1 = "노희재 <kyungtaak@gmail.com>";
		String test2 = "kyungtaak@gmail.com";
		
		
		
		System.out.println(getActorMap(test1));
		System.out.println(getActorMap(test2));
	}
	
	public Map<String, String> getActorMap(String actorString) {
		Pattern fromPattern = Pattern.compile("(.*)<(.*)>");
		
		Map<String, String> resultMap = new HashMap<String, String>();
		
		Matcher matcher = fromPattern.matcher(actorString);
		
		if(matcher.matches()) {
			resultMap.put("ID", matcher.group(1));
			resultMap.put("NAME", matcher.group(2));
			System.out.println(matcher.group(1));
			System.out.println(matcher.group(2));
		} else {
			resultMap.put("ID",actorString);
		}
		
		return resultMap;
	}

	
}
