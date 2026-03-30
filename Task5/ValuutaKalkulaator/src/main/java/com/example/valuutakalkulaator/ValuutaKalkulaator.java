package com.example.valuutakalkulaator;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ValuutaKalkulaator extends Application {

    // siin hoian kõik kursid, hiljem loen failist
    private Map<String, Double> kursid = new HashMap<>();

    @Override
    public void start(Stage peaLava) {

        loeKursid("exchange-rates.txt");

        Canvas logo = looLogo();

        // список валют для выпадающего меню( выбор валюты)
        ObservableList<String> valuutad = FXCollections.observableArrayList(kursid.keySet());
        FXCollections.sort(valuutad);

        ComboBox<String> valuutaValik = new ComboBox<>(valuutad);
        valuutaValik.setValue(valuutad.isEmpty() ? "" : valuutad.get(0));
        valuutaValik.setPrefWidth(100);

        TextField eurVali = new TextField();
        eurVali.setPromptText("EUR summa");
        eurVali.setPrefWidth(150);

        // это поле только для чтения
        TextField voorrVali = new TextField();
        voorrVali.setPromptText("Teise valuuta summa");
        voorrVali.setEditable(false);
        voorrVali.setPrefWidth(150);
        voorrVali.setStyle("-fx-background-color: #f0f0f0;");

        Button tyhjenduNupp = new Button("Tühjenda");

        // property listener - reageerib kohe kui tekst muutub
        eurVali.textProperty().addListener((observable, vanaVaartus, uusVaartus) -> {
            uuendaKonversioon(eurVali, voorrVali, valuutaValik);
        });

        // sama asi aga valuuta vahetamisel
        valuutaValik.valueProperty().addListener((obs, vana, uus) -> {
            uuendaKonversioon(eurVali, voorrVali, valuutaValik);
        });

        // tühjendab mõlemad - lihtne
        tyhjenduNupp.setOnAction(e -> {
            eurVali.clear();
            voorrVali.clear();
        });

        // GridPane на два столбца - лейблы и поля
        GridPane gridPane = new GridPane();
        gridPane.setHgap(10);
        gridPane.setVgap(8);
        gridPane.setPadding(new Insets(10));

        Label eurSilt = new Label("EUR:");
        Label voorrSilt = new Label("Valuuta:");

        gridPane.add(eurSilt, 0, 0);
        gridPane.add(eurVali, 1, 0);
        gridPane.add(voorrSilt, 0, 1);
        gridPane.add(voorrVali, 1, 1);

        // alumine rida - menüü ja nupp kõrvuti
        HBox alamRida = new HBox(10);
        alamRida.setAlignment(Pos.CENTER_LEFT);
        alamRida.setPadding(new Insets(5, 10, 10, 10));
        alamRida.getChildren().addAll(new Label("Valuuta:"), valuutaValik, tyhjenduNupp);

        VBox sisu = new VBox(10);
        sisu.setPadding(new Insets(10));
        sisu.getChildren().addAll(gridPane, alamRida);

        // BorderPane kokku - logo üles, sisu keskele
        BorderPane peaPane = new BorderPane();
        peaPane.setTop(logo);
        peaPane.setCenter(sisu);
        BorderPane.setAlignment(logo, Pos.CENTER);

        Scene stseen = new Scene(peaPane, 400, 350, Color.WHITE);

        peaLava.setTitle("Valuutakalkulaator - EUR konverter");
        peaLava.setScene(stseen);
        peaLava.setResizable(false);
        peaLava.show();
    }

    // arvutab tulemuse ja paneb väljale - kutsutakse listenerist
    private void uuendaKonversioon(TextField eurVali, TextField voorrVali, ComboBox<String> valuutaValik) {
        String tekst = eurVali.getText().trim();
        String valitudValuuta = valuutaValik.getValue();

        if (tekst.isEmpty() || valitudValuuta == null) {
            voorrVali.setText("");
            return;
        }

        try {
            // koma asemel punkt, mõned sisestavad nii
            double eurSumma = Double.parseDouble(tekst.replace(",", "."));
            double kurs = kursid.getOrDefault(valitudValuuta, 1.0);
            double tulemus = eurSumma * kurs;
            voorrVali.setText(String.format("%.2f", tulemus));
        } catch (NumberFormatException ex) {
            // если не число - просто очищаем
            voorrVali.setText("");
        }
    }

    // logo joonistamine Canvas peale
    private Canvas looLogo() {
        Canvas loouend = new Canvas(400, 100);
        GraphicsContext gc = loouend.getGraphicsContext2D();

        // фиолетовый градиентный фон
        gc.setFill(Color.web("#6a0dad"));
        gc.fillRect(0, 0, 400, 100);

        // белый круг слева (акцент)
        gc.setFill(Color.WHITE);
        gc.fillOval(10, 10, 80, 80);

        // фиолетовый € внутри круга
        gc.setFill(Color.web("#6a0dad"));
        gc.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 36));
        gc.fillText("€", 28, 62);

        // белый скруглённый прямоугольник для текста
        gc.setFill(Color.WHITE);
        gc.fillRoundRect(100, 20, 230, 55, 30, 30);

        // фиолетовый текст внутри
        gc.setFill(Color.web("#6a0dad"));
        gc.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 20));
        gc.fillText("Valuutakalkulaator", 108, 53);

        //  крести из карт  справа
        gc.setFill(Color.WHITE);
        gc.setFont(javafx.scene.text.Font.font("Arial", javafx.scene.text.FontWeight.BOLD, 70));
        gc.fillText("♣", 348, 80);

        return loouend;
    }

    // loeb faili ja täidab kursid mapi
    private void loeKursid(String failiNimi) {
        try (BufferedReader lugeja = new BufferedReader(new FileReader(failiNimi))) {
            String rida;
            while ((rida = lugeja.readLine()) != null) {
                rida = rida.trim();
                if (rida.isEmpty() || rida.startsWith("#")) continue; // kommentaarid vahele

                String[] osad = rida.split("=");
                if (osad.length == 2) {
                    String kood = osad[0].trim().toUpperCase();
                    try {
                        double kurs = Double.parseDouble(osad[1].trim());
                        kursid.put(kood, kurs);
                    } catch (NumberFormatException e) {
                        System.out.println("vigane rida: " + rida);
                    }
                }
            }
        } catch (IOException e) {
            // faili pole, kasutan vaikimisi väärtusi
            System.out.println("exchange-rates.txt ei leitud, kasutan hardcoded kursid");
            kursid.put("USD", 1.08);
            kursid.put("GBP", 0.86);
            kursid.put("JPY", 161.5);
            kursid.put("SEK", 11.23);
            kursid.put("NOK", 11.76);
            kursid.put("CHF", 0.95);
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
