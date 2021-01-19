package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.StringTokenizer;

public class Boj12904 {	
	private static String reverseString(String s) {
		return (new StringBuffer(s)).reverse().toString();
	}
	public void solve() {
		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));
			
			String s = br.readLine();
			String t = br.readLine();
		
			int idx = t.length()-1;
			String tSub = t;
			boolean rs = false;
			while(idx >= 0) {
				char ch = tSub.charAt(idx);
				if(ch == 'A') {
					tSub = tSub.substring(0, idx--);
				}
				else {
					tSub = reverseString(tSub.substring(0, idx--));
					
				}
				if(tSub.length() == s.length()) {
					rs = tSub.equals(s);
					break;
				}
			}
			if(rs) bw.write("1"); 
			else bw.write("0");
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
 	[��������]
 	* A�� B�θ� �̷���� ���� �ܾ�
 	* �� ���ڿ� S�� T �־��� -> S�� T�� �ٲ�
 	* �� ���� ���길 ����
 		1. ���ڿ� �ڿ� A �߰�
 		2. ���ڿ� ������ B �߰�
 	* S�� T�� ���� �� �ִ��� ������
 	
 	 �Է�
 	 S : ���ڿ� s 1~999
 	 T : ���ڿ� t 2~1000

 	���
 	S�� T�� �ٲ� �� ���� : 1 / ���� : 0
 
 	[����Ǯ��]
	�׸���
	* T�� �� �ڿ� �� �� A or B
	* -> A�� ��� : �� ���� ������ ���ڿ� �ڸ�
	* -> B�� ��� : �� ���� ������ ���ڿ� �߶� ������
	* �ڸ� ���ڿ��� ���̰� S�� ������ ��� ���� ������ �ٸ��� �� -> ������ 1 / ������ 0 ���
*/