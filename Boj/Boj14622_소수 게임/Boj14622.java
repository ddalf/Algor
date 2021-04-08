package boj;
import java.util.*;
import java.io.*;
public class Boj14622 {

	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(System.out));	
	public static int stoi(String s) {
		 return Integer.parseInt(s);
	}
	public static void solve() {
		try {
			//�����佺�׳׽��� ü
			boolean[] decFlag = new boolean[5000001];//true�� ��� �Ҽ� �ƴ� / false�� ��� �Ҽ�
			decFlag[1] = true;
			for(int i=2; i<Math.sqrt(5000000); i++) {
				if(decFlag[i]) continue;
				for(int j=i*2; j<=5000000; j+=i) decFlag[j] = true; 
			}
			int n = stoi(br.readLine());
			List<Integer> dList = new LinkedList<>();
			List<Integer> gList = new LinkedList<>();
			Set<Integer> record = new HashSet<>();
			StringTokenizer st;
			long dScore = 0;
			long gScore = 0;
			for(int i=0; i<n; i++) {
				st = new StringTokenizer(br.readLine());
				int d = stoi(st.nextToken());
				int g = stoi(st.nextToken());
				if(decFlag[d]) {//�Ҽ��� �ƴ� ���� �θ��� �� ��� 
					//(���� ������ ���ݱ��� ���� �Ҽ��� 3�� �̸��̶�� ������ 1000���� ��� �ȴ�.)
					if(gList.size() < 3) {
						gScore += 1000;
					}
					else{
						//������ ���ݱ��� ������ ���� �Ҽ��߿��� 3��°�� ū ����ŭ ������ ��� �ȴ�.
						Collections.sort(gList, Collections.reverseOrder());					
						gScore += gList.get(2);
					}
				}
				else if(record.contains(d)) {//�ѹ��̶� ������ �Ҽ��� ���� ��� 
					//�ش� �Ҽ��� ���� ���� -1000�� ��� �Ǹ� �ش� �Ҽ��� �� ����� ���� �Ҽ��� ��ϵ��� �ʴ´�.
					dScore -= 1000;
				}
				else {
					dList.add(d);
					record.add(d);
					if(dList.size() > 3) {
						Collections.sort(dList, Collections.reverseOrder());
						dList.remove(dList.size()-1);
					}
				}
				if(decFlag[g]) {
					if(dList.size() < 3) {
						dScore += 1000;
					}
					else{
						//������ ���ݱ��� ������ ���� �Ҽ��߿��� 3��°�� ū ����ŭ ������ ��� �ȴ�.
						Collections.sort(dList, Collections.reverseOrder());
						dScore += dList.get(2);
					}
					
				}
				else if(record.contains(g)) {
					gScore -= 1000;
				}
				else {
					gList.add(g);
					record.add(g);
					if(gList.size() > 3) {
						Collections.sort(gList, Collections.reverseOrder());
						gList.remove(gList.size()-1);
					}
				}
			}
			if(dScore > gScore) bw.write("�Ҽ��� �� �����");
			else if(dScore < gScore) bw.write("�Ҽ� ������ ���Լ�");
			else bw.write("�쿭�� ���� �� ����");
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
