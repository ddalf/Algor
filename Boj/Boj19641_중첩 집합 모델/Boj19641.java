package boj;
import java.util.*;
import java.io.*;
class Ver{
	int left;
	int right;
	Ver(){
		this.left = 0;
		this.right = 0;
	}
	Ver(int left, int right){
		this.left = left;
		this.right = right;
	}
}
public class Boj19641 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	static int n;//Ʈ�� ���� ���� 2~10,000
	static boolean[] visit;
	static List<Integer>[] tree;
	static Ver[] ans;
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	
	static int dfs(int idx, int num)throws IOException {//idx : ���� ��� ��ȣ / num : �ʵ� ��
		visit[idx] = true;
		ans[idx] = new Ver();
		ans[idx].left = num;
		int right = num;
		for(int i=0; i< tree[idx].size(); i++) {//�̾��� ���� ��� �湮
			int nextIdx = tree[idx].get(i);
			if(visit[nextIdx]) continue;
			right = dfs(nextIdx, right+1);
		}
		ans[idx].right = right+1;
		return ans[idx].right;
	}
	
	public static void solve() {
		try {
			n = stoi(br.readLine());
			tree = new ArrayList[n+1];
			ans = new Ver[n+1];
			visit = new boolean[n+1];
			
			StringTokenizer st;
			for(int i=1; i<=n; i++) {
				tree[i] = new ArrayList<>();
			}
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				int node = stoi(st.nextToken());
				while(st.hasMoreTokens()) {
					int nNode = stoi(st.nextToken());
					if(nNode == -1) break;
					tree[node].add(nNode);
				}
			}
			for(int i=1; i<=n; i++) {
				Collections.sort(tree[i]);//��ȣ�� ���� ���� ������ ������������ �湮�ؾ� �ϹǷ� ���� sort����
			}
			
			int root = stoi(br.readLine());
			dfs(root, 1);
			for(int i=1; i<=n; i++) {
				bw.write(i+" "+ans[i].left+" "+ans[i].right+"\n");
			}
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
