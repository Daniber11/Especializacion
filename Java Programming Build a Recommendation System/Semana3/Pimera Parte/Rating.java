// Un objeto de datos pasivo inmutable (PDO) para representar los datos de calificaci�n
public class Rating implements Comparable<Rating> {
    private String item;
    private double value;

    public Rating (String anItem, double aValue) {
        item  = anItem;
        value = aValue;
    }

    // Devuelve el art�culo que est� siendo calificado
    public String getItem () {
        return item;
    }

    // Devuelve el valor de esta calificaci�n (como un n�mero para que pueda usarse en los c�lculos)
    public double getValue () {
        return value;
    }

    // Devuelve una cadena de toda la informaci�n de calificaci�n.
    public String toString () {
        return "[" + getItem() + ", " + getValue() + "]";
    }

    public int compareTo(Rating other) {
        if (value < other.value) return -1;
        if (value > other.value) return 1;        
        return 0;
    }
}

