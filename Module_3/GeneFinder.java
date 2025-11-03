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
                String genes = GeneFinderAdvanced.findAllValidGenes(dnaParts[1].toLowerCase());
                System.out.println("All genes in file, "+dnaParts[0]+" : " + genes);
            }
        }
    }

    private static List<String> getDnaStrings(String fileType) {
        List<String> dnaList = new ArrayList<>();
        for(int i = 4; i < 5; i++) {
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
