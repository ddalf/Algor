# 1351_무한수열

Algorithms: dp

Date: 2021/01/24

Level: G4, ☆

Link: https://www.acmicpc.net/problem/1351

### [문제정리]

- A0 = 1
- A(i) = A(i/P) + A(i/Q) i>=1
- N과 P주어질 때 Q 구하기

입력

- N : 1~10^12
- P,Q : 2~10^9

출력

- 첫째 줄 A(N) 출력

### [문제풀이]

- DP
    - dp크기를 n으로 잡기에는 n이 매우 큰 값이므로 일정크기로 dp에 저장하고 이에 초과되는 값은 재귀로 구한다.

    ```java
    import java.io.BufferedReader;
    import java.io.BufferedWriter;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.io.OutputStreamWriter;
    import java.util.StringTokenizer;

    public class Main {
    	static long n;
    	static long dp[];
    	static int p;
    	static int q;
    	static final long MAXV = 10000000;
    	public static int stoi(String s) {
    		return Integer.parseInt(s);
    	}
    	public static long stol(String s) {
    		return Long.parseLong(s);
    	}

    	private static long go(long x) {
    		if(x <= MAXV) return dp[(int)x];
    		return go(x/p) + go(x/q);
    	}
    	
    	public static void main(String[] args) {
    		try {
    			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    			StringTokenizer st;
    			st = new StringTokenizer(br.readLine());
    			n =stol(st.nextToken());
    			p = stoi(st.nextToken());
    			q = stoi(st.nextToken());
    			long k = Math.min(n, MAXV);
    			dp = new long[(int)k+1];
    			dp[0] = 1;
    			for(int i=1; i<=k; i++) {
    				dp[i] = dp[i/p] + dp[i/q];
    			}
    			
    			bw.write(go(n)+"");
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