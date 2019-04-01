package fasads;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.TimeZone;

public class ReadBD {

    private static final String URL = "jdbc:mysql://localhost:3307/point?serverTimezone=" + TimeZone.getDefault().getID() + "&useSSL=false";
    private static final String USERNAME = "root";
    private static final String PASSWORD = "*******";

    private static ArrayList<CityPoint> listPoint = new ArrayList<CityPoint>();
    private static ArrayList<String> listCategory = new ArrayList<String>();

    public static void mysqlBDStart() {
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            Statement statement = connection.createStatement();

            //Удаляем таблицу (Для демонстрации задания)
            statement.execute("DROP  TABLE IF EXISTS `citypoint`");

            //Создаем таблицу (Для демонстрации задания)
            statement.execute("CREATE TABLE IF NOT EXISTS`citypoint` (`category` varchar(999) NOT NULL,`name` varchar(999) NOT NULL,`rating` int(255) NOT NULL,`description` varchar(999) NOT NULL, `responses` varchar(999) NOT NULL, `worktim` varchar(999) NOT NULL, `telefon` varchar(999) NOT NULL, `adres` varchar(999) NOT NULL) ENGINE=InnoDB AUTO_INCREMENT=0 DEFAULT CHARSET=utf8");

            //Заполняем таблицу данными из файла
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
                            statement.execute("INSERT INTO citypoint (category, name, rating, description, responses, worktim, telefon, adres) VALUES ('" + bdMass[t].split(";;;;")[0] + "', '" + bdMass[t].split(";;;;")[2] + "', '" + Integer.parseInt(bdMass[t].split(";;;;")[3]) + "', '" + bdMass[t].split(";;;;")[4] + "', '" + bdMass[t].split(";;;;")[5] + "', '" + bdMass[t].split(";;;;")[6] + "', '" + bdMass[t].split(";;;;")[7] + "', '" + bdMass[t].split(";;;;")[8] + "')");
                        }
                    }
                    connection.close();

                } else {
                }
            } catch (IOException e) {
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Неудалось загрузить класс драйвера или не создался коннект!");
        }
    }

    //Создаем список объектов из MySQL
    private static ArrayList<CityPoint> readToArraymySQL() {
        listPoint.clear();
        try {
            Connection connection = DriverManager.getConnection(URL, USERNAME, PASSWORD);
            PreparedStatement preparedStatement = connection.prepareStatement("select * from citypoint");
            ResultSet resultSet = preparedStatement.executeQuery("select * from citypoint");

            while (resultSet.next()) {
                listPoint.add(new CityPoint(resultSet.getString(1), resultSet.getString(2), resultSet.getInt(3), resultSet.getString(4), resultSet.getString(5), resultSet.getString(6), resultSet.getString(7), resultSet.getString(8)));
            }

            connection.close();

        } catch (SQLException e) {
            e.printStackTrace();
            System.err.println("Неудалось загрузить класс драйвера или не создался коннект!");
            return null;
        }
        return listPoint;
    }

    //Создаем список объектов из файла БД (не используется)
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

                            //mysqlBD(bdMass[t].split(";;;;")[0], bdMass[t].split(";;;;")[2], Integer.parseInt(bdMass[t].split(";;;;")[3]), bdMass[t].split(";;;;")[4], bdMass[t].split(";;;;")[5], bdMass[t].split(";;;;")[6], bdMass[t].split(";;;;")[7], bdMass[t].split(";;;;")[8]);

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
        listPoint = readToArraymySQL();
        if (listPoint != null) {
            for (int t = 0; t < listPoint.size(); t++) {
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
            if (listCategory.contains(listsPoint.get(t).getCategory())) {
            } else {
                listCategory.add(listsPoint.get(t).getCategory());
            }
        }
        return listCategory;
    }

    public static ArrayList<CityPoint> getListsPoint() {
        ArrayList<CityPoint> listsPoi = readToArraymySQL();
        return listsPoi;
    }

    public static ArrayList<String> getlistCategorySearch() {
        ArrayList<String> listCategory = listCategorySearch();
        return listCategory;
    }

    public static ArrayList<String> getlistCategory(ArrayList<CityPoint> listsPoint) {
        ArrayList<String> listCategory = listCategory(listsPoint);
        return listCategory;
    }
}