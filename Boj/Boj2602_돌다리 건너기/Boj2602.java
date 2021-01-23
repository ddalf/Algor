package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj2602 {
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void solve() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			String str = br.readLine();//1~20
			String[] bridge = new String[2];
			bridge[0] = br.readLine();
			bridge[1] = br.readLine();
			int strLen = str.length();
			int bridgeLen = bridge[0].length();
			int dp[][][] = new int[2][strLen][bridgeLen+1];
//			String a =  br.readLine();//1~100
//			String b = br.readLine();//1~100
			
//			int dpA[][] = new int[str.length()+1][a.length()+1];
//			int dpB[][] = new int[str.length()+1][b.length()+1];
			
			for(int i=0; i<2; i++) {
				for(int j=1; j<=bridgeLen; j++) {
					dp[i][0][j] = dp[i][0][j-1];
					if(str.charAt(0) == bridge[i].charAt(j-1)) {
						++dp[i][0][j];
					}
//					bw.write(dp[i][0][j]+" ");
				}
//				bw.write("\n");
			}
			
			for(int i=0; i<2; i++) {
				int sec = i;
				for(int j=1; j<strLen; j++) {
					char ch = str.charAt(j);
					sec = (sec+1) %2;
					for(int k=j+1; k<=bridgeLen; k++) {
						dp[i][j][k] = dp[i][j][k-1];
						if(bridge[sec].charAt(k-1) == ch) {
							dp[i][j][k] += dp[i][j-1][k-1];
						}
					}
				}
			}
			
			bw.write((dp[0][strLen-1][bridgeLen]+dp[1][strLen-1][bridgeLen]) + "");

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
	* 두 개의 인접한 돌다리. 두 돌다리 길이 항상 동일
	* 악마의 돌다리 : 위 행
	* 천사의 돌다리 : 아래 행
	* 돌다리 건널 때 조건
		1. 왼쪽 -> 오른쪽 다르 지나감. 두루마리에 적힌 문자열 순서대로 밟고 지나감
		2. 악 -> 천 번갈아가면서 돌 밟아야 함. 출발은 둘 중 아무대나 가능
		3. 반드시 한 칸 이상 오른쪽으로 전진. 건너뛰는 칸 수 상관 X. 
	
	입력
	* 두루마리에 적힌 문자열 : R, I, N, G, S 주어짐. 1~20
	* 악마의 돌다리 문자열
	* 천사의 돌다리 문자열

	출력
	* 두무마리에 적힌 문자열 순서대로 다리를 건너갈 수 있는 방법의 수
	* 방법 없으면 0 출력
	* 모든 출력결과 2^31-1 이하
	

*/