package org.jug.algeria.repository;

import com.vividsolutions.jts.geom.Geometry;
import org.jug.algeria.domain.AppUser1;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface User1Repository extends CrudRepository<AppUser1, Long> {

  @Query("select e from AppUser1 e where within(e.geometry, :filter) = true")
  List<AppUser1> findWithin(@Param("filter")Geometry filter);
}
