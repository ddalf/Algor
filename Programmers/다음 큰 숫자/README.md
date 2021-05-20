# 다음 큰 숫자

Algorithms: simulation

Date: 2021/05/20

Level: Level2, ○

Link: https://programmers.co.kr/learn/courses/30/lessons/12911

### 문제정리

자연수 n이 주어졌을 때, n의 다음 큰 숫자는 다음과 같이 정의 합니다.

- 조건 1. n의 다음 큰 숫자는 n보다 큰 자연수 입니다.
- 조건 2. n의 다음 큰 숫자와 n은 2진수로 변환했을 때 1의 갯수가 같습니다.
- 조건 3. n의 다음 큰 숫자는 조건 1, 2를 만족하는 수 중 가장 작은 수 입니다.

예를 들어서 78(1001110)의 다음 큰 숫자는 83(1010011)입니다.

자연수 n이 매개변수로 주어질 때, n의 다음 큰 숫자를 return 하는 solution 함수를 완성해주세요.

**[입력]**

- n : 1,000,000 이하 자연수

**[출력]**

n의 다음 큰 숫자를 return 

### 문제풀이

- 시뮬레이션
    - n이 0이 될때까지 2로 나눠서 나눈 나머지가 1인 개수 = 비트에서 1의개수
    - n의 값을 증가시키며 getOne()함수로 비트에서 1의 개수를 찾고 같은 개수 일 때 반복문 종료

```java
class Solution {
    public int getOne(int n){
        int cnt = 0;
        int nTmp = n;
        while(nTmp > 0){
            if(nTmp % 2 == 1){
                ++cnt;
            }
            nTmp/=2;
        }
        return cnt;
    }
    
    public int solution(int n) {
        int answer = 0;
        int nCnt = getOne(n);
        answer = n+1;
        while(true){
            int cnt = getOne(answer);
            if(nCnt == cnt){
                break;
            }
            ++answer;
        }
        return answer;
    }
}
```