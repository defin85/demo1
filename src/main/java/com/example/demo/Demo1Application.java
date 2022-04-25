package com.example.demo;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.util.Scanner;

@SpringBootApplication
public class Demo1Application {

    public static void main(String[] args) {
        SpringApplication.run(Demo1Application.class, args);
    }

    @Bean
    public CommandLineRunner demo(TrackerRepository repository) {
        return (args) -> {
            // save a few tracker
            repository.save(new Tracker("Task1", "do nothing"));
            repository.save(new Tracker("Task2", "do everithing"));
            repository.save(new Tracker("Task3", "buy netbook"));
            repository.save(new Tracker("Task4", "buy bitcoin"));
            repository.save(new Tracker("Task5", "watch something"));

            Scanner in = new Scanner(System.in);
            int num = 9;
            while (num != 0) {
                System.out.println("Выбрать действие");
                System.out.println("[1]:Добавление");
                System.out.println("[2]:Чтение");
                System.out.println("[3]:Изменение");
                System.out.println("[4]:Удаление");
                System.out.println("[0]:Выход");
                System.out.print("Введите пункт меню: ");

                num = getPointMenu("Введите пункт меню: ", in);

                if (num == 1) {
                    System.out.print("Введите заголовок задачи: ");
                    String title = in.next();
                    System.out.print("Введите описание задачи: ");
                    String desc = in.next();
                    repository.save(new Tracker(title, desc));
                } else if (num == 2) {
                    System.out.println("Отобразить все задачи");
                    System.out.println("-------------------------------");
                    for (Tracker task : repository.findAll()) {
                        System.out.println(task.toString());
                    }
                    System.out.println("-------------------------------");
                } else if (num == 3) {
                    System.out.print("Введите id изменяемой задачи: ");
                    long trackID = getPointMenu("Введите id изменямой задачи: ", in);
                    if (repository.existsById(trackID)) {
                        System.out.print("Введите заголовок задачи: ");
                        String title = in.next();
                        System.out.print("Введите описание задачи: ");
                        String desc = in.next();
                        Tracker track = repository.findById(trackID);
                        track.setTitle(title);
                        track.setDesc(desc);
                        repository.save(track);
                        System.out.printf("Изменена задача с id: %d \n", trackID);
                    } else {
                        System.out.printf("Нет такого id: %d \n", trackID);
                    }
                } else if (num == 4) {
                    System.out.print("Введите id удаляемой задачи: ");
                    long trackID = getPointMenu("Введите id удаляемой задачи: ", in);
                    if (repository.existsById(trackID)) {
                        repository.deleteById(trackID);
                        System.out.printf("Удалена задача с id: %d \n", trackID);
                    } else {
                        System.out.printf("Нет такого id: %d \n", trackID);
                    }

                }
                else {
                    System.out.printf("Нет такого пункта меню: %d \n", num);
                }
            }

            System.out.println("Завершение работы");
            in.close();
        };
    }

    public static int getPointMenu(String mes, Scanner sc) {

        int pointMenu = 0;
        while (!sc.hasNextInt()) {
            System.out.println("Это не цифра");
            System.out.print(mes);
            sc.next();
        }
        pointMenu = sc.nextInt();

        return pointMenu;
    }
}
