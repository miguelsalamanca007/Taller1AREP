package edu.escuelaing.arep.app.view;

public class movieSearchEngine {

    public static String getHomePage() {
        
        String html =  "HTTP/1.1 200 OK\r\n" +
        "Content-Type: text/html\r\n" +
        "\r\n" +
        "<!DOCTYPE html>\n" +
        "<html>\n" +
        "<head>\n" +
        "  <title>Movie Search Engine</title>\n" +
        "  <meta charset=\"UTF-8\">\n" +
        "  <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
        "</head>\n" +
        "<body>\n" +
        "  <h1>Movie Search Engine</h1>\n" +
        "  <form action=\"/search\" method=\"get\">\n" +
        "    <input type=\"text\" name=\"q\" placeholder=\"Enter the name of the movie...\">\n" +
        "    <br><br>\n" +
        "    <button type=\"button\" onclick=\"searchMovie()\">Search</button>\n" +
        "  </form>\n" +
        "  <h1 id=\"movie\">Movie</h1>         " +
        "  <script>\n" +
        "    function searchMovie() {\n" +
        "      var input = document.querySelector('input[name=\"q\"]');\n" +
        "      var movieName = input.value.replace(/ /g, \"+\");\n" +
        "      var url = \"http://localhost:16000/movie/\" + movieName;\n" +
        "      fetch(url)\n" +
        "        .then(response => response.text())\n" +
        "        .then(data => (document.getElementById(\"movie\").innerHTML = data))\n" +
        "        .catch(error => console.error('Error:', error));\n" +
        "    }\n" +
        "  </script>\n" +
        "</body>\n" +
        "</html>";

        return html;          
    }
}
