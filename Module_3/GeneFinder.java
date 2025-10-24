import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

public class GeneFinder {
    private static final String START_CODON = "atg";
    private static final String END_CODON = "taa";

    public static void main(String[] args) {
        List<String> dnaList = getDnaStrings("dna");
        for(String dna: dnaList) {
            String[] dnaParts = dna.split(":");
            if(dnaParts.length == 2) {
                String gene = findGene(dnaParts[1]);
                System.out.println("First gene in file, "+dnaParts[0]+" : " + gene);
            }
        }
    }

    private static String findGene(String str) {
        str = str.toLowerCase();
        int atg = str.indexOf(START_CODON);
        int taa = str.indexOf(END_CODON, atg+3);
        if((taa - (3 + atg)) % 3 == 0) {
            return str.substring(atg, taa + 3);
        }
        return "NA";
    }

    private static List<String> getDnaStrings(String fileType) {
        List<String> dnaList = new ArrayList<>();
        for(int i = 1; i < 4; i++) {
            final String fileName = fileType + i + ".txt";
            try {
                String content = Files.readString(Path.of(fileName));
                dnaList.add(fileName+":"+content.trim().toLowerCase());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return dnaList;
    }
}
