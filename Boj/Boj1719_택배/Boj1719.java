package boj;
import java.util.*;
import java.io.*;

public class Boj1719 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	static int[][] ans;
	static List<Dis>[] adjList;
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
		@Override
		public String toString() {
			return this.x+" "+this.cost+"\n";
		}
	}
	static void dij(int stIdx, int n) throws IOException{
		PriorityQueue<Dis> pq = new PriorityQueue<>();
		int[] dis = new int[n+1];
		Arrays.fill(dis, Integer.MAX_VALUE);
		pq.add(new Dis(stIdx,0));
		dis[stIdx] = 0;
		while(!pq.isEmpty()) {
			Dis curDis = pq.poll();
			for(int i=0; i<adjList[curDis.x].size(); i++) {
				int xx = adjList[curDis.x].get(i).x;
				int cc = adjList[curDis.x].get(i).cost + dis[curDis.x];
				if(dis[xx] > cc) {
					dis[xx] = cc;
					pq.add(new Dis(xx,cc));
					if(ans[stIdx][curDis.x] == 0) {
						ans[stIdx][xx] = xx;
					}
					else {
						ans[stIdx][xx] = ans[stIdx][curDis.x];
					}
				}
			}
		}
	}
	
	
	public static void solve() {
		try {
			StringTokenizer st;
			st= new StringTokenizer(br.readLine());
			int n = stoi(st.nextToken());//�������� ����. 1~200
			int m = stoi(st.nextToken());//�����尣 ����� ����. 1~10000
			ans = new int[n+1][n+1];
			adjList = new ArrayList[n+1];
			for(int i=0; i<=n; i++) {
				adjList[i] = new ArrayList<>();
			}
			//������ �� ���
			//IF. ������ ��� �� �� ������ ��� ����,,?????
			for(int i=0; i<m; i++) {
				st= new StringTokenizer(br.readLine());
				int a = stoi(st.nextToken());
				int b = stoi(st.nextToken());
				int cost = stoi(st.nextToken());
				adjList[a].add(new Dis(b, cost));
				adjList[b].add(new Dis(a, cost));
			}
			
			for(int i=1; i<=n; i++) {				
				dij(i, n);	
			}
			
			for(int i=1; i<=n; i++) {
				for(int j=1; j<=n; j++) {
					if(ans[i][j] == 0)bw.write("- ");
					else bw.write(ans[i][j]+" ");
				}
				bw.newLine();
			}
			
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
