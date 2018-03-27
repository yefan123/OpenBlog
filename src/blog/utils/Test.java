package blog.utils;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Test {
    public static void main(String[] args) {
        List<Model> list = new ArrayList<>();
        List<String> sort = new ArrayList<>();
        int number;
        Scanner scan = new Scanner(System.in);

        number = Integer.parseInt(scan.nextLine());

        while (number > 0) {
            String temp = scan.nextLine();
            String[] test = temp.split(" ");
            Model model = new Model();
            model.name = test[0];
            model.submit = Integer.parseInt(test[1]);
            model.pass = Integer.parseInt(test[2]);
            double percent = model.pass / (double) (model.submit);
            if (percent <= 0.3) {
                model.level = 5;
            } else if (percent <= 0.6) {
                model.level = 4;
            } else {
                model.level = 3;
            }
            list.add(model);
            sort.add(model.name);
            number--;
        }
        Collections.sort(sort);
        List<Model> list2 = new ArrayList<>();
        for (String name : sort) {
            for (Model model : list) {
                if (model.name.equals(name)) {
                    list2.add(model);
                }
            }
        }

        for (Model model : list2) {
            System.out.println(model.name + " " + model.level);
        }
        scan.close();
    }
}

class Model {
    public String name;
    public int submit;
    public int pass;
    public int level;
}