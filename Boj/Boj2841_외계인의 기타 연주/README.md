# 2841_외계인의 기타 연주

Algorithms: PriorityQueue

Date: 2021/04/08

Level: S2, ○

Link: https://www.acmicpc.net/problem/2841

### 문제정리

- 외계인은 손가락을 수십억개 가지고 있다.
- 기타는 1번 줄부터 6번 줄까지 총 6개의 줄이 있고, 각 줄은 P개의 프렛으로 나누어져 있다.
- 프렛의 번호도 1번부터 P번까지 나누어져 있다.
- 각 음은 줄에서 해당하는 프렛을 누르고 줄을 튕기면 연주할 수 있다.
    - 어떤 줄의 프렛을 여러 개 누르고 있다면, 가장 높은 프렛의 음이 발생
- 손가락으로 프렛을 한 번 누르거나 떼는 것을 손가락을 한 번 움직였다고 한다.
- 어떤 멜로디가 주어졌을 때, 손가락의 가장 적게 움직이는 회수

**[입력]**

- N :  멜로디에 포함되어 있는 음의 수. 0~500,000
- P : 한 줄에 있는 프렛의 수 P. 2~300,000

**[출력]**

- 멜로디를 연주하는데 필요한 최소 손가락 움직임

### 문제풀이

- 각 줄에 대한 PriorityQueue를 배열형태로 생성
    - 눌러져 있는 프렛 없는 경우 or 눌러져 있는 프렛보다 큰 경우 : 현재 프렛 queue에 추가
    - 눌러져 있는 프렛보다 작은 경우 : queue가 비거나 더 작은 것 나오기 전까지 뺌
    - 주의 : 눌러져 있는 프렛과 동일할 경우 다시 누르지 않는다.

```java
import java.util.*;
import java.io.*;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	public static void main(String[] args) {
		try {
			PriorityQueue<Integer>[] lines = new PriorityQueue[7];
			for(int i=0; i<=6; i++) {
				lines[i] = new PriorityQueue<>(Collections.reverseOrder());
			}
			int n;//멜로디에 포함되어 있는 음의 수 500,000
			int p;//프렛의 수 300,000
			StringTokenizer st;
			st = new StringTokenizer(br.readLine());
			n = stoi(st.nextToken());
			p = stoi(st.nextToken());
			int ans = 0;
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				int lineNum = stoi(st.nextToken());
				int pNum = stoi(st.nextToken());
				if(lines[lineNum].isEmpty()) {//아무 프렛도 눌려져 있지 않은 경우
					lines[lineNum].add(pNum);
					++ans;
				}
				else if(!lines[lineNum].isEmpty() && pNum > lines[lineNum].peek()) {//누르려는 프렛이 이전에 누른 프렛 번호 보다 큰 경우
					lines[lineNum].add(pNum);
					++ans;
				}
				else if(!lines[lineNum].isEmpty() &&pNum < lines[lineNum].peek()){//누르려는 프렛이 이전에 누른 프랫보다 작은 경우
					while(!lines[lineNum].isEmpty()) {//누르려는 프렛보다 작은 프렛번호 나올 때까지 빼준다.
						if(pNum >= lines[lineNum].peek()) break;
						lines[lineNum].poll();
						++ans;//=손가락 때는 것
					}
					if(!lines[lineNum].isEmpty() && lines[lineNum].peek() == pNum) continue;//눌러져 있는 프렛번호와 동일할 경우 손가락 변화 없음
					lines[lineNum].add(pNum);
					++ans;
				}
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
```