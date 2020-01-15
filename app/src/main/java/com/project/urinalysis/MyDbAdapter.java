package com.project.urinalysis;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Message;
import android.util.Log;

public class MyDbAdapter {
        myDbHelper myhelper;

        public MyDbAdapter(Context context) {
            myhelper = new myDbHelper(context);
//            helps with first time insertion with default values in case of user entering patient details first
            if(isEmpty()) {
                insertData(new Reading());
            }
        }

        public long insertData(Reading data) {
            Log.e("@MyDbAdapter@insertData", data.personalDetailsToStr()+ "\n" + data.newReadStr() + "\n" + data.oldReadStr());
            SQLiteDatabase dbb = myhelper.getWritableDatabase();

//            clear older data from the table and store it newly, thus maintaining a single record
            dbb.delete(myhelper.TABLE_NAME, null, null);

//            Stores data in key:value pairs
            //Patient details
            ContentValues contentValues = new ContentValues();
            contentValues.put(myDbHelper.NAME, data.getName());
            contentValues.put(myDbHelper.AGE, data.getAge());
            contentValues.put(myDbHelper.GENDER, data.getGender());

            //current readings
            contentValues.put(myDbHelper.LEUKOCYTES, data.getLeukocytes());
            contentValues.put(myDbHelper.NITRITE, data.getNitrite());
            contentValues.put(myDbHelper.UROBILINOGEN, data.getUrobilinogen());
            contentValues.put(myDbHelper.PROTEIN, data.getProtein());
            contentValues.put(myDbHelper.PH, data.getPh());
            contentValues.put(myDbHelper.BLOOD, data.getBlood());
            contentValues.put(myDbHelper.SPECIFICGRAVITY, data.getSpecificGravity());
            contentValues.put(myDbHelper.KETONES, data.getKetone());
            contentValues.put(myDbHelper.BILIRUBIN, data.getBilirubin());
            contentValues.put(myDbHelper.GLUCOSE, data.getGlucose());

            //old readings
            contentValues.put(myDbHelper.LEUKOCYTESO, data.getLeukocytes_old());
            contentValues.put(myDbHelper.NITRITEO, data.getNitrite_old());
            contentValues.put(myDbHelper.UROBILINOGENO, data.getUrobilinogen_old());
            contentValues.put(myDbHelper.PROTEINO, data.getProtein_old());
            contentValues.put(myDbHelper.PHO, data.getPh_old());
            contentValues.put(myDbHelper.BLOODO, data.getBlood_old());
            contentValues.put(myDbHelper.SPECIFICGRAVITYO, data.getSpecificGravity_old());
            contentValues.put(myDbHelper.KETONESO, data.getKetone_old());
            contentValues.put(myDbHelper.BILIRUBINO, data.getBilirubin_old());
            contentValues.put(myDbHelper.GLUCOSEO, data.getGlucose_old());

//            insertion of ContentValues into the table
            long id = dbb.insert(myDbHelper.TABLE_NAME, null , contentValues);
            return id;
        }

        public boolean isEmpty() {
            boolean isEmpty = false;
//            returns count of the whole table
            String query = "SELECT count(*) FROM " + myDbHelper.TABLE_NAME;
            Cursor mcursor = myhelper.getReadableDatabase().rawQuery(query , null);
            mcursor.moveToFirst();
            int icount = mcursor.getInt(0);
            if(icount == 0) {
                isEmpty = true;
            }
            return isEmpty;
        }

        public Reading getData() {
            SQLiteDatabase db = myhelper.getWritableDatabase();
//            String array of column names
            String[] columns = {
                    myDbHelper.NAME,
                    myDbHelper.AGE,
                    myDbHelper.GENDER,
                    myDbHelper.LEUKOCYTES,
                    myDbHelper.NITRITE,
                    myDbHelper.UROBILINOGEN,
                    myDbHelper.PROTEIN,
                    myDbHelper.PH,
                    myDbHelper.BLOOD,
                    myDbHelper.SPECIFICGRAVITY,
                    myDbHelper.KETONES,
                    myDbHelper.BILIRUBIN,
                    myDbHelper.GLUCOSE,
                    myDbHelper.LEUKOCYTESO,
                    myDbHelper.NITRITEO,
                    myDbHelper.UROBILINOGENO,
                    myDbHelper.PROTEINO,
                    myDbHelper.PHO,
                    myDbHelper.BLOODO,
                    myDbHelper.SPECIFICGRAVITYO,
                    myDbHelper.KETONESO,
                    myDbHelper.BILIRUBINO,
                    myDbHelper.GLUCOSEO,

            };
//            getting the mentioned columns in a Cursor
            Cursor cursor =db.query(myDbHelper.TABLE_NAME,columns,null,null,null,null,null);

            //Object to store values from Cursor
            Reading data = new Reading();

            // todo while not required as only one record is maintained
            while (cursor.moveToNext()) {
                data.setName(cursor.getString(cursor.getColumnIndex(myDbHelper.NAME)));
                data.setAge(cursor.getInt(cursor.getColumnIndex(myDbHelper.AGE)));
                data.setGender(cursor.getString(cursor.getColumnIndex(myDbHelper.GENDER)));

                data.setLeukocytes(cursor.getInt(cursor.getColumnIndex(myDbHelper.LEUKOCYTES)));
                data.setNitrite(cursor.getInt(cursor.getColumnIndex(myDbHelper.NITRITE)));
                data.setUrobilinogen(cursor.getInt(cursor.getColumnIndex(myDbHelper.UROBILINOGEN)));
                data.setProtein(cursor.getInt(cursor.getColumnIndex(myDbHelper.PROTEIN)));
                data.setPh(cursor.getInt(cursor.getColumnIndex(myDbHelper.PH)));
                data.setBlood(cursor.getInt(cursor.getColumnIndex(myDbHelper.BLOOD)));
                data.setSpecificGravity(cursor.getInt(cursor.getColumnIndex(myDbHelper.SPECIFICGRAVITY)));
                data.setKetone(cursor.getInt(cursor.getColumnIndex(myDbHelper.KETONES)));
                data.setBilirubin(cursor.getInt(cursor.getColumnIndex(myDbHelper.BILIRUBIN)));
                data.setGlucose(cursor.getInt(cursor.getColumnIndex(myDbHelper.GLUCOSE)));

                data.setLeukocytes_old(cursor.getInt(cursor.getColumnIndex(myDbHelper.LEUKOCYTESO)));
                data.setNitrite_old(cursor.getInt(cursor.getColumnIndex(myDbHelper.NITRITEO)));
                data.setUrobilinogen_old(cursor.getInt(cursor.getColumnIndex(myDbHelper.UROBILINOGENO)));
                data.setProtein_old(cursor.getInt(cursor.getColumnIndex(myDbHelper.PROTEINO)));
                data.setPh_old(cursor.getInt(cursor.getColumnIndex(myDbHelper.PHO)));
                data.setBlood_old(cursor.getInt(cursor.getColumnIndex(myDbHelper.BLOODO)));
                data.setSpecificGravity_old(cursor.getInt(cursor.getColumnIndex(myDbHelper.SPECIFICGRAVITYO)));
                data.setKetone_old(cursor.getInt(cursor.getColumnIndex(myDbHelper.KETONESO)));
                data.setBilirubin_old(cursor.getInt(cursor.getColumnIndex(myDbHelper.BILIRUBINO)));
                data.setGlucose_old(cursor.getInt(cursor.getColumnIndex(myDbHelper.GLUCOSEO)));
            }
            return data;
        }

        //update method to enter patient details
        public int updatePersonalDetails(String oldName , String newName, int newAge, String newGender)
        {
            Log.e("Updating: ", oldName + " to " + newName + "who is a " + newGender+ " of age " + newAge);
            SQLiteDatabase db = myhelper.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put(myDbHelper.NAME,newName);
            contentValues.put(myDbHelper.AGE,newAge);
            contentValues.put(myDbHelper.GENDER,newGender);
            String[] whereArgs= {oldName};
//            updating name, age, and gender alone
            int count =db.update(myDbHelper.TABLE_NAME,contentValues, myDbHelper.NAME+" = ?",whereArgs );
            return count;
        }

//        Template helper class for database
//              consists of the table details
        static class myDbHelper extends SQLiteOpenHelper
        {
            private static final String DATABASE_NAME = "myDatabase";    // Database Name
            private static final String TABLE_NAME = "myTable";   // Table Name
            private static final int DATABASE_Version = 1;    // Database Version
//            Column names
            private static final String NAME = "name";
            private static final String AGE = "age";
            private static final String GENDER = "gender";

            private static final String LEUKOCYTES = "leukocytes";
            private static final String NITRITE = "nitrite";
            private static final String UROBILINOGEN= "urobilinogen";
            private static final String PROTEIN = "protein";
            private static final String PH = "pH";
            private static final String BLOOD = "blood";
            private static final String SPECIFICGRAVITY = "specificGravity";
            private static final String KETONES = "ketone";
            private static final String BILIRUBIN = "bilirubin";
            private static final String GLUCOSE = "glucose";

            private static final String LEUKOCYTESO = "leukocytesold";
            private static final String NITRITEO = "nitriteold";
            private static final String UROBILINOGENO= "urobilinogenold";
            private static final String PROTEINO = "proteinold";
            private static final String PHO = "pHold";
            private static final String BLOODO = "bloodold";
            private static final String SPECIFICGRAVITYO = "specificGravityold";
            private static final String KETONESO = "ketoneold";
            private static final String BILIRUBINO = "bilirubinold";
            private static final String GLUCOSEO = "glucoseold";

//            Query to create the table
            private static final String CREATE_TABLE = "CREATE TABLE "+ TABLE_NAME+
                    " ("+NAME+" VARCHAR(255) PRIMARY KEY ," +
                    AGE+" VARCHAR(255),"+
                    GENDER+" VARCHAR(255),"+

                    LEUKOCYTES+" FLOAT(255)," +
                    NITRITE+" FLOAT(255)," +
                    UROBILINOGEN+" FLOAT(255)," +
                    PROTEIN+" FLOAT(255)," +
                    PH+" FLOAT(255)," +
                    BLOOD+" FLOAT(255)," +
                    SPECIFICGRAVITY+"  FLOAT(255)," +
                    KETONES+" FLOAT(255)," +
                    BILIRUBIN+" FLOAT(255)," +
                    GLUCOSE+" FLOAT(255)," +

                    LEUKOCYTESO+" FLOAT(255)," +
                    NITRITEO+" FLOAT(255)," +
                    UROBILINOGENO+" FLOAT(255)," +
                    PROTEINO+" FLOAT(255)," +
                    PHO+" FLOAT(255)," +
                    BLOODO+" FLOAT(255)," +
                    SPECIFICGRAVITYO+" FLOAT(255)," +
                    KETONESO+" FLOAT(255)," +
                    BILIRUBINO+" FLOAT(255)," +
                    GLUCOSEO+" FLOAT(255));";
//            Query to drop the table
            private static final String DROP_TABLE ="DROP TABLE IF EXISTS "+TABLE_NAME;
            private Context context;

            public myDbHelper(Context context) {
                super(context, DATABASE_NAME, null, DATABASE_Version);
                this.context=context;
            }

            public void onCreate(SQLiteDatabase db) {

                try {
                    Log.e("DBHelper ", "table created");
                    db.execSQL(CREATE_TABLE);
                } catch (Exception e) {
                    e.printStackTrace();
//                    Message.message(context,""+e);
                }
            }

            @Override
            public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
                try {
//                    Message.message(context,"OnUpgrade");
                    db.execSQL(DROP_TABLE);
                    onCreate(db);
                }catch (Exception e) {
                    e.printStackTrace();
//                    Message.message(context,""+e);
                }
            }
        }
    }

