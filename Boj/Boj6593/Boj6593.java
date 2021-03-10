package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.LinkedList;
import java.util.Queue;
import java.util.StringTokenizer;

class Location{
	public int f;
	public int x;
	public int y;
	
	Location(int f, int x, int y){
		this.f = f;
		this.x = x;
		this.y = y;
	}
}

public class Boj6593 {
	static BufferedReader br;
	static BufferedWriter bw;
	static int l, r, c;
	static int[] df = { 0, 0, 0, 0, 1, -1 };
	static int[] dx = { -1, 0, 1, 0, 0, 0 };
	static int[] dy = { 0, -1, 0, 1, 0, 0 };
	static char[][][] buildings;
	static int[][][] visits;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}

	static void bfs(Location sp, Location ep) throws IOException {
		Queue<Location> q = new LinkedList<Location>(); 
		q.add(sp);
		visits[sp.f][sp.x][sp.y] = 1;
		while(!q.isEmpty()) {
			Location loc = q.poll();
			for(int i=0; i<6; i++) {
				int f = loc.f + df[i];
				int x = loc.x + dx[i];
				int y = loc.y + dy[i];
				if(f < 0 || x < 0 || y < 0 || f >=l || x>=r || y>=c) continue;
				if(visits[f][x][y] > 0 || buildings[f][x][y] == '#') continue;
				visits[f][x][y] = visits[loc.f][loc.x][loc.y]+1;
				q.add(new Location(f,x,y));

			}
		}
	}
	public static void solve() {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));

		StringTokenizer st;
		try {
			while(true) {
				st= new StringTokenizer(br.readLine());
				l = stoi(st.nextToken());
				r = stoi(st.nextToken());
				c = stoi(st.nextToken());
				if(l == 0 && r == 0 && c == 0) break;
				buildings = new char[l][r][c];
				visits = new int[l][r][c];
				
				Location sp = null, ep = null;
				
				for(int i=0; i<l; i++) {
					for(int j=0; j<r; j++) {
						char tmp[] = br.readLine().toCharArray();
						for(int k=0; k<c; k++) {
							buildings[i][j][k] = tmp[k];
							if(tmp[k] == 'S') sp = new Location(i,j,k);
							else if(tmp[k] == 'E') ep = new Location(i,j,k);
						}
					}
					br.readLine();
				}
				
				bfs(sp, ep);
				
				if(visits[ep.f][ep.x][ep.y] == 0) bw.write("Trapped!\n");
				else bw.write("Escaped in "+(visits[ep.f][ep.x][ep.y]-1)+" minute(s).\n");
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

/*
 * �������� �� ���� ���̰� 1�� ������ü ������ �׾��� - ������ �� X / ������� - ������ �� O ������ 6���� ĭ���� �̵� ����
 * �밢������ �̵��ϴ� �� �Ұ��� �ٱ��� - ������ ���� �־� �ⱸ ���ؼ��� Ż�� ����
 * 
 * [�Է�] �׽�Ʈ ���̽� ������. 0 0 0�̸� ���� L : 1~30. ������ �� �� R : 1~30. ������ �� ���� �� C : 1~30.
 * ������ �� ���� �� ���� ������ 'S'�� ǥ���ǰ�, Ż���� �� �ִ� �ⱸ�� 'E'�� ǥ�� ������ �����־� ������ �� ���� ĭ�� '#'����
 * ǥ���ǰ�, ����ִ� ĭ�� '.'���� ǥ��
 * 
 * [���] Ż�� ���� : Escaped in x minute(s) Ż�� �Ұ��� : Trapped!
 */