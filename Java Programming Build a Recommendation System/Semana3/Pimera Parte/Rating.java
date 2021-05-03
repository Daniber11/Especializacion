// Un objeto de datos pasivo inmutable (PDO) para representar los datos de calificación
public class Rating implements Comparable<Rating> {
    private String item;
    private double value;

    public Rating (String anItem, double aValue) {
        item  = anItem;
        value = aValue;
    }

    // Devuelve el artículo que está siendo calificado
    public String getItem () {
        return item;
    }

    // Devuelve el valor de esta calificación (como un número para que pueda usarse en los cálculos)
    public double getValue () {
        return value;
    }

    // Devuelve una cadena de toda la información de calificación.
    public String toString () {
        return "[" + getItem() + ", " + getValue() + "]";
    }

    public int compareTo(Rating other) {
        if (value < other.value) return -1;
        if (value > other.value) return 1;        
        return 0;
    }
}

