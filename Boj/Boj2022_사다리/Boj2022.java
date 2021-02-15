package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2022 {
	static double x;
	static double y;
	static double c;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static double stod(String s) {
		return Double.parseDouble(s);
	}	
	
	public static boolean isPossibleWidth(double width) {
		double h1 = Math.sqrt(x*x-width*width);
		double h2 = Math.sqrt(y*y-width*width);
		
		return c>=h1*h2/(h1+h2);
	}
	public static void solve() {
		try {
			StringTokenizer st;
			st = new StringTokenizer(br.readLine());
			x = stod(st.nextToken());
			y = stod(st.nextToken());
			c = stod(st.nextToken());
			
			double left=0, right = Math.min(x, y), mid;
			while(left+0.000001 <= right) {
				mid = (left + right) / 2.0;
				if(isPossibleWidth(mid)) right = mid;
				else left = mid;
			}
			bw.write(String.format("%.3f", right));
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
높은 빌딩 사이 따라 좁은 길.
x 사다리 : 오른쪽 빌딩 아래를 받침
y 사다리 : 왼쪽 빌딩 아래를 받침
c : 교차점

입력
x,y,c : 소수점 여섯째 자리

출력
두 빌딩 사이 너비.오차 10^-3

문제풀이
1. 이분탐색 -> 가능한 ?(=width)읙 길이
	left = 가능한 최소 길이
	right = 가능한 최대 길이
	mid = 중간값. 
	c = h1h2/h1+h2 여야 함
2.	* c >= h1*h2/h1+h2 일 때 : h1*h2/h1+h2의 크기 커져야 함 -> w의 크기 줄여야 함 => right = mid; 
	* c < h1*h2/h1+h2  일 때 : h1*h2/h1+h2의 크기 작아져야 함 -> w의 크기 커져야 함 => left = mid;
*/
