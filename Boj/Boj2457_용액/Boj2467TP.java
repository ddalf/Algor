package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2467TP {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static int n;
	public static int[] liquids;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	public static void solve() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			n = stoi(br.readLine());
			st = new StringTokenizer(br.readLine());
			liquids = new int[n];
			for(int i=0; i<n; i++) {
				liquids[i] = stoi(st.nextToken());
			}
			Arrays.sort(liquids);
			int left = 0, right= n-1;
			int minMixV = 2000000001;
			int ansL=0, ansR=0;
			while(left<right) {
				int lv = liquids[left];
				int rv = liquids[right];
				if(Math.abs(lv+rv)<minMixV) {
					minMixV = Math.abs(lv+rv);
					ansL = left;
					ansR = right;
				}
				if(lv+rv < 0) left++;
				else right--;
			}
			bw.write(ansL +" "+ansR);	
			bw.flush(); bw.close(); br.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}

/*
8
-100 -99 99 0 1 2 3 4 5
*/


