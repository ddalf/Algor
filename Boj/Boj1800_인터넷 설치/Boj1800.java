package boj;
import java.util.*;
import java.io.*;

public class Boj1800 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	static List<int[]>[] adjList;
	static int n,k,p;
	static int[] dis;
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	
	static class Cable implements Comparable<Cable>{
		int x;
		int cost;
		Cable(int x, int cost){
			this.x = x;
			this.cost = cost;
		}
		public int compareTo(Cable c) {
			return this.cost - c.cost;
		}
	}
	
	static boolean isPossibleCost(int maxCost) {
		Arrays.fill(dis,  Integer.MAX_VALUE);		
		PriorityQueue<Cable> pq = new PriorityQueue<>();
		pq.add(new Cable(1, 0));
		dis[1] = 0;
		while(!pq.isEmpty()) {
			Cable cur = pq.poll();
			if(dis[cur.x] < cur.cost) continue;
			for(int[] edge : adjList[cur.x]) {
				int xx = edge[0];
				int nCost = edge[1] <= maxCost ? 0 : 1;//maxCost넘으면 가중치 1 아니면 0
				if(dis[xx] > cur.cost + nCost) {
					dis[xx] = cur.cost + nCost;
					pq.add(new Cable(xx, cur.cost + nCost));
				}
			}
		}
		return dis[n] <= k;//dis[n] : 1에서 n도달하는데 maxCost초과의 가중치 사용한 개수
	}
	
	public static void solve() {
		try {
			StringTokenizer st;
			st= new StringTokenizer(br.readLine());
			n = stoi(st.nextToken());//학생 수
			p = stoi(st.nextToken());//케이블선 수
			k = stoi(st.nextToken());//공짜 제공 케이블 수
			adjList = new ArrayList[n+1];
			dis = new int[n+1];
			for(int i=1; i<=n; i++) {
				adjList[i] = new ArrayList<>();
			}
			for(int i=0; i<p; i++) {
				st= new StringTokenizer(br.readLine());
				int a = stoi(st.nextToken());
				int b = stoi(st.nextToken());
				int cost = stoi(st.nextToken());
				adjList[a].add(new int[] {b,cost});
				adjList[b].add(new int[] {a,cost});
			}
			int ans = -1;
			int left = 0, right = Integer.MAX_VALUE;
			while(left <= right) {
				int mid = (left + right) / 2;
				//간선 가중치 mid값 이하로 써서 N까지 도달할 수 있는지 탐색
				if(isPossibleCost(mid)) {//도달할 수 있는 경우 더 작은 가중치로 도달할 수 있는지 탐색 => mid값 감소
					ans = mid;
					right = mid - 1;
				}
				else left = mid + 1;//도달할 수 없는 경우 더 큰 가중치로는 도달할 수 있는지 탐색 => mid값 증가
			}
			
			// 원장선생님이 내게 되는 최소의 돈을 출력한다. 만약 1번과 N번 컴퓨터를 잇는 것이 불가능 하다면 -1을 출력
			bw.write(ans+"");
			
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
