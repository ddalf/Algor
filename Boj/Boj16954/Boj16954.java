package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

public class Boj16954 {
	public static int[] dx = {-1,0,1,0,-1,-1,1,1};
	public static int[] dy = {0,-1,0,1,-1,1,-1,1};
	public static char[][] board;
	public static boolean[][] visit;
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	void bfs() {
		Queue<Point> queue= new LinkedList<Point>();
		queue.add(new Point(8,1));
		int floor = 1;
		while(!queue.isEmpty()) {
			int x = queue.peek().x;
			int y = queue.peek().y;
			queue.poll();
			for(int dir=0; dir<8; dir++) {
				int xx = x + dx[dir];
				int yy = y + dy[dir];
				if(!visit[xx][yy] && board[xx][yy] == '.') {
					visit[xx][yy] = true;
					queue.add(new Point(xx,yy));
				}
			}
		}
	}
	
	public void solve() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			board = new char[10][10];
			visit = new boolean[10][10];
			for(int i=0; i<10; i++) {
				if(i == 0 || i== 9) {
					for(int j=0; j<10; j++) {
						board[i][j] = '#';
					}
				}
				else {
					String str = br.readLine();
					str = "#"+str+"#";
					for(int j=0; j<10; j++) {
						board[i][j] = str.charAt(j);
					}
				}
			}
			
			br.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
