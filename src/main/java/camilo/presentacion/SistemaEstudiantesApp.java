package camilo.presentacion;

import camilo.datos.EstudianteDAO;
import camilo.dominio.Estudiante;

import java.util.Scanner;

public class SistemaEstudiantesApp {
    public static void main(String[] args) {
        var salir = false;
        Scanner sc = new Scanner(System.in);
        var estudianteDao = new EstudianteDAO();
        while (!salir) {
            try {
                mostrarMenu();
                salir = ejecutarOpciones(sc, estudianteDao);
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
            System.out.println();
        }
    }

    private static boolean ejecutarOpciones(Scanner sc, EstudianteDAO estudianteDao) {
        var opcion = Integer.parseInt(sc.nextLine());
        var salir = false;
        switch (opcion) {
            case 1 -> {
                System.out.println("Listado de Estudiantes: ");
                var estudiantes = estudianteDao.listarEstudiantes();
                estudiantes.forEach(System.out::println);
            }
            case 2 -> {
                System.out.println("Buscar Estudiante por ID: ");
                var idEstudiante = Integer.parseInt(sc.nextLine());
                var estudiante = new Estudiante(idEstudiante);
                var encontrado = estudianteDao.buscarEstudiantePorId(estudiante);
                if (encontrado) {
                    System.out.println("Estudiante encontrado: " + estudiante);
                } else {
                    System.out.println("Estudiante no encontrado");
                }
            }
            case 3 -> {
                System.out.println("Agregar Estudiante: ");
                System.out.print("Nombre: ");
                var nombre = sc.nextLine();
                System.out.print("Apellido: ");
                var apellido = sc.nextLine();
                System.out.print("Teléfono: ");
                var telefono = sc.nextLine();
                System.out.print("Email: ");
                var email = sc.nextLine();
                var estudiante = new Estudiante(nombre, apellido, telefono, email);
                var agregado = estudianteDao.agregarEstudiante(estudiante);
                if (agregado) {
                    System.out.println("Estudiante agregado: " + estudiante);
                } else {
                    System.out.println("Estudiante no agregado");
                }
            }
            case 4 -> {
                System.out.println("Modificación de Estudiante: ");
                System.out.print("ID Estudiante: ");
                var idEstudiante = Integer.parseInt(sc.nextLine());
                System.out.print("Nombre: ");
                var nombre = sc.nextLine();
                System.out.print("Apellido: ");
                var apellido = sc.nextLine();
                System.out.print("Teléfono: ");
                var telefono = sc.nextLine();
                System.out.print("Email: ");
                var email = sc.nextLine();
                var estudiante = new Estudiante(idEstudiante, nombre, apellido, telefono, email);
                var modificado = estudianteDao.modificarEstudiante(estudiante);
                if (modificado) {
                    System.out.println("Estudiante modificado: " + estudiante);
                } else {
                    System.out.println("Estudiante no modificado");
                }
            }
            case 5 -> {
                System.out.println("Eliminación de Estudiante: ");
                System.out.println("ID Estudiante: ");
                var idEstudiante = Integer.parseInt(sc.nextLine());
                var estudiante = new Estudiante(idEstudiante);
                var eliminado = estudianteDao.eliminarEstudiante(estudiante);
                if (eliminado) {
                    System.out.println("Estudiante eliminado: " + estudiante);
                } else {
                    System.out.println("Estudiante no eliminado");
                }
            }
            case 6 -> {
                System.out.println("Saliendo...");
                salir = true;
            }
            default -> {
                System.out.println("Opción inválida");
            }
        }
        return salir;
    }

    private static void mostrarMenu() {
        System.out.println("""
                **** Sistema de Estudiantes ****
                1. Listar Estudiantes
                2. Buscar Estudiante
                3. Agregar Estudiante
                4. Modificar Estudiante
                5. Eliminar Estudiante
                6. Salir
                Elige una opcion: 
                """);
    }
}