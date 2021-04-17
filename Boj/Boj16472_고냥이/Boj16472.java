package boj;
import java.util.*;
import java.io.*;

public class Boj16472 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	public static void solve() {
		try {
			//�ִ� N���� ������ ���ĺ��� ���� ���ӵ� ���ڿ��ۿ� �ν����� ���Ѵ�
			//���ڿ��� �־����� �� �� �����Ⱑ �ν��� �� �ִ� �ִ� ���ڿ��� ��
			int n = stoi(br.readLine());//2~26
			char[] words = br.readLine().toCharArray();//1~100,000
			int[] visit = new int[27];
			
			int left = 0;
			int right = 0;
			int ans = -1;
			int maxLen = -1;//�ִ����
			++visit[words[0]-'a'];//left~right������ ������ ���� ����
			int cnt = 1;
			while(true) {
				++right;
				if(right == words.length) break;
				if(visit[words[right]-'a']++ == 0) {//���� right�� ����Ű�� ���� ���� ����
					++cnt;//ó�� �湮�� ������ ��� cnt ����
				}
				if(cnt <= n)maxLen = Math.max(maxLen, right - left + 1);//cnt�� n���� ���� ���
				while(cnt > n && left < right) {//cnt�� n���� ū ���  ��� ���� ���� �ٿ���� �� => left �̵�
					if(--visit[words[left]-'a'] == 0) --cnt;
					++left;
				}
			}
			bw.write(maxLen+"");
			
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
