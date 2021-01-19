package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj12904 {	
	private static String reverseString(String s) {
		return (new StringBuffer(s)).reverse().toString();
	}
	public void solve() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			
			String s = br.readLine();
			String t = br.readLine();
		
			int idx = t.length()-1;
			String tSub = t;
			boolean rs = false;
			while(idx >= 0) {
				char ch = tSub.charAt(idx);
				if(ch == 'A') {
					tSub = tSub.substring(0, idx--);
				}
				else {
					tSub = reverseString(tSub.substring(0, idx--));
					
				}
				if(tSub.length() == s.length()) {
					rs = tSub.equals(s);
					break;
				}
			}
			if(rs) bw.write("1"); 
			else bw.write("0");
			bw.flush();
			bw.close();
			br.close();
		}
		catch(IOException e) {
				e.printStackTrace();
		}
	}
}

/*
 	[문제정리]
 	* A와 B로만 이루어진 영어 단어
 	* 두 문자열 S와 T 주어짐 -> S를 T로 바꿈
 	* 두 가지 연산만 가능
 		1. 문자열 뒤에 A 추가
 		2. 문자열 뒤집고 B 추가
 	* S를 T로 만들 수 있는지 없는지
 	
 	 입력
 	 S : 문자열 s 1~999
 	 T : 문자열 t 2~1000

 	출력
 	S를 T로 바꿀 수 있음 : 1 / 없음 : 0
 
 	[문제풀이]
	그리디
	* T의 맨 뒤에 온 것 A or B
	* -> A일 경우 : 맨 뒤의 전까지 문자열 자름
	* -> B일 경우 : 맨 뒤의 전까지 문자열 잘라서 뒤집음
	* 자른 문자열의 길이가 S와 같아질 경우 둘이 같은지 다른지 비교 -> 같으면 1 / 없으면 0 출력
*/