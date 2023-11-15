package sample;

import java.io.*;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Logic {

    String fileName;
    String path;
    String fileNameCrop;
    FileWriter fileWriter;
    FileWriter logWriter;
    boolean existence;
    DecimalFormat df = new DecimalFormat("0.000000");
    ArrayList<Point> pointsMassive = new ArrayList<>();
    ArrayList<Point> pointsMassiveNext = new ArrayList<>();
    String log;

    public String find(File file) throws IOException {

        fileName = file.getName();
        fileNameCrop = fileName.substring(0, fileName.lastIndexOf('x') + 1);
        path = String.valueOf(file);
        log = "Прочитано:\n" + path + "\n";
        path = path.substring(0, path.lastIndexOf("\\") + 1);
        System.out.println("fileName = " + fileName);
        System.out.println("fileNameCrop = " + fileNameCrop);
        System.out.println("path = " + path);

        Matcher matcher = Pattern.compile("\\d+").matcher(fileName);
        matcher.find();
        int fileNum = Integer.parseInt(matcher.group());

        try {
            readLines(file, pointsMassive);
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

        for (int i = fileNum; i < 100; i++) {
            fileNum = fileNum + 1;
            fileName = path + fileNameCrop + fileNum + ".DAT";
            File fileNext = new File(fileName);
            if (fileNext.exists()) {
                log = log + fileName + "\n";
                System.out.println(fileName);
                pointsMassiveNext.clear();
                readLines(fileNext, pointsMassiveNext);
                for (Point pointNext : pointsMassiveNext) {
                    existence = false;
                    for (Point point : pointsMassive) {
                        if (pointNext.equals(point)) {
                            point.setExx(pointNext.getExx() + point.getExx());
                            point.setNumber(point.getNumber() + 1);
                            existence = true;
                            break;
                        }
                    }
                    if (!existence) {
                        Point point = new Point(pointNext.getCoordinateX(), pointNext.getCoordinateY(), pointNext.getExx(), 1);
                        pointsMassive.add(point); // добавляем новые точки
                        log = log + point + "\n";
                    }
                }
            }
        }
        fileWriter = new FileWriter(path + "summ.DAT");
        for (Point point : pointsMassive) {
            String dfX = df.format(point.getCoordinateX());
            String dfY = df.format(point.getCoordinateY());
            String dfExx = df.format(point.getExx());
            fileWriter.write(dfX + " " + dfY + " " + dfExx + "\n");
        }
        fileWriter.flush();
        for (Point point : pointsMassive) {
            log = log + point.getCoordinateX() + " " + point.getCoordinateY() + " " + point.getExx() + " " + point.getNumber() + "\n";
        }
        logWriter = new FileWriter(path + "log.txt");
        logWriter.write(log);
        logWriter.flush();
        return log;
    }

    private void readLines(File file, ArrayList<Point> pointsMassive) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader(file));
        List<String> lines = new ArrayList<>();
        String line;
        while ((line = reader.readLine()) != null) {
            line = line.replace(',', '.');
            if (!line.contains("--")) {
                lines.add(line);
                lines.removeIf(item -> item == null || "".equals(item));
            }
        }
        for (String s : lines) {
            Point point = new Point(0, 0, 0, 1);
            String[] split1 = s.split(" ");

            point.coordinateX = Double.parseDouble(split1[0]);
            point.coordinateY = Double.parseDouble(split1[1]);
            point.Exx = Double.parseDouble(split1[2]);
            pointsMassive.add(point);
        }
    }
}
