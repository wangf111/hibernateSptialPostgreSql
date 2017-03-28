package org.jug.algeria.domain;


import org.postgresql.geometric.PGpoint;
import org.postgresql.geometric.PGpolygon;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;

@Entity
public class AppUser {

  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  private String username;

  @NotNull
  private PGpolygon pGpolygon;

  public PGpolygon getpGpolygon() {
    return pGpolygon;
  }

  public void setpGpolygon(PGpolygon pGpolygon) {
    this.pGpolygon = pGpolygon;
  }

  public Long getId() {
    return id;
  }

  public void setId(Long id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }
}
