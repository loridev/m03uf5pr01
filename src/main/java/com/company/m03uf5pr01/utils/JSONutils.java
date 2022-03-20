package com.company.m03uf5pr01.utils;

import com.company.m03uf5pr01.models.*;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Path;
import java.util.ArrayList;
import javax.json.*;

public final class JSONutils {
    public static ArrayList readFromJSON(Path path, String className) {
        InputStream is = null;
        JsonReader jsonReader = null;

        try {
            is = new FileInputStream(path.toFile());
            jsonReader = Json.createReader(is);

            JsonArray jsonArray = jsonReader.readArray();

            if (className.equals("Propietari")) {
                ArrayList<Propietari> propietaris = new ArrayList<>();
                if (jsonArray.size() == 0) return propietaris;
                for (JsonValue row : jsonArray) {
                    JsonObject jsonObject = row.asJsonObject();

                    propietaris.add(new Propietari(jsonObject.getString("nom"), jsonObject.getString("password")));
                }
                return propietaris;
            }

        } catch (IOException ioe) {
            ioe.printStackTrace();
            return null;
        } finally {
            if(jsonReader != null) {
                jsonReader.close();
            }
            try {
                if (is != null) {
                    is.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return null;
    }

    public static void writeOnJSON(ArrayList collection, Path path) {
        OutputStream os = null;
        JsonArrayBuilder jsonArrayBuilder = Json.createArrayBuilder();
        JsonWriter jsonWriter = null;

        try {
            for (int i = 0; i < collection.size(); i++) {
                jsonArrayBuilder.add(convertToJsonObject(collection.toArray()[i]));
            }

            os = new FileOutputStream(path.toFile());
            jsonWriter = Json.createWriter(os);
            jsonWriter.writeArray(jsonArrayBuilder.build());
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if(jsonWriter != null) {
                jsonWriter.close();
            }
            try {
                os.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private static JsonObject convertToJsonObject(Object obj) {
        JsonObjectBuilder jsonObjectBuilder = Json.createObjectBuilder();

        if (obj instanceof Propietari) {
            Propietari propietari = (Propietari) obj;
            JsonArrayBuilder mascotesJson = Json.createArrayBuilder();
            for (Animal mascota : propietari.getMascotes()) {
                mascotesJson.add(convertToJsonObject(mascota));
            }
            jsonObjectBuilder.add("nom", propietari.getNom());
            jsonObjectBuilder.add("password", propietari.getPassword());
            jsonObjectBuilder.add("mascotes", mascotesJson);
        } else if (obj instanceof Animal) {
            if (obj instanceof Au) {
                Au au = (Au) obj;
                jsonObjectBuilder.add("urpesTrencades", au.isUrpesTrencades());
                jsonObjectBuilder.add("ratioRepeticioAtac", au.getRatioRepeticioAtac());
                jsonObjectBuilder.add("nom", au.getNom());
                jsonObjectBuilder.add("nivell", au.getNivell());
                jsonObjectBuilder.add("atac", au.getAtac());
                jsonObjectBuilder.add("defensa", au.getDefensa());
                jsonObjectBuilder.add("precisio", au.getPrecisio());
                jsonObjectBuilder.add("vida", au.getVida());
                jsonObjectBuilder.add("enverinat", au.isEnverinat());
                jsonObjectBuilder.add("propietari", convertToJsonObject(au.getPropietari()));
                jsonObjectBuilder.add("tipus", au.getTipus().toString());
            } else if (obj instanceof Mamifer) {
                Mamifer mamifer = (Mamifer) obj;
                jsonObjectBuilder.add("multiplicadorPuny", mamifer.getMultiplicadorPuny());
                jsonObjectBuilder.add("nom", mamifer.getNom());
                jsonObjectBuilder.add("nivell", mamifer.getNivell());
                jsonObjectBuilder.add("atac", mamifer.getAtac());
                jsonObjectBuilder.add("defensa", mamifer.getDefensa());
                jsonObjectBuilder.add("precisio", mamifer.getPrecisio());
                jsonObjectBuilder.add("vida", mamifer.getVida());
                jsonObjectBuilder.add("enverinat", mamifer.isEnverinat());
                jsonObjectBuilder.add("propietari", convertToJsonObject(mamifer.getPropietari()));
                jsonObjectBuilder.add("tipus", mamifer.getTipus().toString());
            } else if (obj instanceof Reptil) {
                Reptil reptil = (Reptil) obj;
                jsonObjectBuilder.add("precissioVeri", reptil.getPrecisio());
                jsonObjectBuilder.add("nom", reptil.getNom());
                jsonObjectBuilder.add("nivell", reptil.getNivell());
                jsonObjectBuilder.add("atac", reptil.getAtac());
                jsonObjectBuilder.add("defensa", reptil.getDefensa());
                jsonObjectBuilder.add("precisio", reptil.getPrecisio());
                jsonObjectBuilder.add("vida", reptil.getVida());
                jsonObjectBuilder.add("enverinat", reptil.isEnverinat());
                jsonObjectBuilder.add("propietari", convertToJsonObject(reptil.getPropietari()));
                jsonObjectBuilder.add("tipus", reptil.getTipus().toString());
            }

        }

        return jsonObjectBuilder.build();

    }
}
