import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
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
                String genes = findAllValidGenes(dnaParts[1].toLowerCase());
                System.out.println("All genes in file, "+dnaParts[0]+" : " + genes);
            }
        }
    }

    private static boolean isGeneValid(int startCodonIdx, int endCodonIdx) {
        return (startCodonIdx - (3 + endCodonIdx)) % 3 == 0;
    }

    private static String findAllValidGenes(String str) {
        StringBuilder genes = new StringBuilder();
        int atg = str.indexOf(START_CODON);
        int taa = str.indexOf(END_CODON, atg + 3);
        int j = taa + 3;
        // as long as we can find the gene
        while (atg > -1 && taa > -1) {
            // is the gene valid? if so, append it to the valid genes list
            if(isGeneValid(taa, atg)) {
                genes.append(str, atg, j);
                genes.append(", ");
            }
            atg = str.indexOf(START_CODON, j);
            taa = str.indexOf(END_CODON, atg + 3);
            j = taa + 3;
        }

        return genes.isEmpty()
                ? "Not-found"
                : genes.toString().replaceAll("[\\s,]+$", "");
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
