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
			//에라토스테네스의 체
			boolean[] decFlag = new boolean[5000001];//true일 경우 소수 아님 / false일 경우 소수
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
				if(decFlag[d]) {//소수가 아닌 수를 부르게 될 경우 
					//(만약 상대방이 지금까지 말한 소수가 3개 미만이라면 상대방은 1000점을 얻게 된다.)
					if(gList.size() < 3) {
						gScore += 1000;
					}
					else{
						//상대방은 지금까지 상대방이 말한 소수중에서 3번째로 큰 수만큼 점수를 얻게 된다.
						Collections.sort(gList, Collections.reverseOrder());					
						gScore += gList.get(2);
					}
				}
				else if(record.contains(d)) {//한번이라도 등장한 소수를 말할 경우 
					//해당 소수를 말한 팀이 -1000을 얻게 되며 해당 소수는 그 사람이 말한 소수로 기록되지 않는다.
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
						//상대방은 지금까지 상대방이 말한 소수중에서 3번째로 큰 수만큼 점수를 얻게 된다.
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
			if(dScore > gScore) bw.write("소수의 신 갓대웅");
			else if(dScore < gScore) bw.write("소수 마스터 갓규성");
			else bw.write("우열을 가릴 수 없음");
			bw.flush();
			bw.close();
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
