package com.example.proiectdam.informatii;



import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class InformatiiJsonParser {

    public static final String NUME = "nume";
    public static final String EMAIL = "email";
    public static final String TELEFON = "nrTel";

    public static List<Facultate> fromJson(String json) {
        try {
            JSONArray array = new JSONArray(json);
            return readFacultati(array);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

    private static List<Facultate> readFacultati(JSONArray array) throws JSONException {
        List<Facultate> facultati = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            Facultate facultate = readInfo(array.getJSONObject(i));
            facultati.add(facultate);
        }
        return facultati;
    }

    private static Facultate readInfo(JSONObject object) throws JSONException {
        String numeFac = object.getString(NUME);
        String email = object.getString(EMAIL);
        String nrTel = object.getString(TELEFON);
        return new Facultate(numeFac,nrTel,email);
    }

}
