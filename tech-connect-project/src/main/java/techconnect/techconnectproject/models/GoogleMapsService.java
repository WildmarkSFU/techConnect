package techconnect.techconnectproject.models;

import com.google.maps.DistanceMatrixApi;
import com.google.maps.GeoApiContext;
import com.google.maps.model.DistanceMatrix;
import com.google.maps.model.LatLng;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import com.google.maps.model.Duration;

@Service
public class GoogleMapsService {

    @Value("${google.maps.api.key}")
    private String apiKey;

    public double calculateDistance(double userLat, double userLng, double ownerLat, double ownerLng) {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();

        LatLng userLocation = new LatLng(userLat, userLng);
        LatLng ownerLocation = new LatLng(ownerLat, ownerLng);

        try {
            DistanceMatrix matrix = DistanceMatrixApi.newRequest(context)
                    .origins(userLocation)
                    .destinations(ownerLocation)
                    .await();
                    double distance = matrix.rows[0].elements[0].distance.inMeters / 1000.0; // Convert meters to kilometers
                    Duration duration = matrix.rows[0].elements[0].duration;
                    
                    // Print and return distance 
                    System.out.println("Distance: " + distance + " km");
            return matrix.rows[0].elements[0].distance.inMeters / 1000.0; // Convert meters to kilometers
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Handle error
        }
    }

    public double calculateDuration(double userLat, double userLng, double ownerLat, double ownerLng) {
        GeoApiContext context = new GeoApiContext.Builder()
                .apiKey(apiKey)
                .build();

        LatLng userLocation = new LatLng(userLat, userLng);
        LatLng ownerLocation = new LatLng(ownerLat, ownerLng);

        try {
            DistanceMatrix matrix = DistanceMatrixApi.newRequest(context)
                    .origins(userLocation)
                    .destinations(ownerLocation)
                    .await();
                    double distance = matrix.rows[0].elements[0].distance.inMeters / 1000.0; // Convert meters to kilometers
                    Duration duration = matrix.rows[0].elements[0].duration;
                    
                    // Print and return duration
                    System.out.println("Duration: " + duration.humanReadable);
            return matrix.rows[0].elements[0].duration.inSeconds / 60.0; // Convert seconds to minutes
        } catch (Exception e) {
            e.printStackTrace();
            return -1; // Handle error
        }
    }
}
