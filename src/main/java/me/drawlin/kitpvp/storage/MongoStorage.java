package me.drawlin.kitpvp.storage;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientOptions;
import com.mongodb.MongoCredential;
import com.mongodb.ServerAddress;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import lombok.Getter;

import java.util.concurrent.CompletableFuture;

@Getter
public class MongoStorage {

    private MongoClient mongoClient;
    private MongoDatabase mongoDatabase;
    private MongoClientOptions mongoClientOptions;
    private MongoCredential mongoCredential;
    private boolean connected;

    public MongoStorage(boolean auth, String ip, int port, String database) {
        this(auth, ip, port, database, "", "");
    }

    public MongoStorage(boolean auth, String ip, int port, String database, String userName, String password) {
        try {
            mongoClientOptions = MongoClientOptions.builder().connectionsPerHost(50).build();
            if (auth) {
                mongoCredential = MongoCredential.createCredential(userName, database, password.toCharArray());

                this.mongoClient = new MongoClient(new ServerAddress(ip, port), mongoCredential, mongoClientOptions);
            } else {
                this.mongoClient = new MongoClient(new ServerAddress(ip, port));
            }

            this.mongoDatabase = this.mongoClient.getDatabase(database);
            connected = true;
            System.out.println("Connected to mongo successfully.");
        } catch (Exception e) {
            connected = false;
            e.printStackTrace();
            System.out.println("Failed to connect to mongo.");
        }
    }

    public CompletableFuture<MongoCollection> getCollection(String collectionName) {
        return CompletableFuture.supplyAsync(() -> mongoDatabase.getCollection(collectionName));
    }

}
