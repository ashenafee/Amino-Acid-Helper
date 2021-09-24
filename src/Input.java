import java.util.Scanner;

public class Input {
    public static void main(String[] args) throws Exception {
        Scanner scn = new Scanner(System.in);
        boolean flag = true;

        while (flag) {
            // Ask the user to input their sequence
            System.out.println("Enter your amino acid sequence below (ASH/Alanine Serine Histidine/Ala Ser His):");
            AminoAcidSequence inputSequence = new AminoAcidSequence(scn.nextLine());

            // Output other ways of writing the inputted sequence
            System.out.println("Single-letter:\t" + inputSequence.toSingleName());
            System.out.println("Three-letter:\t" + inputSequence.toShortName());
            System.out.println("Full-name:\t\t" + inputSequence.toFullName());
            System.out.println("Charge:\t\t\t" + inputSequence.totalCharge());
            System.out.println("Polarity:\t\t" + inputSequence.polarity());

            // Ask the user whether they want to find another sequence
            System.out.println("=".repeat(80));
            System.out.println("Would you like to input another sequence (Y/N)?");
            String choice = scn.nextLine();

            if (choice.toUpperCase().equals("N")) {
                flag = false;
            }
        }


    }
}
