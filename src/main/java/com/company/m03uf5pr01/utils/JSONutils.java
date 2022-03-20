package com.company.m03uf5pr01.utils;

import com.company.m03uf5pr01.models.Animal;
import com.company.m03uf5pr01.models.Propietari;

import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;
import javax.json.*;

public final class JSONutils {
    public static JsonArray readFromJSON(Path path) {
        InputStream is = null;
        JsonReader jsonReader = null;

        try {
            is = new FileInputStream(path.toFile());
            jsonReader = Json.createReader(is);

            return jsonReader.readArray();
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
            JsonArrayBuilder mascotesJson = Json.createArrayBuilder(propietari.getMascotes());
            /*for (Animal mascota : propietari.getMascotes()) {
                mascotesJson.add(convertToJsonObject(mascota));
            }*/
            jsonObjectBuilder.add("nom", propietari.getNom());
            jsonObjectBuilder.add("password", propietari.getPassword());
            jsonObjectBuilder.add("mascotes", mascotesJson);
        } else if (obj instanceof Animal) {
            Animal animal = (Animal) obj;

            jsonObjectBuilder.add("nom", animal.getNom());
            jsonObjectBuilder.add("nivell", animal.getNivell());
            jsonObjectBuilder.add("atac", animal.getAtac());
            jsonObjectBuilder.add("defensa", animal.getDefensa());
            jsonObjectBuilder.add("precisio", animal.getPrecisio());
            jsonObjectBuilder.add("vida", animal.getVida());
            jsonObjectBuilder.add("enverinat", animal.isEnverinat());
            jsonObjectBuilder.add("propietari", convertToJsonObject(animal.getPropietari()));
            jsonObjectBuilder.add("tipus", animal.getTipus().toString());
        }

        return jsonObjectBuilder.build();

    }
}
