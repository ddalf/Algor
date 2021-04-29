# 최댓값과 최솟값

Do: Yes

Level: 2

분류: 연습문제

푼 날짜: 2021/04/29

- 값을 범위 주어져있지 않음 ⇒ 가장 큰 정수 자료형 long으로 생각하고 풀었따.

```java
import java.util.*;
class Solution {
    public String solution(String s) {
        String answer = "";
        List<Long> list = new ArrayList<>();
        for(int i=0; i<s.length(); i++){
            char c = s.charAt(i);
            if(c == '-'){
                long num = s.charAt(++i)-'0';
                while(i+1 < s.length()){
                    if(s.charAt(i+1) == ' ') {
                        break;
                    }
                    num = num * 10 + (s.charAt(++i)-'0');
                }
                list.add(-num);
            }
            else if(c != ' '){
                long num = c-'0';
                while(i+1 < s.length()){
                    if(s.charAt(i+1) == ' ') {
                        break;
                    }
                    num = num * 10 + (s.charAt(++i)-'0');
                }
                list.add(num);
            }
        }
        Collections.sort(list);
        long minV = list.get(0);
        long maxV = list.get(list.size()-1);
        answer = minV +" "+maxV;
        return answer;
    }
}
```