package boj;
import java.util.*;
import java.io.*;

public class Boj2841 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	public static void solve() {
		try {
			PriorityQueue<Integer>[] lines = new PriorityQueue[7];
			for(int i=0; i<=6; i++) {
				lines[i] = new PriorityQueue<>(Collections.reverseOrder());
			}
			int n;//��ε� ���ԵǾ� �ִ� ���� �� 500,000
			int p;//������ �� 300,000
			StringTokenizer st;
			st = new StringTokenizer(br.readLine());
			n = stoi(st.nextToken());
			p = stoi(st.nextToken());
			int ans = 0;
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				int lineNum = stoi(st.nextToken());
				int pNum = stoi(st.nextToken());
				if(lines[lineNum].isEmpty()) {
					lines[lineNum].add(pNum);
					++ans;
				}
				else if(!lines[lineNum].isEmpty() && pNum > lines[lineNum].peek()) {
					lines[lineNum].add(pNum);
					++ans;
				}
				else if(!lines[lineNum].isEmpty() &&pNum < lines[lineNum].peek()){
					while(!lines[lineNum].isEmpty()) {
						if(pNum >= lines[lineNum].peek()) break;
						lines[lineNum].poll();
						++ans;
					}
					if(!lines[lineNum].isEmpty() && lines[lineNum].peek() == pNum) continue;
					lines[lineNum].add(pNum);
					++ans;
				}
				//���� ������ �ִ� ���� ���� ���� ���� pNum(�������� �ϴ� ����)���� �� �۰ų� ���ٸ� �׳� ������ ��.
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
