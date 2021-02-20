package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj1756 {
	public static BufferedReader br;
	public static BufferedWriter bw;
	public static int n;
	public static int d;
	public static int[] pizzas;
	public static int[] ovens;
	public static int stoi(String s) {
		return Integer.parseInt(s);
	}
	
	public static void solve() {
		try {
			br = new BufferedReader(new InputStreamReader(System.in));
			bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			st = new StringTokenizer(br.readLine());
			d = stoi(st.nextToken());
			n = stoi(st.nextToken());
			ovens = new int[d+1];
			pizzas = new int[n+1];
			st = new StringTokenizer(br.readLine());
			ovens[0] = Integer.MAX_VALUE;
			for(int i=1; i<=d; i++) {
				ovens[i] = stoi(st.nextToken());
				ovens[i] = Math.min(ovens[i], ovens[i-1]);
			}
			st = new StringTokenizer(br.readLine());		
			for(int i=1; i<=n; i++) {
				pizzas[i] = stoi(st.nextToken());
			}
			
			int pizzaDepth = d+1;
			for(int i=1; i<=n; i++) {
				int left = 1;
				int right = --pizzaDepth;
				if(right <= 0) break;
				while(left <= right) {
					int mid = (left+right)/2;
					if(ovens[mid] >= pizzas[i]) left = mid + 1;
					else right = mid-1;
				}
				pizzaDepth = right;
			}
			if(pizzaDepth<=0)bw.write("0");
			else bw.write(pizzaDepth+"");
			bw.flush(); bw.close(); br.close();
		}
		catch(IOException e) {
			e.printStackTrace();
		}
	}
}

/*
��������
* N���� ���� ����.
* ���� ���� ���� ������.
* ���� �� ���� ���̿� ���� �ʺ� �ٸ�
* ���� ���� �ϼ��Ǵ� ������� ���쿡 ��
* N���� ���ڰ� ���쿡 ��� ���� �� ���� ���� �󸶳� ���� ��� �̴���

[�Է�]
* D : ���� ����. 1~300,000 
* N : ���� ������ ���� 1~300,000
* ������ ���̿� ���� ������ ����. 1~1,000,000,000
* �ϼ��� ���� ���� ����. �ϼ��� �������. 1~1,000,000,000

[���]
* ������ ���� ���� ��ġ(���� �ֻ�� 1/���ϴ� D).
* ���ڰ� ��� ���쿡 ���� �ʴ´ٸ� 0 ���
*/