package quiz_level_0;

public class 짝수의_합 {
    //정수 n이 주어질 때, n이하의 짝수를 모두 더한 값을 return 하도록 solution 함수를 작성해주세요.

    //재귀
    static int solution(int n) {
        if (n < 2)
            return 0;

        return n % 2 == 0 ? n + solution(n - 2) : solution(n - 1);
    }

    //반복
    static int solution2(int n) {

        int result = 0;
        for (int i = 2; i <= n; i += 2) {
            result += i;
        }
        return result;
    }

    //공식
    static int solution3(int n) {
        int result = 0;
        if(n % 2 == 0 && n != 2) {
            result = (2 + n) * n / 4;
        } else if (n % 2 != 0) {
            result = (2 + n-1) * (n - 1) / 4;
        } else {
            result= 2;
        }
        return result;

//        return n/2 * (n/2 + 1);

    }


    public static void main(String[] args) {

        assert solution3(1) == 0 : "틀렸어요";
        assert solution3(2) == 2 : "틀렸어요";
        assert solution3(3) == 2 : "틀렸어요";
        assert solution3(4) == 6 : "틀렸어요";
        assert solution3(5) == 6 : "틀렸어요";
        assert solution3(6) == 12 : "틀렸어요";
        assert solution3(7) == 12 : "틀렸어요";
        assert solution3(8) == 20 : "틀렸어요";
        assert solution3(10) == 30 : "틀렸어요";
        assert solution3(12) == 42 : "틀렸어요";
        assert solution3(14) == 56 : "틀렸어요";

    }
}
