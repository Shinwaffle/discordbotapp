import kong.unirest.HttpResponse;
import kong.unirest.JsonNode;
import kong.unirest.Unirest;

public class HttpStatusRequest {

    public JsonNode getResponse() {
      HttpResponse<JsonNode> response = Unirest.post("http://httpbin.org/post")
      .header("accept", "application/json")
      .queryString("apiKey", "123")
      .field("parameter", "value")
      .field("firstname", "Gary")
      .asJson();

      return response.getBody();
    }
}
