import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/stock")
public class StockController {

    private final OkHttpClient httpClient = new OkHttpClient();

    @GetMapping("/{symbol}")
    public ResponseEntity<String> fetchStockData(@PathVariable("symbol") String symbol) {
        String apiUrl = "https://holistic-finance-stock-data.p.rapidapi.com/api/v1/symbol/stock?includeNames=false";
        Request request = new Request.Builder()
                .url(apiUrl)
                .get()
                .addHeader("X-RapidAPI-Key", "534daf1863mshb74492b2f23f73ap1ea1bejsn09d7e4837940")
                .addHeader("X-RapidAPI-Host", "holistic-finance-stock-data.p.rapidapi.com")
                .build();

        try {
            Response response = httpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                String responseBody = response.body().string();
                return ResponseEntity.ok(responseBody);
            } else {
                return ResponseEntity.status(response.code()).body("Failed to fetch stock data.");
            }
        } catch (IOException e) {
            return ResponseEntity.status(500).body("Error occurred while fetching stock data.");
        }
    }
}
