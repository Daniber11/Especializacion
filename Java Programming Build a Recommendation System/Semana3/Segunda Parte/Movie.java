import java.util.ArrayList;
import java.util.Arrays;

// Un objeto de datos pasivo inmutable (PDO) para representar los datos del artículo
public class Movie {
    private String id;
    private String title;
    private int year;
    private String genres;
    private String director;
    private String country;
    private String poster;
    private int minutes;

    public Movie (String anID, String aTitle, String aYear, String theGenres) {
        // en caso de que el archivo de datos contenga espacios en blanco adicionales
        id     = anID.trim();
        title  = aTitle.trim();
        year   = Integer.parseInt(aYear.trim());
        genres = theGenres;
    }

    public Movie (String anID, String aTitle, String aYear, String theGenres, String aDirector,
    String aCountry, String aPoster, int theMinutes) {
        // en caso de que el archivo de datos contenga espacios en blanco adicionales
        id       = anID.trim();
        title    = aTitle.trim();
        year     = Integer.parseInt(aYear.trim());
        genres   = theGenres;
        director = aDirector;
        country  = aCountry;
        poster   = aPoster;
        minutes  = theMinutes;
    }

    // Devuelve el ID asociado con este artículo.
    public String getID () {
        return id;
    }

    // Devuelve el título de este artículo
    public String getTitle () {
        return title;
    }

    // Devuelve el año en el que se publicó este artículo.
    public int getYear () {
        return year;
    }

    // Devuelve los géneros asociados con este elemento.
    public String getGenres () {
        return genres;
    }
    
    // Devuelve el pais asociado con este elemento.
    public String getCountry(){
        return country;
    }

    // Devuelve el Director asociado con este elemento.
    public String getDirector(){
        return director;
    }

    // Devuelve el Poster asociado con este elemento.
    public String getPoster(){
        return poster;
    }

    // Devuelve la cantidad de minutos asociados con este elemento.
    public int getMinutes(){
        return minutes;
    }

    // Devuelve un string de información del artículo.
    public String toString () {
        String result = "Pelucla [id =" + id + ", titutlo =" + title + ", año =" + year;
        result += ", genero = " + genres + "]";
        return result;
    }
}
