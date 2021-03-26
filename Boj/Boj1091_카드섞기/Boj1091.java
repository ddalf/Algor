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
				if(curCards[i] != p[i])//���ϴ� �÷��̾�� ī�尡�� ����
					flag = false;
				
			}
			if(flag) break;//flag true : �� ī�� ���ϴ� �÷��̾�� ���� ���
			for(int i=0; i<n; i++) {
				nextCards[i] = curCards[s[i]];
			}
			
			flag = false;
			for(int i=0; i<n; i++) {//����Ŭ Ȯ��
				if(nextCards[i] != originCards[i]) {
					flag = true;
				}
			}
			if(!flag) {//����Ŭ�� �����ϴ� ���
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
			int n = stoi(br.readLine());//n���� ī�� �̿�. 3~48(3�� ���)

			curCards = new int[n];
			nextCards = new int[n];
			originCards = new int[n];
			p = new int[n];//�� ī�尡 � �÷��̾�� ���� �ϴ����� ���� ����. p�� ���� 0,1,2 �� �ϳ�
			s = new int[n];//ī�� ���� ���
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
