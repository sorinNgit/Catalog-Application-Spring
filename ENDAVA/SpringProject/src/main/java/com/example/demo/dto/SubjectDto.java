package com.example.demo.dto;

import javax.validation.constraints.*;

public class SubjectDto {

    private Long id;

    @NotEmpty(message = "Must not be empty")
    @NotBlank(message = "must.not.be.blank")
    @NotNull(message = "name.cannot.be.null")
    @Size(min = 2, max = 50, message = "must.be.between.2.and.50.characters")
    private String name;

    @NotNull(message = "credit.points.cannot.be.empty")
    @Min(value = 1, message = "credit.points.cannot.be.less.than.one")
    @Max(value = 20,message = "credit.points.cannot.be.more.than.twenty" )
    private Integer creditPoints;

    @NotNull(message = "optional.can.not.be.null")
    private Boolean isOptional;

    @NotNull(message = "scoring.can.not.be.null")
    private SubjectScoringDto ssdto;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getCreditPoints() {
        return creditPoints;
    }

    public void setCreditPoints(Integer creditPoints) {
        this.creditPoints = creditPoints;
    }

    public Boolean getOptional() {
        return isOptional;
    }

    public void setOptional(Boolean optional) {
        isOptional = optional;
    }

    public SubjectScoringDto getSsdto() {
        return ssdto;
    }

    public void setSsdto(SubjectScoringDto ssdto) {
        this.ssdto = ssdto;
    }
}
