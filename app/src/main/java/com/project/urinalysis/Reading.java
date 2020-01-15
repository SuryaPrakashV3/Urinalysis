package com.project.urinalysis;

import org.json.JSONException;
import org.json.JSONObject;

//Bean class to (retrieve and store) data (to and from) database
public class Reading {
    private String name;
    private int age;
    private String gender;
    //current values
    private double leukocytes;
    private double nitrite;
    private double urobilinogen;
    private double protein;
    private double ph;
    private double blood;
    private double specificGravity;
    private double ketone;
    private double bilirubin;
    private double glucose;

    //old values
    private double leukocytes_old;
    private double nitrite_old;
    private double urobilinogen_old;
    private double protein_old;
    private double ph_old;
    private double blood_old;
    private double specificGravity_old;
    private double ketone_old;
    private double bilirubin_old;
    private double glucose_old;

//  addong new reading manually
    public void newReading(double leukocytes, double nitrite, double urobilinogen, double protein, double ph, double blood, double specificGravity, double ketone, double bilirubin, double glucose) {
//        current values shifted to old
        this.leukocytes_old = this.leukocytes;
        this.nitrite_old = this.nitrite;
        this.urobilinogen_old = this.urobilinogen ;
        this.protein_old = this.protein;
        this.ph_old = this.ph;
        this.blood_old = this.blood;
        this.specificGravity_old= this.specificGravity;
        this.ketone_old = this.ketone;
        this.bilirubin_old = this.bilirubin;
        this.glucose_old = this.glucose;
//        adding new values
        this.leukocytes = leukocytes;
        this.nitrite = nitrite;
        this.urobilinogen = urobilinogen ;
        this.protein = protein;
        this.ph = ph;
        this.blood = blood;
        this.specificGravity = specificGravity;
        this.ketone = ketone;
        this.bilirubin = bilirubin;
        this.glucose = glucose;
    }

//    updating current reading using Stringified-JSON object
    public void newJSONReading(String data) {
        try {
            JSONObject json = new JSONObject(data);
//        current values shifted to old
            this.leukocytes_old = this.leukocytes;
            this.nitrite_old = this.nitrite;
            this.urobilinogen_old = this.urobilinogen ;
            this.protein_old = this.protein;
            this.ph_old = this.ph;
            this.blood_old = this.blood;
            this.specificGravity_old= this.specificGravity;
            this.ketone_old = this.ketone;
            this.bilirubin_old = this.bilirubin;
            this.glucose_old = this.glucose;
//        updating new values
            this.leukocytes = Double.parseDouble(json.getString("leukocytes"));
            this.nitrite = Double.parseDouble(json.getString("nitrite"));
            this.urobilinogen = Double.parseDouble(json.getString("urobilinogen"));
            this.protein = Double.parseDouble(json.getString("protein"));
            this.ph = Double.parseDouble(json.getString("ph"));
            this.blood = Double.parseDouble(json.getString("blood"));
            this.specificGravity = Double.parseDouble(json.getString("specificGravity"));
            this.ketone = Double.parseDouble(json.getString("ketone"));
            this.bilirubin = Double.parseDouble(json.getString("bilirubin"));
            this.glucose = Double.parseDouble(json.getString("glucose"));
        } catch (JSONException je) {
            je.printStackTrace();
        }
    }

//  Default constructor
    public Reading() {
        this.name = "Unknown";
        this.age = -1;
        this.gender = "Unknown";
        this.leukocytes = -1;
        this.nitrite = -1;
        this.urobilinogen = -1;
        this.protein = -1;
        this.ph = -1;
        this.blood = -1;
        this.specificGravity = -1;
        this.ketone = -1;
        this.bilirubin = -1;
        this.glucose = -1;
        this.leukocytes_old = -1;
        this.nitrite_old = -1;
        this.urobilinogen_old = -1;
        this.protein_old = -1;
        this.ph_old = -1;
        this.blood_old = -1;
        this.specificGravity_old = -1;
        this.ketone_old = -1;
        this.bilirubin_old = -1;
        this.glucose_old = -1;
    }

    public Reading(String name, int age, String gender, double leukocytes, double nitrite, double urobilinogen, double protein, double ph, double blood, double specificGravity, double ketone, double bilirubin, double glucose, double leukocytes_old, double nitrite_old, double urobilinogen_old, double protein_old, double ph_old, double blood_old, double specificGravity_old, double ketone_old, double bilirubin_old, double glucose_old) {
        this.name = name;
        this.age = age;
        this.gender = gender;
        this.leukocytes = leukocytes;
        this.nitrite = nitrite;
        this.urobilinogen = urobilinogen;
        this.protein = protein;
        this.ph = ph;
        this.blood = blood;
        this.specificGravity = specificGravity;
        this.ketone = ketone;
        this.bilirubin = bilirubin;
        this.glucose = glucose;
        this.leukocytes_old = leukocytes_old;
        this.nitrite_old = nitrite_old;
        this.urobilinogen_old = urobilinogen_old;
        this.protein_old = protein_old;
        this.ph_old = ph_old;
        this.blood_old = blood_old;
        this.specificGravity_old = specificGravity_old;
        this.ketone_old = ketone_old;
        this.bilirubin_old = bilirubin_old;
        this.glucose_old = glucose_old;
    }

//    Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public double getLeukocytes() {
        return leukocytes;
    }

    public void setLeukocytes(double leukocytes) {
        this.leukocytes = leukocytes;
    }

    public double getNitrite() {
        return nitrite;
    }

    public void setNitrite(double nitrite) {
        this.nitrite = nitrite;
    }

    public double getUrobilinogen() {
        return urobilinogen;
    }

    public void setUrobilinogen(double urobilinogen) {
        this.urobilinogen = urobilinogen;
    }

    public double getProtein() {
        return protein;
    }

    public void setProtein(double protein) {
        this.protein = protein;
    }

    public double getPh() {
        return ph;
    }

    public void setPh(double ph) {
        this.ph = ph;
    }

    public double getBlood() {
        return blood;
    }

    public void setBlood(double blood) {
        this.blood = blood;
    }

    public double getSpecificGravity() {
        return specificGravity;
    }

    public void setSpecificGravity(double specificGravity) {
        this.specificGravity = specificGravity;
    }

    public double getKetone() {
        return ketone;
    }

    public void setKetone(double ketone) {
        this.ketone = ketone;
    }

    public double getBilirubin() {
        return bilirubin;
    }

    public void setBilirubin(double bilirubin) {
        this.bilirubin = bilirubin;
    }

    public double getGlucose() {
        return glucose;
    }

    public void setGlucose(double glucose) {
        this.glucose = glucose;
    }

    public double getLeukocytes_old() {
        return leukocytes_old;
    }

    public void setLeukocytes_old(double leukocytes_old) {
        this.leukocytes_old = leukocytes_old;
    }

    public double getNitrite_old() {
        return nitrite_old;
    }

    public void setNitrite_old(double nitrite_old) {
        this.nitrite_old = nitrite_old;
    }

    public double getUrobilinogen_old() {
        return urobilinogen_old;
    }

    public void setUrobilinogen_old(double urobilinogen_old) {
        this.urobilinogen_old = urobilinogen_old;
    }

    public double getProtein_old() {
        return protein_old;
    }

    public void setProtein_old(double protein_old) {
        this.protein_old = protein_old;
    }

    public double getPh_old() {
        return ph_old;
    }

    public void setPh_old(double ph_old) {
        this.ph_old = ph_old;
    }

    public double getBlood_old() {
        return blood_old;
    }

    public void setBlood_old(double blood_old) {
        this.blood_old = blood_old;
    }

    public double getSpecificGravity_old() {
        return specificGravity_old;
    }

    public void setSpecificGravity_old(double specificGravity_old) {
        this.specificGravity_old = specificGravity_old;
    }

    public double getKetone_old() {
        return ketone_old;
    }

    public void setKetone_old(double ketone_old) {
        this.ketone_old = ketone_old;
    }

    public double getBilirubin_old() {
        return bilirubin_old;
    }

    public void setBilirubin_old(double bilirubin_old) {
        this.bilirubin_old = bilirubin_old;
    }

    public double getGlucose_old() {
        return glucose_old;
    }

    public void setGlucose_old(double glucose_old) {
        this.glucose_old = glucose_old;
    }

//    method to return patient details in String format
    public String personalDetailsToStr() {
        StringBuffer buffer = new StringBuffer();
        buffer.append( "Person details: "
                + this.getName() + " "
                + this.getAge() + " "
                + this.getGender()
        );
        return buffer.toString();
    }

//    method to return current readings in String format
    public String newReadStr() {
        StringBuffer buffer = new StringBuffer();
        buffer.append( "New readings: " +
                + this.getLeukocytes() + " " +
                + this.getNitrite() + " " +
                + this.getUrobilinogen() + " " +
                + this.getProtein() + " " +
                + this.getPh() + " " +
                + this.getBlood() + " " +
                + this.getSpecificGravity() + " " +
                + this.getKetone() + " " +
                + this.getBilirubin() + " " +
                + this.getGlucose()

        );
        return buffer.toString();
    }

    //    method to return old readings in String format
    public String oldReadStr() {
        StringBuffer buffer = new StringBuffer();
        buffer.append( "Old readings: " +
                + this.getLeukocytes_old() + " " +
                + this.getNitrite_old() + " " +
                + this.getUrobilinogen_old() + " " +
                + this.getProtein_old() + " " +
                + this.getPh_old() + " " +
                + this.getBlood_old() + " " +
                + this.getSpecificGravity_old() + " " +
                + this.getKetone_old() + " " +
                + this.getBilirubin_old() + " " +
                + this.getGlucose_old()

        );
        return buffer.toString();
    }
}
