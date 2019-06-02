/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package vendingmachinetest;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Random;

/**
 *
 * @author 曾馨慧
 */
public class VendingMachine {

    Drink[] dr;

    public void show() {

        System.out.println("*********************");
        System.out.println("飲料販賣機");
        System.out.println("正常販售中");
        System.out.println("1.買飲料");
        System.out.println("2.補飲料");
        System.out.println("3.終極密碼");
        System.out.println("0.離開");
        System.out.println("********************");
        while (true) {
            System.out.println("請選擇:");
            int choose = -1;
            boolean error = false; // 輸入有沒有正確
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            try {
                String str = br.readLine();
                choose = Integer.parseInt(str);
            } catch (Exception e) {
                error = true;
            }
            if (error || choose < 0 || choose > 3) {
                System.out.println("輸入錯誤!");
            }
            if (choose == 1) {
                buyDrink();
            }
            if (choose == 2) {
                addDrink();
            }
            if (choose == 3) {
                playGuessGame();
            }
            if (choose == 0) {
                break;
            }

        }// end of while-loop

    }// end of show()

    public void loadDrinks() {
        try {

            BufferedReader br = new BufferedReader(new FileReader("drinks.txt"));
            int n = Integer.parseInt(br.readLine());
            dr = new Drink[n];
            for (int i = 0; i < n; i++) {
                dr[i] = new Drink();
                String str = br.readLine();
                String[] arr = str.split(",");
                dr[i].setName(arr[0]);
                dr[i].setManufacturer(arr[1]);
                dr[i].setQuantity(Integer.parseInt(arr[2]));
                dr[i].setPrice(Integer.parseInt(arr[3]));

            }// end of for-loop
            br.close();
        } catch (Exception e) {
            System.out.println(e);
        }
    }//end of loadDrinks()

    public void saveDrinks() {
        try {
            FileWriter fw = new FileWriter("drinks.txt");
            fw.write(dr.length + "\n");
            for (int i = 0; i < dr.length; i++) {
                fw.write(dr[i].getName() + "," + dr[i].getManufacturer() + "," + dr[i].getQuantity() + "," + dr[i].getPrice() + "\n");

            }
            fw.close();
        } catch (Exception e) {

        }

    }//end of saveDrinks()

    public void showDrinks() {
        for (int i = 0; i < dr.length; i++) {

            System.out.printf("%d.%s %d元 剩餘%d個", i + 1, dr[i].getName(), dr[i].getPrice(), dr[i].getQuantity());
            System.out.println();
        }
    }//end of showDrinks()

    public void addDrink() {

        loadDrinks();
        showDrinks();
        while (true) {
            int choose = -1;
            int num = -1;
            boolean error = false;
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            try {
                System.out.println("請輸入補貨的飲料:");
                String str = br.readLine();
                choose = Integer.parseInt(str);
                System.out.println("請輸入要補貨的數量:");
                str = br.readLine();
                num = Integer.parseInt(str);

            } catch (Exception e) {
                error = true;
            }
            if (error || choose < 1 || choose > dr.length || num < 0) {
                System.out.println("輸入錯誤!");
                continue;
            }
            if (choose < dr.length || choose > 0) {
                System.out.println("補貨成功!");
                System.out.println();
                int s = dr[choose - 1].getQuantity();
                dr[choose - 1].setQuantity(s + num);
                saveDrinks();
                break;
            }

        }//end of while
    }//end of addDrink()

    public void buyDrink() {

        loadDrinks();
        showDrinks();
        while (true) {
            int money = -1;
            int choose = -1;
            boolean error = false; // 輸入有沒有正確
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

            try {
                System.out.println("請輸入要買的飲料:");
                String str = br.readLine();
                choose = Integer.parseInt(str);
                System.out.println("請輸入要付的金額:");
                str = br.readLine();
                money = Integer.parseInt(str);

            } catch (Exception e) {
                error = true;
            }
            if (error || choose < 1 || choose > dr.length) {
                System.out.println("輸入錯誤!");
                continue;
            }
            if (money < dr[choose - 1].getPrice()) {
                System.out.println("喝霸王飲料???");
                continue;
            }
            if (money >= dr[choose - 1].getPrice()) {

                int n;
                n = money - dr[choose - 1].getPrice();
                System.out.printf("謝謝惠顧，找您%d元", n);
                System.out.println();
                int s = dr[choose - 1].getQuantity();
                dr[choose - 1].setQuantity(s - 1);
                saveDrinks();
                break;
            }

        }// end of while-loo[
    }//end of buyDrink()

    public void playGuessGame() {
        int x;
        int up = 200;
        int down = 1;
        int money = 50;
        int a = 0;
        int count = 1;
        Random r = new Random();
        x = r.nextInt(200) + 1;
        System.out.println("終極密碼");
        System.out.println("***********遊戲開始*************");
        while (true) {
            loadDrinks();
            System.out.printf("第%d次 %d~%d請輸入數字:", count, down, up);
            BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
            boolean error = false;
            try {
                String str = br.readLine();
                a = Integer.parseInt(str);
            } catch (Exception e) {
                error = true;
            }
            if (error || a > up || a < down) {
                System.out.println("輸入錯誤");

            }
            if (a < up && a > down) {
                int choose = -1;
                if (a == x) { // 答對的情況
                    System.out.printf("恭喜獲得獎金%d元\n", money);
                    

                    showDrinks();

                    while (true) {
                        try {
                            System.out.println("請輸入要買的飲料:");
                            String str = br.readLine();
                            choose = Integer.parseInt(str);
                        } catch (Exception e) {
                            error = true;
                        }
                        if (error || choose < 1 || choose > dr.length) {
                            System.out.println("輸入錯誤!");
                            continue;
                        }
                        if (money < dr[choose - 1].getPrice()) {
                            System.out.println("獎金不夠啦87");
                            continue;
                        }
                        break;
                    }
                
                if (money >= dr[choose - 1].getPrice()) {

                    System.out.println("謝謝惠顧");
                    System.out.println();
                }//答對情況
                    int s = dr[choose - 1].getQuantity();
                    dr[choose - 1].setQuantity(s - 1);
                    saveDrinks();
                    break;
                } else if (a < x) {
                    down = a;
                    count++;
                    money -= 10;

                } else if (a > x) {
                    up = a;
                    count++;
                    money -= 10;
                }

                if (count > 5) {
                    System.out.println(" 這次沒中獎喔!! 幫QQ ");
                    break;
                }
            }//end of if
        }//end of while
    }//end of playGuessGame()

}//end of class VendingMachine

