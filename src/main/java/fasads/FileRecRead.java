package fasads;

import java.io.*;

public class FileRecRead {

    //Чтение текста из файла
    public static String ReadFile(String a){

        String text="";
        try{
            FileInputStream fstream = new FileInputStream(a);
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream, "UTF-8"));
            String strLine;
            while ((strLine = br.readLine()) != null){
                text = text.concat(strLine).concat(System.lineSeparator());
            }
        }catch (IOException e){
            return null;
        }
        return text;
    }
}
