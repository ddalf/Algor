package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;
public class Boj2866 {
	public static BufferedReader br;
	public static BufferedWriter bw;
	static int r;
	static int c;
	static char[][] tables;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	static boolean isRepeat(int mid) {
		HashSet<String> set = new HashSet<String>();
		for(int i=0; i<c; i++) {
			StringBuilder sb = new StringBuilder();
			for(int j=mid; j<r; j++) {
				sb.append(tables[j][i]);
			}
			if(set.contains(sb.toString())) return true;
			set.add(sb.toString());
		}		
		
		return false;	
	}
	
	public static void solve() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st = new StringTokenizer(br.readLine());
			r = stoi(st.nextToken());
			c = stoi(st.nextToken());
			tables = new char[r][c];
			for(int i=0; i<r; i++) {
				tables[i] = br.readLine().toCharArray();
			}
			
			int left = 0, right = r-1;
			while(left <= right) {
				int mid = (left+right)/2;
				if(isRepeat(mid)) right = mid-1;
				else left = mid+1;
			}
			int ans = left-1;
			bw.write(ans+"");
/*
중복되는 것 있을 경우 : right = mid-1. 문자열 길이를 늘림 -> 더 긴 문자열에서도 중복되는지 탐색
중복되는 것 없을 경우 : left = mid+1. 문자열 길이 줄임 -> 더 짧은 문자열에서 중복되는 것 있는지 탐색 
 */
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
문제정리
* R개 행 C개 열로 이루어진 테이블. 원소 알파벳 솟문자
* 각 테이블 열 위->아래로 읽어서 하나의 문자열 만듬
* 가장 위의 행 지워도 테이블 열 읽어서 문자열 중복 X -> 가장 위의 행 지워줌. count 1증가
* 동일한 문자열 발견 : 반복 멈추고 count개수 반환

[입력]
* R, C : 테이블 행의 개수, 열의 개수. 2~1000
* R줄에 C개의 소문자

[출력]
count 값

문제풀이
6 6 
abcdef
abcdef
abcdef
abcdef
ggcdef
ggcdef

* 위의 경우 count = 3일 때 gg,gg 가 동일하게 나타나서 종료된다.
* 이분탐색. 0~n-1까지 탐색해서 중복되는 문자열 있는지 탐색
	* left : 0
	* right : n-1
	* mid : 중간값.
* mid : mid ~ n-1까지 나올 수 있는 문자열
	* 중복되는 것 있을 경우 : right = mid-1. 문자열 길이를 늘림 -> 더 긴 문자열에서도 중복되는지 탐색
	* 중복되는 것 없을 경우 : left = mid+1. 문자열 길이 줄임 -> 더 짧은 문자열에서 중복되는 것 있는지 탐색
	


*/