package quiz_level_0;

import java.util.Arrays;

public class 정수를_나선형으로_배치하기 {

    //양의 정수 n이 매개변수로 주어집니다. n × n 배열에 1부터 n2 까지 정수를 인덱스 [0][0]부터
    //시계방향 나선형으로 배치한 이차원 배열을 return 하는 solution 함수를 작성해 주세요.

    class Solution {
        public static int[][] turnLeftArr(int[][] arr, int n) {
            int[][] result = new int[n][n];
            int m = n - 1;
            for (int i = 0; i < n; i++) {
                for (int j = 0; j < n; j++) {
                    result[i][j] = arr[j][m];
                }
                --m;
            }
            return result;
        }

        public static int[][] solution(int n) {
            int[][] result = new int[n][n];
            int assistNum = 0;
            int loopCount = 0;
            for (int z = 0; z < n / 2; z++) {
                int minNum = 1;
                if (n > 2 && z != 0) {
                    minNum = (n) * (n) - (n - assistNum) * (n - assistNum) + 1;
                }
                int realMin = minNum;
                int[] allNumber = new int[(n - assistNum) * (n - assistNum)];
                for (int k = 0; k < allNumber.length; k++) {
                    allNumber[k] = minNum;
                    ++minNum;
                }
                if (allNumber.length != 4) {
                    int allNumberIndex = 0;
                    for (int i = 0; i < 4; i++) {
                        for (int j = 0; j < n - assistNum; j++) {
                            result[loopCount][j + loopCount] = allNumber[allNumberIndex];
                            ++allNumberIndex;
                        }
                        --allNumberIndex;
                        result = turnLeftArr(result, n);
                    }
                    result[loopCount][loopCount] = realMin;
                } else {
                    int what = n/2-1;
                    result[what][what] = allNumber[0];
                    result[what][what+1] = allNumber[1];
                    result = turnLeftArr(result, n);
                    result = turnLeftArr(result, n);
                    result[what][what] = allNumber[2];
                    result[what][what+1] = allNumber[3];
                    result = turnLeftArr(result, n);
                    result = turnLeftArr(result, n);
                }
                assistNum += 2;
                ++loopCount;
            }
            if (n % 2 == 1)
                result[n / 2][n / 2] = n * n;
            return result;
        }
    }

    public static void main(String[] args) {

        int[][] result = Solution.solution(10);
        System.out.println(Arrays.deepToString(result));
    }
}
