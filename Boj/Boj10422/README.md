10422_괄호
========
**문제정리**

- 괄호 문자열 : (랑 )로만 이루어진 문자열
- 열리고 닫힌 쌍 제대로 이루어지만 올바른 괄호 문자열. 1,000,000,007로 나눈 나머지

**문제풀이**

- 카탈란 수 문제
- 앞에 괄호 고정하고 그 안에 들어갈 괄호 개수 * 나머지 조합해야 할 괄호 개수로 구함

    EX. 괄호 6개인 경우

    ( 4 ) * 0 개로 만들 수 있는 경우 → dp[0] * dp[4]

    ( 2 ) * 2 개로 만들 수 있는 경우 → dp[2] * dp[2]

    ( 0 ) * 4 개로 만들 수 있는 경우 → dp[4] * dp[0]


> ref. [https://velog.io/@undefcat/백준-10422-괄호](https://velog.io/@undefcat/%EB%B0%B1%EC%A4%80-10422-%EA%B4%84%ED%98%B8)