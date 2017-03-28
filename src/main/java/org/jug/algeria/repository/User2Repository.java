package org.jug.algeria.repository;

import com.vividsolutions.jts.geom.Geometry;
import org.jug.algeria.domain.AppUser1;
import org.jug.algeria.domain.AppUser2;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface User2Repository extends CrudRepository<AppUser2, Long> {

  @Query("select e from AppUser2 e where within(e.geometry, :filter) = true")
  List<AppUser1> findWithin(@Param("filter") Geometry filter);
}
