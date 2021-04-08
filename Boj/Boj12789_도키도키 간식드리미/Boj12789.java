package boj;
import java.util.*;
import java.io.*;

public class Boj12789 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	public static void solve() {
		try {
			Queue<Integer> line = new LinkedList<>();
			Stack<Integer> wait = new Stack<>();
			int n =stoi(br.readLine());
			StringTokenizer st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				line.add(stoi(st.nextToken()));
			}
			int order = 1;
			String ans = "Nice";
			while(!line.isEmpty()) {
				int cur = line.poll();
				if(cur == order) {
					++order;
					while(!wait.isEmpty() && wait.peek() == order) {
						wait.pop();
						++order;
					}
				}
				else {
					wait.add(cur);
				}
			}
			if(wait.size() > 0) bw.write("Sad");
			else bw.write("Nice");
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
