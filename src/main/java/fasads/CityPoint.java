package fasads;

import java.util.ArrayList;

public class CityPoint {

    private String category;
    private String name;
    private int rating;
    private String description;
    private String responses;
    private String worktim;
    private String telefon;
    private String adres;

    public CityPoint() {
    }

    public CityPoint(String category, String name, int rating, String description, String responses, String worktim, String telefon, String adres) {
        this.category = category;
        this.name = name;
        this.rating = rating;
        this.description = description;
        this.responses = responses;
        this.worktim = worktim;
        this.telefon = telefon;
        this.adres = adres;
    }

    public String getCategory() {
        return category;
    }

    public String getName() {
        return name;
    }

    public int getRating() {
        return rating;
    }

    public String getDescription() {
        return description;
    }

    public String getResponses() {
        return responses;
    }

    public String getWorktim() {
        return worktim;
    }

    public String getTelefon() {
        return telefon;
    }

    public String getAdres() {
        return adres;
    }

    public void resultConsol(ArrayList<CityPoint> listPointSelect, int numberConsolSelect) {
        //Выводем в консоль Характеристики выбранного объекта.
        System.out.println("\n" + "\n" + "-----------------------------------------------------------------");
        System.out.println("Название: " + listPointSelect.get(numberConsolSelect).getName());
        System.out.println("Рейтинг: " + listPointSelect.get(numberConsolSelect).getRating());
        System.out.println("Адрес: " + listPointSelect.get(numberConsolSelect).getAdres());
        System.out.println("Телефон: " + listPointSelect.get(numberConsolSelect).getTelefon());
        System.out.println("Время работы: c " + listPointSelect.get(numberConsolSelect).getWorktim().replace("_", " по "));
        System.out.println("Описание: " + listPointSelect.get(numberConsolSelect).getDescription() + "\n");
        System.out.println("Отзывы: ");

        String bdMass[] = listPointSelect.get(numberConsolSelect).getResponses().split("::::");
        for (int t = 0; t < bdMass.length; t++) {
            System.out.println(bdMass[t]);
        }
        System.out.println("-----------------------------------------------------------------");
    }
}