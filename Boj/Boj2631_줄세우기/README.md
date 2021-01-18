# 2631_줄세우기

Algorithms: dp

Date: 2021/01/18

Level: G5, ○

Link: https://www.acmicpc.net/problem/2631

### **[문제정리]**

- 1~N번 까지 有 -> 번호 순서 바뀜.
- 번호 순서대로 줄 세우기 위해 필요한 최소 수

**입력**

- N : 아이들의 수. 2~200

**출력**

- 번호 순대로 줄 세우는데 옮겨지는 아이들의 최소 수

### [문제풀이]

- DP

    번호를 교환 하면서 순서 바꾸는 것이 아니라 밀어내면서 바뀜

    -> 연속된 숫자의 최대 개수를 구하고 이를 N에서 빼면 옮겨지는 아이의 최소 수 구할 수 있을 것이라 생각.

    - dp[i] 정의 :  i번째 순서의 번호에서 에서 i 이전에 자기보다 작은 번호 존재하면 해당 번호가 가진 값 + 1 중 최댓값.
    - dp[i] = Max(dp[line[0])~dp[line[i-1]]) + 1;

    ```java
    import java.io.BufferedReader;
    import java.io.BufferedWriter;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.io.OutputStreamWriter;
    import java.util.StringTokenizer;

    public class Main {

    	private static int stoi(String s) {
    		return Integer.parseInt(s);
    	}
    	public static void main(String[] args) {
    		try {
    			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    			int n = stoi(br.readLine());
    			int[] line = new int[n];
    			int[] dp = new int[n+1];
    			for(int i=0; i<n; i++) {
    				line[i] = stoi(br.readLine());
    			}
    			
    			int ans = 1;
    			dp[line[0]] = 1;
    			for(int i=1; i<n; i++) {
    				int curNum = line[i];
    				dp[curNum] = 1;
    				for(int j=0; j<i; j++) {
    					int bfNum = line[j];
    					if(curNum > bfNum) {
    						dp[curNum] = Math.max(dp[curNum], dp[bfNum]+1);
    					}
    				}
    				ans = Math.max(ans, dp[curNum]);//curNum의 dp값을 ans와 비교해야 하는데 i를 비교해서 틀렸음
    			}
    			int result = n - ans;
    			bw.write(result+"");
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