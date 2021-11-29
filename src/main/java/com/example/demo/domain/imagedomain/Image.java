package com.example.demo.domain.imagedomain;

import java.util.UUID;

import javax.persistence.Embeddable;
import javax.persistence.Entity;
import com.example.demo.core.EntityBase;

import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.relational.core.mapping.Table;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@RedisHash("Image")

@Embeddable

@Entity

@Table("image")
public @NoArgsConstructor @Getter @Setter class Image extends EntityBase {

    @NotEmpty
    private byte[] image;

    @Override
    public boolean isNew() {
        return this.isThisNew();
    }

    public boolean isThisNew() {
        return false;
    }

    @Override
    public UUID getId() {

        return null;
    }

    public void setThisNew(boolean b) {
    }

    public void setId(UUID randomUUID) {
    }

    public Object getImage() {
        return null;
    }

    public void setImage(byte[] bytes) {

    }

    public byte[] getContent() {
        return null;
    }
}
