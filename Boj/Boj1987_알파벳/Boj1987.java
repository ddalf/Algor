package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj1987 {
	public static BufferedReader br;
	public static BufferedWriter bw;
	static int r,c;
	static char[][] board;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,-1,0,1};
	static boolean[] visitAlpha;
	static int ans = 0;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

	public static void findPath(int x, int y, int cnt) {
		ans = Math.max(ans,  cnt);
		for(int i=0; i<4; i++) {
			int xx = x + dx[i];
			int yy = y + dy[i];
			if(xx < 0 || yy < 0 || xx >= r || yy >=c) continue;
			if(visitAlpha[board[xx][yy]-'A']) continue;
			visitAlpha[board[xx][yy]-'A'] = true;
			findPath(xx, yy, cnt+1);
			visitAlpha[board[xx][yy]-'A'] = false;
		}
	}
	
	public static void solve() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			st = new StringTokenizer(br.readLine());
			r = stoi(st.nextToken());//보드 행 크기
			c = stoi(st.nextToken());//보드 열 크기
			board = new char[r][c];
			visitAlpha = new boolean[26];//알파벳 체크
			for(int i=0; i<r; i++) {
				board[i] = br.readLine().toCharArray();
			}
			visitAlpha[board[0][0]-'A'] = true;
			findPath(0,0,1);
			bw.write(ans+"");
			bw.flush();
			bw.close();
			br.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
