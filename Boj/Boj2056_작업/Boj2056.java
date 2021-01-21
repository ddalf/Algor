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
 [��������]
 * �۾� : N�� / �۾� �ð� : 1~100.
 * �۾��� ������ ���� ���� �� ->: K�� �۾��� ���� ���� �����ִ� �۾�  : 1~K-1��
 * ���� ���� ���� X -> ���� ���� ����
  
 �Է�
 N : �۾� ����. 1~100
 �۾� �ð�, ���� ���迡 �ִ� �۾� ���� (0~100), ���� ���� �۾� ��ȣ
 
 [����Ǯ��]
 
*/
