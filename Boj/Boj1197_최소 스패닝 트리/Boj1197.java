package boj;
import java.util.*;
import java.io.*;

public class Boj1197 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	static int[] tree;
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	
	static class Edge implements Comparable<Edge>{
		int a;
		int b;
		int cost;
		Edge(int a, int b, int cost){
			this.a = a;
			this.b = b;
			this.cost = cost;
		}
		public int compareTo(Edge e) {
			return this.cost - e.cost;
		}
	}
	
	static int findRoot(int x) {
		if(tree[x] == x) return x;
		return tree[x] = findRoot(tree[x]);
	}
	
	static boolean merge(int a, int b) {
		int aRoot = findRoot(a);
		int bRoot = findRoot(b);
		if(aRoot == bRoot) return false;
		tree[aRoot] = bRoot;
		return true;
	}
	
	public static void solve() {
		try {
			StringTokenizer st;
			st= new StringTokenizer(br.readLine());
			int v = stoi(st.nextToken());
			int e = stoi(st.nextToken());
			
			tree = new int[v+1];
			for(int i=0; i<=v; i++) {
				tree[i] = i;
			}
			PriorityQueue<Edge> pq = new PriorityQueue<>();
			
			for(int i=0; i<e; i++) {
				st= new StringTokenizer(br.readLine());
				int a = stoi(st.nextToken());
				int b = stoi(st.nextToken());
				int cost = stoi(st.nextToken());
				pq.add(new Edge(a,b,cost));
			}
			
			long ans = 0;
			while(!pq.isEmpty()) {
				Edge cur = pq.poll();
				if(merge(cur.a, cur.b)) {
					ans += (long)cur.cost;
				}
			}
			bw.write(ans+"");
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
