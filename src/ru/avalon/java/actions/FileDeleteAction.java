package ru.avalon.java.actions;

import java.io.*;
import java.nio.file.*;

/**
 * Действие, которое удаляет файлы в пределах дискового пространства.
 */
public class FileDeleteAction implements Action {

    // переменная для хранения пути удаляемого файла.
    private Path from;

    /**
     * Конструктор класса.
     * @param from - путь удаляемого файла.
     */
    public FileDeleteAction(Path from) {
        this.from = from;
    }

    private void delete() throws IOException {
        Files.delete(from);
        try {
            close();
        } catch (Exception ignore) {
        } finally {
            service.shutdownNow();
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void run() {
        try {
            delete();
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                close();
            } catch (Exception ignore) {
            } finally {
                service.shutdownNow();
            }
        }
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void close() throws Exception {
        service.shutdown();
    }
}
