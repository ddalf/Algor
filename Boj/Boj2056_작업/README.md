# 2056_작업

Algorithms: dp, graph, 위상정렬

Date: 2021/01/21

Level: G4, ☆

Link: https://www.acmicpc.net/problem/2056

### [문제정리]

- 작업 : N개 / 작업 시간 : 1~100.
- 작업들 사이의 선행 관계 有 ->: K번 작업에 대해 선행 관계있는 작업 : 1~K-1개
- 서로 선행 관계 X -> 동시 수행 가능

**입력**

- N : 작업 개수. 1~100
- 작업 시간(time), 선행 관계에 있는 작업 개수 (preCnt, 0~100), 선행 관계 작업 번호

**출력**

- 작업 완료하기 위한 최소 시간

### [문제풀이]

- DP, 위상정렬
    1. 입력(그래프 초기화)
    2. 선행작업이 없는 작업들로 dp 초기화
    3. 위상정렬 순서대로 정점 방문
        - dp[next] = Max(dp[pre] + time[n[)
        : 다음 수행하려는 작업(next) 끝난 시간 = 선행 작업(pre) 중 끝나는 시간이 가장 큰 것 + 수행 하려는 작업(next) 시간
        - 선행 작업이 모두 끝난 후 (preCnt[next] == 0 일 때) queue에 다음 수행하려는 작업(next) push 가능.
    4. 위상정렬로 수행된 값 중 가장 큰 값 = 작업 완료하기 위한 최소시간

```java
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
			int[] preCnt = new int[n+1];//선행 작업 개수
			int[] t = new int[n+1];//각 작업에 걸리는 시간
			int[] dp = new int[n+1];//i번작업 끝난 시간
			ArrayList<Integer>[] adj = new ArrayList[n+1];//작업들 사이 관계
			Queue<Integer> q = new LinkedList<>();
			
			//1. 입력
			for(int i=1; i<=n; i++) {
				st = new StringTokenizer(br.readLine());
				t[i] = stoi(st.nextToken());
				preCnt[i] = stoi(st.nextToken());
				for(int j=0; j<preCnt[i]; j++) {
					int num = stoi(st.nextToken());
					if(adj[num] == null) adj[num] = new ArrayList<>();
					adj[num].add(i);//num 작업 뒤에 수행될 수 있는 작업 저장 -> 위상
				}
			}
			//2. 선행작업이 없는 작업들로 dp 초기화
			for(int i=1; i<=n; i++) {
				if(preCnt[i] == 0) {
					dp[i] = t[i];
					q.add(i);
				}
			}
			//3. 위상정렬 순서대로 정점 방문
			while(!q.isEmpty()) {
				int n= q.poll();
				if(adj[n] != null) {
					for(int next : adj[n]) {
						dp[next] = Math.max(dp[nnnext, dp[n] + t[next]);//next의 선행 작업 중 끝나는 시간이 가장 큰 것 + next 수행하는데 걸린 시간
						if(--preCnt[next] == 0) q.add(next);//수행된 선행작업 개수 줄임. 0이 되면 더이상 선행 작없 없는 경우 -> queue에 넣어줌
					}
				}
			}
			int ans = 0;
			//4. 위상정렬된 값 중 가장 큰 값(= 작업 완료하기 위한 최소 시간) 출력
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
```