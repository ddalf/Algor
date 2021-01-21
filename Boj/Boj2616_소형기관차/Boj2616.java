package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj2616 {
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void solve() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			int n = stoi(br.readLine());
			st = new StringTokenizer(br.readLine());
			int[] capacityArr = new int[n+1];
			int[][] dp = new int[3][n+1];
			for(int i=1; i<=n; i++) {
				capacityArr[i] = stoi(st.nextToken());
			}
			int maxCapacity = stoi(br.readLine());
			
			for(int i=1; i<=n; i++) {
				if(i+maxCapacity > n+1) break;
				for(int j=0; j<maxCapacity; j++) {
					dp[0][i] += capacityArr[i+j];
				}
			}
			
			for(int i=1; i<3; i++) {
				for(int j=i*maxCapacity+1; j<=n-(2-i)*maxCapacity; j++) {
					int ans = Integer.MIN_VALUE;
					for(int k= (i-1)*maxCapacity+1; k<=j-maxCapacity; k++) {
						ans = Math.max(ans, dp[i-1][k]);
					}
					dp[i][j] = ans + dp[0][j];
				}
			}
			int rs  = Integer.MIN_VALUE;
			for(int i=0; i<=n; i++) {
				rs = Math.max(rs, dp[2][i]);
			}
			bw.write(rs+"");
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
 	* 기차 : 맨 앞 기관차 1대가 여러칸 끔
 	* 소형 기관차 : 기관차 고장 대비. 기존 기관차보다 작은 칸 끌수 있음. 3대
 		1. 소형 기관차 최대로 끌 수 있는 객차 수 정해져 있음. 모두 같음.
 		2. 최대한 많은 손님 운송. 각 객체 손님 수 정해져 있음
 		3. 각 소형기관차 -> 번호 연속적으로 이어진 객차 끔
 	입력
 	n : 기관차가 끌고 가던 객차 수(1~50000)
 	capacityArr : 각 객차 손님 수
 	maxCapacity : 소형 기관차가 최대로 끌 수 있는 객차 수(1~50000/3)
 	
 	출력
 	소형 기관차 3대로 최대 운송할 수 있는 손님 수
 	
 	[문제풀이]
 	* dp[i][j] : i번 소형기관차가 j번째 끌 때 최대 손님 수
*/
