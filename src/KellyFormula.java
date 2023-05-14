import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class KellyFormula {
    public static void main(String[] args) {
        double winProbability = 0.65;  // 이길 확률
        double winOdds = 1.01;  // 이길 때의 배당률
        double losePercentage = 0.01;  // 질 때 잃는 금액의 비율

        double kellyFraction;
        try {
            kellyFraction = calculateKellyFraction(winProbability, winOdds, losePercentage)*100; //100이 정상인듯 1%잃으니 *100보정
        } catch (IllegalArgumentException e) {
            System.out.println("이길 때의 배당률은 1보다 커야 합니다. 다시 설정해주세요.");
            return;
        }

        double totalCapital = 1000000;  // 초기 자본
        DecimalFormat formatter = new DecimalFormat("#,###");

        int numSimulations = 10000;  // 시뮬레이션 횟수
        int numInvestments = 20;  // 투자 횟수

        List<Double> simulationResults = new ArrayList<>();
        List<Double> profits = new ArrayList<>(); // 투자에서의 이익을 추적하기 위한 List

        for (int i = 0; i < numSimulations; i++) {
            double currentCapital = totalCapital;
            System.out.println("Simulation " + (i + 1));

            for (int j = 0; j < numInvestments; j++) {
                double initialCapital = currentCapital;

                if (Math.random() < winProbability) {
                    currentCapital += totalCapital * kellyFraction * (winOdds - 1);
                } else {
                    currentCapital -= totalCapital * kellyFraction * losePercentage; // 질 때 투자한 금액의 비율만큼 잃음
                }

                double profit = currentCapital - initialCapital;
                profits.add(profit); // 각 투자에서의 이익을 profits 리스트에 추가

                System.out.println("Investment " + (j + 1) + ". Current Asset: " + formatter.format(currentCapital));

                if (j == numInvestments - 1) {
                    System.out.println("\nRemaining Asset (Final): " + formatter.format(currentCapital) + "\n");
                    simulationResults.add(currentCapital);
                }
            }
        }

        double averageCapital = simulationResults.stream().mapToDouble(a -> a).average().orElse(0.0);
        double averageProfit = profits.stream().mapToDouble(a -> a).average().orElse(0.0); // 평균 이익을 계산
        double adjustedBetAmount = totalCapital * kellyFraction;
        System.out.println("Kelly Fraction: " + kellyFraction);
        System.out.println("Investment Amount per Simulation: " + formatter.format(adjustedBetAmount));
        System.out.println("Average Remaining Asset over " + numSimulations + " simulations: " + formatter.format(averageCapital));
        System.out.println("Average Profit per Investment over " + (numSimulations * numInvestments) + " investments: " + formatter.format(averageProfit)); // 평균 이익 출력
    }

    private static double calculateKellyFraction(double winProbability, double winOdds, double losePercentage) {
        if (winOdds <= 1) {
            throw new IllegalArgumentException("이길 때의 배당률은 1보다 커야 합니다.");
        }

        if (losePercentage > 1 || losePercentage < 0) {
            throw new IllegalArgumentException("질 때 잃는 금액의 비율은 0과 1 사이의 값이어야 합니다.");
        }

        return (winProbability * (winOdds - 1) - (1 - winProbability) * losePercentage) / ((winOdds - 1) + losePercentage);
    }
}






