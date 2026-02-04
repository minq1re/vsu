import java.util.Locale;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Locale.setDefault(Locale.ROOT);
        Scanner sc = new Scanner(System.in);
        System.out.println("Введите сумму, запрашиваемую в кредит: ");
        double summa = sc.nextDouble();

        System.out.println("Введите срок взятия кредита в месяцах: ");
        int period = sc.nextInt();

        System.out.println("Введите процентную ставку в %: ");
        double rate = sc.nextDouble();

        System.out.println("Введите способ погашения кредита в виде diff или annuity");
        String pay = sc.next();

        System.out.println("Введите до какого по счету месяца рассчитать кредит: ");
        int n = sc.nextInt();

        Credit credit = new Credit(summa, period, rate, pay, n);
        System.out.println("Сумма платежей до " + n + "-ого месяца: ");
        System.out.println(credit.countToN());
        System.out.println("Сумма платежей по кредиту: ");
        System.out.println(credit.count());
    }
}
