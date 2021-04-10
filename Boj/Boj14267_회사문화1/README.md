# 14267_회사문화1

Algorithms: dfs, tree

Date: 2021/04/10

Level: G4, ○

Link: https://www.acmicpc.net/problem/14267

### 문제정리

- 상사가 직속 부하를 칭찬하면 그 부하가 부하의 직속 부하를 연쇄적으로 칭찬하는 내리 칭찬
- 모든 칭찬에는 칭찬의 정도를 의미하는 수치가 있는데, 이 수치 또한 부하들에게 똑같이 칭찬 받는다.
- 직속 상사와 직속 부하관계에 대해 주어지고, 칭찬에 대한 정보가 주어질 때, 각자 얼마의 칭찬을 받았는지 출력

**[입력]**

- n : 회사의 직원 수. 2~100,000
- m : 최초의 칭찬의 횟수 m. 2~100,000
- 직원 n명의 직속 상사의 번호
    - 최종적으로 1번이 사장. 1번의 경우, 상사가 없으므로 -1이 입력
- m줄에는 직속 상사로부터 칭찬을 받은 직원 번호 i, 칭찬의 수치 w

**[출력]**

- 1번부터 n번의 직원까지 칭찬을 받은 정도

### 문제풀이

- Dfs
    - 주의 1. 직속상사 한명이 부하직원 여러명 가질 수 있음
    - 주의 2. 직속상사로부터 여러번 칭찬받을 수 있다.
    - `List<Integer>[] sup` : 상사 i가 가진 부하직원 List에 저장하는 배열
    - `void dfs(int num, long w)`
        - `num` : 현재 직원 번호, w : `num`번째 직원이 가지는 칭찬 값
        - `num`이 가지는 부하직원들을 모두 dfs로 탐색하면서 칭찬 값 더해줌.
    - Big-O : O(n)

```java
import java.util.*;
import java.io.*;
public class Main {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	static int n, m;
	static List<Integer>[] sup; 
	static int[] praise;
	static long[] ans;
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	
	static void dfs(int num, long w) {//num : 현재 직원 번호
		ans[num] = w;
		for(int i=0; i<sup[num].size(); i++) {
			int subNum = sup[num].get(i);
			dfs(subNum, w + praise[subNum]);
		}
	}
	
	public static void main(String[] args) {
		try {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = stoi(st.nextToken());//회사 직원 수. 2~100,000
			m = stoi(st.nextToken());//칭찬 횟수. 2~100,000
			sup = new ArrayList[n+1];
			ans = new long[n+1];
			praise = new int[n+1];
			//직원 n명의 직속 상사의 번호
			//직속 상사의 번호는 자신의 번호보다 작으며, 최종적으로 1번이 사장. 
			st = new StringTokenizer(br.readLine());
			stoi(st.nextToken());
			for(int i=1; i<=n; i++) {
				sup[i] = new ArrayList<>();
			}
			for(int i=2; i<=n; i++) {
				sup[stoi(st.nextToken())].add(i);
			}
			//직속 상사로부터 칭찬을 받은 직원 번호 i, 칭찬의 수치 w
			for(int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				praise[stoi(st.nextToken())] += stoi(st.nextToken());
			}
			
			dfs(1, 0);
			for(int i=1; i<=n; i++) {
				bw.write(ans[i]+" ");
			}
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
```