package boj;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.util.*;

public class Boj6137 {
	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	static int n;
	static List<Character> chars;
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	public static void solve() {
		try {
			//n���� ���ڷ� �̷���� ���ڿ� S
			//���ڿ��� �� ���ڵ�� ���ο� ���ڿ� T ���������
			//S�� T����� ��Ģ
				//S�� ���� ���� ���� �ϳ��� ���ڿ� T�� �������� �߰�
				//S�� ���� ���� ���� �ϳ��� ���ڿ� T�� �������� �߰�
			//�� ��Ģ���� ������� ���ڿ� T�� �� ���������� ���� ���� ���ڿ�
			n = stoi(br.readLine());
			chars = new LinkedList<>();
			for(int i=0; i<n; i++) {
				chars.add(br.readLine().toCharArray()[0]);
			}
			StringBuffer sf = new StringBuffer("");
			int front = 0, back = n-1, idx = 0;
			
			while(front <= back) {
				++idx;
				char frontChar = chars.get(front);
				char backChar = chars.get(back);
				
				if(backChar < frontChar) {
					sf.append(backChar);
					--back;
				}
				else if(frontChar < backChar) {
					sf.append(frontChar);
					++front;
				}
				else {//frontChar == backChar
					int nextFront = front + 1;
					int beforeBack = back - 1;
					while(true) {
						if(nextFront > beforeBack || chars.get(nextFront) < chars.get(beforeBack)) {
							sf.append(frontChar);
							++front;
							break;
						}
						else if(chars.get(beforeBack) < chars.get(nextFront)){
							sf.append(backChar);
							--back;
							break;
						}
						++nextFront;
						--beforeBack;
					}
				}
				if(idx % 80 == 0) sf.append("\n");
			}
			bw.write(sf.toString());
			//��� : ������� ���������� ���� ���� ���ڿ���80���ڸ��� ���� ���ڸ� ����ؾ� �Ѵ�.
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
