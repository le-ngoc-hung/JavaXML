package bt;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class bai1 {
    public static void main(String[] args) {
        String directoryPath = "D:\\BaiTapTKW";

        File directory = new File(directoryPath);
        if (directory.exists() && directory.isDirectory()) {
            StringBuilder xmlBuilder = new StringBuilder();
            xmlBuilder.append("<directory>").append(System.lineSeparator());

            listDirectory(directory, xmlBuilder);

            xmlBuilder.append("</directory>");

            String xmlString = xmlBuilder.toString();

            try {
                FileWriter fileWriter = new FileWriter("directory_listing.xml");
                fileWriter.write(xmlString);
                fileWriter.close();
                System.out.println("File XML đã được tạo thành công.");
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            System.out.println("Thư mục không tồn tại.");
        }
    }

    private static void listDirectory(File directory, StringBuilder xmlBuilder) {
        File[] files = directory.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    xmlBuilder.append("<").append(file.getName()).append(">").append(System.lineSeparator());
                    listDirectory(file, xmlBuilder);
                    xmlBuilder.append("</").append(file.getName()).append(">").append(System.lineSeparator());
                } else {
                    xmlBuilder.append("<file>").append(file.getName()).append("</file>").append(System.lineSeparator());
                }
            }
        }
    }
}
