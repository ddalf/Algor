package boj;
import java.util.*;
import java.io.*;

public class Boj1613 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	public static void solve() {
		try {
			StringTokenizer st;
			st= new StringTokenizer(br.readLine());
			int n = stoi(st.nextToken());//node 수
			int k = stoi(st.nextToken());//사건 전후 관계. edge 수
			int[][] costs = new int[n][n];
			for(int i=0; i<k; i++) {
				st= new StringTokenizer(br.readLine());
				int a = stoi(st.nextToken())-1;
				int b = stoi(st.nextToken())-1;
				costs[a][b] = -1;
				costs[b][a] = 1;
			}
			
			for(int bw=0; bw<n; bw++) {
				for(int i=0; i<n; i++) {
					for(int j=0; j<n; j++) {
						if(costs[i][bw] == -1 && costs[bw][j] == -1) costs[i][j] = -1;
						else if(costs[i][bw] == 1 && costs[bw][j] == 1) costs[i][j] = 1;
					}
				}
			}
			
			int s = stoi(br.readLine());
			for(int i=0; i<s; i++) {
				st= new StringTokenizer(br.readLine());
				int a = stoi(st.nextToken())-1;
				int b = stoi(st.nextToken())-1;
				bw.write(costs[a][b]+"\n");
			}
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
