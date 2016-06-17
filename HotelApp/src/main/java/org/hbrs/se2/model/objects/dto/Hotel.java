package org.hbrs.se2.model.objects.dto;

/**
 *
 * @author Felix
 */
public class Hotel implements java.io.Serializable {

    private String name;
    private Integer id;
    private String ort;
    private String description;

    public Hotel(String name, Integer id, String ort, String description) {
        this.name = name;
        this.id = id;
        this.ort = ort;
        this.description = description;
    }
    
    public Hotel() {
        
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public Integer getId() {
        return id;
    }

    public String getOrt() {
        return ort;
    }

    public String getDescription() {
        return description;
    }

}
