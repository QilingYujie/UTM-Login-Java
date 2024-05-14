/*
Java实现-启灵域界科技
JsonDB算法基于aztice的JsonDB
 */
package com.APRT.UTMDB;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;
import com.APRT.UTMDB.LLogger;
import org.yaml.snakeyaml.Yaml;
import com.APRT.UTMDB.Dir;
import com.APRT.UTMDB.LightSK;
import  java.io.*;
import java.util.logging.Logger;


public class Main {
    String Cmd;
    String sourceFilePath = "src/main/resources/config.yml";
    static String destinationFolderPath = "config/";
    static Scanner in = new Scanner(System.in);
    private static final Logger logger = Logger.getLogger(Main.class.getName());
    public static void main(String[] args) {
        Dir.mkdir(".\\","log");
        HashMap<String,Boolean> hashMap = new HashMap<>();
        Boolean CmdStatus = false;
        hashMap.put("exit",true);
        hashMap.put("reload",true);
        hashMap.put("Ver",true);
        hashMap.put("Version",true);
        hashMap.put("logoff",true);
        hashMap.put("version",true);
        LLogger.LogRec("Starting server!");
        Dir.mkdir(".","db");
        String Cmd = "-";
        try {
            // 读取资源文件
            InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("config.yml");
            if (inputStream == null) {
                System.out.println("Can not find config!");
                LLogger.LogRec("Can not find config!");
                return;
            }

            // 创建目标文件夹
            File destinationFolder = new File(destinationFolderPath);
            if (!destinationFolder.exists()) {
                destinationFolder.mkdirs();
            }

            // 写入文件到目标文件夹
            OutputStream outputStream = new FileOutputStream(destinationFolderPath + "config.yml");
            byte[] buffer = new byte[1024];
            int length;
            while ((length = inputStream.read(buffer)) > 0) {
                outputStream.write(buffer, 0, length);
            }

            // 关闭流
            inputStream.close();
            outputStream.close();

            System.out.println("Created config");
            LLogger.LogRec("Created config");
        } catch (IOException e) {
            logger.warning("Error while creating config!!!");
            LLogger.LogRec("Error while creating config!!!");
            e.printStackTrace();
        }
        System.out.println("Auto backup?,="+ ReadYaml.readYamlBoolean("config/config.yml","Config.autoBackup.Enable"));
        LLogger.LogRec("Server started!");
        while(!Objects.equals(Cmd, "exit")){

            System.out.print(">");
            Cmd = in.nextLine();
            System.out.println();
            if(Objects.equals(Cmd, "exit")){
                System.out.println("Bye");
                break;
            }
            if(Objects.equals(Cmd, "Ver") || Cmd.equals("Version") || Cmd.equals("version")){
                System.out.println("""
                        UTM-DB 1.0 SnapShot
                        By QiLingYuJie-John
                        Welcome!
                        """);
            }
            if(Objects.equals(Cmd, "reload")) {
                try {
                    // 读取资源文件
                    InputStream inputStream = Main.class.getClassLoader().getResourceAsStream("config.yml");
                    if (inputStream == null) {
                        System.out.println("Can not find config!");
                        LLogger.LogRec("Can not find config!");
                        return;
                    }

                    // 创建目标文件夹
                    File destinationFolder = new File(destinationFolderPath);
                    if (!destinationFolder.exists()) {
                        destinationFolder.mkdirs();
                    }

                    // 写入文件到目标文件夹
                    OutputStream outputStream = new FileOutputStream(destinationFolderPath + "config.yml");
                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = inputStream.read(buffer)) > 0) {
                        outputStream.write(buffer, 0, length);
                    }

                    // 关闭流
                    inputStream.close();
                    outputStream.close();

                    System.out.println("Created config");
                    LLogger.LogRec("Created config");
                } catch (IOException e) {
                    logger.warning("Error while creating config!!!");
                    LLogger.LogRec("Error while creating config!!!");
                    e.printStackTrace();

                }
                System.out.print("Complete!");
            }
            for (String key : hashMap.keySet()) {
                if (Cmd!=null&&!key.equals(Cmd)) {
                    // 如果不包含特定字符串
                     CmdStatus = true;

                }
                else {
                    CmdStatus=false;
                    break;
                }

            }
            if(CmdStatus==true){
                System.out.println("Wrong command: " + Cmd);
                CmdStatus = false;
            }
        }

    }


}

