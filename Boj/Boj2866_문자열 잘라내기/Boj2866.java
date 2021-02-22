package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.HashSet;
import java.util.StringTokenizer;
public class Boj2866 {
	public static BufferedReader br;
	public static BufferedWriter bw;
	static int r;
	static int c;
	static char[][] tables;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	static boolean isRepeat(int mid) {
		HashSet<String> set = new HashSet<String>();
		for(int i=0; i<c; i++) {
			StringBuilder sb = new StringBuilder();
			for(int j=mid; j<r; j++) {
				sb.append(tables[j][i]);
			}
			if(set.contains(sb.toString())) return true;
			set.add(sb.toString());
		}		
		
		return false;	
	}
	
	public static void solve() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st = new StringTokenizer(br.readLine());
			r = stoi(st.nextToken());
			c = stoi(st.nextToken());
			tables = new char[r][c];
			for(int i=0; i<r; i++) {
				tables[i] = br.readLine().toCharArray();
			}
			
			int left = 0, right = r-1;
			while(left <= right) {
				int mid = (left+right)/2;
				if(isRepeat(mid)) right = mid-1;
				else left = mid+1;
			}
			int ans = left-1;
			bw.write(ans+"");
/*
�ߺ��Ǵ� �� ���� ��� : right = mid-1. ���ڿ� ���̸� �ø� -> �� �� ���ڿ������� �ߺ��Ǵ��� Ž��
�ߺ��Ǵ� �� ���� ��� : left = mid+1. ���ڿ� ���� ���� -> �� ª�� ���ڿ����� �ߺ��Ǵ� �� �ִ��� Ž�� 
 */
			bw.flush();
			bw.close();
			br.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}
/*
��������
* R�� �� C�� ���� �̷���� ���̺�. ���� ���ĺ� �ڹ���
* �� ���̺� �� ��->�Ʒ��� �о �ϳ��� ���ڿ� ����
* ���� ���� �� ������ ���̺� �� �о ���ڿ� �ߺ� X -> ���� ���� �� ������. count 1����
* ������ ���ڿ� �߰� : �ݺ� ���߰� count���� ��ȯ

[�Է�]
* R, C : ���̺� ���� ����, ���� ����. 2~1000
* R�ٿ� C���� �ҹ���

[���]
count ��

����Ǯ��
6 6 
abcdef
abcdef
abcdef
abcdef
ggcdef
ggcdef

* ���� ��� count = 3�� �� gg,gg �� �����ϰ� ��Ÿ���� ����ȴ�.
* �̺�Ž��. 0~n-1���� Ž���ؼ� �ߺ��Ǵ� ���ڿ� �ִ��� Ž��
	* left : 0
	* right : n-1
	* mid : �߰���.
* mid : mid ~ n-1���� ���� �� �ִ� ���ڿ�
	* �ߺ��Ǵ� �� ���� ��� : right = mid-1. ���ڿ� ���̸� �ø� -> �� �� ���ڿ������� �ߺ��Ǵ��� Ž��
	* �ߺ��Ǵ� �� ���� ��� : left = mid+1. ���ڿ� ���� ���� -> �� ª�� ���ڿ����� �ߺ��Ǵ� �� �ִ��� Ž��
	


*/