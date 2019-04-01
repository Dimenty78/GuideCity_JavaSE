import fasads.CityPoint;
import fasads.ReadBD;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Читаем файл БД, ищем в нем Категории, добавляем пункт Поиск, выводим в консоль Категории и получаем из консоли номер выбранного пункта.

        //ReadBD.mysqlBDStop();
        ReadBD.mysqlBDStart();


        ArrayList<CityPoint> listPoint = ReadBD.getListsPoint();
        ArrayList<String> catrgorySearch = ReadBD.getlistCategorySearch();

        if (catrgorySearch != null) {
            int numberConsolSelect = new Consol().consolSelect(catrgorySearch);

            //____________________________________________________________________Если номер выбранного пункта не равен поисковому
            if (numberConsolSelect != (ReadBD.getlistCategorySearch().size() - 1)) {

                //Выделяем из БД объектов выбранной Категории
                ArrayList<CityPoint> listPointSelect = new ArrayList<CityPoint>();
                ArrayList<String> listPointSelectName = new ArrayList<String>();

                for (int t = 0; t < listPoint.size(); t++) {
                    if (listPoint.get(t).getCategory().contains(ReadBD.getlistCategorySearch().get(numberConsolSelect))) {

                        listPointSelect.add(listPoint.get(t));
                        listPointSelectName.add(listPoint.get(t).getName());
                    }
                }

                //Выводем в консоль список Имен объектов принадлежащих выбранной Категории и получаем из консоли номер выбранного пункта.
                numberConsolSelect = new Consol().consolSelect(listPointSelectName);

                //Вывод на консоль результата
                CityPoint cityPoint = new CityPoint();
                cityPoint.resultConsol(listPointSelect, numberConsolSelect);


                //____________________________________________________________________Если номер выбранного пункта равен поисковому
            } else {

                //Вывод в консоль строки поискового запроса
                System.out.print("\n" + "ВВЕДИТЕ СЛОВО ПОИСКА: ");
                Scanner in = new Scanner(System.in);
                String search = in.next().toLowerCase();

                ArrayList<CityPoint> listPointSearch = new ArrayList<CityPoint>();

                //Поиск в объектах из БД текста содержащую поисковую строку
                for (int t = 0; t < listPoint.size(); t++) {

                    if (
                            listPoint.get(t).getCategory().toLowerCase().contains(search) ||
                                    listPoint.get(t).getName().toLowerCase().contains(search) ||
                                    listPoint.get(t).getResponses().toLowerCase().contains(search) ||
                                    listPoint.get(t).getDescription().toLowerCase().contains(search)
                    ) {
                        listPointSearch.add(listPoint.get(t));
                    }
                }

                //Если найден хоть один объект, то выводим в консоль список Категорий в которых присутствуют объекты содержащие искомую строку и получаем из консоли номер выбранного пункта
                if (listPointSearch.size() > 0) {

                    numberConsolSelect = new Consol().consolSelect(ReadBD.getlistCategory(listPointSearch));

                    ArrayList<CityPoint> listPointSelect = new ArrayList<CityPoint>();
                    ArrayList<String> listPointSelectName = new ArrayList<String>();

                    for (int t = 0; t < listPointSearch.size(); t++) {
                        if (listPointSearch.get(t).getCategory().toLowerCase().contains(ReadBD.getlistCategory(listPointSearch).get(numberConsolSelect).toLowerCase())) {

                            listPointSelect.add(listPointSearch.get(t));
                            listPointSelectName.add(listPointSearch.get(t).getName());
                        }
                    }

                    //Выводем в консоль список Имен объектов принадлежащих выбранной Категории и получаем из консоли номер выбранного пункта.
                    numberConsolSelect = new Consol().consolSelect(listPointSelectName);

                    //Вывод на консоль результата
                    CityPoint cityPoint = new CityPoint();
                    cityPoint.resultConsol(listPointSelect, numberConsolSelect);



                } else {
                    System.out.println("\n" + "Поиск не дал результатов");
                }
            }

        } else {
            System.out.println("\n" + "\n" + "\n" + "!!!!!!!!!!! ФАЙЛ БД НЕ НАЙДЕН !!!!!!!!!!! ");
        }
    }
}