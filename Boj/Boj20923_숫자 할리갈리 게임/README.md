# 20923_숫자 할리갈리 게임

Algorithms: recursive, simulation

Date: 2021/03/27

Level: S1, △

Link: https://www.acmicpc.net/problem/20923

### 문제정리

1. 도도와 수연 각각 N장 덱(카드로 이루어짐) 배분 / 게임 시작 시 그라운드 빔
    - 덱은 숫자 보이지 않게 카드 뒤집어 쌓어 놓은 카드 더미.
    - 도도와 수연 자신의 덱 가지고 게임 진행
    - 그라운드 : 도도, 수연 게임 진행하며 자신이 가진 덱에서 있는  카드 그라운드에 내려놓음.
2. 도도 시작 → 도도, 수연 차례대로 그라운드에 자신이 가진 덱에서 가장 위에 위치한 카드 내려놓음
3. 종 먼저 치는 사람 : 그라운드에 나와 있는 카드 더미 모두 가져감
    - 수연 종 침 : 그라운드 각각의 가장 위에 위치한 카드 합 5. 단, 어느쪽의 그라운드도 비어있으면 안된다.
    - 도도 종 침 : 그라운드 각각의 가장 위에 위치한 카드 5 나옴.
4. 종 침 ⇒ 상대방의 그라운드에 있는 카드 더미 자신의 덱 아래로 합침 + 자신의 그라운드에 있는 카드 더미 자신의 덱 아래로 합침
    - 종 쳐서 그라운드에 있는 카드 더미 가져가는 행위는 순서에 영향 X
5. M번 진행 후 자신의 덱에 더 많은 카드 가진 사람 승리
    - 카드 개수 같음 = 비김
    - 게임 진행 도중 자신의 덱에 있는 카드의 수 0개가 되는 즉시 상대방이 승리한 것으로 본다.

**[입력]**

- N : 도도, 수연이 가지는 카드 개수 (1~30000)
- M : 게임 진행 횟수(1~2,500,000)
- N개의 줄에 도도와 수연이 가진 덱의 맨 아래~맨 위까지 차례대로 주어짐

**[출력]**

- 게임 이긴 사람 도도 : do
- 게임 이긴 사람 수연 : su
- 비김 : dosu

출력

### 문제풀이

- 시뮬ㄹ

```java
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

public class Main {
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

	public static void main(String args[]) {
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
```