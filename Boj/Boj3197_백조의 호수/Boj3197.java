package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.LinkedList;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.StringTokenizer;

class Loc{
	int x;
	int y;
	Loc(int x, int y){
		this.x = x;
		this.y = y;
	}
}

public class Boj3197 {
	static BufferedReader br;
	static BufferedWriter bw;
	static int r;
	static int c;
	static int maxDay;
	static char[][] lake;
	static int[][] meltingDays;
	static Queue<Loc> water;
	static ArrayList<Loc> swans;
	static boolean[][] visits;
	static int[] dx = {-1,0,1,0};
	static int[] dy = {0,-1,0,1};
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	static void meltingIce(){
		while(!water.isEmpty()) {
			Loc curLoc = water.poll();
			for(int dir=0; dir<4; dir++) {
				int x = curLoc.x + dx[dir];
				int y = curLoc.y + dy[dir];
				if(x < 0 || y < 0 || x >= r || y >= c) continue;
				if(meltingDays[x][y] > -1) continue;
				water.add(new Loc(x,y));
				meltingDays[x][y] = meltingDays[curLoc.x][curLoc.y] + 1;
				maxDay = Math.max(maxDay, meltingDays[x][y]);
			}
		}
	}
	
	static boolean isMeetSwan(int day) {
		Loc endLoc = swans.get(1);
		Queue<Loc> q = new LinkedList<>();
		for(int i=0; i<r; i++) {
			Arrays.fill(visits[i], false);
		}
		q.add(swans.get(0));
		visits[swans.get(0).x][swans.get(0).y] = true;
		while(!q.isEmpty()) {
			Loc curLoc = q.poll();
			for(int dir=0; dir<4; dir++) {
				int x = curLoc.x + dx[dir];
				int y = curLoc.y + dy[dir];
				if(x < 0 || y < 0 || x >= r || y >= c) continue;
				if(!visits[x][y] && meltingDays[x][y] <= day) {
					if(x == endLoc.x && y == endLoc.y) {
						return true;
					}
					visits[x][y] = true;
					q.add(new Loc(x,y));
				}
			}
		}
		return false;
	}
	
	public static void solve() {
		try {			
			br = new BufferedReader(new InputStreamReader(System.in));
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			st = new StringTokenizer(br.readLine());
			r = stoi(st.nextToken());
			c = stoi(st.nextToken());
				
			lake = new char[r][c];
			meltingDays = new int[r][c];
			visits = new boolean[r][c];
			swans = new ArrayList<>();
			water= new LinkedList<>();
			
			for(int i=0; i<r; i++) {
				lake[i] = br.readLine().toCharArray();
				Arrays.fill(meltingDays[i], -1);
				for(int j=0; j<c; j++) {
					if(lake[i][j] == 'L') {
						swans.add(new Loc(i,j));
						lake[i][j] = '.';
					}
					if(lake[i][j] == '.'){
						water.add(new Loc(i,j));
						meltingDays[i][j] = 0;						
					}
				}
			}
			
			meltingIce();
			
			int left = 0, right = maxDay;
			while(left <= right) {
				int mid = (left+right) / 2;
				if(isMeetSwan(mid)) right = mid - 1;
				else left = mid + 1;
			}
			
			bw.write(left+"");
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
