package com.iharkusha.util;

import com.iharkusha.convert.Converter;
import com.iharkusha.convert.impl.JsonConverter;
import com.iharkusha.convert.impl.XmlConverter;
import com.iharkusha.dataFormat.factoryImpl.JsonDataFormatFactory;
import com.iharkusha.dataFormat.factoryImpl.XmlDataFormatFactory;
import com.iharkusha.util.menu_mini_framework.Menu;
import com.iharkusha.util.menu_mini_framework.MenuItem;
import com.iharkusha.validation.ConverterExtension;
import com.iharkusha.validation.FormatValidator;
import org.xml.sax.SAXException;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.TransformerException;
import java.io.*;
import java.util.Scanner;

public class UserInterfaceFacade {
    private final Scanner scanner = new Scanner(System.in);
    private final ConverterExtension defaultFormatValidator = new FormatValidator();
    private String inputFilePath;
    private String outputFilePath;

    public void mainMenu() {
        Menu menu = new Menu();
        menu.setTitle("You are in the main menu. Choose module of the project to use:");
        menu.addItem(new MenuItem("JSON-XML compiler", this, "formats"));
        menu.execute();
    }

    private void formats() {
        Menu menu = new Menu();
        menu.setTitle("Choose conversion direction:");
        menu.addItem(new MenuItem("JSON-XML", this, "toXml"));
        menu.addItem(new MenuItem("XML-JSON", this, "toJson"));
        menu.execute();
    }

    private void toXml() {
        choosePaths();
        Converter converter = new JsonConverter(new XmlDataFormatFactory(), defaultFormatValidator);
        convert(converter);
    }

    private void toJson() {
        choosePaths();
        Converter converter = new XmlConverter(new JsonDataFormatFactory(), defaultFormatValidator);
        convert(converter);
    }

    private void choosePaths() {
        promptInputFilePath();
        inputFilePath = getInputLine();
        promptOutputFilePath();
        outputFilePath = getInputLine();
    }

    private void convert(Converter converter) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(outputFilePath))) {
            var input = readData();
            String outputData = converter.convert(input);
            writer.write(outputData);
            System.out.println("Conversion successful!");
        } catch (IOException | ParserConfigurationException | SAXException | TransformerException e) {
            System.out.println("Error during conversion: " + e.getMessage());
        }
    }

    private String readData() {
        try (BufferedReader reader = new BufferedReader(new FileReader(inputFilePath))) {
            StringBuilder inputData = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                inputData.append(line);
            }
            return inputData.toString();
        } catch (IOException e) {
            System.out.println("Error during reading file: " + e.getMessage());
        }
        return null;
    }

    private void promptInputFilePath() {
        System.out.print("Type path to the input file: ");
    }

    private void promptOutputFilePath() {
        System.out.print("Type path to the output file: ");
    }

    private String getInputLine() {
        return scanner.next();
    }
}
