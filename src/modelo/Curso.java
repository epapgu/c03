/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

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
public class Curso {
    public int id;
    public String nombre;
    public int capacidad;
    ArrayList<Estudiantes> Estudiantes = new ArrayList<>();

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

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    public ArrayList<Estudiantes> getEstudiantes() {
        return Estudiantes;
    }

    public void setEstudiantes(ArrayList<Estudiantes> Estudiantes) {
        this.Estudiantes = Estudiantes;
    }
    
    public ArrayList<Curso> listar() {
        Conexion con = new Conexion();
        //Conecto a BD
        con.conectar();
        String sql = "SELECT * FROM cursos";
        ArrayList<Curso> listaCursos = new ArrayList<>();

        try {
            //Prepara la sentencia SQL a ejecutar
            PreparedStatement sp = con.getCon().prepareStatement(sql);
            ResultSet rs;
            //Ejecuata la sentencia y retorna los datos
            rs = sp.executeQuery();
            while (rs.next()) {
                Curso curso = new Curso();
                curso.setId(rs.getInt("id"));
                curso.setNombre(rs.getString("nombre"));               
                curso.setCapacidad(rs.getInt("capacidad"));                        
                listaCursos.add(curso);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Profesor.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.cerrarConexion();
        return listaCursos;
    }
    
    public ArrayList<Estudiantes> listarEstudiantes(int id) {
        Conexion con = new Conexion();
        //Conecto a BD
        con.conectar();
        String sql = "SELECT estudiantes.* FROM cursos INNER JOIN curso_estudiante "
                + " ON cursos.id = curso_estudiante.id_curso "
                + " INNER JOIN estudiantes ON estudiantes.id = curso_estudiante.id_estudiante "
                + " WHERE id_curso = ?";
        ArrayList<Estudiantes> listaEstudiantes = new ArrayList<>();

        try {
            //Prepara la sentencia SQL a ejecutar
            PreparedStatement sp = con.getCon().prepareStatement(sql);
            ResultSet rs;
            sp.setInt(1, id);
            //Ejecuata la sentencia y retorna los datos
            rs = sp.executeQuery();
            while (rs.next()) {
                Estudiantes alumno = new Estudiantes();
                alumno.setId(rs.getInt("id"));
                alumno.setNombre(rs.getString("nombre"));               
                alumno.setEdad(rs.getInt("edad"));                        
                alumno.setCelular(rs.getString("celular"));                        
                listaEstudiantes.add(alumno);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Profesor.class.getName()).log(Level.SEVERE, null, ex);
        }
        con.cerrarConexion();
        return listaEstudiantes;
    }
}
