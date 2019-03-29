package fasads;

import java.util.ArrayList;

public class ReadBD {

    private static ArrayList<CityPoint> listsPoi = new ArrayList<CityPoint>();
    private static ArrayList<String> listCategory = new ArrayList<String>();


    //Создаем список объектов из БД
    private static ArrayList<CityPoint> rearToArray() {

        listsPoi.clear();
        String bdText = new FileRecRead().ReadFile("BD_text.bd");

        if (bdText !=null) {
            String bdMass[] = bdText.split("\n");

            for (int t = 1; t < bdMass.length; t++) {
                listsPoi.add(new CityPoint(t, bdMass[t].split(";;;;")[0], bdMass[t].split(";;;;")[1], bdMass[t].split(";;;;")[2], Integer.parseInt(bdMass[t].split(";;;;")[3]), bdMass[t].split(";;;;")[4], bdMass[t].split(";;;;")[5], bdMass[t].split(";;;;")[6], bdMass[t].split(";;;;")[7], bdMass[t].split(";;;;")[8]));
            }
            return listsPoi;
        }else {
            return null;
        }
    }

    //Создаем список Категорий с добавлением позиции ПОИСК
    private static ArrayList<String> listCategorySearch() {
        listCategory.clear();
        listsPoi = rearToArray();
        if (listsPoi !=null) {
            for (int t = 0; t < listsPoi.size(); t++) {
                ;
                if (listCategory.contains(listsPoi.get(t).getCategory())) {
                } else {
                    listCategory.add(listsPoi.get(t).getCategory());
                }
            }
            listCategory.add(">>>ОБЩИЙ ПОИСК");
            return listCategory;
        }else {
            return null;
        }
    }

    //Создаем список Категорий без добавления позиции ПОИСК
    private static ArrayList<String> listCategory(ArrayList<CityPoint> listsPoi) {
        listCategory.clear();
        for (int t = 0; t < listsPoi.size(); t++) { ;
            if (listCategory.contains(listsPoi.get(t).getCategory())) {
            } else {
                listCategory.add(listsPoi.get(t).getCategory());
            }
        }
        return listCategory;
    }

    public static ArrayList<CityPoint> getListsPoi() {
        ArrayList<CityPoint> listsPoi = rearToArray();
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
