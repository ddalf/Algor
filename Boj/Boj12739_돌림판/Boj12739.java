package boj;
import java.util.*;
import java.io.*;

public class Boj12739 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	static Map<Character, Integer> colorMap;
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	static char[] changeColor(char[] board, int n) {
		char[] boardTmp = new char[n];
		for(int i=0; i<n; i++) {
			char part = board[i];
			char lChar = board[(i+n-1) % n];
			char rChar = board[(i+n+1) % n];
			int[] colors = new int[3];
			++colors[colorMap.get(part)];
			++colors[colorMap.get(lChar)];
			++colors[colorMap.get(rChar)];
			if(colors[0] == 3 || colors[1] == 3 || colors[2] == 3) boardTmp[i] = 'B';
			else if(colors[0] == 1 && colors[1] == 1 && colors[2] == 1) boardTmp[i] = 'B';
			else if((colors[0] == 2 && colors[1] == 1) 
					|| (colors[1] == 2 && colors[2] == 1) 
					|| (colors[2] == 2 && colors[0] == 1)) boardTmp[i] = 'R';
			else boardTmp[i] = 'G';
		}
		
		return boardTmp;
	}
	public static void solve() {
		try {
			StringTokenizer st;
			st= new StringTokenizer(br.readLine());
			int n = stoi(st.nextToken());//돌림판 등분 개수
			int k = stoi(st.nextToken());//색 바꾼 횟수
			char[] board = br.readLine().toCharArray();
			colorMap = new HashMap<>();
			colorMap.put('R', 0);
			colorMap.put('G', 1);
			colorMap.put('B', 2);
			while(--k >= 0) {
				board = changeColor(board, n);
			}
			int[] ans = new int[3];
			for(int i=0; i<n; i++) {
				++ans[colorMap.get(board[i])];
			}
			
			for(int i=0; i<3; i++) {
				bw.write(ans[i]+" ");
			}
			
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
