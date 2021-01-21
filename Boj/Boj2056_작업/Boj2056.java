package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

class WorkInfo{
	int time;
	int[] preWorks;
	WorkInfo(){
		this.time = 0; preWorks = null;
	}
	WorkInfo(int time, int[] preWorks){
		this.time = time;
		this.preWorks = preWorks;
	}
}

public class Boj2056 {
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public static void solve() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			int n = stoi(br.readLine());
			
			WorkInfo wi[] = new WorkInfo[n+1];
			int[] preCnt = new int[n+1];
			int[] t = new int[n+1];
			int[] dp = new int[n+1];
			ArrayList<Integer>[] adj = new ArrayList[n+1];
			Queue<Integer> q = new LinkedList<>();
			
			
			for(int i=1; i<=n; i++) {
				st = new StringTokenizer(br.readLine());
				t[i] = stoi(st.nextToken());
				preCnt[i] = stoi(st.nextToken());
				for(int j=0; j<preCnt[i]; j++) {
					int num = stoi(st.nextToken());
					if(adj[num] == null) adj[num] = new ArrayList<>();
					adj[num].add(i);
				}
			}
			
			for(int i=1; i<=n; i++) {
				if(preCnt[i] == 0) {
					dp[i] = t[i];
					q.add(i);
				}
			}
			
			
			while(!q.isEmpty()) {
				int num = q.poll();
				if(adj[num] != null) {
					for(int nn : adj[num]) {
						dp[nn] = Math.max(dp[nn], dp[num] + t[nn]);
						if(--preCnt[nn] == 0) q.add(nn);
					}
				}
			}
			int ans = 0;
			for(int i=1; i<=n; i++) {
				ans = Math.max(ans, dp[i]);
			}
			bw.write(ans+"");
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
 * 작업 : N개 / 작업 시간 : 1~100.
 * 작업들 사이의 선행 관계 有 ->: K번 작업에 대해 선행 관계있는 작업  : 1~K-1개
 * 서로 선행 관계 X -> 동시 수행 가능
  
 입력
 N : 작업 개수. 1~100
 작업 시간, 선행 관계에 있는 작업 개수 (0~100), 선행 관계 작업 번호
 
 [문제풀이]
 
*/
