package com.example.demo.domain.imagedomain;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.validation.constraints.NotEmpty;

import com.example.demo.core.EntityBase;

import org.springframework.data.relational.core.mapping.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;


@Embeddable
@Entity
@Table("image")
public @NoArgsConstructor @Getter @Setter class Image extends EntityBase {

    @NotEmpty
    private byte[] image;

}