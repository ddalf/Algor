package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;
class Point{
	int x,y;
	public Point() {x=0;y=0;};
	public Point(int x, int y) { this.x = x; this.y = y;};
}
public class Boj11758 {
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	public void solve() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st;
			Point p[] = new Point[3];
			for(int i=0; i<3; i++) {
				st = new StringTokenizer(br.readLine());
				int x = stoi(st.nextToken());
				int y = stoi(st.nextToken());
				p[i] = new Point(x,y);
			}
			System.out.println(ccw(p));
			br.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
	
	private int ccw(Point[] p) {
		int first = (p[1].x - p[0].x) * (p[2].y-p[0].y);
		int second = (p[1].y - p[0].y) * (p[2].x-p[0].x);
		int result = first - second;
		if(result < 0) return -1;
		else if(result == 0) return 0;
		return 1;
	}
}
