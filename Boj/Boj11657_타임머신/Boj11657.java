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
			int v = stoi(st.nextToken());//���� ����(��� ����. 1~500)
			int e = stoi(st.nextToken());//���� �뼱 ����(���� ����. 1~6000)
			List<int[]>[] edges = new ArrayList[v+1];
			long costs[] = new long[v+1];//1���� i��° ��� ���µ� �ɸ��� �ּ� �ð�
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
			boolean flag = false;//negative cycle�� ��� true�� �ٲ�
			for(int cycle = 1; cycle <=v; cycle++) {//edge-relaxation v-1�� ����, v��°���� �ٲ�� �� �ִٸ� negative cycle
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
