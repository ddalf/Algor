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
	
	static void dfs(int num, int w) {//num : ���� ���� ��ȣ
		ans[num] = w;
		for(int i=0; i<sup[num].size(); i++) {//num�� ���� ���� ������
			int subNum = sup[num].get(i);
			dfs(subNum, w + praise[subNum]);
		}
	}
	
	public static void solve() {
		try {
			StringTokenizer st = new StringTokenizer(br.readLine());
			n = stoi(st.nextToken());//ȸ�� ���� ��. 2~100,000
			m = stoi(st.nextToken());//Ī�� Ƚ��. 2~100,000
			sup = new ArrayList[n+1];
			ans = new int[n+1];
			praise = new int[n+1];
			//���� n���� ���� ����� ��ȣ
			//���� ����� ��ȣ�� �ڽ��� ��ȣ���� ������, ���������� 1���� ����. 
			st = new StringTokenizer(br.readLine());
			stoi(st.nextToken());
			for(int i=1; i<=n; i++) {
				sup[i] = new ArrayList<>();
			}
			for(int i=2; i<=n; i++) {
				sup[stoi(st.nextToken())].add(i);//�Է¹޴� �� : ��� => ��簡 ���� ���� ���� i�� �߰�����
			}
			//���� ���κ��� Ī���� ���� ���� ��ȣ i, Ī���� ��ġ w
			for(int i=0; i<m; i++) {
				st = new StringTokenizer(br.readLine());
				praise[stoi(st.nextToken())] += stoi(st.nextToken());//���� ���� ���� ������ Ī�� ���� �� �ִ� => += ����
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
