import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Telephone {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите имя:");
        String name = scanner.nextLine();

        System.out.println("Введите фамилию:");
        String surname = scanner.nextLine();

        System.out.println("Введите отчество:");
        String patronymic = scanner.nextLine();

        System.out.println("Введите дату рождения в формате dd.mm.yyyy:");
        String dateString = scanner.nextLine();

        System.out.println("Введите номер телефона:");
        String phoneNumber = scanner.nextLine();

        System.out.println("Введите пол (f или m):");
        String gender = scanner.nextLine();

        if (!gender.equals("f") && !gender.equals("m")) {
            System.out.println("Неверный ввод. Пол должен быть 'f' или 'm'.");
            return;
        }

        // Проверка количества введенных данных
        List<String> inputList = new ArrayList<>();
        inputList.add(name);
        inputList.add(surname);
        inputList.add(patronymic);
        inputList.add(dateString);
        inputList.add(phoneNumber);
        inputList.add(gender);

        if (6 != inputList.size()) {
            System.out.println("Ошибка: введены меньше или больше данных, чем требуется.");
            return;
        }

        // Проверка формата даты
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        try {
            LocalDate.parse(dateString, formatter);
        } catch (DateTimeParseException e) {
            System.out.println("Ошибка: неверный формат даты. Должен быть в формате dd.MM.yyyy.");
            return;
        }

        // Проверка формата телефона
        Pattern phonePattern = Pattern.compile("\\d{11}");
        Matcher phoneMatcher = phonePattern.matcher(phoneNumber);
        if (!phoneMatcher.matches()) {
            System.out.println("Ошибка: неверный формат телефона. Должен быть в формате 83334445555.");
            return;
        }

        // Проверка формата пол
        if (!gender.equals("f") && !gender.equals("m")) {
            System.out.println("Ошибка: неверный ввод. Пол должен быть 'f' или 'm'.");
            return;
        }

        // Создание файла и запись данных
        File file = new File(surname + ".txt");
        try (FileWriter writer = new FileWriter(file)) {
            writer.write(surname + " " + name + " " + patronymic + " " + dateString + " " + phoneNumber + " " + gender + "\n");
        } catch (IOException e) {
            System.out.println("Ошибка при записи файла: " + e.getMessage());
            return;
        }

        System.out.println("Данные успешно сохранены в файл " + file.getName() + ".");
    }
}