package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj14719 {
	static BufferedReader br;
	static BufferedWriter bw;
	static int[] blocks;
	static int h,w;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	static int findLeftMax(int curIdx) {
		int maxV = -1, maxIdx = -1;
		for(int i=0; i<curIdx; i++) {
			if(blocks[i] > maxV) {
				maxIdx = i;
				maxV = blocks[i];
			}
		}
		return maxV;
	}
	
	static int findRightMax(int curIdx) {
		int maxV = -1, maxIdx = -1;
		for(int i=curIdx+1; i<w; i++) {
			if(blocks[i] > maxV) {
				maxIdx = i;
				maxV = blocks[i];
			}
		}
		return maxV;
	}
	
	public static void solve() {
		br = new BufferedReader(new InputStreamReader(System.in));
		bw = new BufferedWriter(new OutputStreamWriter(System.out));
		StringTokenizer st;
		try {
			st = new StringTokenizer(br.readLine());
			h = stoi(st.nextToken());
			w = stoi(st.nextToken());
			blocks = new int[w];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<w; i++) {
				blocks[i] = stoi(st.nextToken());
			}
			int ans = 0;
			for(int i=1; i<w-1; i++) {
				int leftMax = findLeftMax(i);
				int rightMax = findRightMax(i);
//				bw.write("idx "+i+":"+leftMax+" "+rightMax+"\n");
				if(leftMax > blocks[i] && rightMax > blocks[i]) {
					ans += Math.min(leftMax, rightMax) - blocks[i];
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
