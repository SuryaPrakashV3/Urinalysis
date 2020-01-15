package com.project.urinalysis;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.CompoundButton;
import android.widget.TextView;
import android.widget.ToggleButton;

public class GetReadingsActivity extends AppCompatActivity {
    ToggleButton currOld;
    TextView report;
    TextView name, age, gender;
    TextView
        leukocytes,
        nitrite,
        urobilinogen,
        protein,
        ph,
        blood,
        specificGravity,
        ketone,
        bilirubin,
        glucose
    ;
    MyDbAdapter dba;
    Reading data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_readings);

        name = findViewById(R.id.nameView);
        age = findViewById(R.id.ageView);
        gender = findViewById(R.id.genderView);
        currOld = findViewById(R.id.oldToggleButton);

//      Status report view
        report = findViewById(R.id.report);

//      TextView parameters
        leukocytes = findViewById(R.id.leukocytes);
        nitrite = findViewById(R.id.nitrite);
        urobilinogen = findViewById(R.id.Urobilinogen);
        protein = findViewById(R.id.protein);
        ph = findViewById(R.id.ph);
        blood = findViewById(R.id.blood);
        specificGravity = findViewById(R.id.specificGravity);
        ketone = findViewById(R.id.ketone);
        bilirubin = findViewById(R.id.bilirubin);
        glucose = findViewById(R.id.glucose);

//        get data from SQLite database
        dba = new MyDbAdapter(this);
        data = dba.getData();

//        setting patient details from DB
        name.setText(data.getName());
        age.setText(data.getAge()+"");
        gender.setText(data.getGender());
//        filling new readings by default
        fillNewReadings();

//        toggle the content between old data and current data
        currOld.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked) {
                    fillOldReadings();
                } else {
                    fillNewReadings();
                }
            }
        });
    }

//    fills the table content(TextView parameters) with new DB parameter values
    @SuppressWarnings("")
    private void fillNewReadings() {
        leukocytes.setText("" + data.getLeukocytes());
        nitrite.setText("" + data.getNitrite());
        urobilinogen.setText("" + data.getUrobilinogen());
        protein.setText("" + data.getProtein());
        ph.setText("" + data.getPh());
        blood.setText("" + data.getBlood());
        specificGravity.setText("" + data.getSpecificGravity());
        ketone.setText("" + data.getKetone());
        bilirubin.setText("" + data.getBilirubin());
        glucose.setText("" + data.getGlucose());
        generateReport();
    }

//    fills the table content(TextView parameters) with old DB parameter values
    private void fillOldReadings() {
        leukocytes.setText("" + data.getLeukocytes_old());
        nitrite.setText("" + data.getNitrite_old());
        urobilinogen.setText("" + data.getUrobilinogen_old());
        protein.setText("" + data.getProtein_old());
        ph.setText("" + data.getPh_old());
        blood.setText("" + data.getBlood_old());
        specificGravity.setText("" + data.getSpecificGravity_old());
        ketone.setText("" + data.getKetone_old());
        bilirubin.setText("" + data.getBilirubin_old());
        glucose.setText("" + data.getGlucose_old());
        generateReport();
    }

//    updates "Status report" View based on the table content(TextView parameters)
    private void generateReport() {
        //threshold stores the value to which each parameter is compared to. Change it based on the expected value.
        double[] threshold = new double[]{-1, -1, 1, -1, 6, -1, 1.03, -1, -1, -1};

        //Gets the table contents(TextView parameters)
        double leukocytes = Double.parseDouble(this.leukocytes.getText().toString());
        double nitrite = Double.parseDouble(this.nitrite.getText().toString());
        double urobilinogen = Double.parseDouble(this.urobilinogen.getText().toString());
        double protein = Double.parseDouble(this.protein.getText().toString());
        double ph = Double.parseDouble(this.ph.getText().toString());
        double blood = Double.parseDouble(this.blood.getText().toString());
        double specificGravity = Double.parseDouble(this.specificGravity.getText().toString());
        double ketone = Double.parseDouble(this.ketone.getText().toString());
        double bilirubin = Double.parseDouble(this.bilirubin.getText().toString());
        double glucose = Double.parseDouble(this.glucose.getText().toString());
        //Buffer to store the complete report to update it at last
        StringBuilder reportBuffer = new StringBuilder();

        //Hard-coded strings for status report based on the comparison
        String negativeReport = "Result negative, Patient is healthy!\n";
        String leukocytesPositive =
                "The detection of white blood cells (WBC's) in the urine suggests a possible " +
                        "Urinary Tract Infection (UTI) somewhere in the urinary tract such as the bladder," +
                        " or the urethra. It is also a sign of kidney infection." +
                        " Kidney stones, pelvic area tumor, or a blockage in the urinary tract.\n"
                ;

        String nitritePositive =
                "Screening for possible asymptomatic infections caused by nitrate-reducing bacteria - " +
                        "Suggesting a possible Urinary Tract Infection (UTI)" +
                        "Possible infections by enteric bacteria.\n"
                ;
        String urobilinogenPositive =
                "Positive test results can indicate liver diseases such as cirrhosis, viral hepatitis," +
                        " liver damage due to drugs or toxic substances," +
                        " and/or conditions associated with increased RBC destruction (hemolytic anemia).\n"
                ;
        String proteinPositive =
                "Protein may be excreted in the urine when the kidneys are not working properly," +
                        " or when high levels of certain proteins are present in the bloodstream." +
                        "Small increases in protein in urine usually are not a cause for concern," +
                        " but larger amounts may indicate a kidney problem.\n"
                ;
        String phPositive =
                "Abnormal pH levels may indicate a kidney or urinary tract disorder. Acidity in your urine may also be a sign of kidney stones." +
                        " Regulating diet mainly controls urinary pH, although using medication can also control it." +
                        " Diets with animal proteins tends to have acidic urine while vegetable diets produce alkaline urine" +
                        " Urine pH tends to be more acidic during daytime and alkaline at night time.\n"
                ;
        String bloodPositive =
                "Also called hematuria, can be caused by " +
                        "Urinary Tract Infection (UTI), Kidney infection, medication, strenuous exercise." +
                        " False positive may be observed when severeity is low.\n"
                ;
        String sgPositive =
                "Result less than 1 is low. It is normally in range of 1.02 to 1.03. " +
                        "A higher than normal concentration often is a result of not drinking enough fluids - " +
                        "water-loss dehydration happens when people do not drink enough fluid.\n"
                ;
        String ketonePositive =
                "Also called Ketonuria. If your cells don't get enough glucose, your body burns fat which produces a substance called ketones, which can show up in your blood and urine. " +
                        "Sign of diabetes and requires follow-up testing. " +
                        "In healthy individuals, ketones formed are only negligible amounts appear in the urine.\n"
                ;
        String bilirubinPositive =
                "Bilirubin is by-product of haemoglobin degradation. If your liver is damaged, bilirubin can leak into the blood and urine. " +
                        "Is an early indication of liver disease such as hepatitis, a blockage. " +
                        "Note: this test is known to yield high rates of false positives and requires further investigation.\n"
                ;
        String glucosePositive =
                "Glucose in the urine could indicate diabetes or renal glycosuria.It is detectable in urine at blood sugar concentrations of 10 mmol/L (180 mg/dL) and above" +
                        ".  Normally the amount of sugar (glucose) in urine is too low to be detected. More accurate measurement must be done using blood samples. \n"
                ;

        //Adding to buffer based on comparison with threshold values.
        reportBuffer.append("\nLeukocyte test:\n");
        if(isOkay(leukocytes, threshold[0])) {
            reportBuffer.append(negativeReport);
        } else {
            reportBuffer.append(leukocytesPositive);
        }

        reportBuffer.append("\nNitrate test:\n");
        if(isOkay(nitrite, threshold[1])) {
            reportBuffer.append(negativeReport);
        } else {
            reportBuffer.append(nitritePositive);
        }

        reportBuffer.append("\nUrobilinogen test:\n");
        if(isOkay(urobilinogen, threshold[2])) {
            reportBuffer.append(negativeReport);
        } else {
            reportBuffer.append(urobilinogenPositive);
        }

        reportBuffer.append("\nProtein test:\n");
        if(isOkay(protein, threshold[3])) {
            reportBuffer.append(negativeReport);
        } else {
            reportBuffer.append(proteinPositive);
        }

        reportBuffer.append("\npH test:\n");
        if(isOkay(ph, threshold[4])) {
            reportBuffer.append(negativeReport);
        } else {
            reportBuffer.append(phPositive);
        }

        reportBuffer.append("\nBlood test:\n");
        if(isOkay(blood, threshold[5])) {
            reportBuffer.append(negativeReport);
        } else {
            reportBuffer.append(bloodPositive);
        }

        reportBuffer.append("\nSpecific gravity test:\n");
        if(!isOkay(specificGravity, threshold[6])) {
            reportBuffer.append(negativeReport);
        } else {
            reportBuffer.append(sgPositive);
        }

        reportBuffer.append("\nKetone test:\n");
        if(isOkay(ketone, threshold[7])) {
            reportBuffer.append(negativeReport);
        } else {
            reportBuffer.append(ketonePositive);
        }

        reportBuffer.append("\nBilirubin test:\n");
        if(isOkay(bilirubin, threshold[8])) {
            reportBuffer.append(negativeReport);
        } else {
            reportBuffer.append(bilirubinPositive);
        }

        reportBuffer.append("\nGlucose test:\n");
        if(isOkay(glucose, threshold[9])) {
            reportBuffer.append(negativeReport);
        } else {
            reportBuffer.append(glucosePositive);
        }

//        Updates the status report view using buffer
        report.setText(reportBuffer.toString());
    }

//    Checks if double a is less than or equal to double b ,(i.e.)  (a <= b)?true:false
    private boolean isOkay(double a, double b) {
        if(Double.compare(a, b) == 0 || Double.compare(a,b) < 0) {
            return true;
        }
        return false;
    }
}
