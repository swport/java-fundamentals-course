public class GeneFinderAdvanced {

    private static boolean isGeneValid(int startCodonIdx, int endCodonIdx) {
        return (endCodonIdx - (3 + startCodonIdx)) % 3 == 0;
    }

    private static int findStartCodon(String str) {
        return str.indexOf("atg", 0);
    }

    private static int findStartCodon(String str, int startIndex) {
        return str.indexOf("atg", startIndex);
    }

    private static int findValidEndCodon(String str, int startCodon) {
        int taaIdx = str.indexOf("taa", startCodon + 3);
        int tagIdx = str.indexOf("tag", startCodon + 3);
        int tgaIdx = str.indexOf("tga", startCodon + 3);
        int min = -1;
        for(int codon: new int[]{taaIdx, tagIdx, tgaIdx}) {
            if(codon != -1 && (min == -1 || codon < min)) {
                min = codon;
            }
        }
        return min;
    }

    public static String findAllValidGenes(String str) {
        StringBuilder genes = new StringBuilder();

        int start = findStartCodon(str);
        int end = findValidEndCodon(str, start);

        while(start > -1 && end > -1) {
            if(isGeneValid(start, end)) {
                genes.append(str, start, end + 3);
                genes.append(", ");
            }
            start = findStartCodon(str, end + 3);
            end = findValidEndCodon(str, start + 3);
        }

        return genes.isEmpty()
                ? "Not-found"
                : genes.toString().replaceAll("[\\s,]+$", "");
    }
}
