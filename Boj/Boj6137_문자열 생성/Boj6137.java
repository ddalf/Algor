package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Boj6137 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	static int n;
	static List<Character> chars;
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	public static void solve() {
		try {
			//n개의 문자로 이루어진 문자열 S
			//문자열의 각 문자들로 새로운 문자열 T 만들려고함
			//S로 T만드는 규칙
				//S의 가장 앞의 문자 하나를 문자열 T의 마지막에 추가
				//S의 가장 뒤의 문자 하나를 문자열 T의 마지막에 추가
			//위 규칙으로 만들어진 문자열 T들 중 사전순으로 가장 빠른 문자열
			n = stoi(br.readLine());
			chars = new LinkedList<>();
			for(int i=0; i<n; i++) {
				chars.add(br.readLine().toCharArray()[0]);
			}
			StringBuffer sf = new StringBuffer("");
			int front = 0, back = n-1, idx = 0;
			
			while(front <= back) {
				++idx;
				char frontChar = chars.get(front);
				char backChar = chars.get(back);
				
				if(backChar < frontChar) {
					sf.append(backChar);
					--back;
				}
				else if(frontChar < backChar) {
					sf.append(frontChar);
					++front;
				}
				else {//frontChar == backChar
					int nextFront = front + 1;
					int beforeBack = back - 1;
					while(true) {
						if(nextFront > beforeBack || chars.get(nextFront) < chars.get(beforeBack)) {
							sf.append(frontChar);
							++front;
							break;
						}
						else if(chars.get(beforeBack) < chars.get(nextFront)){
							sf.append(backChar);
							--back;
							break;
						}
						++nextFront;
						--beforeBack;
					}
				}
				if(idx % 80 == 0) sf.append("\n");
			}
			bw.write(sf.toString());
			//출력 : 만즐어진 사전순으로 가장 빠른 문자열열80글자마다 새줄 문자를 출력해야 한다.
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
