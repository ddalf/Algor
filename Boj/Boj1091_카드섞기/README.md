# 1091_카드섞기

Algorithms: simulation

Date: 2021/03/26

Level: G5, ○

Link: https://www.acmicpc.net/problem/1091

### 문제정리

- 3명의 플레이어, N개의 카드 이용
- 0번째 위치 카드 → 플레이어 0
1번재 위치 카드 → 플레이어 1
2번째 위치 카드 → 플레이어 2
3번째 위치 카드 → 플레이어 0
...
- 카드 섞기 전에 순서 알고 있고, 각 카드가 특정한 플레이어에게 보내짐
- 수열 S : 길이가 N인 . 카드 섞을 때 주어진 방법
    - i번째 위치에 있던 카드 ⇒ S[i]번째 위치로 이동
- 수열 P : 각 카드가 어떤 플레이어에게 가야 하는지에 대한 정보
- 목적 달성하기 위해 필요한 카드 섞는 횟수

**[입력]**

- N : 3~48인 3의 배수
- 수열 P : 길이 N. 값 0,1,2 중 하나
- 수열 S : 길이 N. 값 자연수 or 0 중복 X(0~N-1)

**[출력]**

- 몇 번 섞어야 하는지
    - 계속 섞어도 카드를 해당하는 플레이어에게 줄 수 없는 경우 : -1 출력레이어에게 줄 수 없는 경우 : -1 출력

### 문제풀이

- 시뮬레이션
    - suffleCard() : 수열 P와 일치할 때까지 카드 썩음
        - orginalCards[] : 맨 처음 카드 상태
        - curCards[] : 현재 카드 섞인 상태
        - nextCards[] : 카드 섞어서 다음에 배치된 카드 상태
        - 싸이클 체크(★★★★★)

            계속 섞어도 카드를 해당하는 플레이어에게 줄 수 없는 경우 = 싸이클 돔 = 맨 처음 상태와 같아짐 ⇒ originalCards 와 nextCards값 비교

            [//orginalCards](//orginalcards는)[i]의 각 값은 i%3이므로 배열 없이 i%3값 비교해도 된다.

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;

public class Main {
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
	
	static int shuffleCard(int n){
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
			for(int i=0; i<n; i++) {//싸이클 확인
				curCards[i] = nextCards[i];
			}
			++cnt;	
		}
		return cnt;
	}
	
	public static void main(String[] args) {
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
```