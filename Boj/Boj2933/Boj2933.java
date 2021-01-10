package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj2933 {
	public static String[] board;
	private static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	public void solve(){
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			StringTokenizer st = new StringTokenizer(br.readLine());
		
			int r = stoi(st.nextToken());
			int c = stoi(st.nextToken());
			board = new String[r];
			for(int i=0; i<r; i++) {
				board[i] = br.readLine();
			}
			for(int i=0; i<r; i++) {
				System.out.println(board[i]);
			}
			int n = stoi(br.readLine());
			for(int i=0; i<n; i++) {
				
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	

}
