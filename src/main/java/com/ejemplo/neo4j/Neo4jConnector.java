/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
/*package com.ejemplo.neo4j;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.TransactionWork;

public class Neo4jConnector {
    private final Driver driver;

    public Neo4jConnector(String uri, String user, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    public void close() {
        driver.close();
    }

    public void createNode(final String nodeName) {
        try (Session session = driver.session()) {
            session.writeTransaction(new TransactionWork<Void>() {
                @Override
                public Void execute(Transaction tx) {
                    tx.run("CREATE (n:Node {name: $name})", org.neo4j.driver.Values.parameters("name", nodeName));
                    return null;
                }
            });
        }
    }

    public static void main(String[] args) {
        Neo4jConnector connector = new Neo4jConnector("bolt://localhost:7687", "neo4j", "1234567890");

        // Crear un nodo
        connector.createNode("MiNodo");

        // Cerrar la conexión
        connector.close();
    }
}*/
/*package com.ejemplo.neo4j;

import org.neo4j.driver.AuthTokens;
import org.neo4j.driver.Driver;
import org.neo4j.driver.GraphDatabase;
import org.neo4j.driver.Session;
import org.neo4j.driver.Transaction;
import org.neo4j.driver.TransactionWork;

public class Neo4jConnector {
    private final Driver driver;

    public Neo4jConnector(String uri, String user, String password) {
        driver = GraphDatabase.driver(uri, AuthTokens.basic(user, password));
    }

    public void close() {
        driver.close();
    }

    public void createMovie(String id, String name, String genre) {
        try (Session session = driver.session()) {
            session.writeTransaction(new TransactionWork<Void>() {
                @Override
                public Void execute(Transaction tx) {
                    tx.run("CREATE (m:Movie {id: $id, name: $name})", 
                            org.neo4j.driver.Values.parameters("id", id, "name", name));
                    tx.run("MERGE (g:Genre {name: $genre})", 
                            org.neo4j.driver.Values.parameters("genre", genre));
                    tx.run("MATCH (m:Movie {id: $id}), (g:Genre {name: $genre}) " +
                           "CREATE (m)-[:BELONGS_TO]->(g)",
                            org.neo4j.driver.Values.parameters("id", id, "genre", genre));
                    return null;
                }
            });
        }
    }

    public static void main(String[] args) {
        Neo4jConnector connector = new Neo4jConnector("bolt://localhost:7687", "neo4j", "1234567890");

        // Example usage
        connector.createMovie("1", "MiNodo", "Drama");
        connector.createMovie("2", "matrix", "");

        // Close the connection
        connector.close();
    }
}*/

package com.ejemplo.neo4j;

import javax.swing.JFrame;

import servicios.PeliculaServices;
import vistas.VtnPrincipal;

public class Neo4jConnector {
        public static void main(String[] args) {
        // Instancia de PeliculaServices
        PeliculaServices peliculaServices = new PeliculaServices();

        // Instancia de VtnPrincipal y configuración del JFrame
        VtnPrincipal vtnPrincipal = new VtnPrincipal(peliculaServices);

        JFrame frame = new JFrame("Registro de Películas");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(vtnPrincipal.getContentPane());
        frame.pack();
        frame.setVisible(true);
    }


}