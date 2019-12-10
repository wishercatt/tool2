import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static List<String> Readfile(File file) {
        List<String> lines = new ArrayList<>();
        try {
            Scanner scan = new Scanner(file);
            while (scan.hasNext()) {
                String line = scan.nextLine();
                if (line.equals(""))
                    continue;
                //按句号和分号将句子拆分出来
                if (line.contains("。")) {
                    String[] tmp = line.split("。");
                    for (String s : tmp) {
                        if (s.equals("")) continue;
                        if (s.contains("；")) {
                            String[] tmp1 = s.split("；");
                            for (String s1 : tmp1) {
                                if (s1.equals("")) continue;
                                lines.add(s1 + "。");
                            }
                        } else {
                            lines.add(s + "。");
                        }
                    }
                } else lines.add(line);
            }
        } catch (Exception e) {
            System.out.println("no such file");
        }
        return lines;
    }

    public static void verify(String app, String business, List<String> pis) {
        //File purposefile = new File("");
        //List<String> purpose = Readfile(purposefile);

        File pifile = new File("./rule/business/" + business + "/pi.txt");
        List<String> pi = Readfile(pifile);
        List<String> uncompliance = new ArrayList<>();
        for (String str : pis) {
            if (!pi.contains(str)) {
                uncompliance.add(str);
            } else continue;
        }
        if (uncompliance.size() > 0) {
            System.out.println(app + "的隐私政策在“" + business + "”这一业务功能方面不符合最少信息原则\n");
            System.out.println("超出范围收集的个人信息如下：");
            System.out.println(uncompliance);
        }
    }

    public static void main(String[] args) {
        String app = "淘宝";
        File file = new File("./resource/" + app + "隐私政策标注.txt");
        List<String> demo = Readfile(file);
        List<String> pis = new ArrayList<>();
        for (String str : demo) {
            model m = new model();
            m.getModel(str);
            List<String> pi = m.getPI();
            for (String s : pi) {
                if (!pis.contains(s))
                    pis.add(s);
                else continue;
            }
        }
        String business = "网上购物";
        verify(app, business, pis);
    }
}
