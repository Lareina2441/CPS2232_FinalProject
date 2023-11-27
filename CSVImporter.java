package CPS2232.FinalProject;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class CSVImporter {
    public static void main(String[] args) {
        String csvFile = "D:\\MCMTraining\\dataFolder\\2023_MCM_Problem_Y_Boats.csv";
        String line;
        String csvSplitBy = ",";

        try (BufferedReader br = new BufferedReader(new FileReader(csvFile))) {
            while ((line = br.readLine()) != null) {
                String[] data = line.split(csvSplitBy);
                // 处理每一行的数据
                for (String value : data) {
                    System.out.print(value + " ");
                }
                System.out.println();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}