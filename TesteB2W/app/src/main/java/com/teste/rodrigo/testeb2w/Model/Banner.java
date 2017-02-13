package com.teste.rodrigo.testeb2w.Model;

import java.io.Serializable;

/**
 * Created by not on 09/02/2017.
 */

public class Banner implements Serializable {
    private long id;
    private String urlImagem;
    private String linkUrl;
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getUrlImagem() {
        return urlImagem;
    }

    public void setUrlImagem(String urlImagem) {
        this.urlImagem = urlImagem;
    }

    public String getLinkUrl() {
        return linkUrl;
    }

    public void setLinkUrl(String linkUrl) {
        this.linkUrl = linkUrl;
    }


}
