import java.util.ArrayList;
import java.util.Scanner;

public class Consol {

    private static int num;
    private static String numString;

    public int consolSelect(ArrayList<String> list) {

        int categorySize = list.size();
        for (int t = 0; t < list.size(); t++) {
            System.out.println(t + " - " + list.get(t));
        }
        int w = 1;
        while (w > 0) {
            System.out.println("");
            System.out.print("Введите номер: ");

            Scanner in = new Scanner(System.in);
            numString = in.next();
            if (isDigit(numString) == true) {

                num = Integer.parseInt(numString);
                if (num < categorySize & num >= 0) {
                    w = 0;
                } else {
                    System.out.println("ВВЕДЕНО НЕКОРРЕКТНОЕ ЧИСЛО. ПОВТОРИТЕ");
                }
            } else {
                System.out.println("ВЫ ВВЕЛИ НЕ ЧИСЛО. ПОВТОРИТЕ");
            }
        }
        return num;
    }

    private static boolean isDigit(String s) throws NumberFormatException {
        try {
            Integer.parseInt(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}
