public class GeneFinderBasic {
    private static final String START_CODON = "atg";
    private static final String END_CODON = "taa";

    private static boolean isGeneValid(int startCodonIdx, int endCodonIdx) {
        return (endCodonIdx - (3 + startCodonIdx)) % 3 == 0;
    }

    public static String findAllValidGenes(String str) {
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
}
