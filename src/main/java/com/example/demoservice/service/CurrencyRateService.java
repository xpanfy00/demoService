package com.example.demoservice.service;


import com.example.demoservice.model.CurrencyRate;
import com.example.demoservice.repository.CurrencyRateRepository;
import com.google.gson.Gson;
import com.google.gson.JsonObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

@Service
public class CurrencyRateService {

    private final CurrencyRateRepository currencyRateRepository;

    @Autowired
    public CurrencyRateService(CurrencyRateRepository currencyRateRepository) {
        this.currencyRateRepository = currencyRateRepository;
    }

    public static String getCurrencyResponse(String apiKey) {
        StringBuilder jsonResponse = new StringBuilder();
        try {
            String query = "https://openexchangerates.org/api/latest.json?app_id=" + apiKey + "&symbols=KZT,RUB";
            URL url = new URL(query);

            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");

            int responseCode = conn.getResponseCode();

            if (responseCode == HttpURLConnection.HTTP_OK) {
                Scanner scanner = new Scanner(conn.getInputStream());
                while (scanner.hasNextLine()) {
                    jsonResponse.append(scanner.nextLine());
                }
                scanner.close();
            } else {
                jsonResponse.append("Ошибка при выполнении запроса: ").append(responseCode);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonResponse.toString();
    }

    public void jsonResponseworker() {
        try {
            LocalDate currentDate = LocalDate.now();
            List<CurrencyRate> existingRate = currencyRateRepository.findAllByDate(currentDate);

            if (!existingRate.isEmpty()) {
                System.out.println("Курс валют для сегодняшней даты уже существует в базе данных.");
                return;
            }
            String jsonResponse = getCurrencyResponse("20041aa934914bdd80be706a34460da2");


            Gson gson = new Gson();
            JsonObject jsonObject = gson.fromJson(jsonResponse, JsonObject.class);


            JsonObject ratesObject = jsonObject.getAsJsonObject("rates");

            for (String currencyCode : ratesObject.keySet()) {
                Double closeRate = ratesObject.get(currencyCode).getAsDouble();
                CurrencyRate currencyRate = new CurrencyRate();
                currencyRate.setCurrencyCode(currencyCode);
                currencyRate.setDate(currentDate);
                currencyRate.setCloseRate(closeRate);
                currencyRate.setPreviousCloseRate(closeRate);


                currencyRateRepository.save(currencyRate);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


}
