package com.example.stellarScanOrbit;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.http.ResponseEntity;
import org.json.JSONObject;

@Service
public class GeolocationService {

    private static final String API_KEY = "5e584542822b48509e672a4a45e5a3af";

    public String getCountryFromCoords(double lat, double lon) {
        String url = String.format(
                "https://api.geoapify.com/v1/geocode/reverse?lat=%f&lon=%f&apiKey=%s",
                lat, lon, API_KEY
        );

        RestTemplate restTemplate = new RestTemplate();
        try {
            ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
            JSONObject json = new JSONObject(response.getBody());
            JSONObject properties = json
                    .getJSONArray("features")
                    .getJSONObject(0)
                    .getJSONObject("properties");

            return properties.optString("country", "over water...");
        } catch (Exception e) {
            System.err.println("Geoapify Error: " + e.getMessage());
            return "Unknown";
        }
    }
}
