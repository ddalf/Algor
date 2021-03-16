# 피보나치 수3

Algorithms: math, 거듭제곱근, 피사노주기, 행렬

Date: 2021/03/10

Level: G2, ☆

Link: https://www.acmicpc.net/problem/2749

### 문제정리

- 피보나치 수 : 0번째 0, 1번째 1, 2번째 ~ 바로 앞 두 피보나치 수의 합
- F(n) = F(n-1) + F(n-2)
- 0, 1, 1, 2, 3, 5, 8, 13, 21, 34, 55, 89, 144, 233, 377, 610, 987, 1597
- n번째 피보나치 수 구하는 프로그램

**[입력]**

- n : 1000000000000000000 보다 작거나 같은 자연수

**[출력]**

- n번째 피보나치 수 1000000로 나눈 나머지

### 문제풀이

- 피보나치 식 ⇒ 행렬로 변환 ⇒ 행렬 제곱 최적화 해서 구하는 방법

    O(logN)

    ```java
    import java.io.BufferedReader;
    import java.io.BufferedWriter;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.io.OutputStreamWriter;

    public class Main {
    	static BufferedReader br;
    	static BufferedWriter bw;
    	static long n;
    	static long[][] matrix = {{1,1},{1,0}};
    	static long[][] ans = {{1,0},{0,1}};
    	public static long stol(String s) {
    		return Long.parseLong(s);
    	}
    	
    	public static long[][] mulMatrix(long[][] x, long[][] y) {
    		long[][] ret = new long[2][2];
    		for(int i=0; i<2; i++) {
    			for(int j=0; j<2; j++) {
    				for(int k=0; k<2; k++) {
    					ret[i][j] = (ret[i][j] + x[i][k] * y[k][j]) % 1000000;
    				} 
    			}
    		}
    		return ret;
    	}
    	
    	public static void powMatrix() throws IOException {
    		while(n > 0) {
    			if(n % 2 == 1) {
    				ans = mulMatrix(ans, matrix);
    			}
    			n/=2;
    			matrix = mulMatrix(matrix, matrix);
    		}
    	}
    	
    	public static void main(String[] args) {
    		br = new BufferedReader(new InputStreamReader(System.in));
    		bw = new BufferedWriter(new OutputStreamWriter(System.out));

    		try {
    			n = stol(br.readLine()) - 1;
    			powMatrix();
    			bw.write(ans[0][0]+"");
    			bw.flush();
    			bw.close();
    			br.close();
    		} catch (IOException e) {
    			e.printStackTrace();
    		}
    	}
    }
    ```

- 피사노 주기 이용피사노 주기 이용
    - *피보나치 수를 나눈 수는 항상 주기를 가진다.*

        *피보나치 수를 나눌 수를 k라고 할 때, k=10^n이면,*

        *피사노 주기는 15∗10^(n-1)이다.*

        O(P) // p : 피사노 주기

        ```java
        import java.io.BufferedReader;
        import java.io.BufferedWriter;
        import java.io.IOException;
        import java.io.InputStreamReader;
        import java.io.OutputStreamWriter;

        public class Main {
        	static BufferedReader br;
        	static BufferedWriter bw;
        	static final int MODV = 1000000;
        	static long n;
        	static long[] fibNums;
        	
        	public static long stol(String s) {
        		return Long.parseLong(s);
        	}
        	
        	public static void main(String[] args) {
        		br = new BufferedReader(new InputStreamReader(System.in));
        		bw = new BufferedWriter(new OutputStreamWriter(System.out));

        		try {
        			int period = 1500000;
        			n = stol(br.readLine());
        			fibNums = new long[period];
        			fibNums[0]=0; fibNums[1] = 1;
         			for(int i=2; i<period && i<=n; i++) {
        				fibNums[i] = (fibNums[i-1] + fibNums[i-2]) % MODV;
        			}
         			n %= period;
         			bw.write(fibNums[(int)n]+"");
        			bw.flush();
        			bw.close();
        			br.close();
        		} catch (IOException e) {
        			e.printStackTrace();
        		}
        	}
        }
        ```