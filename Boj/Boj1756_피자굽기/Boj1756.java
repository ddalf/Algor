package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj1756 {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static int n;
	public static int d;
	public static int[] pizzas;
	public static int[] ovens;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	public static void solve() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			st = new StringTokenizer(br.readLine());
			d = stoi(st.nextToken());
			n = stoi(st.nextToken());
			ovens = new int[d+1];
			pizzas = new int[n+1];
			st = new StringTokenizer(br.readLine());
			ovens[0] = Integer.MAX_VALUE;
			for(int i=1; i<=d; i++) {
				ovens[i] = stoi(st.nextToken());
				ovens[i] = Math.min(ovens[i], ovens[i-1]);
			}
			st = new StringTokenizer(br.readLine());		
			for(int i=1; i<=n; i++) {
				pizzas[i] = stoi(st.nextToken());
			}
			
			int pizzaDepth = d+1;
			for(int i=1; i<=n; i++) {
				int left = 1;
				int right = --pizzaDepth;
				if(right <= 0) break;
				while(left <= right) {
					int mid = (left+right)/2;
					if(ovens[mid] >= pizzas[i]) left = mid + 1;
					else right = mid-1;
				}
				pizzaDepth = right;
			}
			if(pizzaDepth<=0)bw.write("0");
			else bw.write(pizzaDepth+"");
			bw.flush(); bw.close(); br.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}

/*
문제정리
* N개의 피자 반죽.
* 피자 반죽 지름 제각각.
* 오븐 관 지름 깊이에 따라 너비 다름
* 피자 반죽 완성되는 순서대로 오븐에 들어감
* N개의 피자가 오븐에 모두 들어가면 맨 위의 피자 얼마나 깊이 들어 이는지

[입력]
* D : 오븐 깊이. 1~300,000 
* N : 피자 반죽의 갯수 1~300,000
* 오븐의 깊이에 따른 오븐의 지름. 1~1,000,000,000
* 완성된 피자 반죽 지름. 완성된 순서대로. 1~1,000,000,000

[출력]
* 마지막 피자 반죽 위치(오븐 최상단 1/최하단 D).
* 피자가 모두 오븐에 들어가지 않는다면 0 출력
*/