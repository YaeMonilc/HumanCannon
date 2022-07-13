package cc.kocho;

import cc.kocho.config.Config;
import cc.kocho.util.HttpRequest;

import java.io.IOException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {


        Scanner scanner = new Scanner(System.in);

        System.out.print("请输入服务器地址：");
        Config.serveUrl = scanner.nextLine();

        System.out.print("尝试连接服务器:");


        try {
            if (!HttpRequest.get(Config.serveUrl, "").contains("Grasscutter")){
                Config.connectStats = false;
                System.out.println("失败");
            }else {
                Config.connectStats = true;
                System.out.println("成功");
            }
        } catch (IOException e) {
            Config.connectStats = false;
            System.out.println("出现错误");
        }

        if (!Config.connectStats){
            scanner.nextLine();
            System.exit(100);
        }

        try {
            System.out.println(HttpRequest.get(Config.serveUrl + "/query_region_list", "version=CNRELWin2.8.0&lang=2&platform=3&binary=1&time=353&channel_id=1&sub_channel_id=1"));
        } catch (IOException e) {
            Config.connectStats = false;
            System.out.println("出现错误");
        }
        if (!Config.connectStats){
            scanner.nextLine();
            System.exit(101);
        }

        System.out.print("请输入次数：");
        int times = Integer.parseInt(scanner.nextLine());

        System.out.print("自定义前缀：");
        String qName = scanner.nextLine();

        for (int i = 0;i < times;i++){
            int finalI = i;
            new Thread(() -> {
               String q = "";
               if (qName.contains("%")){
                   q = String.format(qName, (int) (Math.random() * 10), (int) (Math.random() * 100), (int) (Math.random() * 1000));
               }
               Cannonball cannonball = Cannon.fire(q);
               System.out.printf("名称:%s,状态:%s,次数:%s%n",cannonball.name,cannonball.result, finalI);
            }).start();
        }


    }
}