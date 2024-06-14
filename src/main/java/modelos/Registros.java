/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelos;

/**
 *
 * @author eliza
 */
public class Registros {
    private String idpelicula;
    private String Nombre;
    private String Genero;

    public Registros(String idpelicula, String Nombre, String Genero) {
        this.idpelicula = idpelicula;
        this.Nombre = Nombre;
        this.Genero = Genero;
    }
    
    

    public String getIdpelicula() {
        return idpelicula;
    }

    public void setIdpelicula(String idpelicula) {
        this.idpelicula = idpelicula;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String Nombre) {
        this.Nombre = Nombre;
    }

    public String getGenero() {
        return Genero;
    }

    public void setGenero(String Genero) {
        this.Genero = Genero;
    }
    
    
}
