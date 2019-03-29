import fasads.CityPoint;
import fasads.ReadBD;

import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        ArrayList<CityPoint> listPoi = ReadBD.getListsPoi();

        int nnn = new Consol().consolSelect(ReadBD.getlistCategorySearch());

        if (nnn != (ReadBD.getlistCategorySearch().size()-1)){

            ArrayList<CityPoint> listPoiSelect = new ArrayList<CityPoint>();
            ArrayList<String> listPoiSelectName = new ArrayList<String>();

            for (int t = 0; t < listPoi.size(); t++) {
                if (listPoi.get(t).getCategory().contains(ReadBD.getlistCategorySearch().get(nnn))){

                    listPoiSelect.add(listPoi.get(t));
                    listPoiSelectName.add(listPoi.get(t).getName());
                }
            }

            System.out.println("");
            nnn = new Consol().consolSelect(listPoiSelectName);

            System.out.println("\n" + "-----------------------------------------------------------------");
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






        }else {//_____________________________________________________________________________________________Реализация поиска

            System.out.print("\n" + "ВВЕДИ СЛОВА ПОИСКА: ");
            Scanner in = new Scanner(System.in);
            String num = in.next();

            ArrayList<CityPoint> listPoiSearch = new ArrayList<CityPoint>();


            for (int t = 0; t < listPoi.size(); t++) {

                if(
                    listPoi.get(t).getCategory().contains(num) ||
                    listPoi.get(t).getName().contains(num) ||
                    listPoi.get(t).getResponses().contains(num) ||
                    listPoi.get(t).getDescription().contains(num)
                ){

                    listPoiSearch.add(listPoi.get(t));
                }
            }

            if (listPoiSearch.size() > 0) {

                nnn = new Consol().consolSelect(ReadBD.getlistCategory(listPoiSearch));

                ArrayList<CityPoint> listPoiSelect = new ArrayList<CityPoint>();
                ArrayList<String> listPoiSelectName = new ArrayList<String>();

                for (int t = 0; t < listPoiSearch.size(); t++) {
                    if (listPoiSearch.get(t).getCategory().toLowerCase().contains(ReadBD.getlistCategorySearch().get(nnn).toLowerCase())){

                        listPoiSelect.add(listPoiSearch.get(t));
                        listPoiSelectName.add(listPoiSearch.get(t).getName());
                    }
                }

                System.out.println("");
                nnn = new Consol().consolSelect(listPoiSelectName);

                System.out.println("\n" + "-----------------------------------------------------------------");
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

            }else {
                System.out.println("\n" + "Поиск не дал результатов");
            }
        }
    }
}