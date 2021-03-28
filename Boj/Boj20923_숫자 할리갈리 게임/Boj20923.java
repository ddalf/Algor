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
		if(!ground[0].isEmpty() && (ground[0].peekFirst() == 5)) return DO;//DO가 종 침
		if(!ground[1].isEmpty() && (ground[1].peekFirst() == 5)) return DO;//DO가 종 침
		if(!ground[0].isEmpty() && !ground[1].isEmpty() && (ground[0].peekFirst() + ground[1].peekFirst() == 5)) return SU;//SU가 종 침
		return -1;
	}

	public static void solve() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = stoi(st.nextToken());//각자 가진 카드 개수
			m = stoi(st.nextToken());//게임 진행 횟수
			cards = new Deque[2];
			cards[DO] = new LinkedList<>();
			cards[SU] = new LinkedList<>();
			ground = new Deque[2];
			ground[DO] = new LinkedList<>();
			ground[SU] = new LinkedList<>();
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				cards[DO].addFirst(stoi(st.nextToken()));//맨 위의 카드가 맨 앞에 존재
				cards[SU].addFirst(stoi(st.nextToken()));
			}			
			
			int turn = DO;
			for(int i=0; i<m; i++) {
				ground[turn].addFirst(cards[turn].pollFirst());//현재 turn의 카드에서 맨 위 카드 꺼냄
				if(cards[turn].isEmpty()) break;
				int win = checkWinner();//둘 중 한명이 종 쳤을 경우
				if(win != -1) {
					int dt = 1 - win;//종 친 사람 말고 다른 사람의 그라운드에 있는 카드 먼저 가져옴
					for(int j=0; j<2; j++) {
						while(!ground[dt].isEmpty()) {
							cards[win].addLast(ground[dt].pollLast());//카드 뒤집음 => 가장 아래쪽(가장 뒤쪽) 카드가 가장 위쪽에 오게 됨
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
