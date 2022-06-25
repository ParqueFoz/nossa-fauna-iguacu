package br.edu.utfpr.nossafaunaiguacu.data.model;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

public class AnimalModel implements Serializable {

    @SerializedName("id_animal")
    private Integer id;
    @SerializedName("nome")
    private String name;
    @SerializedName("nome_cientifico")
    private String cName;
    @SerializedName("img_profile_url")
    private String imgUrl;
    @SerializedName("caracteristica")
    private String particulars;
    @SerializedName("alimentacao")
    private String feeding;
    @SerializedName("reproducao")
    private String repro;
    @SerializedName("habitat")
    private String habitat;
    @SerializedName("import_eco")
    private String importEco;
    @SerializedName("pegada_img_url")
    private String footPrint;
    @SerializedName("encontrado")
    private String encontrado;
    @SerializedName("background_image")
    private String backgroundImage;

    public String getcName() {
        return cName;
    }

    public void setcName(String cName) {
        this.cName = cName;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getParticulars() {
        return particulars;
    }

    public void setParticulars(String particulars) {
        this.particulars = particulars;
    }

    public String getFeeding() {
        return feeding;
    }

    public void setFeeding(String feeding) {
        this.feeding = feeding;
    }

    public String getRepro() {
        return repro;
    }

    public void setRepro(String repro) {
        this.repro = repro;
    }

    public String getHabitat() {
        return habitat;
    }

    public void setHabitat(String habitat) {
        this.habitat = habitat;
    }

    public String getImportEco() {
        return importEco;
    }

    public void setImportEco(String importEco) {
        this.importEco = importEco;
    }

    public String getFootPrint() {
        return footPrint;
    }

    public void setFootPrint(String footPrint) {
        this.footPrint = footPrint;
    }

    public String getEncontrado() {
        return encontrado;
    }

    public void setEncontrado(String encontrado) {
        this.encontrado = encontrado;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
