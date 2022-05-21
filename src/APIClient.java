import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import com.google.gson.Gson;

public class APIClient {
    public static Score[] getRanking(Score myScore) {
        try {
            HttpClient client = HttpClient.newBuilder().build();

            URI uri = new URI("http://localhost:8080/scores?score=" + myScore.getScore() + "&name=" + myScore.getName());

            HttpRequest request = HttpRequest.newBuilder(uri)
              .GET()
              .build();

            HttpResponse<String> response = client.send(request, HttpResponse.BodyHandlers.ofString());

            String resultJSON = response.body();

            Gson gson = new Gson();
            
            Score[] array = gson.fromJson(resultJSON, Score[].class);
            return array;

        } catch(Exception e) {
            System.out.println("スコアデータ取得に失敗しました");
            return null;
        }
    }
}
