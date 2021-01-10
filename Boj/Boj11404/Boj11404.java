package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Boj11404 {
	static final int INF = 987654321;
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public void solve() {
		try {
			int n, m;
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st;
			n = Integer.parseInt(br.readLine());
			m = Integer.parseInt(br.readLine());
			int[][] city = new int[n+1][n+1];
			
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=n; j++) {
					city[i][j] = INF;
				}
				city[i][i] = 0;
			}
			
			for(int i=1; i<=m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = stoi(st.nextToken());
				int b = stoi(st.nextToken());
				int c = stoi(st.nextToken());
				city[a][b] = Math.min(city[a][b], c);
			}
			
			for(int k=1; k<=n; k++) {
				for(int i=1; i<=n; i++) {
					for(int j=1; j<=n; j++) {
						city[i][j] = Math.min(city[i][j], city[i][k] + city[k][j]);
					}
				}
			}
			
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=n; j++) {
					if(city[i][j] == INF) city[i][j] = 0;
					System.out.print(city[i][j] + " ");
				}
				System.out.println();
			}
			br.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
/*
	
 */
