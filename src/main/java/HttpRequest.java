import kong.unirest.Unirest;

public class HttpRequest {

 
  String url = "https://api.stackexchange.com/2.2/search/advanced?order=desc&sort=relevance&q=how%20to%20center%20div&answers=2&site=stackoverflow&filter=!9_bDE(fI5"; 

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
      .getJSONObject(index)
      .toString();

      return result;  
    }
}
