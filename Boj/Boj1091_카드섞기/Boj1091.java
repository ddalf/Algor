package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Boj1091 {
	public static BufferedReader br;
	public static BufferedWriter bw;
	static int n;
	static int[] p;
	static int[] s;
	static int[] originCards;
	static int[] curCards;
	static int[] nextCards;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	static int shuffleCard(int n) throws IOException{
		for(int i=0; i<n; i++) {
			originCards[i] = i%3;
			curCards[i] = i%3;
		}
		int cnt = 0;
		while(true) {
			boolean flag = true;
			for(int i=0; i<n; i++) {
				if(curCards[i] != p[i])//원하는 플레이어에게 카드가지 않음
					flag = false;
				
			}
			if(flag) break;//flag true : 각 카드 원하는 플레이어에게 갔을 경우
			for(int i=0; i<n; i++) {
				nextCards[i] = curCards[s[i]];
			}
			
			flag = false;
			for(int i=0; i<n; i++) {//싸이클 확인
				if(nextCards[i] != originCards[i]) {
					flag = true;
				}
			}
			if(!flag) {//싸이클이 존재하는 경우
				cnt = -1;
				break;
			}
			for(int i=0; i<n; i++) {
				curCards[i] = nextCards[i];
			}
			++cnt;	
		}
		return cnt;
	}
	
	public static void solve() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			int n = stoi(br.readLine());//n개의 카드 이용. 3~48(3의 배수)

			curCards = new int[n];
			nextCards = new int[n];
			originCards = new int[n];
			p = new int[n];//각 카드가 어떤 플레이어에게 가야 하는지에 대한 정보. p의 값은 0,1,2 중 하나
			s = new int[n];//카드 섞는 방법
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				p[i] = stoi(st.nextToken());
			}
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				s[i] = stoi(st.nextToken());
			}
			
			int ans = -1;
//			bw.write("n:"+n+"\n");
			ans = shuffleCard(n);
			bw.write(ans+"");
			bw.flush();
			bw.close();
			br.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
