package boj;
import java.util.*;
import java.io.*;

public class Boj1992 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	static int[][] nums;
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	public static void quadTree(int x, int y, int size) throws IOException {
		boolean flag = true;
		for(int i=x; i<x+size; i++) {
			for(int j=y; j<y+size; j++) {
				if(nums[i][j] != nums[x][y]) {
					flag = false;
					break;
				}
			}
		}
		if(flag) {
			bw.write(nums[x][y]+"");
		}
		else {
			size /= 2;
			bw.write("(");
			quadTree(x,y,size);
			quadTree(x,y+size,size);
			quadTree(x+size,y,size);
			quadTree(x+size, y+size, size);
			bw.write(")");
		}
	}
	
	public static void solve() {
		try {
			int n = stoi(br.readLine());
			nums = new int[n][n];
			for(int i=0; i<n; i++) {
				char[] chars = br.readLine().toCharArray();
				for(int j=0; j<n; j++) {
					nums[i][j] = chars[j]-'0';
				}
			}
			quadTree(0,0,n);
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
