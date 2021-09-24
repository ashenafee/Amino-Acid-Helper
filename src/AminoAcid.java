import org.json.*;

import java.io.*;
import java.util.Iterator;
import java.util.Objects;

public class AminoAcid {

    private String aminoAcidSingle = "";
    private String aminoAcidThree = "";
    private String aminoAcidFull = "";

    private boolean polarity;
    private String charge;

    private String jsonString = "";
    private static JSONObject jsonObject;
    private static Iterator<String> jsonKeys;

    public AminoAcid() {}

    public AminoAcid(String aminoAcid) throws Exception {
        this.jsonObject = parseJsonFile();

        if (aminoAcid.length() == 1) {
            onlyOneLetterName(aminoAcid);
            this.aminoAcidSingle = aminoAcid;
        } else if (aminoAcid.length() == 3) {
            onlyThreeLetterName(aminoAcid);
            this.aminoAcidThree = aminoAcid;

        } else {
            this.aminoAcidFull = aminoAcid;
            this.aminoAcidSingle = jsonObject.getJSONObject(this.aminoAcidFull).getString("Single");
            this.aminoAcidThree = jsonObject.getJSONObject(this.aminoAcidFull).getString("Three");
        }
    }

    private void onlyOneLetterName(String aminoAcid) {
        jsonKeys = jsonObject.keys();
        while (jsonKeys.hasNext()) {
            String key = jsonKeys.next();
            if (Objects.equals(((JSONObject) jsonObject.get(key)).getString("Single"), aminoAcid)) {
                this.polarity = ((JSONObject) jsonObject.get(key)).getBoolean("Polar");
                this.charge = ((JSONObject) jsonObject.get(key)).getString("Charge");
                this.aminoAcidThree = ((JSONObject) jsonObject.get(key)).getString("Three");
                this.aminoAcidFull = key;
            }
        }
    }

    private void onlyThreeLetterName(String aminoAcid) {
        while (jsonKeys.hasNext()) {
            String key = jsonKeys.next();
            if (Objects.equals(((JSONObject) jsonObject.get(key)).getString("Three"), aminoAcid)) {
                this.polarity = ((JSONObject) jsonObject.get(key)).getBoolean("Polar");
                this.charge = ((JSONObject) jsonObject.get(key)).getString("Charge");
                this.aminoAcidSingle = ((JSONObject) jsonObject.get(key)).getString("Single");
                this.aminoAcidFull = key;
            }
        }
    }

    private JSONObject parseJsonFile() throws Exception {
        // Open <aa_data.json>
        String path = System.getProperty("user.dir") + "\\aa_data";
        File jsonFile = new File(".\\src\\aa_data.json");
        BufferedReader br = new BufferedReader(new FileReader(jsonFile));

        String st;
        while ((st = br.readLine()) != null)
            this.jsonString += st;

        this.jsonKeys = new JSONObject(jsonString).keys();

        return new JSONObject(jsonString);
    }

    protected String getSingleLetter() {
        return this.aminoAcidSingle;
    }

    protected String getShortForm() {
        return this.aminoAcidThree;
    }

    protected String getFullName() {
        return this.aminoAcidFull;
    }

    protected String getCharge() {
        return this.charge;
    }

    protected boolean getPolarity() {
        return this.polarity;
    }

    public static void main(String[] args) throws Exception {
        // Create AminoAcid from its full name
        AminoAcid full = new AminoAcid("Alanine");
        System.out.println(full.getSingleLetter());
        System.out.println(full.getShortForm());
        System.out.println(full.getFullName());

        // Create AminoAcid from its three-letter name
        AminoAcid three = new AminoAcid("Leu");
        System.out.println(three.getSingleLetter());
        System.out.println(three.getShortForm());
        System.out.println(three.getFullName());

        // Create AminoAcid from its single-letter name
        AminoAcid single = new AminoAcid("W");
        System.out.println(single.getSingleLetter());
        System.out.println(single.getShortForm());
        System.out.println(single.getFullName());
    }

}
