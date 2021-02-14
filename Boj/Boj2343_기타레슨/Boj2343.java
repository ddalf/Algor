package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj2343 {
	static int n;
	static int m;
	static int[] lessons;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	public static boolean isPossibleSize(int bSize) throws IOException {
		int sum = 0, cnt = 1;
		for(int i=0; i<n; i++) {
			if(lessons[i] > bSize) return false; //레슨 하나가 최대 녹음시간보다 길 수 x.
			sum += lessons[i];
			if(sum > bSize) {
				sum = lessons[i];
				++cnt;
			}
		}
		return m >= cnt;
	}
	
	public static void solve() {
		try {
			StringTokenizer st;
			st = new StringTokenizer(br.readLine());
			n = stoi(st.nextToken());
			m = stoi(st.nextToken());
			lessons = new int[n];
			st = new StringTokenizer(br.readLine());
			int sum = 0;
			for(int i=0; i<n; i++) {
				lessons[i] = stoi(st.nextToken());
				sum += lessons[i];
			}
			int left = 1, right = sum, mid = 0;
			while(left <= right) {
				mid = (left+right) / 2;
				if(isPossibleSize(mid)) right = mid-1;//포함된 개수 m개 이하
				else left = mid+1;//포함된 개수 m개 초과
			}
			bw.write(left+"");
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
블루레이 : N개의 레슨 들어가 있음
녹화 시 -> 녹화의 순서 바뀌면 X.
i번 레슨, j번 레슨 같은 블루레이에 녹화 -> i와 j 사이 모든 레슨도 블루레이에 녹화
녹화 블루레이 수 : M개 -> 블루레이 크기 최소. M개 블루레이 모두 같은 크기
각 레슨의 길이 분 단위 -> 가능한 블루레이 크기 중 최소

입력
N : 레슨의 수. 1~100000
M : 녹화 블루레이 수. 1~N
레슨 길이 : 1~10000

출력
가능한 블루레이 크기 중 최소 출력

문제풀이
1. 이분탐색 풀이 -> 가능한 블루레이크 크기 결정
left = 가능한 최소 블루레이 크기
right = 가능한 최대 블루레이 크기
mid = 중간값. mid 이하 크기로 가능한 블루레이 개수  확인

2. mid 크기로 포함할 수 있는 레슨 여부
* 포함된 개수 m개 이하 : 가능한 블루레이 크기 줄임(right = mid-1) -> 한 블루레이에 포함된 레슨 수 줄임
* 포함된 개수 m개 초과 : 가능한 블루레이 크기 늘림(left = mid+1) -> 한 블루레이에 포함된 레슨 수 늘림
*/