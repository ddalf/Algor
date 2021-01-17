package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.Collections;
import java.util.StringTokenizer;


public class Boj10159 {
	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
		
	public void solve() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			int n = stoi(br.readLine());
			int m = stoi(br.readLine());
			boolean[][] weightcomp = new boolean[n+1][n+1];
			for(int i=1; i<=m; i++) {
				st = new StringTokenizer(br.readLine());
				int h = stoi(st.nextToken());
				int l = stoi(st.nextToken());
				weightcomp[h][l] = true;
			}
			
			for(int k=1; k<=n; k++) {
				for(int i=1; i<=n; i++) {
					for(int j=1; j<=n; j++) {
						if(weightcomp[i][k] && weightcomp[k][j]) weightcomp[i][j] = true;
					}
				}
			}
			
			for(int i=1; i<=n; i++) {
				int count = 0;
				for(int j=1;j<=n; j++) {
					if(i==j) continue;
					if(!weightcomp[i][j] && !weightcomp[j][i])++count;
				}
				bw.write(count+"\n");
			}
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
	[���� ����]
	���� �ֿ� ���� ���� ����� ���� ��
	
	[�Է�]
	N : ���� ����. 5~100
	M : ������ ���� ���� ����. 0~2000
	m���� �ٿ� �̸� ������ �� ���. �� > ��
	
	[���]
	n���� �ٿ� ��� ���. i��° �� ���� i�� �� ��� �� �� ���� ������ ����
	
*/