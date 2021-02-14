package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2110 {
	private static int n;
	private static int c;
	private static int[] housePos;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static boolean isPossibleDist(int gap) {
		int cnt = 1;
		int cur = housePos[0];
		for(int i=1; i<n; i++) {//cur위치 ~ i번째 위치의 차이가 gap보다 커지면 i번째 위치에 새 공유기 설치
			if(housePos[i]-cur >= gap) {
				++cnt;//설치 공유기 수 증가
				cur = housePos[i];
			}
		}
		return cnt >= c;
	}
	public static void solve() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			st = new StringTokenizer(br.readLine());
			n = stoi(st.nextToken());
			c = stoi(st.nextToken());
			housePos = new int[n];
			for(int i=0; i<n; i++) {
				housePos[i] = stoi(br.readLine());
			}
			Arrays.sort(housePos);
			int left = 1, right = housePos[n-1];
			while(left <= right) {
				int mid = (left+right)/2;
				if(isPossibleDist(mid)) left = mid + 1;
				else right = mid - 1;
			}
			bw.write(right+"");
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
도현의 집 : N개. 수직선 위에 있음. 각 좌표 x1, ... xn. 같은 좌표 가지지 X
공유기 : C개 설치. 최대한 많은 곳에서 와이파이 사용하려고 함.
	한 집에 하나만 설치 가능.
	가장 인접한 두 공유기 사이의 거리 가능한 크게.

입력
N : 집 개수. 2~200,000
C : 공유기 개수.
xi : 0~1,000,000,000

출력
가장 인접한 두 공유기 사이의 최대 거리

[문제풀이]
1. 이분 탐색 이용 -> 인접한 공유기의 최소 거리 결정
left = 가능한 최소 거리
right = 가능한 최대 거리
mid = 중간값. mid 이상의 간격으로 공유기 3개 설치 할 수 있는지 확인

2. mid 간격으로 설치가능 여부
- 설치 된 개수 c 이상 : 공유기 사이의 거리 늘림 -> 설치 수 줄여야 함
- 설치 된 개수 c 미만 : 공유기 사이의 거리 좁힘 -> 설치 수 늘려야 함
*/