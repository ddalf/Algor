package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj13397 {
	public static int n,m;
	public static int[] arr;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static boolean checkSection(int maxSectionV) {
		int cnt = 1;
		int maxValue = arr[0], minValue = arr[0];
		for(int i=0; i<n; i++) {
			if(arr[i]<minValue) minValue = arr[i];
			if(arr[i]>maxValue) maxValue = arr[i];
			if(maxValue - minValue > maxSectionV) {
				++cnt;
				maxValue = arr[i];
				minValue = arr[i];
			}
		}		
		return m >= cnt ? true : false;
	}
	
	public void solve() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			st = new StringTokenizer(br.readLine());
			n = stoi(st.nextToken());
			m = stoi(st.nextToken());
			arr = new int[n];
			st = new StringTokenizer(br.readLine());
			int sum = 0;
			for(int i=0; i<n; i++) {
				arr[i] = stoi(st.nextToken());
				sum += arr[i];
			}
			
			int left = 0, right = sum, mid, ans = 987654321;
			while(left <= right) {
				mid = (left + right) / 2;
				if(checkSection(mid)) {
					right = mid - 1;
					if(mid < ans) ans = mid;
				}
				else {
					left = mid + 1;
				}
			}
			bw.write(ans+"");
			bw.flush();	bw.close();	br.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
/*
	문제정리
	* N개 수로 이루어진 1차원 배열 -> M개 이하 구간으로 나눔. 구간 점수의 최댓값 최소로 하려고 함.
	* 조건 : 하나의 구간 -> 하나 이상 연속된 수 / 배열 각 수 -> 모두 하나의 구간에 포함
	* 구간 점수 = 구간 내 최댓값 - 최솟값
*/