package boj;
import java.util.*;
import java.io.*;
public class Boj14267 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	static int n, m;
	static List<Integer>[] sup; 
	static int[] praise;
	static int[] ans;
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	
	static void dfs(int num, int w) {//num : 현재 직원 번호
		ans[num] = w;
		for(int i=0; i<sup[num].size(); i++) {//num이 가진 부하 직원들
			int subNum = sup[num].get(i);
			dfs(subNum, w + praise[subNum]);
		}
	}
	
	public static void solve() {
		try {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = stoi(st.nextToken());//회사 직원 수. 2~100,000
			m = stoi(st.nextToken());//칭찬 횟수. 2~100,000
			sup = new ArrayList[n+1];
			ans = new int[n+1];
			praise = new int[n+1];
			//직원 n명의 직속 상사의 번호
			//직속 상사의 번호는 자신의 번호보다 작으며, 최종적으로 1번이 사장. 
			st = new StringTokenizer(br.readLine());
			stoi(st.nextToken());
			for(int i=1; i<=n; i++) {
				sup[i] = new ArrayList<>();
			}
			for(int i=2; i<=n; i++) {
				sup[stoi(st.nextToken())].add(i);//입력받는 값 : 상사 => 상사가 가진 부하 직원 i를 추가해줌
			}
			//직속 상사로부터 칭찬을 받은 직원 번호 i, 칭찬의 수치 w
			for(int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				praise[stoi(st.nextToken())] += stoi(st.nextToken());//직속 상사로 부터 여러번 칭찬 받을 수 있다 => += 써줌
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
