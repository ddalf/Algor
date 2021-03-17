package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

public class Boj9376 {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static int w,h;
	public static char[][] maps;
	public static int[][] visits;
	public static int[][] visitSum;
	public static ArrayList<Pair> prisoners;
	public static int[] dx = {-1,0,1,0};
	public static int[] dy = {0,-1,0,1};
	public static int ans;
	
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	public static void findPath(Pair p) throws IOException{
		PriorityQueue<Triple> pq = new PriorityQueue<>();
		for(int i=0; i<=h+1; i++) { 
			Arrays.fill(visits[i], -1);
		}
		pq.add(new Triple(p.x,p.y,0));
		while(!pq.isEmpty()) {
			Triple cur = pq.poll();
//			bw.write("("+cur.x+","+cur.y+") : "+cur.v+"\n");
			if(visits[cur.x][cur.y] >= 0) continue;
			visits[cur.x][cur.y] = cur.v;
			for(int i=0; i<4; i++) {
				int xx = cur.x + dx[i];
				int yy = cur.y + dy[i];
				if(xx < 0 || yy < 0 || xx >= h+2 || yy >= w+2) continue;
				if(visits[xx][yy] >= 0 || maps[xx][yy] == '*') continue;
				int value = cur.v;
				if(maps[xx][yy] == '#') {
//					bw.write("("+xx+","+yy+") : "+value+"\n");
					++value;
				}
				pq.add(new Triple(xx,yy,value));
			}
		}
		for(int i=0; i<=h+1; i++) {
			for(int j=0; j<=w+1; j++) {
				visitSum[i][j] += visits[i][j];
//				bw.write(visitSum[i][j]+" ");
			}
//			bw.newLine();
		}
//		bw.newLine();
//		bw.newLine();
	}
	
	
	public static void solve() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			int testCase = stoi(br.readLine());
			while(testCase-- > 0) {
				st = new StringTokenizer(br.readLine());
				h = stoi(st.nextToken());
				w = stoi(st.nextToken());
				maps = new char[h+2][w+2];
				visits = new int[h+2][w+2];
				visitSum = new int[h+2][w+2];
				prisoners = new ArrayList<>();
				ans = Integer.MAX_VALUE;
				prisoners.add(new Pair(0,0));
				for(int i=0; i<=h+1; i++) {
					for(int j=0; j<=w+1; j++) {
						maps[i][j] = '.';
					}
				}
				for(int i=1; i<=h; i++) {
					char[] mapTmp = br.readLine().toCharArray();
					for(int j=1; j<=w; j++) {
						maps[i][j] = mapTmp[j-1];
						if(maps[i][j] == '$') {
							prisoners.add(new Pair(i,j));
						}
					}
				}
				for(int i=0; i<3; i++) {
					findPath(prisoners.get(i));
				}
				ans = Integer.MAX_VALUE;
				for(int i=0; i<=h+1; i++) {
					for(int j=0; j<=w+1; j++) {
//						bw.write(visitSum[i][j]+" ");
						if(maps[i][j] == '*'||visitSum[i][j] < 0) continue;
						if(maps[i][j] == '#') visitSum[i][j] -= 2;
						ans = Math.min(ans, visitSum[i][j]);
					}
//					bw.newLine();
				}
				bw.write(ans+"\n");
			}
			bw.flush();
			bw.close();
			br.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
