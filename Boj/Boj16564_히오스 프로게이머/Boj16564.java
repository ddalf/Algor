package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj16564 {
	static int n;
	static int k;
	static int[] xArr;
	static BufferedReader br;
	static BufferedWriter bw;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static boolean isPossibleT(long t) {
		long sum = 0;
		for(int i=0; i<n; i++) {
			if(t <= xArr[i]) break;
			sum += t - (long)xArr[i];
		}
		return k >= sum;
	}
	public static void solve() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			st = new StringTokenizer(br.readLine());
			n = stoi(st.nextToken());
			k = stoi(st.nextToken());
			xArr = new int[n];
			for(int i=0; i<n; i++) {
				xArr[i] = stoi(br.readLine());
			}
			Arrays.sort(xArr);
			long left =0, right = xArr[n-1] + (long)k;
			while(left <= right) {
				long mid = (left + right) / 2;
				if(isPossibleT(mid)) left = mid + 1;
				else right = mid - 1;
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
* N개의 캐릭터, 캐릭터 각 레벨  X(i)
* 레벨의 총합 K만큼 올릴 수 O
* 팀 목표레벨 T = min(X(i)).
* 게임 끝날 때 까지 성권이가 달성할 수 있는 최대 팀 목표 레벨
[입력]
* N : 캐릭터 개수. 1~1,000,000
* K : 올릴 수 있는 레벨 총합. 1~1,000,000,000
* X(i) : 각 캐릭터 레벨. 1~1,000,000,000
[출력]
* 가능한 최대 팀 목표레벨 T

문제풀이
* 1. 이분탐색.
* 2. 
*/
