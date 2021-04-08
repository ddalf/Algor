# 14622_소수 게임

Algorithms: simulation, 에라토스테네스의 체

Date: 2021/04/08

Level: G3, △

Link: https://www.acmicpc.net/problem/14622

### 문제정리

- 누가 더 소수를 사랑하는지 내기
- 소수게임의 규칙
    1. 두 사람이 번갈아가며 소수를 말한다.
    2. 소수가 아닌 수를 부르게 될 경우 상대방은 지금까지 상대방이 말한 소수중에서 3번째로 큰 수만큼 점수를 얻게 된다.(만약 상대방이 지금까지 말한 소수가 3개 미만이라면 상대방은 1000점을 얻게 된다.)
    3. 만약 지금까지 한번이라도 등장한 소수를 말할 경우 해당 소수를 말한 팀이 -1000을 얻게 되며 해당 소수는 그 사람이 말한 소수로 기록되지 않는다.
    4. 규성이는 도전자이므로 게임은 항상 대웅이부터 시작한다.
    5. 두 사람이 말할 수 있는 소수는 항상 5000000 미만이다.

**[입력]**

- N : 두 사람이 대결을 하는 라운드 수. 5~100,000
- 대웅이, 규성이가 말하는 정수. 0~5,000,000

**[출력]**

- 대웅이가 이길 경우 “소수의 신 갓대웅”
- 규성이가 이길 경우 “소수 마스터 갓규성”
- 비길 경우 “우열을 가릴 수 없음”

### 문제풀이

- 소수 여부 : 에라스토테네스 체 알고리즘
- 오류 1. 시간초과
    - dList, gList에서 크기를 3으로 유지하지 않고 계속 넣을 경우 : 늘어난 크기 만큼 계속 sort하기 때문에 시간초과 발생.
- 오류 2.
    - 1을 소수 아님으로(decFlag[1] = true) 하는 것 빼먹음

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
			boolean[] decFlag = new boolean[5000001];//true일 경우 소수 아님 / false일 경우 소수
			for(int i=2; i<Math.sqrt(5000000); i++) {
				if(decFlag[i]) continue;
				for(int j=i*2; j<=5000000; j+=i) decFlag[j] = true; 
			}
      decFlag[1] = true;
			int n = stoi(br.readLine());
			List<Integer> dList = new LinkedList<>();
			List<Integer> gList = new LinkedList<>();
			Set<Integer> record = new HashSet<>();
			StringTokenizer st;
			long dScore = 0;
			long gScore = 0;
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				int d = stoi(st.nextToken());
				int g = stoi(st.nextToken());
				if(decFlag[d]) {//소수가 아닌 수를 부르게 될 경우 
					//(만약 상대방이 지금까지 말한 소수가 3개 미만이라면 상대방은 1000점을 얻게 된다.)
					if(gList.size() < 3) {
						gScore += 1000;
					}
					else{
						//상대방은 지금까지 상대방이 말한 소수중에서 3번째로 큰 수만큼 점수를 얻게 된다.
						Collections.sort(gList, Collections.reverseOrder());//내림차순				
						gScore += gList.get(2);
					}
				}
				else if(record.contains(d)) {//한번이라도 등장한 소수를 말할 경우 
					//해당 소수를 말한 팀이 -1000을 얻게 되며 해당 소수는 그 사람이 말한 소수로 기록되지 않는다.
					dScore -= 1000;
				}
				else {
					dList.add(d);
					record.add(d);
					if(dList.size() > 3) {
						Collections.sort(dList, Collections.reverseOrder());//내림차순
						dList.remove(dList.size()-1);
					}
				}
				if(decFlag[g]) {
					if(dList.size() < 3) {
						dScore += 1000;
					}
					else{
						//상대방은 지금까지 상대방이 말한 소수중에서 3번째로 큰 수만큼 점수를 얻게 된다.
						Collections.sort(dList, Collections.reverseOrder());//내림차순
						dScore += dList.get(2);
					}
					
				}
				else if(record.contains(g)) {
					gScore -= 1000;
				}
				else {
					gList.add(g);
					record.add(g);
					if(gList.size() > 3) {
						Collections.sort(gList, Collections.reverseOrder());
						gList.remove(gList.size()-1);
					}
				}
			}
			if(dScore > gScore) bw.write("소수의 신 갓대웅");
			else if(dScore < gScore) bw.write("소수 마스터 갓규성");
			else bw.write("우열을 가릴 수 없음");
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
```