package boj;
import java.util.*;
import java.io.*;

public class Boj11657 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	public static void solve() {
		try {
			StringTokenizer st;
			st= new StringTokenizer(br.readLine());
			int v = stoi(st.nextToken());//도시 개수(노드 개수. 1~500)
			int e = stoi(st.nextToken());//버스 노선 개수(간선 개수. 1~6000)
			List<int[]>[] edges = new ArrayList[v+1];
			long costs[] = new long[v+1];//1에서 i번째 노드 가는데 걸리는 최소 시간
			for(int i=1; i<=v; i++) {
				edges[i] = new ArrayList<>();
			}
			for(int i=0; i<e; i++) {
				st= new StringTokenizer(br.readLine());
				int a = stoi(st.nextToken());
				int b = stoi(st.nextToken());
				int cost = stoi(st.nextToken());
				edges[a].add(new int[] {b,cost});
			}
			Arrays.fill(costs, Long.MAX_VALUE);
			costs[1] = 0;
			boolean flag = false;//negative cycle일 경우 true로 바뀜
			for(int cycle = 1; cycle <=v; cycle++) {//edge-relaxation v-1번 돌고, v번째에서 바뀌는 것 있다면 negative cycle
				for(int i=1; i<=v; i++) {
					if(flag) break;//negative cycle
					 for(int j=0; j<edges[i].size(); j++) {
						 int curEdge = edges[i].get(j)[0];
						 int cost = edges[i].get(j)[1];
						 if(costs[i] != Long.MAX_VALUE && costs[curEdge] > costs[i]+cost) {
							 if(cycle == v) flag = true;
							 costs[curEdge] = costs[i] + cost;
						 }
					 }
				}
			}
			if(flag) bw.write("-1");
			else {
				for(int i=2; i<=v; i++) {
					if(costs[i] == Long.MAX_VALUE) bw.write("-1\n");
					else bw.write(costs[i]+"\n");
				}
			}
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
