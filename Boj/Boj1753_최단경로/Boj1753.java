package boj;
import java.util.*;
import java.io.*;

public class Boj1753 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	static List<int[]>[] adjList;
	static int[] ans;
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	static class Dis implements Comparable<Dis>{
		int x;
		int cost;
		Dis(int x, int cost){
			this.x = x;
			this.cost =cost;
		}
		@Override
		public int compareTo(Dis o) {
			return this.cost - o.cost;
		}
		
		
	}
	static void dij(int stIdx) throws IOException {
		PriorityQueue<Dis> pq = new PriorityQueue<>();
		pq.add(new Dis(stIdx, 0));
		ans[stIdx] = 0;
		while(!pq.isEmpty()) {
			int x = pq.peek().x;
			int cost = pq.peek().cost;
			pq.poll();
			for(int i=0; i<adjList[x].size(); i++) {
				int xx = adjList[x].get(i)[0];
				int cc = ans[x] + adjList[x].get(i)[1];
				if(ans[xx] > cc) {
					pq.add(new Dis(xx, cc));
					ans[xx] = cc;
				}
			}
		}
	}
	public static void solve() {
		try {
			StringTokenizer st;
			st= new StringTokenizer(br.readLine());
			int v = stoi(st.nextToken());
			int e = stoi(st.nextToken());
			adjList = new ArrayList[v];
			ans = new int[v];
			Arrays.fill(ans, Integer.MAX_VALUE);
			for(int i=0; i<v; i++) {
				adjList[i] = new ArrayList<>();
			}
			int stIdx = stoi(br.readLine())-1;
			
			for(int i=0; i<e; i++) {
				st= new StringTokenizer(br.readLine());
				int a = stoi(st.nextToken())-1;
				int b = stoi(st.nextToken())-1;
				int cost = stoi(st.nextToken());
				adjList[a].add(new int[] {b,cost});
			}
			
			dij(stIdx);
			
			for(int i=0; i<v; i++) {
				if(i == stIdx) bw.write("0\n");
				else if(ans[i] == Integer.MAX_VALUE) bw.write("INF\n");
				else bw.write(ans[i]+"\n");
			}
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
