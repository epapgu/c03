/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author pcdev
 */
public class Profesor {

    public int id;
    public String nombre, correo;
    public int edad;
    public ArrayList<Curso> cursos = new ArrayList();

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public ArrayList<Curso> getCursos() {
        return cursos;
    }

    public void setCursos(ArrayList<Curso> cursos) {
        this.cursos = cursos;
    }

    public boolean guardar() {
        boolean res = false;
        Conexion con = new Conexion();
        //Conecto a BD
        con.conectar();
        String sql = "INSERT INTO profesores (nombre, edad, correo) "
                + " VALUES (?,?,?)";
        try {
            PreparedStatement psm = con.getCon().prepareStatement(sql);
            psm.setString(1, this.nombre);
            psm.setInt(2, this.edad);
            psm.setString(3, this.correo);
            psm.executeUpdate();
            res = true;
        } catch (SQLException ex) {
            Logger.getLogger(Profesor.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.cerrarConexion();
        return res;
    }

    public ArrayList<Profesor> listar() {
        Conexion con = new Conexion();
        //Conecto a BD
        con.conectar();
        String sql = "SELECT * FROM profesores";
        ArrayList<Profesor> listaProfesores = new ArrayList<>();

        try {
            //Prepara la sentencia SQL a ejecutar
            PreparedStatement sp = con.getCon().prepareStatement(sql);
            ResultSet rs;
            //Ejecuata la sentencia y retorna los datos
            rs = sp.executeQuery();
            while (rs.next()) {
                Profesor profe = new Profesor();
                profe.setId(rs.getInt("id"));
                profe.setNombre(rs.getString("nombre"));
                profe.setCorreo(rs.getString("correo"));
                profe.setEdad(rs.getInt("edad"));
                listaProfesores.add(profe);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Profesor.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.cerrarConexion();
        return listaProfesores;
    }

    public String getDatosBasico() {
        return id + "-" + nombre;
    }
}
