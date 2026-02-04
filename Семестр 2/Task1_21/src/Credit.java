import java.math.BigDecimal;
import java.math.RoundingMode;

public class Credit {
    private double summa;
    private int period;
    private double rate;
    private String pay;
    private int n;

    public Credit(double summa, int period, double rate, String pay, int n) {
        this.summa = summa;
        this.period = period;
        this.rate = rate;
        this.pay = pay;
        this.n = n;
    }

    private double annuityPay(double summa, int period, double rate) {
        double monthRate = rate / 100 / 12;
        double ann = summa * ((monthRate * Math.pow((1 + monthRate), 12)) / (Math.pow((1 + monthRate), 12) - 1));

        BigDecimal resultAnn = new BigDecimal(ann);
        resultAnn = resultAnn.setScale(2, RoundingMode.HALF_UP);
        double result = resultAnn.doubleValue();

        return result * period;
    }

    private double diffPay(double summa, int period, double rate) {
        double result = 0;
        double dif = 0;
        double monthRate = rate / 12 / 100;

        double persents = 0;
        double remainSumma = summa;
        double mainDebt = summa / period;

        for (int i = 0; i < period; i++){
            persents = remainSumma * monthRate;
            remainSumma -= mainDebt;
            remainSumma = Math.abs(remainSumma);
            dif = (persents + mainDebt);
            result += dif;
        }
        BigDecimal resultDif = new BigDecimal(result);
        resultDif = resultDif.setScale(2, RoundingMode.HALF_UP);

        return resultDif.doubleValue();
    }
    public double countToN() {
        return count(n);
    }

    public double count() {
        return count(period);
    }

    private double count(int countOfMonth) {
        if (pay.equals("annuity")) {
            return annuityPay(summa, countOfMonth, rate);
        }
        else if (pay.equals("diff")) {
            return diffPay(summa, countOfMonth, rate);
        }
        return 0;
    }
}
