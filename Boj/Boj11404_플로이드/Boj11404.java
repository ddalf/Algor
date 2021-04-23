package boj;
import java.util.*;
import java.io.*;
public class Boj11404 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	public static void solve() {
		try {
			int nodeNum = stoi(br.readLine());
			int edgeNum = stoi(br.readLine());
			int[][] costs = new int[nodeNum][nodeNum];
			for(int i=0; i<nodeNum; i++) {
				Arrays.fill(costs[i], 987654321);
				costs[i][i] = 0;
			}
			
			StringTokenizer st;
			for(int i=0; i<edgeNum; i++) {//간선 정보
				st= new StringTokenizer(br.readLine());
				int a = stoi(st.nextToken())-1;
				int b = stoi(st.nextToken())-1;
				int cost = stoi(st.nextToken());
				costs[a][b] = Math.min(costs[a][b],cost);
			}
			
			for(int k=0; k<nodeNum; k++) {
				for(int i=0; i<nodeNum; i++) {
					for(int j=0; j<nodeNum; j++) {
						costs[i][j] = Math.min(costs[i][j], costs[i][k]+costs[k][j]);
					}
				}
			}
			
			for(int i=0; i<nodeNum; i++) {
				for(int j=0; j<nodeNum; j++) {
					if(costs[i][j] == 987654321) bw.write("0 ");
					else bw.write(costs[i][j]+" ");
				}
				bw.newLine();
			}
		
			
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
