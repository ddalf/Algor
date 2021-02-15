package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2022 {
	static double x;
	static double y;
	static double c;
	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));

	public static double stod(String s) {
		return Double.parseDouble(s);
	}	
	
	public static boolean isPossibleWidth(double width) {
		double h1 = Math.sqrt(x*x-width*width);
		double h2 = Math.sqrt(y*y-width*width);
		
		return c>=h1*h2/(h1+h2);
	}
	public static void solve() {
		try {
			StringTokenizer st;
			st = new StringTokenizer(br.readLine());
			x = stod(st.nextToken());
			y = stod(st.nextToken());
			c = stod(st.nextToken());
			
			double left=0, right = Math.min(x, y), mid;
			while(left+0.000001 <= right) {
				mid = (left + right) / 2.0;
				if(isPossibleWidth(mid)) right = mid;
				else left = mid;
			}
			bw.write(String.format("%.3f", right));
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
���� ���� ���� ���� ���� ��.
x ��ٸ� : ������ ���� �Ʒ��� ��ħ
y ��ٸ� : ���� ���� �Ʒ��� ��ħ
c : ������

�Է�
x,y,c : �Ҽ��� ����° �ڸ�

���
�� ���� ���� �ʺ�.���� 10^-3

����Ǯ��
1. �̺�Ž�� -> ������ ?(=width)�� ����
	left = ������ �ּ� ����
	right = ������ �ִ� ����
	mid = �߰���. 
	c = h1h2/h1+h2 ���� ��
2.	* c >= h1*h2/h1+h2 �� �� : h1*h2/h1+h2�� ũ�� Ŀ���� �� -> w�� ũ�� �ٿ��� �� => right = mid; 
	* c < h1*h2/h1+h2  �� �� : h1*h2/h1+h2�� ũ�� �۾����� �� -> w�� ũ�� Ŀ���� �� => left = mid;
*/
