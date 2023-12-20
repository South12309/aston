package aston.takushinov.io;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.function.UnaryOperator;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        Path path = Path.of("resources", "text.txt");
        Pattern regPatternForFindWords = Pattern.compile("\\w+", Pattern.UNICODE_CHARACTER_CLASS);

        //1. Задан файл с текстом, найти и вывести в консоль все слова, начинающиеся с гласной буквы.
        String vowels = "аоуэиыеёяю";
        try (Stream<String> lines = Files.lines(path)){
            lines.flatMap(x->regPatternForFindWords.matcher(x).results())
                    .map(MatchResult::group)
                    .filter(x->vowels.indexOf(x.charAt(0))!=-1)
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //2. Задан файл с текстом, найти и вывести в консоль все слова,  для которых последняя буква одного слова совпадает с первой буквой следующего слова
        try (Scanner scanner = new Scanner(path)) {
            String prev="", curr="";
            while (scanner.hasNext()) {
                curr = regPatternForFindWords.matcher(scanner.next()).results().findFirst().get().group();
                if (!prev.isEmpty() && prev.charAt(prev.length()-1)==curr.charAt(0)) {
                    System.out.println(prev + " " + curr);
                }
                prev = curr;
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //3. Задан файл с текстом. В каждой строке найти и вывести наибольшее число цифр, идущих подряд.
        Pattern regPatternForFindNumbers = Pattern.compile("\\d+", Pattern.UNICODE_CHARACTER_CLASS);
        try(Stream<String> lines = Files.lines(path);) {
            lines
                    .map(x->regPatternForFindNumbers.matcher(x).results()
                            .map(MatchResult::group)
                            .max(Comparator.comparingInt(String::length)))
                    .filter(x-> x.isPresent())
                    .map(x->x.get())
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //4. Задан файл с java-кодом. Прочитать текст программы из файла и все слова public
        // в объявлении атрибутов и методов класса заменить на слово private. Результат сохранить в другой заранее созданный файл.

        Path pathResult = Path.of("resources", "textResult.txt");
        try (Stream<String> lines = Files.lines(path);
        FileWriter scannerResult = new FileWriter(pathResult.toFile())) {
            lines
                    .map(x -> x.replace("public", "private"))
                    .forEach(str -> {
                        try {
                            scannerResult.write(str + "\n");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });

        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        //5. Задан файл с java-кодом. Прочитать текст программы из файла и записать
        // в другой файл в обратном порядке символы каждой строки.
        Path pathResult2 = Path.of("resources", "textResult2.txt");
        try (Stream<String> lines = Files.lines(path); FileWriter scannerResult = new FileWriter(pathResult2.toFile())) {
            lines
                    .map(x->new StringBuilder(x).reverse())
                    .forEach(x-> {
                        try {
                            scannerResult.write(x.toString() + "\n");
                        } catch (IOException e) {
                            throw new RuntimeException(e);
                        }
                    });
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
