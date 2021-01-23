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
	[��������]
	* �� ���� ������ ���ٸ�. �� ���ٸ� ���� �׻� ����
	* �Ǹ��� ���ٸ� : �� ��
	* õ���� ���ٸ� : �Ʒ� ��
	* ���ٸ� �ǳ� �� ����
		1. ���� -> ������ �ٸ� ������. �η縶���� ���� ���ڿ� ������� ��� ������
		2. �� -> õ �����ư��鼭 �� ��ƾ� ��. ����� �� �� �ƹ��볪 ����
		3. �ݵ�� �� ĭ �̻� ���������� ����. �ǳʶٴ� ĭ �� ��� X. 
	
	�Է�
	* �η縶���� ���� ���ڿ� : R, I, N, G, S �־���. 1~20
	* �Ǹ��� ���ٸ� ���ڿ�
	* õ���� ���ٸ� ���ڿ�

	���
	* �ι������� ���� ���ڿ� ������� �ٸ��� �ǳʰ� �� �ִ� ����� ��
	* ��� ������ 0 ���
	* ��� ��°�� 2^31-1 ����
	

*/