import java.util.LinkedList;

public class Ejercicio {
    public static void ejercicio(String[] args) {
        LinkedList<Integer> edades = new LinkedList<>();
        edades.add(23);
        edades.add(20);
        edades.add(15);
        edades.add(14);
        edades.add(18);


    }

    int contador = 0;
    int acumulador = 0;
    public int mostraredades(LinkedList<Integer> edades) {
        for (Integer edad : edades) {
            if (edad >= 18) {
                contador++;
                acumulador += edad;
            }
            System.out.println(acumulador);

        }
        return contador;
    }
}

