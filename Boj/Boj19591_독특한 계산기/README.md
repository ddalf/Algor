# 19591_독특한계산기

Algorithms: deque, simulation

Date: 2021/04/06

Level: G3, ○

Link: https://www.acmicpc.net/problem/19591

### 문제정리

- 수식을 독특한 방식으로 계산
    1. 수식에서 맨 앞의 연산자, 또는 맨 뒤의 연산자 먼저 계산한다. 단, 음수의 부호는 연산자로 취급하지 않는다.
    2. 곱셈, 나눗셈을 덧셈, 뺄셈보다 더 먼저 계산한다.
    3. 연산자의 우선순위가 같다면 해당 연산자를 계산했을 때 결과가 큰 것부터 계산한다.
    4. 계산했을 때 결과 값 또한 같다면 앞에 것을 먼저 계산한다.
    - 맨 앞을 제외하고 음수가 들어오는 경우 존재하지 않는다.
    - 불필요한 0이 앞에 있을 수 있다. 즉, 001 + 0002 같은 수식이 나올 수 있다.
    - 나누어지는 수가 양수면 나머지가 0 이상, 음수면 나머지가 0 이하로 처리가 되는 식으로 진행했을 때 나오는 몫을 계산하는 방식으로 이루어진다. 예를 들어, 3 / 2 = 1, (−3) / 2 = −1, 3 / (−2) = −1, (−3) / (−2) = 1로 계산된다.

**[입력]**

- 숫자, '+', '*', '-', '/'로만 이루어진 길이가 106 이하인 수식
- 계산 과정 중의 모든 수는 −2^63 이상 2^63 미만. 0으로 나누는 경우는 없다.

**[출력]**

- 주어진 식을 계산한 결과 값을 출력한다. 불필요한 0은 제거해야 한다.

### 문제풀이

- 시뮬레이션, Deque
    - 연산자 : +,-,*,/를 0,1,2,3로 취급 & 우선순위 1,1,2,2
    - numDq  : 숫자를 순서대로 저장하는 Deque
    - opsDq : 연산자를 순서대로 저장하는 Deque
    - check() : 연산할 수식 판단
        - 맨 앞 연산자, 맨 뒤 연산자를 가지고 경우의 수 나누어 판단
            - 연산자가 1개남은 경우 or 맨 앞 연산자가 우선순위가 큰 경우
            - 맨 뒤 연산자가 우선순위가 큰 경우
            - 우선순위가 같은 경우
    - calc : 연산 수행
        - 주의 : 연산 시 수의 순서가 바뀌어서 안된다

            - calc(연산자, 맨 앞의 값,  맨 앞 다음의 값)

            - calc(연산자, 맨 뒤의 이전 값, 맨 뒤의 값)

- 오류 1. 시간초과

    수식에 대한 String을 나누는 것을 replaceAll을 써서 정규식으로 연산자 지움 / 숫자 지움 ⇒ split 시킴으로 문자열에 대해 나누는 방식으로 했음

    ⇒ 수식의 길이 10^6으로 매우길고, 문자열 처리가 오래걸리기 때문에 시간 초과 발생

    ⇒ String을 char형 배열로 변경 ~> 숫자, 연산자 구분해서 넣어줌 

```java
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Deque;
import java.util.LinkedList;
import java.util.StringTokenizer;

public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	static Deque<Long> numDq = new LinkedList<>();
	static Deque<Integer> opsDq = new LinkedList<>();
	static int[] priority = {1,1,2,2};
	public static long stol(String s) {
		 return Long.parseLong(s);
	}
	
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	
	static long calc(int ops, long a, long b) throws IOException{
		long rt = 0;
		switch(ops) {
		case 0 : rt = a + b; break;
		case 1 : rt = a - b; break;
		case 2 : rt = a * b; break;
		case 3 : {
			rt = Math.abs(a) / Math.abs(b); 
			if(a < 0 && b > 0 || a > 0 && b < 0) rt = -rt;
			break;
			}
		}
		return rt;
	}
	public static void check() throws IOException {
		while(!opsDq.isEmpty()) {
			int fOps = opsDq.peekFirst();//맨 앞 연산자
			int lastOps = opsDq.peekLast();//맨 뒤 연산자
			if(opsDq.size()==1 || priority[fOps] > priority[lastOps]) {//맨 앞의 우선순위가 높은 경우
				numDq.addFirst(calc(fOps, numDq.pollFirst(), numDq.pollFirst()));
				opsDq.pollFirst();
			}
			else if(priority[fOps] < priority[lastOps]) {
				long last = numDq.pollLast();
				numDq.addLast(calc(lastOps, numDq.pollLast(), last));//순서 주의
				opsDq.pollLast();
			}
			else {
				long frontNum = numDq.pollFirst();
				long backNum = numDq.pollLast();
				long frontRs = calc(fOps, frontNum, numDq.peekFirst());
				long lastRs = calc(lastOps, numDq.peekLast(), backNum);//순서 주의
				if(frontRs >= lastRs) {
					numDq.addLast(backNum);
					numDq.pollFirst();
					numDq.addFirst(frontRs);		
					opsDq.pollFirst();
				}
				else {
					numDq.addFirst(frontNum);
					numDq.pollLast();
					numDq.addLast(lastRs);	
					opsDq.pollLast();
				}
			}
		}
		
		long ans = numDq.pollFirst();
		bw.write(ans+"\n");
	}
	
	public static void main(String[] args) {
		try {
			numDq = new LinkedList<>();
			opsDq = new LinkedList<>();
			
			char[] equations = br.readLine().toCharArray();
			boolean minusFlag = false;
			int i = 0;
			if(equations[0] == '-') {
				minusFlag = true;
				++i;
			}
			for(; i<equations.length; i++) {
				char op = equations[i];
				if(op >= '0' && op <= '9') {
					long num = op -'0';				
						while(true) {
							if(i+1 == equations.length || equations[i+1] < '0' || equations[i+1] > '9') break;	
							op = equations[++i];									
							num = num * 10 + (op-'0');			
						}
					numDq.add(num);
				}
				else {
					switch(op) {
						case '+' : opsDq.add(0); break;
						case '-' : opsDq.add(1); break;
						case '*' : opsDq.add(2); break;
						case '/' : opsDq.add(3); break;
					}
				}
			}
			if(minusFlag) numDq.add(-numDq.pollFirst());
			check();
			
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
```