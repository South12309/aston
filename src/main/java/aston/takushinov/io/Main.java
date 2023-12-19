package aston.takushinov.io;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.LinkedList;
import java.util.Scanner;
import java.util.function.UnaryOperator;
import java.util.regex.MatchResult;
import java.util.regex.Pattern;
import java.util.stream.Stream;

public class Main {

    public static void main(String[] args) {

        Path path = Path.of("resources","text.txt");
        Pattern regPattern = Pattern.compile("\\w+", Pattern.UNICODE_CHARACTER_CLASS);

        //1. Задан файл с текстом, найти и вывести в консоль все слова, начинающиеся с гласной буквы.
        String vowels = "аоуэиыеёяю";
        try (Stream<String> lines = Files.lines(path)){
            lines.flatMap(x->regPattern.matcher(x).results())
                    .map(MatchResult::group)
                    .filter(x->vowels.indexOf(x.charAt(0))!=-1)
                    .forEach(System.out::println);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
