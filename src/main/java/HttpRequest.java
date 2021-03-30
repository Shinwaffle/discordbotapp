import kong.unirest.Unirest;

public class HttpRequest {

 
  String url = "https://api.stackexchange.com/2.2/search/advanced?order=desc&sort=relevance&q=how%20to%20center%20div&answers=2&site=stackoverflow&filter=!)rTkr_OQen1do4oxw7QI"; 

  /**
   * Also, currently I have it set to retrieve the JSON of the first answer.
   */
    public String getResponse(int index) {
     
     Unirest.config().cookieSpec("standard");
      String result = Unirest.get(url)
      .asJson()
      .getBody()
      .getObject()
      .getJSONArray("items")
      .getJSONObject(0)
      .getJSONArray("answers")
      .getJSONObject(index)
      .getString("body");

      return result;  
    }
}
