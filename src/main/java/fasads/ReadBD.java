package fasads;

import java.io.*;
import java.util.ArrayList;

public class ReadBD {

    private static ArrayList<CityPoint> listPoint = new ArrayList<CityPoint>();
    private static ArrayList<String> listCategory = new ArrayList<String>();


    //Создаем список объектов из БД
    private static ArrayList<CityPoint> readToArray() {

        listPoint.clear();
        String bdText = "";
        try {
            FileInputStream fileStream = new FileInputStream("BD_text.bd");
            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(fileStream, "UTF-8"));
            String strLine;
            while ((strLine = bufferedReader.readLine()) != null) {
                bdText = bdText.concat(strLine).concat(System.lineSeparator());
            }
            //Проверка БД на корректность и создание объектов
            if (bdText.split("\n").length - 1 > 0) {
                String bdMass[] = bdText.split("\n");

                for (int t = 1; t < bdMass.length; t++) {

                    if (bdMass[t].split(";;;;").length - 1 == 9) {
                        try {
                            Integer.parseInt(bdMass[t].split(";;;;")[3]);
                            listPoint.add(new CityPoint(bdMass[t].split(";;;;")[0], bdMass[t].split(";;;;")[2], Integer.parseInt(bdMass[t].split(";;;;")[3]), bdMass[t].split(";;;;")[4], bdMass[t].split(";;;;")[5], bdMass[t].split(";;;;")[6], bdMass[t].split(";;;;")[7], bdMass[t].split(";;;;")[8]));
                        } catch (NumberFormatException e) {
                        }
                    }
                }
                return listPoint;
            } else {
                return null;
            }
        } catch (IOException e) {
            return null;
        }
    }

    //Создаем список Категорий с добавлением позиции ПОИСК
    private static ArrayList<String> listCategorySearch() {
        listCategory.clear();
        listPoint = readToArray();
        if (listPoint != null) {
            for (int t = 0; t < listPoint.size(); t++) {
                ;
                if (listCategory.contains(listPoint.get(t).getCategory())) {
                } else {
                    listCategory.add(listPoint.get(t).getCategory());
                }
            }
            listCategory.add(">>>ОБЩИЙ ПОИСК");
            return listCategory;
        } else {
            return null;
        }
    }

    //Создаем список Категорий без добавления позиции ПОИСК
    private static ArrayList<String> listCategory(ArrayList<CityPoint> listsPoint) {
        listCategory.clear();
        for (int t = 0; t < listsPoint.size(); t++) {
            ;
            if (listCategory.contains(listsPoint.get(t).getCategory())) {
            } else {
                listCategory.add(listsPoint.get(t).getCategory());
            }
        }
        return listCategory;
    }

    public static ArrayList<CityPoint> getListsPoit() {
        ArrayList<CityPoint> listsPoi = readToArray();
        return listsPoi;
    }

    public static ArrayList<String> getlistCategorySearch() {
        ArrayList<String> listCategory = listCategorySearch();
        return listCategory;
    }

    public static ArrayList<String> getlistCategory(ArrayList<CityPoint> listsPoi) {
        ArrayList<String> listCategory = listCategory(listsPoi);
        return listCategory;
    }
}
