import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class KellyFormula {
    public static void main(String[] args) {
        double winProbability = 0.6;  // 이길 확률
        double winOdds = 2;  // 이길 때의 배당률

        double kellyFraction;
        try {
            kellyFraction = calculateKellyFraction(winProbability, winOdds);
        } catch (IllegalArgumentException e) {
            System.out.println("이길 때의 배당률은 1보다 커야 합니다. 다시 설정해주세요.");
            return;
        }

        double totalCapital = 1000000;  // 초기 자본
        DecimalFormat formatter = new DecimalFormat("#,###");

        int numSimulations = 1000;  // 시뮬레이션 횟수
        int numInvestments = 5;  // 투자 횟수

        List<Double> simulationResults = new ArrayList<>();

        for (int i = 0; i < numSimulations; i++) {
            double currentCapital = totalCapital;
            System.out.println("Simulation " + (i + 1));

            for (int j = 0; j < numInvestments; j++) {
                double adjustedBetAmount = totalCapital * kellyFraction;

                if (Math.random() < winProbability) {
                    currentCapital += adjustedBetAmount * (winOdds - 1);
                } else {
                    currentCapital -= adjustedBetAmount;
                }

                System.out.println("Investment " + (j + 1) + ". Current Asset: " + formatter.format(currentCapital));

                if (j == numInvestments - 1) {
                    System.out.println("\nRemaining Asset (Final): " + formatter.format(currentCapital) + "\n");
                    simulationResults.add(currentCapital);
                }
            }
        }

        double averageCapital = simulationResults.stream().mapToDouble(a -> a).average().orElse(0.0);
        double adjustedBetAmount = totalCapital * kellyFraction;
        System.out.println("Kelly Fraction: " + kellyFraction);
        System.out.println("Investment Amount per Simulation: " + formatter.format(adjustedBetAmount));
        System.out.println("Average Remaining Asset over " + numSimulations + " simulations: " + formatter.format(averageCapital));
    }

    private static double calculateKellyFraction(double winProbability, double winOdds) {
        if (winOdds <= 1) {
            throw new IllegalArgumentException("이길 때의 배당률은 1보다 커야 합니다.");
        }

        return (winProbability * (winOdds - 1) - (1 - winProbability)) / (winOdds - 1);
    }
}
