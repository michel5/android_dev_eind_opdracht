package com.example.michel_desktop.mobiledev.models;

import android.arch.persistence.room.ColumnInfo;
import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;
import android.support.annotation.NonNull;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import java.io.Serializable;

@Entity(tableName = "balanceStorge")
public class BalanceModel implements Serializable {
    @PrimaryKey(autoGenerate = false)
	@ColumnInfo(name = "asset")
	@NonNull
    @SerializedName("asset")
    @Expose
    private String asset;
    @ColumnInfo(name = "free")
    @SerializedName("free")
    @Expose
    private String free;
    @ColumnInfo(name = "locked")
    @SerializedName("locked")
    @Expose
    private String locked;
    private final static long serialVersionUID = 3673153441403797104L;

    /**
     * Getter cointag
     * @return cointag
     */
    public String getAsset() {
        return asset;
    }

    /**
     * Set cointag
     * @param asset cointag
     */
    public void setAsset(String asset) {
        this.asset = asset;
    }

    /**
     * Beschikbare belance
     * @return bschikbare balance
     */
    public String getFree() {
        return free;
    }

    /**
     * Set beschikbare balance
     * @param free hoeveel belance er beschikbaar is
     */
    public void setFree(String free) {
        this.free = free;
    }

    /**
     * Hoeveel balance er in order staat
     * @return balance wat in order staat
     */
    public String getLocked() {
        return locked;
    }


    public void setLocked(String locked) {
        this.locked = locked;
    }

}

