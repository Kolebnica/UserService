package pojo;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Metadata implements Serializable {

    private String[] clani;

    @JsonProperty("opis_projekta")
    private String opisProjekta;
    private String[] mikrostoritve;
    private String[] github;
    private String[] travis;
    private String[] dockerhub;

    public String[] getClani() {
        return clani;
    }

    public void setClani(String[] clani) {
        this.clani = clani;
    }

    public String getOpisProjekta() {
        return opisProjekta;
    }

    public void setOpisProjekta(String opisProjekta) {
        this.opisProjekta = opisProjekta;
    }

    public String[] getMikrostoritve() {
        return mikrostoritve;
    }

    public void setMikrostoritve(String[] mikrostoritve) {
        this.mikrostoritve = mikrostoritve;
    }

    public String[] getGithub() {
        return github;
    }

    public void setGithub(String[] github) {
        this.github = github;
    }

    public String[] getTravis() {
        return travis;
    }

    public void setTravis(String[] travis) {
        this.travis = travis;
    }

    public String[] getDockerhub() {
        return dockerhub;
    }

    public void setDockerhub(String[] dockerhub) {
        this.dockerhub = dockerhub;
    }
}
