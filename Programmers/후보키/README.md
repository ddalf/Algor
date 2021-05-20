# 후보키

Algorithms: combination, simulation

Date: 2021/05/20

Level: Level2, ○

Link: https://programmers.co.kr/learn/courses/30/lessons/42890

### 문제정리

- 관계 데이터베이스에서 릴레이션(Relation)의 튜플(Tuple)을 유일하게 식별할 수 있는 속성(Attribute) 또는 속성의 집합 중, 다음 두 성질을 만족하는 것을 후보 키(Candidate Key)라고 한다.
    - 유일성(uniqueness) : 릴레이션에 있는 모든 튜플에 대해 유일하게 식별되어야 한다.
    - 최소성(minimality) : 유일성을 가진 키를 구성하는 속성(Attribute) 중 하나라도 제외하는 경우 유일성이 깨지는 것을 의미한다. 즉, 릴레이션의 모든 튜플을 유일하게 식별하는 데 꼭 필요한 속성들로만 구성되어야 한다.
- 학생들의 인적사항이 주어졌을 때, 후보 키의 최대 개수를 구하라.

**[입력]**

- 릴레이션을 나타내는 문자열 배열 relation이 매개변수로 주어짐 때,

**[출력]**

- 릴레이션에서 후보 키의 개수를 return 하도록 solution 함수를 완성하라.

### 문제풀이

- 시뮬레이션, 조합
    - 1~컬럼 길이 개에 대한 각각의 조합을 만듬
    - 조합을 만들 때 이미 만들어진 키가 포함되어 있는지 판단
    - 만들어진 조합으로 유일성 판단
        - Set을 이용하여 조합에 해당하는 문자열을 모두 합쳐 Set에 넣음
        - Set의 크기가 row의 크기와 같다면 유일하다 판단

```java
import java.util.*;
class Solution {
    int row, col;
    boolean[] visit;
    String[][] relation;
    List<List<Integer>> keys;
    int answer;
    public void getUniqueness(){//유일성 판단
        List<Integer> comb = new ArrayList<>();
        for(int i=0; i<col; i++){
            if(visit[i]) comb.add(i);
        }
        Set<String> keySet = new HashSet<>();
        for(int i=0; i<row; i++){
            String key = "";
            for(int j=0; j<comb.size(); j++){
                int idx = comb.get(j);
                key += relation[i][idx];
            }
            keySet.add(key);
        }
        if(keySet.size() == row){
            ++answer;
            keys.add(comb);
        }
    }
    
    public void getCombination(int idx, int cnt, int end){
        //최소성 판단
        for(int i=0; i<keys.size(); i++){
            List<Integer> key = keys.get(i);
            int keyCnt = 0;
            for(int j=0; j<key.size(); j++){
                if(visit[key.get(j)]) ++keyCnt;
            }
            if(key.size() == keyCnt){
                return;
            }
        }
        if(cnt == end) {
            getUniqueness();
            return;
        }
        if(idx == col) return;
        for(int i=idx; i<col; i++){
            if(visit[i]) continue;
            visit[i] = true;
            getCombination(i+1,cnt+1,end);
            visit[i] = false;
        }
    }
    
    public int solution(String[][] relation) {
        row = relation.length;
        col = relation[0].length;
        this.relation = relation;
        keys = new ArrayList<>();
        visit = new boolean[col];
        //1. 1~n개까지 조합을 구함
        for(int i=1; i<=col; i++){
            getCombination(0,0,i);
        }
        return answer;
    }
}
```