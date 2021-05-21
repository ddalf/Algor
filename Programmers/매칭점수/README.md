# 매칭점수

Algorithms: simulation

Date: 2021/05/21

Level: L3, ○

Link: https://programmers.co.kr/learn/courses/30/lessons/42893

### 문제정리

웹페이지의 매칭점수는 다음과 같이 계산할 수 있다.

- 기본 점수는 각 웹페이지에서 hi가 등장한 횟수이다.
    - A,B,C 웹페이지의 기본점수는 각각 1점, 4점, 9점이다.
- 외부 링크수는 다른 웹페이지로 링크가 걸린 개수이다.
    - A,B,C 웹페이지의 외부 링크 수는 각각 1점, 2점, 3점이다.
- A 웹페이지로 링크가 걸린 페이지는 B와 C가 있다.
    - A 웹페이지의 링크점수는 B의 링크점수 2점(4 ÷ 2)과 C의 링크점수 3점(9 ÷ 3)을 더한 5점이 된다.
- 그러므로, A 웹페이지의 매칭점수는 기본점수 1점 + 링크점수 5점 = 6점이 된다.

**[입력]**

- 검색어 word와 웹페이지의 HTML 목록인 pages가 주어짐

**[출력]**

- 매칭점수가 가장 높은 웹페이지의 index를 구하라. 만약 그런 웹페이지가 여러 개라면 그중 번호가 가장 작은 것을 구하라.

### 문제풀이

- 시뮬레이션

```java
import java.util.*;

class Solution{	
	class Page{
		int baseScore;
		int exLinkSize;
		double linkScore;
		List<String> exLinkList;
		
		Page(){
			this.baseScore = 0;
			this.linkScore = 0;
			this.exLinkSize = 0;
			exLinkList = new LinkedList<>();
		}
	}
	
	public int solution(String word, String[] pages){
        int answer = 0;
        int n = pages.length;
        Map<String, Integer> pageMap = new HashMap<>();
        Page[] pageArr = new Page[n];
        int mapIdx = 0;
        word = word.toLowerCase();
        for(String page : pages) {
        	Page p = new Page();
        	page = page.toLowerCase();
        	
            String nameHeader = "<meta property=\"og:url\" content=";
        	int nameIdx = page.indexOf(nameHeader);//헌재 웹 페이지의 이름(링크)
        	String name = page.substring(nameIdx+nameHeader.length()).split("\"")[0].trim();
        	
        	int fromIdx = -1;
        	int lineCnt = 0;
            String linkHeader = "<a href=\"";
        	while((fromIdx = page.indexOf(linkHeader, fromIdx+1)) >= 0) {//외부 링크가 존재할 경우(indexOf 발견하지 못할 경우 : -1 반환)
        		String exPage = page.substring(fromIdx+linkHeader.length()).split("\"")[0].trim();
        		p.exLinkList.add(exPage);
        		++p.exLinkSize;
        	}
        	page = page.replaceAll("[^a-z]", " ");//알파벳 소문자를 제외한 모든 문자 " "로 변경
        	String[] pageWords = page.split(" ");
        	for(String pw : pageWords) {
        		if(pw.trim().equals(word)) {
        			++p.baseScore;
        		}
        	}
        	
        	pageMap.put(name, mapIdx);
        	pageArr[mapIdx++] = p;
        }
        

        for(Page p : pageArr) {//링크 점수 계산 : 모든 페이지의 정보 입력된 후에 계산한다. 
        	for(String exLink : p.exLinkList) {
        		if(pageMap.containsKey(exLink)) {//Map에 현재 페이지가 가진 외부 링크 존재 하면
            		Page exPage = pageArr[pageMap.get(exLink)];//Map에서 외부 링크 이름으로 index값 가져와서 외부 페이지 객체 가져옴 
            		exPage.linkScore += (double)p.baseScore / p.exLinkSize;//링크 점수 계산
        		}
        	}
        }
        
        answer = -1;
        double ansScore = -1;
        for(int i=0; i<n; i++) {
        	Page p = pageArr[i];
        	if(p.baseScore + p.linkScore > ansScore) {
        		ansScore = p.baseScore + p.linkScore;
        		answer = i;
        	}
        }
        return answer;
    }
}
```