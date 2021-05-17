package boj;
import java.util.*;
import java.io.*;

public class Boj1939 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	static int[] trees;
	public static class Node implements Comparable<Node>{
		int v, u,cost;
		Node(int v, int u, int cost){
			this.v = v;
			this.u = u;
			this.cost = cost;
		}
		public int compareTo(Node n) {
			return n.cost - this.cost;
		}
	}
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	public static int find(int x) {
		if(trees[x] == x) return x;
		else return trees[x] = find(trees[x]);
	}
	public static void merge(int u, int v) {
		int a = find(u);
		int b = find(v);
		trees[a] = b;
	}
	
	public static void solve() {
		try {
			StringTokenizer st;
			st= new StringTokenizer(br.readLine());
			int n = stoi(st.nextToken());
			int m = stoi(st.nextToken());
			trees = new int[n+1];
			PriorityQueue<Node> pq = new PriorityQueue<>();
			for(int i=0; i<m; i++) {
				st= new StringTokenizer(br.readLine());
				int a = stoi(st.nextToken());
				int b = stoi(st.nextToken());
				int c = stoi(st.nextToken());
				pq.add(new Node(a,b,c));
			}
			st = new StringTokenizer(br.readLine());
			int start = stoi(st.nextToken());
			int end = stoi(st.nextToken());
			
			for(int i=1; i<=n; i++) trees[i] = i;
			int answer = 0;
			while(!pq.isEmpty()) {
				Node cur = pq.poll();
				merge(cur.u, cur.v);
				if(find(start) == find(end)) {
					answer = cur.cost;
					break;
				}
			}
			bw.write(answer+"");
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
