import fasads.CityPoint;
import fasads.ReadBD;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        //Читаем файл БД, ищем в нем Категории, добавляем пункт Поиск, выводим в консоль Категории и получаем из консоли номер выбранного пункта.
        ArrayList<CityPoint> listPoi = ReadBD.getListsPoi();
        ArrayList<String> catrgorySearch = ReadBD.getlistCategorySearch();

        if (catrgorySearch != null){
            int nnn = new Consol().consolSelect(catrgorySearch);

        //____________________________________________________________________Если номер выбранного пункта не равен поисковому
        if (nnn != (ReadBD.getlistCategorySearch().size() - 1)) {


            //Выделяем из БД объектов выбранной Категории
            ArrayList<CityPoint> listPoiSelect = new ArrayList<CityPoint>();
            ArrayList<String> listPoiSelectName = new ArrayList<String>();

            for (int t = 0; t < listPoi.size(); t++) {
                if (listPoi.get(t).getCategory().contains(ReadBD.getlistCategorySearch().get(nnn))) {

                    listPoiSelect.add(listPoi.get(t));
                    listPoiSelectName.add(listPoi.get(t).getName());
                }
            }

            //Выводем в консоль список Имен объектов принадлежащих выбранной Категории и получаем из консоли номер выбранного пункта.
            nnn = new Consol().consolSelect(listPoiSelectName);

            //Выводем в консоль Характеристики выбранного объекта.
            System.out.println("\n" + "\n" + "-----------------------------------------------------------------");
            System.out.println("Название: " + listPoiSelect.get(nnn).getName());
            System.out.println("Рейтинг: " + listPoiSelect.get(nnn).getRating());
            System.out.println("Адрес: " + listPoiSelect.get(nnn).getAdres());
            System.out.println("Телефон: " + listPoiSelect.get(nnn).getTelefon());
            System.out.println("Время работы: c " + listPoiSelect.get(nnn).getWorktim().replace("_", " по "));
            System.out.println("Описание: " + listPoiSelect.get(nnn).getDescription() + "\n");
            System.out.println("Отзывы: ");

            String bdMass[] = listPoiSelect.get(nnn).getResponses().split("::::");

            for (int t = 0; t < bdMass.length; t++) {
                System.out.println(bdMass[t]);
            }
            System.out.println("-----------------------------------------------------------------");


            //____________________________________________________________________Если номер выбранного пункта равен поисковому
        } else {

            //Вывод в консоль строки поискового запроса
            System.out.print("\n" + "ВВЕДИ СЛОВА ПОИСКА: ");

            Scanner in = new Scanner(System.in);
            String num = in.next().toLowerCase();

            ArrayList<CityPoint> listPoiSearch = new ArrayList<CityPoint>();


            //Поиск в объектах из БД текста содержащую поисковую строку
            for (int t = 0; t < listPoi.size(); t++) {

                if (
                        listPoi.get(t).getCategory().toLowerCase().contains(num) ||
                                listPoi.get(t).getName().toLowerCase().contains(num) ||
                                listPoi.get(t).getResponses().toLowerCase().contains(num) ||
                                listPoi.get(t).getDescription().toLowerCase().contains(num)
                ) {

                    listPoiSearch.add(listPoi.get(t));
                }
            }

            //Если найден хоть один объект, то выводим в консоль список Категорий в которых присутствуют объекты содержащие искомую строку и получаем из консоли номер выбранного пункта
            if (listPoiSearch.size() > 0) {

                nnn = new Consol().consolSelect(ReadBD.getlistCategory(listPoiSearch));

                ArrayList<CityPoint> listPoiSelect = new ArrayList<CityPoint>();
                ArrayList<String> listPoiSelectName = new ArrayList<String>();

                for (int t = 0; t < listPoiSearch.size(); t++) {
                    if (listPoiSearch.get(t).getCategory().toLowerCase().contains(ReadBD.getlistCategorySearch().get(nnn).toLowerCase())) {

                        listPoiSelect.add(listPoiSearch.get(t));
                        listPoiSelectName.add(listPoiSearch.get(t).getName());
                    }
                }

                //Выводем в консоль список Имен объектов принадлежащих выбранной Категории и получаем из консоли номер выбранного пункта.
                nnn = new Consol().consolSelect(listPoiSelectName);

                //Выводем в консоль Характеристики выбранного объекта.
                System.out.println("\n" + "\n" + "-----------------------------------------------------------------");
                System.out.println("Название: " + listPoiSelect.get(nnn).getName());
                System.out.println("Рейтинг: " + listPoiSelect.get(nnn).getRating());
                System.out.println("Адрес: " + listPoiSelect.get(nnn).getAdres());
                System.out.println("Телефон: " + listPoiSelect.get(nnn).getTelefon());
                System.out.println("Время работы: c " + listPoiSelect.get(nnn).getWorktim().replace("_", " по "));
                System.out.println("Описание: " + listPoiSelect.get(nnn).getDescription() + "\n");
                System.out.println("Отзывы: ");

                String bdMass[] = listPoiSelect.get(nnn).getResponses().split("::::");

                for (int t = 0; t < bdMass.length; t++) {
                    System.out.println(bdMass[t]);
                }
                System.out.println("-----------------------------------------------------------------");

            } else {
                System.out.println("\n" + "Поиск не дал результатов");
            }
        }


    }else   {
            System.out.println("\n" + "\n" + "\n" + "!!!!!!!!!!! ФАЙЛ БД НЕ НАЙДЕН !!!!!!!!!!! ");
        }
    }
}