package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;

class Town implements Comparable<Town>{
	int pos;
	int num;
	
	public int compareTo(Town t) {
		return this.pos-t.pos;
	}
}

public class Boj2141 {
	public static BufferedReader br;
	public static BufferedWriter bw;
	static int n;
	static Town[] town;
	static long[] sums;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	public static void solve() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			n = stoi(br.readLine());
			town = new Town[n];
			sums = new long[n];
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				town[i] = new Town();
				town[i].pos = stoi(st.nextToken());
				town[i].num = stoi(st.nextToken());
			}
			Arrays.sort(town);
			sums[0] = (long)town[0].num;
			for(int i=1; i<n; i++) {
				sums[i] = (long)sums[i-1]+town[i].num;
			}
			int left = 0, right = n-1;
			int minPos = Integer.MAX_VALUE;
			while(left <= right) {
				int mid = (left + right) / 2;
				long lv = sums[mid];//0~mid 마을까지 누적합
				long rv = sums[n-1]-sums[mid];//mid~n-1마을까지 누적합
				if(lv >= rv) {//왼편 >= 오른편 이면 우체국 위치 왼쪽이 유리 -> r = mid-1
					minPos = Math.min(minPos, town[mid].pos);
					right = mid - 1;
				}
				else left = mid + 1;
			}
			bw.write(minPos+"");
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
* 수직선 위 N 개의 마을
* i번째 마을 -> X[i]에 위치. A[i]명 살고 있다.
* 마을을 위한 우체국. 각 사람들까지의 거리 합이 최소가 되는 위치.
* 우체국을 세울 위치 구하기
* 각 마을까지의 거리 X. 각 사람까지의 거리 합임!!

입력
* N : 마을 개수. 1~100,000
* x[i] : 마을 위치. 1~1,000,000,000
* a[i] : 사람 명 수
*/