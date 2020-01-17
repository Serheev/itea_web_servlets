package com.serheev.app.entities;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode
@Table(name = "users")
@NamedQuery(name = "UserEntity.getAll", query = "SELECT u FROM UserEntity u")
public class UserEntity extends BaseEntity {
    @Column(name = "name")
    private String name;
    @Column(name = "password")
    private String password;
}
