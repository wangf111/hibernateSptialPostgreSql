package org.jug.algeria.domain;


import com.vividsolutions.jts.geom.Geometry;
import org.postgresql.geometric.PGpolygon;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
public class AppUser1 {

  @Id @GeneratedValue(strategy = GenerationType.AUTO)
  private Long id;

  @NotNull
  private String username;

  @NotNull
  @Column(name = "geometry")
  private Geometry geometry;

  public Geometry getGeometry() {
    return geometry;
  }

  public void setGeometry(Geometry geometry) {
    this.geometry = geometry;
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


  @Override
  public String toString() {
    return "AppUser1{" +
      "id=" + id +
      ", username='" + username + '\'' +
      ", geometry=" + geometry +
      '}';
  }
}
