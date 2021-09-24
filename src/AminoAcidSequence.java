import java.util.ArrayList;
import java.util.Scanner;

public class AminoAcidSequence extends AminoAcid {


    private String[] singleSequence = null;
    private ArrayList<AminoAcid> aaSequence = new ArrayList<AminoAcid>();

    public AminoAcidSequence(String sequence) throws Exception {
        if (sequence.equals(sequence.toUpperCase())) {
            this.singleSequence = sequence.split("");

            for (String aminoAcid : singleSequence) {
                AminoAcid aa = new AminoAcid(aminoAcid);
                aaSequence.add(aa);
            }
        } else {
            this.singleSequence = sequence.split(" ");

            for (String aminoAcid : singleSequence) {
                AminoAcid aa = new AminoAcid(aminoAcid);
                aaSequence.add(aa);
            }
        }
    }

    public String toFullName() {
        String fullSequence = "";
        for (AminoAcid aa : aaSequence) {
            fullSequence += aa.getFullName() + "-";
        }

        return fullSequence.substring(0, fullSequence.length() - 1);
    }

    public String toShortName() {
        String shortSequence = "";
        for (AminoAcid aa : aaSequence) {
            shortSequence += aa.getShortForm() + "-";
        }

        return shortSequence.substring(0, shortSequence.length() - 1);
    }

    public String toSingleName() {
        String singleSequence = "";
        for (AminoAcid aa : aaSequence) {
            singleSequence += aa.getSingleLetter() + "-";
        }

        return singleSequence.substring(0, singleSequence.length() - 1);
    }

    public int totalCharge() {
        int charge = 0;
        for (AminoAcid aa : aaSequence) {
            if (aa.getCharge().equals("+")) {
                charge += 1;
            } else if (aa.getCharge().equals("-")) {
                charge -= 1;
            }
        }
        return charge;
    }

    public String polarity() {
        int polar = 0;
        int nonPolar = 0;
        for (AminoAcid aa : aaSequence) {
            if (aa.getPolarity()) {
                polar += 1;
            } else {
                nonPolar += 1;
            }
        }

        if (polar - nonPolar == 0) {
            return "The number of polar/non-polar amino acids is the same.";
        } else if (polar - nonPolar > 0) {
            return "There are " + (polar - nonPolar) + " more polar amino acids than non-polar.";
        } else {
            return "There are " + (nonPolar - polar) + " more non-polar amino acids than polar.";
        }
    }

    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        AminoAcidSequence test = new AminoAcidSequence(scn.nextLine());
        System.out.println(test.toSingleName());
    }
}
