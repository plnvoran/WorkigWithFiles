package ru.plnvoran;

import com.codeborne.pdftest.PDF;
import com.codeborne.xlstest.XLS;
import com.opencsv.CSVReader;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class ParsingVariousFiles {
    private ClassLoader cl = ParsingVariousFiles.class.getClassLoader();

    @Test
    @DisplayName("Проверка содержимого файла типа PDF")
    void parsingPdfFileFromZip() throws Exception {
        try (InputStream is = cl.getResourceAsStream("files.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {

                if (entry.getName().equals("Treasure Island.pdf")) {
                    PDF pdf = new PDF(zis);
                    assertTrue(pdf.text.startsWith("The Old Sea-dog at the Admiral Benbow"));
                    assertTrue(pdf.text.contains("Dr. Livesey"));
                }
            }
        }
    }
    @Test
    @DisplayName("Проверка содержимого файла типа CSV")
    void parsingCVSFileFromZip() throws Exception {
        try (InputStream is = cl.getResourceAsStream("files.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("Characters_CVS.csv")) {
                    CSVReader csvReader = new CSVReader(new InputStreamReader(zis));
                    List<String[]> content = csvReader.readAll();
                    assertArrayEquals(new String[]{"Jim Hawkins", "The narrator of most of the novel"}, content.get(0));
                    assertArrayEquals(new String[]{"John Silver", "The secret leader of the pirates"}, content.get(1));
                    assertArrayEquals(new String[]{"Dr. Livesey", "A doctor and magistrate"}, content.get(2));
                }
            }
        }
    }
    @Test
    @DisplayName("Проверка содержимого файла типа XLSX")
    void zipXlsTest() throws Exception {
        try (InputStream is = cl.getResourceAsStream("files.zip");
             ZipInputStream zis = new ZipInputStream(is)) {
            ZipEntry entry;
            while ((entry = zis.getNextEntry()) != null) {
                if (entry.getName().equals("Characters_XLS.xlsx")) {
                    XLS xls = new XLS(zis);
                    assertTrue(xls.excel.getSheetAt(0).getRow(1).getCell(1).getStringCellValue().equals("The secret leader of the pirates"));
                    assertTrue(xls.excel.getSheetAt(0).getRow(2).getCell(1).getStringCellValue().startsWith("A doctor"));
                }
            }
        }
    }
    }

