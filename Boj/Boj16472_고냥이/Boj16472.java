package boj;
import java.util.*;
import java.io.*;

public class Boj16472 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	public static void solve() {
		try {
			//최대 N개의 종류의 알파벳을 가진 연속된 문자열밖에 인식하지 못한다
			//문자열이 주어졌을 때 이 번역기가 인식할 수 있는 최대 문자열의 길
			int n = stoi(br.readLine());//2~26
			char[] words = br.readLine().toCharArray();//1~100,000
			int[] visit = new int[27];
			
			int left = 0;
			int right = 0;
			int ans = -1;
			int maxLen = -1;//최대길이
			++visit[words[0]-'a'];//left~right사이의 각각의 문자 개수
			int cnt = 1;
			while(true) {
				++right;
				if(right == words.length) break;
				if(visit[words[right]-'a']++ == 0) {//현재 right가 가르키는 문자 개수 증가
					++cnt;//처음 방문한 문자일 경우 cnt 증가
				}
				if(cnt <= n)maxLen = Math.max(maxLen, right - left + 1);//cnt가 n보다 작을 경우
				while(cnt > n && left < right) {//cnt가 n보다 큰 경우  사용 문자 개수 줄여줘야 함 => left 이동
					if(--visit[words[left]-'a'] == 0) --cnt;
					++left;
				}
			}
			bw.write(maxLen+"");
			
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
