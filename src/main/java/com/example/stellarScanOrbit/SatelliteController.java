package com.example.stellarScanOrbit;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

//@CrossOrigin(origins = "https://stellerscan.netlify.app/")
//@CrossOrigin(origins = "http://localhost:5173")
@CrossOrigin
@RestController
@RequestMapping("/api")
public class SatelliteController {

    private final GeolocationService geolocationService;

    public SatelliteController(GeolocationService geolocationService) {
        this.geolocationService = geolocationService;
    }

    @GetMapping("/country")
    public ResponseEntity<String> getCountry(@RequestParam double lat, @RequestParam double lon) {
        String country = geolocationService.getCountryFromCoords(lat, lon);
        return ResponseEntity.ok(country);
    }



    @GetMapping("/test")
    public String test() {
        return "This is working.";
    }
}
