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
				int nCost = edge[1] <= maxCost ? 0 : 1;//maxCost������ ����ġ 1 �ƴϸ� 0
				if(dis[xx] > cur.cost + nCost) {
					dis[xx] = cur.cost + nCost;
					pq.add(new Cable(xx, cur.cost + nCost));
				}
			}
		}
		return dis[n] <= k;//dis[n] : 1���� n�����ϴµ� maxCost�ʰ��� ����ġ ����� ����
	}
	
	public static void solve() {
		try {
			StringTokenizer st;
			st= new StringTokenizer(br.readLine());
			n = stoi(st.nextToken());//�л� ��
			p = stoi(st.nextToken());//���̺� ��
			k = stoi(st.nextToken());//��¥ ���� ���̺� ��
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
				//���� ����ġ mid�� ���Ϸ� �Ἥ N���� ������ �� �ִ��� Ž��
				if(isPossibleCost(mid)) {//������ �� �ִ� ��� �� ���� ����ġ�� ������ �� �ִ��� Ž�� => mid�� ����
					ans = mid;
					right = mid - 1;
				}
				else left = mid + 1;//������ �� ���� ��� �� ū ����ġ�δ� ������ �� �ִ��� Ž�� => mid�� ����
			}
			
			// ���弱������ ���� �Ǵ� �ּ��� ���� ����Ѵ�. ���� 1���� N�� ��ǻ�͸� �մ� ���� �Ұ��� �ϴٸ� -1�� ���
			bw.write(ans+"");
			
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
