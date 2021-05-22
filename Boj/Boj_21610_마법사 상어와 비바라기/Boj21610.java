package boj;
import java.util.*;
import java.io.*;

public class Boj21610 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	static int[] dx = {0,-1,-1,-1,0,1,1,1};
	static int[] dy = {-1,-1,0,1,1,1,0,-1};
	static boolean[][] lastCloud;
	static List<int[]> clouds;
	static int[][] arr;
	static int n,m;
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	static void moveCloud(int d, int s) throws IOException{
		for(int i=0; i<clouds.size(); i++) {
			int[] cloud = clouds.get(i);
			cloud[0] = (cloud[0] + dx[d] * s + n) % n;
			cloud[1] = (cloud[1] + dy[d] * s + n) % n;
			++arr[cloud[0]][cloud[1]];
			lastCloud[cloud[0]][cloud[1]] = true;
			clouds.set(i, cloud);
		}
	}
	static void copyWater() {
		Queue<int[]> q = new LinkedList<>();
		for(int i=0; i<clouds.size(); i++) {
			int[] cloud = clouds.get(i);
			int cnt = 0;
			for(int j=1; j<8; j+=2) {
				int x = cloud[0]+dx[j];
				int y = cloud[1]+dy[j];
				if(x<0 || y<0 || x>=n || y>=n) continue;
				if(arr[x][y] > 0) ++cnt;
			}
			if(cnt > 0) q.add(new int[] {cloud[0],cloud[1],cnt});
		}
		while(!q.isEmpty()) {
			int[] cur = q.poll();
			arr[cur[0]][cur[1]] += cur[2];
		}
	}
	static void setClouds() {
		clouds.clear();
		for(int i=0; i<n; i++) {
			for(int j=0; j<n; j++) {
				if(!lastCloud[i][j] && arr[i][j] >= 2) {
					clouds.add(new int[] {i,j});
					arr[i][j]-=2;
				}
			}
		}
		for(int i=0; i<n; i++) {
			Arrays.fill(lastCloud[i], false);
		}
	}
	public static void solve() {
		try {
			StringTokenizer st;
			st= new StringTokenizer(br.readLine());
			n = stoi(st.nextToken());//arr�� ũ��
			m = stoi(st.nextToken());//���� �̵� Ƚ��
			arr = new int[n][n];//����� ���� ��
			lastCloud = new boolean[n][n];
			clouds = new ArrayList<>();
			clouds.add(new int[] {n-1,0});
			clouds.add(new int[] {n-1,1});
			clouds.add(new int[] {n-2,0});
			clouds.add(new int[] {n-2,1});
			
			for(int i=0; i<n; i++) {
				st= new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					arr[i][j] = stoi(st.nextToken());
				}
			}

			for(int i=0; i<m; i++) {
				st= new StringTokenizer(br.readLine());
				int d = stoi(st.nextToken())-1;//d����
				int s = stoi(st.nextToken()) % n;//sĭ �̵�
				moveCloud(d,s);//���� ������, ���� �� ����
				copyWater();//���� ������ ĭ�� ������ ���� ����
				setClouds();//���� ���� 2�̻��� ��� ĭ�� ���� ����� ���� �� 2 �پ��.���� ����� ĭ�� ������ ����� �����ִ� ĭ ���� 
			}
			int answer = 0;
			for(int i=0; i<n; i++) {
				for(int j=0; j<n; j++) {
					answer += arr[i][j];
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
