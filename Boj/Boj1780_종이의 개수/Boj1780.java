package boj;
import java.util.*;
import java.io.*;

public class Boj1780 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	static int[] papers;
	static int[][] arr;
	
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	
	static void cutPaper(int x, int y, int size) throws IOException {
		boolean flag = true;
		for(int i=x; i<x+size; i++) {
			for(int j=y; j<y+size; j++) {
				if(arr[i][j] != arr[x][y]) {
					flag = false;
					break;
				}
			}
		}
		if(flag) {
			++papers[arr[x][y]];
		}
		else {
			size /= 3;
			for(int i=0; i<3; i++) {
				for(int j=0; j<3; j++) {
					cutPaper(x+i*size, y+j*size, size);
				}
			}
		}
	}
	
	
	
	public static void solve() {
		try {
			int n = stoi(br.readLine());
			papers = new int[3];
			arr = new int[n][n];
			StringTokenizer st ;
			for(int i=0;i<n;i++) {
				st = new StringTokenizer(br.readLine());
				for(int j=0; j<n; j++) {
					arr[i][j] = stoi(st.nextToken())+1;
				}
			}
			cutPaper(0,0,n);
			for(int i=0; i<3; i++) {
				bw.write(papers[i]+"\n");
			}
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
