package com.example.michel_desktop.mobiledev.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "keystorge")
public class KeyModel {
    @PrimaryKey(autoGenerate = true)
    private Long id;

    @ColumnInfo(name = "api_key")
    private String apiKey;

    @ColumnInfo(name = "secret_key")
    private String secretKey;

    /**
     * Constructor
     * @param apiKey api key
     * @param secretKey secret key
     */
    public KeyModel(String apiKey, String secretKey) {
        this.apiKey = apiKey;
        this.secretKey = secretKey;
    }

    /**
     * Getter api key
     * @return api key
     */
    public String getApiKey() {
        return apiKey;
    }

    /**
     * Setter api key
     * @param apiKey api key setter
     */
    public void setApiKey(String apiKey) {
        this.apiKey = apiKey;
    }

    /**
     * Getter secret key
     * @return secret key
     */
    public String getSecretKey() {
        return secretKey;
    }

    /**
     * Setter secret key
     * @param secretKey secret key
     */
    public void setSecretKey(String secretKey) {
        this.secretKey = secretKey;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
