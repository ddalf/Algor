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
	[문제 정리]
	물건 쌍에 대해 양팔 저울로 무게 비교
	
	[입력]
	N : 물건 개수. 5~100
	M : 측정된 물건 쌍의 개수. 0~2000
	m개의 줄에 미리 측정된 비교 결과. 앞 > 뒤
	
	[출력]
	n개의 줄에 결과 출력. i번째 줄 물건 i와 비교 결과 알 수 없는 물건의 개수
	
*/