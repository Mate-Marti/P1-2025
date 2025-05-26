package co.edu.uniquindio.poo.app;

import co.edu.uniquindio.poo.model.*;

import java.util.List;

/**
 * Clase principal para probar el sistema de reservas de ExpresoColombia.
 */
public class Main {

    public static void main(String[] args) {

        // Crear empresa
        Empresa empresa = new Empresa("ExpresoColombia");

        // Crear y registrar vehículos
        Vehiculo bus = new Bus("ABC001", "Volvo", 40, 2, true);
        Vehiculo van = new Van("DEF002", "Mercedes", 12, false, TipoTraccion.TRACCION_4X2);
        Vehiculo microbus = new Microbus("GHI003", "Hyundai", 20, TipoEnergia.ELECTRICO);

        empresa.registrarVehiculo(bus);
        empresa.registrarVehiculo(van);
        empresa.registrarVehiculo(microbus);

        // Crear y registrar conductor
        Conductor conductor = new Conductor("Luis Torres", "123456789", "C1", 10);
        empresa.registrarConductor(conductor);

        // Crear y registrar rutas
        Ruta ruta1 = new Ruta("Armenia", "Pereira", 1, 45);
        Ruta ruta2 = new Ruta("Bogotá", "Cali", 8, 400);
        Ruta ruta3 = new Ruta("Manizales", "Medellín", 6, 250);

        empresa.registrarRuta(ruta1);
        empresa.registrarRuta(ruta2);
        empresa.registrarRuta(ruta3);

        // Crear pasajeros
        List<Pasajero> pasajeros = List.of(
                new Pasajero("Ana Pérez", "111", true),
                new Pasajero("Carlos Ruiz", "222", false)
        );

        // Crear y registrar reservas (con IDs únicos)
        Reserva reserva1 = new Reserva(1, bus, conductor, ruta1, pasajeros);
        Reserva reserva2 = new Reserva(2, van, conductor, ruta2, pasajeros);
        Reserva reserva3 = new Reserva(3, microbus, conductor, ruta3, pasajeros);

        empresa.registrarReserva(reserva1);
        empresa.registrarReserva(reserva2);
        empresa.registrarReserva(reserva3);

        // Mostrar resumen de todas las reservas
        System.out.println("=== RESUMEN DE TODAS LAS RESERVAS ===");
        empresa.mostrarTodasLasReservas();

        // Mostrar vehículos reservados para Armenia–Pereira
        System.out.println("\n=== VEHÍCULOS RESERVADOS PARA ARMENIA–PEREIRA ===");
        List<Vehiculo> reservados = empresa.obtenerVehiculosPorRuta("Armenia", "Pereira");
        for (Vehiculo vehiculo : reservados) {
            System.out.println("Placa: " + vehiculo.getPlaca() + ", Modelo: " + vehiculo.getModelo());
        }

        // Calcular y mostrar el costo de una reserva por ruta y vehículo (sin try-catch)
        double costoRutaVehiculo = empresa.calcularCostoReservaPorRutaYVehiculo("Bogotá", "Cali", "DEF002");
        System.out.println("\nCosto de la reserva (Bogotá–Cali, DEF002): $" + costoRutaVehiculo);

    }
}