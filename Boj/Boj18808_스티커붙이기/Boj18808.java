package boj;
import java.util.*;
import java.io.*;

public class Boj18808 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	static int[][] board;
	static int n,m;
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	static int[][] turn(int dir, int[][] sticker) throws IOException{
		if(dir == 0) return sticker;
		int sr = sticker.length;
		int sc = sticker[0].length;
		int[][] sTmp = new int[sc][sr];
		
		for(int i=0; i<sTmp.length; i++) {
			for(int j=0; j<sTmp[0].length; j++) {
				sTmp[i][j] = sticker[sr-j-1][i];
			}
		}
		return sTmp;
	}
	static boolean checkSticker(int sx, int sy, int[][] sticker)throws IOException {
		for(int i=sx; i<sx+sticker.length; i++) {
			for(int j=sy; j<sy+sticker[0].length; j++) {
				if(i<0 || j<0 || i>=n||j>=m) return false;
				if(sticker[i-sx][j-sy] == 1 && board[i][j] == 1) {
					return false;
				}
			}
		}
		for(int i=sx; i<sx+sticker.length; i++) {
			for(int j=sy; j<sy+sticker[0].length; j++) {
				board[i][j] += sticker[i-sx][j-sy];
			}
		}
		return true;
	}
	static void insertSticker(int[][] sticker)throws IOException {
		int dir = 0;
		boolean flag = false;
		int[][] sTmp = sticker;
		while(dir < 4) {
			//현재 방향에 따라 스티커 돌려줌 0:그대로/1:90도/2:180도/3:270도
			sTmp = turn(dir, sTmp);
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					//i,j 위치에서 스티커 들어가는지 확인
					if(checkSticker(i,j,sTmp)) {
						flag = true;//스티커가 들어가면
						break;
					}
				}
				if(flag) break;
			}
			if(flag) break;
			++dir;
		} 
	}
	public static void solve() {
		try {
			StringTokenizer st;
			st= new StringTokenizer(br.readLine());
			n = stoi(st.nextToken());//행 길이
			m = stoi(st.nextToken());//열 길이
			int k = stoi(st.nextToken());//스티거 개수
			board = new int[n][m];
			List<int[][]> stickers = new ArrayList<>();
			for(int i=0; i<k; i++) {
				st= new StringTokenizer(br.readLine());
				int sr = stoi(st.nextToken());
				int sc = stoi(st.nextToken());
				int[][] sticker = new int[sr][sc];
				for(int j=0; j<sr; j++) {
					st= new StringTokenizer(br.readLine());
					for(int z=0; z<sc; z++) {
						sticker[j][z] = stoi(st.nextToken());
					}
				}
				stickers.add(sticker);
			}
			for(int i=0; i<k; i++) {
				insertSticker(stickers.get(i));
			}
			int ans = 0;
			for(int i=0; i<n; i++) {
				for(int j=0; j<m; j++) {
					ans+=board[i][j];
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
