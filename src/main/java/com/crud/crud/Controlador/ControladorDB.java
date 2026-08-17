package com.crud.crud.Controlador;

import com.crud.crud.Tablas.Persona;

import java.sql.*;
import java.util.ArrayList;

public class ControladorDB {

    private static final String URL = "jdbc:mariadb://localhost:3306/agenda";
    private static final String USER = "usuario1";
    private static final String PASSWORD = "superpassword";


    /**
     * Metodo para dar de alta a personas utilizando los datos de un opbjeto Persona
     */
    public void darAlta(Persona persona){
        Connection conn = null;
        PreparedStatement pstmt = null; //pstmt es para datos dinamicos (variables de la clase Persona)
        //result set no se utiliza pues no hacemos consultas
        String sql = "INSERT INTO Personas (nombre, direccion) VALUES (?, ?)";

        try {
            // 1. Registrar el driver JDBC
            Class.forName("org.mariadb.jdbc.Driver");
            // 2. Establecer la conexión
            System.out.println("Conectando a la base de datos...");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            // 3. llamar al comando de insertar
            System.out.println("\nDAR DE ALTA A PERSONA");
            pstmt = conn.prepareStatement(sql);

            // 4. Tomar los datos del objeto persona y asignarlos a la consulta
            pstmt.setString(1, persona.getNombre());
            pstmt.setString(2, persona.getDireccion());

            // 5. Ejecutar la inserción en la base de datos
            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Registro insertado");
            } else {
                System.out.println("No se pudo insertar el registro");
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6. Cerrar recursos
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    /**
     * Metodo para dar de baja a personas utilizando los datos de un opbjeto Persona
     */

    public void darBaja(Persona persona){
        Connection conn = null;
        PreparedStatement pstmt = null; //pstmt es para datos dinamicos (variables de la clase Persona)
        //result set no se utiliza pues no hacemos consultas
        String sql = "DELETE FROM Personas WHERE id = ?";

        try {
            // 1. Registrar el driver JDBC
            Class.forName("org.mariadb.jdbc.Driver");
            // 2. Establecer la conexión
            System.out.println("Conectando a la base de datos...");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            // 3. llamar al comando de insertar
            System.out.println("\nDAR DE BAJA A PERSONA");
            pstmt = conn.prepareStatement(sql);

            // 4. Tomar los datos del objeto persona y asignarlos a la consulta
            pstmt.setInt(1, persona.getId());

            // 5. Ejecutar la inserción en la base de datos
            int filasAfectadas = pstmt.executeUpdate();

            if (filasAfectadas > 0) {
                System.out.println("Registro eliminado");
            } else {
                System.out.println("No se pudo insertar el registro");
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 6. Cerrar recursos
            try {
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
    }

    /**
     * Metodo para consultar a los datos de la tabla personas
     */
    public ArrayList<Persona> getPersonas() {
        //Elemento a regresar
        ArrayList<Persona> personas = new ArrayList<>();

        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // 1. Registrar el driver JDBC
            Class.forName("org.mariadb.jdbc.Driver");
            // 2. Establecer la conexión
            System.out.println("Conectando a la base de datos...");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);
            // 3. Consultar la tabla Personas
            System.out.println("\n=== LISTADO DE PERSONAS ===");
            stmt = conn.createStatement();
            rs = stmt.executeQuery("SELECT * FROM Personas");

            while (rs.next()) {
                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String direccion = rs.getString("direccion");

                System.out.println("ID: " + id + ", Nombre: " + nombre + ", Dirección: " + direccion);
                personas.add(new Persona(id,nombre,direccion));
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5. Cerrar recursos
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
        return personas;
    }

    /**
     *Metodo para consultar todos los telefonos de una sola persona
     */

    public ArrayList<String> getTelefonosPorPersona(Persona persona) {
        ArrayList<String> listaTelefonos = new ArrayList<>();
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;

        int id = persona.getId();

        try {
            // 1. Registrar el driver JDBC
            Class.forName("org.mariadb.jdbc.Driver");

            // 2. Establecer la conexión
            System.out.println("Conectando a la base de datos...");
            conn = DriverManager.getConnection(URL, USER, PASSWORD);

            // 3. Consulta usando TUS nombres de columnas exactos: 'telefono' y 'personaId'
            System.out.println("\n=== CONSULTANDO TELÉFONOS DE LA PERSONA ID: " + id + " ===");
            String sql = "SELECT telefono FROM Telefonos WHERE personaId = ?";
            pstmt = conn.prepareStatement(sql);

            // 4. Pasar el ID al parámetro
            pstmt.setInt(1, id);

            // 5. Ejecutar consulta
            rs = pstmt.executeQuery();

            // 6. Recorrer los resultados
            while (rs.next()) {
                String numTelefono = rs.getString("telefono");
                System.out.println("Teléfono encontrado: " + numTelefono);
                listaTelefonos.add(numTelefono);
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 7. Cerrar todos los recursos manualmente
            try {
                if (rs != null) rs.close();
                if (pstmt != null) pstmt.close();
                if (conn != null) conn.close();
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }

        return listaTelefonos;
    }

}

