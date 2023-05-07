package quiz_level_0;

public class 두_수의_나눗셈 {

    //두 수의 나눗셈
    //문제 설명
    //정수 num1과 num2가 매개변수로 주어질 때, num1을 num2로 나눈 값에 1,000을 곱한 후 정수 부분을 return 하도록 solution 함수를 완성해주세요.

    static int solution(int num1, int num2) {
        int ans = num1 * 1000 / num2;
        return ans;
    }
    public static void main(String[] args) {

        assert solution(3, 2) == 1500 : "틀렸어요";
        assert solution(7, 3) == 2333 : "틀렸어요";
        assert solution(1, 16) == 62 : "틀렸어요";
        assert solution(5, 2) == 2500 : "틀렸어요";

    }
}

