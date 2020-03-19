package com.example.carbonfootprint;


import java.util.Hashtable;
import java.util.LinkedHashMap;

public class EmissionCalculatorTable {
    private static EmissionCalculatorTable instance = new EmissionCalculatorTable();
    static private LinkedHashMap<String,Float> emissiontable;
    static private Hashtable<String,Float> conversionTable;


    private EmissionCalculatorTable() {
    emissiontable = new LinkedHashMap<String,Float>();
    conversionTable = new Hashtable<String,Float>();
    }

    /*
    Records the multiplication conversion factors and identifiers for different types of emissions.
     */
    private static void conversionTableValues(){
        conversionTable.put("type_train",0.1f);
        conversionTable.put("type_car",1.0f);
        conversionTable.put("type_plane",0.02f);
        conversionTable.put("type_computer",1.5f);
        conversionTable.put("type_laptop",0.1f);
        conversionTable.put("type_printer",0.1f);
        conversionTable.put("type_gym",0.8f);
        conversionTable.put("type_library",0.1f);
        conversionTable.put("type_lecture",0.1f);

    }

    public static void initialise(){
        emissiontable.clear();
        conversionTable.clear();
        conversionTableValues();
    }

    public static LinkedHashMap getEmissionTable(){
       return emissiontable;}

    /*
    multiplies an input number by the conversion factor and records the result along with the identifier in the table
    use with the identifiers in conversionTableValues
     */
    public static void calculateAndRecord(String type, Float amount){
        Float factor = conversionTable.get(type);
        Float value = factor*amount;
        emissiontable.put(type,value);
    }

    public static void removeLast() {
        String[] keys = emissiontable.keySet().toArray(new String[emissiontable.size()]);
        if(keys.length>0){emissiontable.remove(keys[keys.length -1]);}
        }

}

