package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Edge{
	int num;
	int dis;
	Edge(){
		this.num = 0; 
		this.dis = 0;
	}
	
	Edge(int num, int dis){
		this.num = num;
		this.dis = dis;
	}
}

class Position implements Comparable<Position>{
	int num;
	int minLiter;
	long cost;
	
	Position(int num, int minLiter, long cost){
		this.num = num;
		this.minLiter = minLiter;
		this.cost = cost;
	}
	
	@Override
	public int compareTo(Position p) {
		return (int)(this.cost-p.cost);
	}
}

public class Boj13308 {
	static BufferedReader br;
	static BufferedWriter bw;
	static int n;//도시 수
	static int m;//간선 수
	static int[] liters;
	static ArrayList<Edge>[] paths;
	static long[][] visits;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	static long findPath(){
		PriorityQueue<Position> pq = new PriorityQueue<>(); 
		pq.add(new Position(1, liters[1], (long)0));
		while(!pq.isEmpty()) {
			Position curPos = pq.poll();
			if(curPos.num == n) {
				return curPos.cost;
			}
			if(visits[curPos.num][curPos.minLiter] != -1) continue;
			visits[curPos.num][curPos.minLiter] = curPos.cost;
			for(int i=0; i<paths[curPos.num].size(); i++) {
				Edge nextEdge = paths[curPos.num].get(i);
				int minL = Math.min(curPos.minLiter, liters[curPos.num]);
				if(visits[nextEdge.num][minL] != -1) continue;
				long cost = curPos.cost + (long)nextEdge.dis * minL;
				pq.add(new Position(nextEdge.num, minL, cost));
			}
		}
		return -1;
	}
	
	public static void solve() {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		
		StringTokenizer st;
		try {
			st = new StringTokenizer(br.readLine());
			
			n = stoi(st.nextToken());
			m = stoi(st.nextToken());
			liters = new int[n+1];
			paths = new ArrayList[n+1];
			visits = new long[n+1][2510];
			
			st = new StringTokenizer(br.readLine());
			for(int i=1; i<=n; i++) { 
				liters[i] = stoi(st.nextToken());//도시 주유소 리터당 가격
				paths[i] = new ArrayList<>();
				Arrays.fill(visits[i], -1);
			}
			
			for(int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				int a = stoi(st.nextToken());
				int b = stoi(st.nextToken());
				int dis = stoi(st.nextToken());
				paths[a].add(new Edge(b, dis));
				paths[b].add(new Edge(a, dis));
			}
			bw.write(findPath()+"");
			
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
