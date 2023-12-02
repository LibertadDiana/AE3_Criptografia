package main;

import java.util.Scanner;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class Encriptador {

    private static String fraseEncriptada = "";
    private static String usuarioActual = "";
    private static HashMap<String, String> usuarios = new HashMap<>();

    public static void main(String[] args) {
        
        crearUsuarios();
        imprimirUsuarios();

        Scanner scanner = new Scanner(System.in);
        int intentos = 0;

        while (intentos < 3) {
            System.out.println("Ingrese su nombre de usuario:");
            String nombreUsuario = scanner.nextLine();

            System.out.println("Ingrese su contraseña:");
            String contrasena = scanner.nextLine();

            if (validarCredenciales(nombreUsuario, contrasena)) {
                usuarioActual = nombreUsuario;
                System.out.println("Bienvenido, " + usuarioActual + "!");
                mostrarMenu(scanner);
                break;
            } else {
                System.out.println("Credenciales incorrectas. Intente nuevamente.");
                intentos++;
            }
        }

        if (intentos == 3) {
            System.out.println("Demasiados intentos fallidos. Saliendo del programa.");
        }
    }

    private static void mostrarMenu(Scanner scanner) {
        int opcion;
        do {
            System.out.println("\n======== Menú ========");
            System.out.println("1. Encriptar frase");
            System.out.println("2. Desencriptar frase");
            System.out.println("0. Salir del programa");
            System.out.println("Ingrese su opción:");

            opcion = scanner.nextInt();
            scanner.nextLine(); 

            switch (opcion) {
                case 1:
                    System.out.println("Ingrese la frase a encriptar:");
                    String frase = scanner.nextLine();
                    fraseEncriptada = encriptar(frase);
                    System.out.println("Frase encriptada: " + fraseEncriptada);
                    break;
                case 2:
                    System.out.println("Frase desencriptada: " + desencriptar(fraseEncriptada));
                    break;
                case 0:
                    System.out.println("Saliendo del programa.");
                    break;
                default:
                    System.out.println("Opción inválida. Intente nuevamente.");
                    break;
            }
        } while (opcion != 0);
    }

    private static String encriptar(String frase) {
        int clave = 3; 
        StringBuilder resultado = new StringBuilder();

        for (char caracter : frase.toCharArray()) {
            if (Character.isLetter(caracter)) {
                char encriptado = (char) (((caracter - 'a' + clave) % 26) + 'a');
                resultado.append(encriptado);
            } else {
                resultado.append(caracter);
            }
        }

        return resultado.toString();
    }

    private static String desencriptar(String fraseEncriptada) {
        int clave = 3; 
        StringBuilder resultado = new StringBuilder();

        for (char caracter : fraseEncriptada.toCharArray()) {
            if (Character.isLetter(caracter)) {
                char desencriptado = (char) (((caracter - 'a' - clave + 26) % 26) + 'a');
                resultado.append(desencriptado);
            } else {
                resultado.append(caracter);
            }
        }

        return resultado.toString();
    }

    private static void crearUsuarios() {
        usuarios.put("usuario1", hashContrasena("password1"));
        usuarios.put("usuario2", hashContrasena("password2"));
        usuarios.put("usuario3", hashContrasena("password3"));
    }

    private static boolean validarCredenciales(String nombreUsuario, String contrasena) {
        imprimirUsuarios();
        return usuarios.containsKey(nombreUsuario) && usuarios.get(nombreUsuario).equals(hashContrasena(contrasena));
    }

    private static String hashContrasena(String contrasena) {
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(contrasena.getBytes());
            StringBuilder hash = new StringBuilder();

            for (byte b : hashBytes) {
                hash.append(String.format("%02x", b));
            }

            return hash.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            return null;
        }
    }

    private static void imprimirUsuarios() {
        System.out.println("Usuarios y contraseñas hasheadas:");
        for (String usuario : usuarios.keySet()) {
            System.out.println(usuario + " - " + usuarios.get(usuario));
        }
    }
}