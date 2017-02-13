package com.teste.rodrigo.testeb2w.Model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by not on 09/02/2017.
 */

public class Banners {
    @SerializedName("data")
    private List<Banner> banners;

    public List<Banner> getBanners() {
        return banners;
    }

    public void setBanners(List<Banner> banners) {
        this.banners = banners;
    }
}
