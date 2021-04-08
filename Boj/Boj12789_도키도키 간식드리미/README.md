# 12789_도키도키 간식드리미

Algorithms: queue, stack

Date: 2021/04/08

Level: S4, ○

Link: https://www.acmicpc.net/problem/12789

### 문제정리

- 사람들은 현재 1열로 줄을 서있고, 맨 앞의 사람만 이동이 가능
- 번호표 순서대로만 통과할 수 있는 라인
- 라인과 대기열의 맨 앞 사람 사이에는 한 사람씩 1열이 들어갈 수 있는 공간
- 현재 대기열의 사람들은 이 공간으로 올 수 있지만 반대는 불가능

**[입력]**

- N : 앞에 서 있는 학생들의 수(1~1000)
- 승환이 앞에 서있는 모든 학생들의 번호표(1,2,...,N) 순서

**[출력]**

- 승환이가 무사히 간식을 받을 수 있으면 "Nice"(따옴표는 제외)를 출력
- 그렇지 않다면 "Sad"(따옴표는 제외)를 출력

### 문제풀이

- 현재 줄에 있는 사람 순서 Queue에, 대기열에 들어가는 순서 Stack에 넣음
    - order = 1부터 시작
        - order와 일치할 때 queue에서 뺌 & 다음 순서가 대기열 첫번째에 있는 것과 동일하면 대기열 첫번째가 순서와 맞지 않을 때까지 뺌
        - order가 일치하지 않을 때 대기열에 넣음
    - 최종적으로 대기열에 남아있는 것 있을 경우 : Sad

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
```