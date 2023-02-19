// В файле содержится строка с данными:
// [{"фамилия":"Иванов","оценка":"5","предмет":"Математика"}, 
// {"фамилия":"Петрова","оценка":"4","предмет":"Информатика"}, 
// {"фамилия":"Краснов","оценка":"5","предмет":"Физика"}]
// Напишите метод, который разберёт её на составные части и, 
// используя StringBuilder, создаст массив строк такого вида:
// Студент Иванов получил 5 по предмету Математика.
// Студент Петрова получил 4 по предмету Информатика.
// Студент Краснов получил 5 по предмету Физика.

package Homework2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Task3 {
    public static String getLineOfFile(String fileName) {
        String strOfFile = null;
        try {
            File file = new File(fileName);
            FileReader fr = new FileReader(file);
            BufferedReader reader = new BufferedReader(fr);
            strOfFile = reader.readLine();
            reader.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return strOfFile;
    }

    private static String[][] fileParseToArray(String fileStr) {
        String line = fileStr.substring(1, fileStr.length() - 1);
        String[] fileArray = line.split(", ");
        String[][] fileArrayOfBase = new String[fileArray.length][3];
        for (int i = 0; i < fileArray.length; i++) {
            line = fileArray[i].substring(1, fileArray[i].length() - 1);
            for (int j = 0; j < 3; j++) {
                String[] minFileArray = line.split(",");
                String[] microFileArray = minFileArray[j].split(":");
                fileArrayOfBase[i][j] = microFileArray[1].substring(1, microFileArray[1].length() - 1);
            }
        }
        return fileArrayOfBase;
    }

    private static String[] connectingStrings(String[][] arrayStr) {
        String[] connectedStr = new String[arrayStr.length];
        StringBuilder newStr = new StringBuilder();
        for (int i = 0; i < arrayStr.length; i++) {
            newStr.append("Студент ");
            newStr.append(arrayStr[i][0]);
            newStr.append(" получил ");
            newStr.append(arrayStr[i][1]);
            newStr.append(" по предмету ");
            newStr.append(arrayStr[i][2]);
            connectedStr[i] = newStr.toString();
            newStr.delete(0, newStr.length());
        }
        return connectedStr;
    }

    public static void main(String[] args) {
        String strOfFile = getLineOfFile("file3.txt");
        System.out.println(strOfFile);
        String[] array = connectingStrings(fileParseToArray(strOfFile));
        for (int i = 0; i < array.length; i++)
            System.out.println(array[i]);
    }
}