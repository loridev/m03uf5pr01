package com.company.m03uf5pr01.utils;

import com.company.m03uf5pr01.models.*;

import java.io.*;
import java.lang.reflect.Field;
import java.nio.file.Files;
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
                    ArrayList<Animal> mascotes = new ArrayList<>();

                    Propietari propietari = new Propietari(jsonObject.getString("nom"), jsonObject.getString("password"),
                            jsonObject.getInt("diners"));

                    for (JsonValue row2: jsonObject.getJsonArray("mascotes")) {
                        JsonObject jsonObject2 = row2.asJsonObject();

                        if (jsonObject2.getJsonNumber("ratioRepeticioAtac") != null) {
                            mascotes.add(new Au(
                                    jsonObject2.getString("nom"),
                                    jsonObject2.getInt("nivell"),
                                    ((Double) jsonObject2.getJsonNumber("atac").doubleValue()).floatValue(),
                                    ((Double) jsonObject2.getJsonNumber("defensa").doubleValue()).floatValue(),
                                    ((Double) jsonObject2.getJsonNumber("precisio").doubleValue()).floatValue(),
                                    jsonObject2.getInt("vida"),
                                    jsonObject2.getBoolean("enverinat"),
                                    propietari,
                                    TipusAnimal.valueOf(jsonObject2.getString("tipus")),
                                    jsonObject2.getBoolean("urpesTrencades"),
                                    ((Double) jsonObject2.getJsonNumber("ratioRepeticioAtac").doubleValue()).floatValue()
                            ));
                        }
                        if (jsonObject2.getJsonNumber("multiplicadorPuny") != null) {
                            new Mamifer(
                                    jsonObject2.getString("nom"),
                                    jsonObject2.getInt("nivell"),
                                    ((Double) jsonObject2.getJsonNumber("atac").doubleValue()).floatValue(),
                                    ((Double) jsonObject2.getJsonNumber("defensa").doubleValue()).floatValue(),
                                    ((Double) jsonObject2.getJsonNumber("precisio").doubleValue()).floatValue(),
                                    jsonObject2.getInt("vida"),
                                    jsonObject2.getBoolean("enverinat"),
                                    propietari,
                                    TipusAnimal.valueOf(jsonObject2.getString("tipus")),
                                    ((Double) jsonObject2.getJsonNumber("multiplicadorPuny").doubleValue()).floatValue()
                            );
                        }
                        if (jsonObject2.getJsonNumber("precissioVeri") != null) {
                            mascotes.add(new Reptil(
                                    jsonObject2.getString("nom"),
                                    jsonObject2.getInt("nivell"),
                                    ((Double) jsonObject2.getJsonNumber("atac").doubleValue()).floatValue(),
                                    ((Double) jsonObject2.getJsonNumber("defensa").doubleValue()).floatValue(),
                                    ((Double) jsonObject2.getJsonNumber("precisio").doubleValue()).floatValue(),
                                    jsonObject2.getInt("vida"),
                                    jsonObject2.getBoolean("enverinat"),
                                    propietari,
                                    TipusAnimal.valueOf(jsonObject2.getString("tipus")),
                                    ((Double) jsonObject2.getJsonNumber("precissioVeri").doubleValue()).floatValue()
                                    ));
                        }

                        propietari.setMascotes(mascotes);
                    }
                    propietaris.add(propietari);
                }
                return propietaris;
            } else if (className.equals("Animal")) {
                ArrayList<Animal> animals = new ArrayList<>();
                if (jsonArray.size() == 0) return animals;
                for (JsonValue row : jsonArray) {
                    JsonObject jsonObject = row.asJsonObject();
                    if (jsonObject.getJsonNumber("ratioRepeticioAtac") != null) {
                        animals.add(new Au(
                                jsonObject.getString("nom"),
                                jsonObject.getInt("nivell"),
                                ((Double) jsonObject.getJsonNumber("atac").doubleValue()).floatValue(),
                                ((Double) jsonObject.getJsonNumber("defensa").doubleValue()).floatValue(),
                                ((Double) jsonObject.getJsonNumber("precisio").doubleValue()).floatValue(),
                                jsonObject.getInt("vida"),
                                jsonObject.getBoolean("enverinat"),
                                Globals.propietaris.get(
                                        Globals.propietaris.indexOf(new Propietari(jsonObject.getString("propietari")))
                                ),
                                TipusAnimal.valueOf(jsonObject.getString("tipus")),
                                jsonObject.getBoolean("urpesTrencades"),
                                ((Double) jsonObject.getJsonNumber("ratioRepeticioAtac").doubleValue()).floatValue()
                        ));
                    }
                    if (jsonObject.getJsonNumber("multiplicadorPuny") != null) {
                        animals.add(new Mamifer(
                                jsonObject.getString("nom"),
                                jsonObject.getInt("nivell"),
                                ((Double) jsonObject.getJsonNumber("atac").doubleValue()).floatValue(),
                                ((Double) jsonObject.getJsonNumber("defensa").doubleValue()).floatValue(),
                                ((Double) jsonObject.getJsonNumber("precisio").doubleValue()).floatValue(),
                                jsonObject.getInt("vida"),
                                jsonObject.getBoolean("enverinat"),
                                Globals.propietaris.get(
                                        Globals.propietaris.indexOf(new Propietari(jsonObject.getString("propietari")))
                                ),
                                TipusAnimal.valueOf(jsonObject.getString("tipus")),
                                ((Double) jsonObject.getJsonNumber("multiplicadorPuny").doubleValue()).floatValue()
                        ));
                    }
                    if (jsonObject.getJsonNumber("precissioVeri") != null) {
                        animals.add(new Reptil(
                                jsonObject.getString("nom"),
                                jsonObject.getInt("nivell"),
                                ((Double) jsonObject.getJsonNumber("atac").doubleValue()).floatValue(),
                                ((Double) jsonObject.getJsonNumber("defensa").doubleValue()).floatValue(),
                                ((Double) jsonObject.getJsonNumber("precisio").doubleValue()).floatValue(),
                                jsonObject.getInt("vida"),
                                jsonObject.getBoolean("enverinat"),
                                Globals.propietaris.get(
                                        Globals.propietaris.indexOf(new Propietari(jsonObject.getString("propietari")))
                                ),
                                TipusAnimal.valueOf(jsonObject.getString("tipus")),
                                ((Double) jsonObject.getJsonNumber("precissioVeri").doubleValue()).floatValue()
                        ));
                    }
                }

                return animals;
            }

        } catch (IOException ioe) {
            System.out.println("IO EXCEPTION: " + ioe.getMessage());
            return null;
        } catch (Exception e) {
            System.out.println("EXCEPTION: " + e.getMessage());
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
                if (os != null) {
                    os.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void loadConf(Path path) {
        InputStream is = null;
        JsonReader jsonReader = null;

        try {
            is = new FileInputStream(path.toFile());
            jsonReader = Json.createReader(is);

            JsonObject object = jsonReader.readObject();

            Globals.dia = object.getInt("dia");
        } catch (IOException ioe) {
            ioe.getMessage();
        }
    }

    public static void saveConf(Path path) {
        OutputStream os = null;
        JsonObjectBuilder job = Json.createObjectBuilder();
        JsonWriter jsonWriter = null;

        try {
            job.add("dia", Globals.dia);

            os = new FileOutputStream(path.toFile());
            jsonWriter = Json.createWriter(os);
            jsonWriter.writeObject(job.build());
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            if (jsonWriter != null) {
                jsonWriter.close();
            }
            try {
                if (os != null) {
                    os.close();
                }
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
                mascota.setPropietari(propietari);
                mascotesJson.add(convertToJsonObject(mascota));
            }
            jsonObjectBuilder.add("nom", propietari.getNom());
            jsonObjectBuilder.add("password", propietari.getPassword());
            jsonObjectBuilder.add("mascotes", mascotesJson);
            jsonObjectBuilder.add("diners", propietari.getDiners());
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
                if (au.getPropietari() != null) {
                    jsonObjectBuilder.add("propietari", au.getPropietari().getNom());
                }
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
                if (mamifer.getPropietari() != null) {
                    jsonObjectBuilder.add("propietari", mamifer.getPropietari().getNom());
                }
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
                if (reptil.getPropietari() != null) {
                    jsonObjectBuilder.add("propietari", reptil.getPropietari().getNom());
                }
                jsonObjectBuilder.add("tipus", reptil.getTipus().toString());
            }

        }

        return jsonObjectBuilder.build();
    }
}
