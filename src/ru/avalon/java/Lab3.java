package ru.avalon.java;

import ru.avalon.java.console.ConsoleUI;

import java.io.*;
import java.nio.file.*;
import ru.avalon.java.actions.*;

/**
 * Лабораторная работа №3
 * <p>
 * Курс: "Программирование на платформе Java. Разработка многоуровневых
 * приложений"
 * <p>
 * Тема: "Потоки исполнения (Threads) и многозадачность"
 *
 * @author Daniel Alpatov <danial.alpatov@gmail.com>
 */
public class Lab3 extends ConsoleUI<Commands> {

    Path from;
    Path to;

    BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

    /**
     * Точка входа в приложение.
     *
     * @param args
     */
    public static void main(String[] args) {
        new Lab3().run();
    }

    /**
     * Конструктор класса.
     * <p>
     * Инициализирует экземпляр базового типа с использоавнием перечисления
     * {@link Commands}.
     */
    Lab3() {
        super(Commands.class);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected void onCommand(Commands command) throws IOException {
        switch (command) {
            case create:
                System.out.println("Enter the path of the file to be created");
                to = Paths.get(in.readLine());
                new FileCreateAction(to).start();
                break;
            case copy:
                System.out.println("Enter the path of the file to be copied");
                from = Paths.get(in.readLine());
                System.out.println("Enter where to copy the file");
                to = Paths.get(in.readLine());
                new FileCopyAction(from, to).start();
                break;
            case move:
                System.out.println("Enter the path of the file to be moved");
                from = Paths.get(in.readLine());
                System.out.println("Enter where to move the file");
                to = Paths.get(in.readLine());
                new FileMoveAction(from, to).start();
                break;
            case delete:
                System.out.println("Enter the path of the file to be deleted");
                from = Paths.get(in.readLine());
                new FileDeleteAction(from).start();
                break;
            case exit:
                close();
                break;
        }
    }
}
