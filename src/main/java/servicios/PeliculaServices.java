/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package servicios;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import modelos.Registros;
import org.neo4j.driver.TransactionWork;

public class PeliculaServices {
    private Driver driver;

    public PeliculaServices() {
        // Configura la conexión al servidor Neo4j, ajusta según tu configuración
        this.driver = GraphDatabase.driver("bolt://localhost:7687", AuthTokens.basic("neo4j", "1234567890"));
    }

    public Registros consultarPelicula(String id) {
        Registros pelicula = null;
        try (Session session = driver.session()) {
            pelicula = session.readTransaction(new TransactionWork<Registros>() {
                @Override
                public Registros execute(Transaction tx) {
                    org.neo4j.driver.Result result = tx.run("MATCH (p:Pelicula {id: $id}) RETURN p.id AS id, p.Nombre AS nombre, p.Genero AS genero",
                            org.neo4j.driver.Values.parameters("id", id));
                    if (result.hasNext()) {
                        org.neo4j.driver.Record record = result.next();
                        return new Registros(record.get("id").asString(), record.get("nombre").asString(), record.get("genero").asString());
                    }
                    return null;
                }
            });
        }
        return pelicula;
    }

public Registros registrarPelicula(Registros pelicula, String idGenero) {
    try (Session session = driver.session()) {
        session.writeTransaction(new TransactionWork<Void>() {
            @Override
            public Void execute(Transaction tx) {
                // Crear la película
                tx.run("CREATE (p:Pelicula {id: $id, Nombre: $nombre})",
                        org.neo4j.driver.Values.parameters("id", pelicula.getIdpelicula(),
                                "nombre", pelicula.getNombre()));

                // Crear la relación con el género
                tx.run("MATCH (p:Pelicula {id: $idPelicula}), (g:Genero {id: $idGenero}) " +
                        "CREATE (p)-[:ES_DE]->(g)",
                        org.neo4j.driver.Values.parameters("idPelicula", pelicula.getIdpelicula(),
                                "idGenero", idGenero));

                return null;
            }
        });
    }
    return pelicula;
}

    public Registros actualizarPelicula(Registros pelicula, String id) {
        try (Session session = driver.session()) {
            session.writeTransaction(new TransactionWork<Void>() {
                @Override
                public Void execute(Transaction tx) {
                    tx.run("MATCH (p:Pelicula {id: $id}) SET p.Nombre = $nombre, p.Genero = $genero",
                            org.neo4j.driver.Values.parameters("id", id,
                                    "nombre", pelicula.getNombre(),
                                    "genero", pelicula.getGenero()));
                    return null;
                }
            });
        }
        return pelicula;
    }

    public Boolean eliminarPelicula(String id) {
        try (Session session = driver.session()) {
            session.writeTransaction(new TransactionWork<Void>() {
                @Override
                public Void execute(Transaction tx) {
                    tx.run("MATCH (p:Pelicula {id: $id}) DELETE p",
                            org.neo4j.driver.Values.parameters("id", id));
                    return null;
                }
            });
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    // Método adicional para consultar si existe una película por su código
    public Boolean consultarSiExistePelicula(String id) {
        try (Session session = driver.session()) {
            return session.readTransaction(new TransactionWork<Boolean>() {
                @Override
                public Boolean execute(Transaction tx) {
                    org.neo4j.driver.Result result = tx.run("MATCH (p:Pelicula {id: $id}) RETURN count(p) > 0",
                            org.neo4j.driver.Values.parameters("id", id));
                    return result.single().get(0).asBoolean();
                }
            });
        } catch (Exception e) {
            return false;
        }
    }

    public void close() {
        driver.close();
    }
}