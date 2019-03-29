import java.util.ArrayList;
import java.util.Scanner;

public class Consol {

    private static int num;
    private static String numString;

    public int consolSelect (ArrayList<String> list)    {

        ArrayList<String> ListCategory = list;
        int categorySize = ListCategory.size();
        for (int t = 0; t < ListCategory.size(); t++) {
            System.out.println(t + " - " + ListCategory.get(t));
        }
        int w = 1;
        while (w>0){
            System.out.println("");
            System.out.print("Введи номер: ");

            Scanner in = new Scanner(System.in);
            numString = in.next();
            if (isDigit(numString) == true){

                num = Integer.parseInt(numString);
                if ( num < categorySize & num >=0){
                    w = 0;
                }else {
                    System.out.println("ВВЕДЕНО НЕКОРРЕКТНОЕ ЧИСЛО. ПОВТОРИ");
                }

            }else {
                System.out.println("ВЫ ВВЕЛИ НЕ ЧИСЛО. ПОВТОРИ");
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
