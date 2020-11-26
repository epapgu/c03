
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextField;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import vista.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author NATC
 */
public class SIColegioTest 
{
    
    private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
    private final PrintStream originalOut = System.out;
    
    @Before
    public void setUpStreams() {
        System.setOut(new PrintStream(outContent));
    }

    @After
    public void restoreStreams() {
        System.setOut(originalOut);
    }
    
    @Test
    public void probarCursos()
    {
       frmCurso frame;
       JButton boton;
                
       frame = new frmCurso();
       frame.setVisible(Boolean.TRUE);
        
        JTextField id;
        JTextField nombre;
        JTextField capacidad;
        JComboBox profesor;
        
        id = (JTextField)TestUtils.getChildNamed(frame, "id");
        nombre = (JTextField)TestUtils.getChildNamed(frame, "nombre");
        capacidad = (JTextField)TestUtils.getChildNamed(frame, "capacidad");
        profesor = (JComboBox)TestUtils.getChildNamed(frame, "profesor");
        
        id.setText("100");
        nombre.setText("Pedro");
        capacidad.setText("20");
        profesor.setSelectedIndex(0);
        
        boton = (JButton)TestUtils.getChildNamed(frame, "agregar");
        
        assertNotNull("No puede acceder al Botón agregar", boton);

        ActionEvent event;
        long when;

        when  = System.currentTimeMillis();
        event = new ActionEvent(boton, ActionEvent.ACTION_PERFORMED, "Prueba", when, 0);

        for (ActionListener listener : boton.getActionListeners()) 
        {
            listener.actionPerformed(event);
        }
        
        assertEquals("Katherine termina esta vaina", outContent.toString().trim());
    }
    
    @Test
    public void probarProfesor()
    {
       frmProfesor frame;
       JButton boton;
                
       frame = new frmProfesor();
       frame.setVisible(Boolean.TRUE);
        
        JTextField id;
        JTextField nombre;
        JTextField correo;
        JTextField edad;
        
        id = (JTextField)TestUtils.getChildNamed(frame, "id");
        nombre = (JTextField)TestUtils.getChildNamed(frame, "nombre");
        correo = (JTextField)TestUtils.getChildNamed(frame, "correo");
        edad = (JTextField)TestUtils.getChildNamed(frame, "edad");
        
        id.setText("100");
        nombre.setText("Pedro Pérez");
        correo.setText("pedro.perez@gmail.com");
        edad.setText("50");
        
        boton = (JButton)TestUtils.getChildNamed(frame, "agregar");
        
        assertNotNull("No puede acceder al Botón agregar", boton);

        ActionEvent event;
        long when;

        when  = System.currentTimeMillis();
        event = new ActionEvent(boton, ActionEvent.ACTION_PERFORMED, "Prueba", when, 0);

        for (ActionListener listener : boton.getActionListeners()) 
        {
            listener.actionPerformed(event);
        }
        
        assertEquals("Guardado con éxito", outContent.toString().trim());
    }
    
}
