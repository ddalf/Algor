package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.Arrays;
import java.util.StringTokenizer;

public class Boj2352 {
	public static int[] ports;
	private static int stoi(String str) {
		return Integer.parseInt(str);
	}
	
	public void solve() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			StringTokenizer st;
			int n = stoi(br.readLine());
			ports = new int[n];
			st = new StringTokenizer(br.readLine());
			for(int i=0; i<n; i++) {
				int p = stoi(st.nextToken());
				ports[i] = p;
			}
			int[] lis = new int[n];
			lis[0] = ports[0];
			int idx = 1;
			for(int i=1; i<n; i++) {
				if(lis[idx-1] < ports[i]) lis[idx++] = ports[i];
				else if(lis[0] > ports[i]) lis[0] = ports[i];
				else {
					int tmp = Arrays.binarySearch(lis, 0, idx, ports[i]);
					lis[tmp < 0 ? (-tmp-1) : tmp] = ports[i];
				}
			}
			bw.write(idx+"\n");
			bw.flush();
			bw.close();
			br.close();
		}
		catch(IOException e){
			e.printStackTrace();
		}
	}
}
/*
	�ݵ�ü ����. n���� ��Ʈ - �ٸ� n�� ��Ʈ�� ����
	n���� ��Ʈ�� �ٸ� n���� ��Ʈ�� ��� ����Ǿ�� �ϴ��� �־��� 
	-> ���ἱ�� ���� ������ �ʵ��� �ϸ鼭 ������ �� �ִ� �ִ��� ����
*/
