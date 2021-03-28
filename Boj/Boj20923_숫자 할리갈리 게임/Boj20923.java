package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Stack;
import java.util.StringTokenizer;

public class Boj20923 {
	public static BufferedReader br;
	public static BufferedWriter bw;
	static int n, m;
	static Deque<Integer>[] cards;//0 : do / 1: su
	static Deque<Integer>[] ground;//0 : do / 1: su
	static final int DO = 0;
	static final int SU = 1;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	public static int checkWinner() {
		if(!ground[0].isEmpty() && (ground[0].peekFirst() == 5)) return DO;//DO�� �� ħ
		if(!ground[1].isEmpty() && (ground[1].peekFirst() == 5)) return DO;//DO�� �� ħ
		if(!ground[0].isEmpty() && !ground[1].isEmpty() && (ground[0].peekFirst() + ground[1].peekFirst() == 5)) return SU;//SU�� �� ħ
		return -1;
	}

	public static void solve() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = stoi(st.nextToken());//���� ���� ī�� ����
			m = stoi(st.nextToken());//���� ���� Ƚ��
			cards = new Deque[2];
			cards[DO] = new LinkedList<>();
			cards[SU] = new LinkedList<>();
			ground = new Deque[2];
			ground[DO] = new LinkedList<>();
			ground[SU] = new LinkedList<>();
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				cards[DO].addFirst(stoi(st.nextToken()));//�� ���� ī�尡 �� �տ� ����
				cards[SU].addFirst(stoi(st.nextToken()));
			}			
			
			int turn = DO;
			for(int i=0; i<m; i++) {
				ground[turn].addFirst(cards[turn].pollFirst());//���� turn�� ī�忡�� �� �� ī�� ����
				if(cards[turn].isEmpty()) break;
				int win = checkWinner();//�� �� �Ѹ��� �� ���� ���
				if(win != -1) {
					int dt = 1 - win;//�� ģ ��� ���� �ٸ� ����� �׶��忡 �ִ� ī�� ���� ������
					for(int j=0; j<2; j++) {
						while(!ground[dt].isEmpty()) {
							cards[win].addLast(ground[dt].pollLast());//ī�� ������ => ���� �Ʒ���(���� ����) ī�尡 ���� ���ʿ� ���� ��
						}
						dt = 1 - dt;
					}
				}
				turn = 1 - turn;
			}
			
			if(cards[DO].size() > cards[SU].size()) {
				bw.write("do");
			}
			else if(cards[DO].size() < cards[SU].size()) {
				bw.write("su");
			}
			else {
				bw.write("dosu");
			}
			
			bw.flush();
			bw.close();
			br.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
