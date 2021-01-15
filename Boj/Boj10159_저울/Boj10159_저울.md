# 10159_저울

Algorithms: floyd
Level: ☆
Link: https://www.acmicpc.net/problem/10159

**[문제 정리]**

- 물건 쌍에 대해 양팔 저울로 무게 비교

**[입력]**

- N : 물건 개수. 5~100
- M : 측정된 물건 쌍의 개수. 0~2000
- m개의 줄에 미리 측정된 비교 결과. 앞 > 뒤

**[출력]**

- n개의 줄에 결과 출력. i번째 줄 물건 i와 비교 결과 알 수 없는 물건의 개수

**[문제풀이]**

- 문제풀이1(안됨)

    무거운 경우 / 가벼운 경우 나눠서 계산함. 

    -> 사용한 방법으로는 나중에 check 안되는 경우 생김

    ```cpp
    import java.io.BufferedReader;
    import java.io.BufferedWriter;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.io.OutputStreamWriter;
    import java.util.Arrays;
    import java.util.Collections;
    import java.util.StringTokenizer;
    
    public class Main {
    	public final static int LIGHT = -1;
    	public final static int HEAVY = 1;
    	private static int stoi(String str) {
    		return Integer.parseInt(str);
    	}
    		
    	public static void main(String[] args) {
    		try {
    			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    			StringTokenizer st;
    			int n = stoi(br.readLine());
    			int m = stoi(br.readLine());
    			int[][] weightcomp = new int[n+1][n+1];
    			for(int i=1; i<=m; i++) {
    				st = new StringTokenizer(br.readLine());
    				int h = stoi(st.nextToken());
    				int l = stoi(st.nextToken());
    				weightcomp[h][l] = LIGHT;
    				weightcomp[l][h] = HEAVY; 
    			}
    
    			for(int i=1; i<=n; i++) {
    				for(int j=1; j<=n; j++) {
    					if(i == j) continue;
    					if(weightcomp[i][j] == HEAVY) {
    						for(int k=1; k<=n; k++) {
    							if(weightcomp[j][k] == HEAVY) {
    								weightcomp[i][k] = HEAVY;
    								weightcomp[k][i] = LIGHT;
    							}
    						}
    					}
    					else if(weightcomp[i][j] == LIGHT) {
    						for(int k=1; k<=n; k++) {
    							if(weightcomp[j][k] == LIGHT) {
    								weightcomp[i][k] = LIGHT;
    								weightcomp[k][i] = HEAVY;
    							}
    						}
    					}
    				}
    			}
    			for(int i=1; i<=n; i++) {
    				int count = 0;
    				for(int j=1;j<=n; j++) {
    					if(i==j) continue;
    					if(weightcomp[i][j] == 0) ++count;
    				}
    				bw.write(count+"\n");
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

- 문제풀이 2(플로이드 와샬)
    - 각 번호 = 노드
    - a > b 일 때 a에서 b로 가는 길이 있다고 생각(양방향x. 단일방향)
    - 모든 정점 사이의 경로 → all-to-all 이고, n최댓값 100이므로 O(n^3) 이여도 시간초과 나지 않음. → 플로이드 와샬 사용

    ```cpp
    import java.io.BufferedReader;
    import java.io.BufferedWriter;
    import java.io.IOException;
    import java.io.InputStreamReader;
    import java.io.OutputStreamWriter;
    import java.util.Arrays;
    import java.util.Collections;
    import java.util.StringTokenizer;

    public class Main {
    	private static int stoi(String str) {
    		return Integer.parseInt(str);
    	}
    		
    	public static void main(String[] args) {
    		try {
    			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
    			StringTokenizer st;
    			int n = stoi(br.readLine());
    			int m = stoi(br.readLine());
    			boolean[][] weightcomp = new boolean[n+1][n+1];
    			for(int i=1; i<=m; i++) {
    				st = new StringTokenizer(br.readLine());
    				int h = stoi(st.nextToken());
    				int l = stoi(st.nextToken());
    				weightcomp[h][l] = true;
    			}
    			
    			for(int k=1; k<=n; k++) {
    				for(int i=1; i<=n; i++) {
    					for(int j=1; j<=n; j++) {
    						if(weightcomp[i][k] && weightcomp[k][j]) weightcomp[i][j] = true;
    					}
    				}
    			}
    			
    			for(int i=1; i<=n; i++) {
    				int count = 0;
    				for(int j=1;j<=n; j++) {
    					if(i==j) continue;
    					if(!weightcomp[i][j] && !weightcomp[j][i])++count;
    				}
    				bw.write(count+"\n");
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