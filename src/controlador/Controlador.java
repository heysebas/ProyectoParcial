/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import src.ClsConexion;
import src.Persona;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.table.DefaultTableModel;
import src.Producto;

/**
 *
 * @author Sebastian
 */
public class Controlador {

    ClsConexion conexion = new ClsConexion();

    public Controlador() {

    }

    public boolean guardar(String cedula, String nombre, String apellido, String correo, String contrasena) {
        Persona usuario = new Persona(cedula, nombre, apellido, correo, contrasena);
        conexion.conectar();
        try {
            conexion.getSentenciaSQL().execute("insert into usuario(cedula,nombre,apellido,correo,contrasena) "
                    + "values('" + usuario.getCedula() + "','"
                    + usuario.getNombre() + "','"
                    + usuario.getApellido() + "',"
                    + usuario.getCorreo() + ","
                    + usuario.getContrasena() + ")");//consulta
            conexion.desconectar();//se desconecta de la base de datos          
            return true;
        } catch (SQLException ex) {
            conexion.desconectar();//se desconecta de la base de datos          
            return false;
        }
    }

    public boolean modificar(String cedula, String nombre, String apellido, String correo, String contrasena) {
        Persona articulo = new Persona(cedula, nombre, apellido, correo, contrasena);
        conexion.conectar();
        try {
            conexion.getSentenciaSQL().execute("update usuario set nombre='" + articulo.getNombre()
                    + "',apellido='" + articulo.getApellido()
                    + "',correo='" + articulo.getCorreo() + "',"
                    + "contrasena=" + articulo.getContrasena()
                    + " where cedula='" + articulo.getCedula() + "'");//consulta
            conexion.desconectar();//se desconecta de la base de datos          
            return true;
        } catch (SQLException ex) {
            conexion.desconectar();//se desconecta de la base de datos          
            return false;
        }
    }


    public List<String> buscarCorreo(String correo) {

        List<String> temp = new ArrayList<String>();

        conexion.conectar();

        try {
            conexion.setResultadoDB(conexion.getSentenciaSQL().
                    executeQuery("select cedula,nombre,apellido,correo,"
                            + "contrasena from usuario where "
                            + "cedula='" + correo + "'"));//consulta        

            if (conexion.getResultadoDB().next()) {
                temp.add(conexion.getResultadoDB().getString("cedula"));
                temp.add(conexion.getResultadoDB().getString("nombre"));
                temp.add(conexion.getResultadoDB().getString("apellido"));
                temp.add(conexion.getResultadoDB().getString("correo"));
                temp.add(conexion.getResultadoDB().getDouble("contrasena") + "");
            }
            conexion.desconectar();//se desconecta de la base de datos                
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName())
                    .log(Level.SEVERE, null, ex);
            conexion.desconectar();//se desconecta de la base de datos
        }
        return temp;
    }

    public List<String> buscar(String cedula) {

        List<String> temp = new ArrayList<String>();

        conexion.conectar();

        try {
            conexion.setResultadoDB(conexion.getSentenciaSQL().
                    executeQuery("select cedula,nombre,apellido,correo,"
                            + "contrasena from usuario where "
                            + "cedula='" + cedula + "'"));//consulta        

            if (conexion.getResultadoDB().next()) {
                temp.add(conexion.getResultadoDB().getString("cedula"));
                temp.add(conexion.getResultadoDB().getString("nombre"));
                temp.add(conexion.getResultadoDB().getString("apellido"));
                temp.add(conexion.getResultadoDB().getString("correo"));
                temp.add(conexion.getResultadoDB().getDouble("contrasena") + "");
            }
            conexion.desconectar();//se desconecta de la base de datos                
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName())
                    .log(Level.SEVERE, null, ex);
            conexion.desconectar();//se desconecta de la base de datos
        }
        return temp;
    }

    public boolean eliminar(String cedula) {

        conexion.conectar();

        try {
            conexion.getSentenciaSQL().execute("delete from usuario where cedula='" + cedula + "'");//consulta
            conexion.desconectar();//se desconecta de la base de datos          
            return true;
        } catch (SQLException ex) {
            conexion.desconectar();//se desconecta de la base de datos          
            return false;
        }
    }

    public DefaultTableModel listar() {
        DefaultTableModel temporal;
        String nombreColumnas[] = {"Cedula", "Nombre", "Apellido", "Correo", "Contraseña"};
        temporal = new DefaultTableModel(
                new Object[][]{}, nombreColumnas);
        conexion.conectar();
        try {
            conexion.setResultadoDB(conexion.getSentenciaSQL().
                    executeQuery("select cedula,nombre,apellido,correo,"
                            + "contrasena from usuario order by cedula"));//consulta        
            while (conexion.getResultadoDB().next()) {
                temporal.addRow(new Object[]{
                    conexion.getResultadoDB().getString("cedula"),
                    conexion.getResultadoDB().getString("nombre"),
                    conexion.getResultadoDB().getString("apellido"),
                    conexion.getResultadoDB().getString("correo"),
                    conexion.getResultadoDB().getString("contrasena")});
            }
            conexion.desconectar();//se desconecta de la base de datos                
        } catch (SQLException ex) {
            Logger.getLogger(Persona.class.getName()).
                    log(Level.SEVERE, null, ex);
            conexion.desconectar();//se desconecta de la base de datos
        }

        return temporal;

    }

    public boolean validarLoging(String correo, String contrasena) {

        List<Persona> usuarios = new ArrayList<Persona>();

        conexion.conectar();

        try {

            conexion.setResultadoDB(conexion.getSentenciaSQL().executeQuery("select cedula, nombre, apellido, correo, contrasena "
                    + "from usuario"));
////              conexion.setResultadoDB(conexion.getSentenciaSQL().executeQuery("select cedula,nombre,apellido,correo,contrasena "
////                      +"from usuario "
////                            ));//consulta    

            while (conexion.getResultadoDB().next()) {

                String cedula = conexion.getResultadoDB().getString("cedula");
                String nombre = conexion.getResultadoDB().getString("nombre");
                String apellido = conexion.getResultadoDB().getString("apellido");
                String corre = conexion.getResultadoDB().getString("correo");
                String contra = conexion.getResultadoDB().getString("contrasena");

                Persona temp = new Persona(cedula, nombre, apellido, corre, contra);

                usuarios.add(temp);

            }

            for (int i = 0; i < usuarios.size(); i++) {

                if (usuarios.get(i).getCorreo().equals(correo)) {

                    if (usuarios.get(i).getContrasena().equals(contrasena)) {

                        System.out.println(usuarios.get(i).getNombre());
                        conexion.desconectar();
                        return true;
                    }
                }
            }
            conexion.desconectar();
            return true;

        } catch (SQLException ex) {

            Logger.getLogger(Controlador.class.getName()).log(Level.SEVERE, null, ex);
            conexion.desconectar();
            return false;

        }

    }


    //-------------------------------------------------------------------
    public boolean guardarProductString(String codigo, String nombre, double precio, int cantida, String categoria, String descripcion, String cedulaPersona) {
        Producto producto = new Producto(codigo, nombre, precio, cantida, categoria, descripcion, cedulaPersona);
        conexion.conectar();
        try {
            conexion.getSentenciaSQL().execute("insert into pruducto(codigo,nombre,precio,cantida,categoria,descripcion,cedula) "
                    + "values('" + producto.getCodigo() + "','"
                    + producto.getNombre() + "','"
                    + producto.getPrecio() + "',"
                    + producto.getCantida() + ","
                    + producto.getCategoria() + ","
                    + producto.getDescripcion() + ","
                    + producto.getCedulaPersona() + ")");//consulta
            conexion.desconectar();//se desconecta de la base de datos          
            return true;
        } catch (SQLException ex) {
            conexion.desconectar();//se desconecta de la base de datos          
            return false;
        }
    }

    public DefaultTableModel listarProducto(String cedula) {

        DefaultTableModel temporal;
        String nombreColumnas[] = {"Codigo", "Nombre", "Precio", "Cantida", "Categoria", "Descripcion"};
        temporal = new DefaultTableModel(
                new Object[][]{}, nombreColumnas);
        conexion.conectar();
        try {
            conexion.setResultadoDB(conexion.getSentenciaSQL().
                    executeQuery("select codigo,nombre,precio,cantida,categoria,"
                            + "descripcion,cedula from pruducto order by codigo"));//consulta        
            while (conexion.getResultadoDB().next()) {
                if (conexion.getResultadoDB().getString("cedula").equals(cedula)) {
                    temporal.addRow(new Object[]{
                        conexion.getResultadoDB().getString("codigo"),
                        conexion.getResultadoDB().getString("nombre"),
                        conexion.getResultadoDB().getString("precio"),
                        conexion.getResultadoDB().getString("cantida"),
                        conexion.getResultadoDB().getString("categoria"),
                        conexion.getResultadoDB().getString("descripcion")});

                }

            }
            conexion.desconectar();//se desconecta de la base de datos                
        } catch (SQLException ex) {
            Logger.getLogger(Persona.class.getName()).
                    log(Level.SEVERE, null, ex);
            conexion.desconectar();//se desconecta de la base de datos
        }

        return temporal;

    }

    public List<String> buscarProducto(String codigo) {

        List<String> temp = new ArrayList<String>();

        conexion.conectar();

        try {
            conexion.setResultadoDB(conexion.getSentenciaSQL().
                    executeQuery("select codigo,nombre,precio,cantida,categoria,"
                            + "descripcion,cedula from pruducto where "
                            + "codigo='" + codigo + "'"));//consulta        

            if (conexion.getResultadoDB().next()) {
                temp.add(conexion.getResultadoDB().getString("codigo"));
                temp.add(conexion.getResultadoDB().getString("nombre"));
                temp.add(conexion.getResultadoDB().getDouble("precio") + "");
                temp.add(conexion.getResultadoDB().getInt("cantida") + "");
                temp.add(conexion.getResultadoDB().getString("categoria"));
                temp.add(conexion.getResultadoDB().getString("descripcion"));
            }
            conexion.desconectar();//se desconecta de la base de datos                
        } catch (SQLException ex) {
            Logger.getLogger(Controlador.class.getName())
                    .log(Level.SEVERE, null, ex);
            conexion.desconectar();//se desconecta de la base de datos
        }
        return temp;
    }

    public boolean eliminarProducto(String codigo) {

        conexion.conectar();

        try {
            conexion.getSentenciaSQL().execute("delete from pruducto where codigo='" + codigo + "'");//consulta
            conexion.desconectar();//se desconecta de la base de datos          
            return true;
        } catch (SQLException ex) {
            conexion.desconectar();//se desconecta de la base de datos          
            return false;
        }
    }

    public boolean modificarProducto(String codigo, String nombre, double precio, int cantida, String categoria, String descripcion, String cedulaPersona) {
        Producto articulo = new Producto(codigo, nombre, precio, cantida, categoria, descripcion, cedulaPersona);
        conexion.conectar();
        try {
            conexion.getSentenciaSQL().execute("update pruducto set nombre='" + articulo.getNombre()
                    + "',precio='" + articulo.getPrecio()
                    + "',cantida='" + articulo.getPrecio()
                    + "',categoria='" + articulo.getCategoria()
                    + "',descripcion='" + articulo.getDescripcion()
                    + "',cedula='" + articulo.getCedulaPersona()
                    + "' where codigo=" + articulo.getCodigo());//consulta
            conexion.desconectar();//se desconecta de la base de datos          
            return true;
        } catch (SQLException ex) {
            conexion.desconectar();//se desconecta de la base de datos          
            return false;
        }
    }

}
