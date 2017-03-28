package org.jug.algeria.controller;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Geometry;
import org.jug.algeria.domain.AppUser;
import org.jug.algeria.domain.AppUser1;
import org.jug.algeria.domain.AppUser2;
import org.jug.algeria.repository.User1Repository;
import org.jug.algeria.repository.User2Repository;
import org.jug.algeria.repository.UserRepository;
import org.jug.algeria.util.GeoToolsUtil;
import org.postgresql.geometric.PGpoint;
import org.postgresql.geometric.PGpolygon;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Consumer;

@RestController
@RequestMapping(value = "/", produces = MediaType.APPLICATION_JSON_VALUE)
public class HomeController {

  @Autowired
  private UserRepository userRepository;
  @Autowired
  private JdbcTemplate jdbcTemplate;
  @Autowired
  private User1Repository user1Repository;
  @Autowired
  private User2Repository user2Repository;


  @GetMapping
  public ModelAndView home() {
    return new ModelAndView("index");
  }

  @GetMapping(value = "/hello")
  public ResponseEntity<String> sayHello() {
    return ResponseEntity.ok().body("Hello there !");
  }

  @PostMapping(value = "/user/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AppUser> create(@PathVariable String username) {
    AppUser appUser = new AppUser();
    appUser.setUsername(username);
    List<PGpoint> pGpoints = new ArrayList<>();
    for(int i=0;i<10000;i++) {
      pGpoints.add(new PGpoint(i - 1, i));
    }

    PGpoint[] points = pGpoints.toArray(new PGpoint[pGpoints.size()]);
    PGpolygon pgPolygon = new PGpolygon(points);
    appUser.setpGpolygon(pgPolygon);
    AppUser saved = userRepository.save(appUser);
    return ResponseEntity.ok().body(saved);
  }

  @GetMapping(value = "/user", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<AppUser>> findAll() {
//    jdbcTemplate.execute("delete from app_user");
    final List<AppUser> resultList = new ArrayList<>();
    final Iterable<AppUser> all = userRepository.findAll();
    all.forEach(resultList::add);
    return ResponseEntity.ok().body(resultList);
  }

  @PostMapping(value = "/user1", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AppUser1> create1(@RequestParam String username,@RequestParam double lon,@RequestParam double lat) {
    AppUser1 appUser = new AppUser1();
    appUser.setUsername(username);

    Coordinate coordinate = new Coordinate(lon, lat);
    Geometry geometry = GeoToolsUtil.createPoint(coordinate);
    appUser.setGeometry(geometry);
    AppUser1 saved = user1Repository.save(appUser);
    return ResponseEntity.ok().body(saved);
  }
  @PostMapping(value = "/user1/update1", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AppUser1> update1(@RequestParam Long id,@RequestParam double lon,@RequestParam double lat) {
    AppUser1 appUser = user1Repository.findOne(id);
    Coordinate coordinate = new Coordinate(lon, lat);
    Geometry geometry = GeoToolsUtil.createPoint(coordinate);
    appUser.setGeometry(geometry);
    AppUser1 saved = user1Repository.save(appUser);
    saved.setGeometry(null);
    return ResponseEntity.ok().body(saved);
  }

  @PostMapping(value = "/user1/findWithIn", produces = MediaType.APPLICATION_JSON_VALUE)
  public List<AppUser1> findWithin() {

    Coordinate[] coordinates  =  new Coordinate[5];
    coordinates[0] = new Coordinate(10, 10);
    coordinates[1] = new Coordinate(20, 10);
    coordinates[2] = new Coordinate(20, 20);
    coordinates[3] = new Coordinate(10, 20);
    coordinates[4] = new Coordinate(10, 10);
    Geometry geometry = GeoToolsUtil.createPolygon(coordinates);
    List<AppUser1> aa = user1Repository.findWithin(geometry);
    /*aa.stream().forEach(bb->{
      bb.setGeometry(null);
    });*/
    return aa;

  }


  @PostMapping(value = "/user2/{username}", produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<AppUser2> create2(@PathVariable String username) {
    AppUser2 appUser = new AppUser2();
    appUser.setUsername(username);
    List<Coordinate> coordinates  =  new ArrayList<>();
    for(int i=0;i<100000;i++) {
      coordinates.add(new Coordinate(i, i+1));
    }
    coordinates.add(new Coordinate(0, 1));
    Coordinate[] array = coordinates.toArray(new Coordinate[coordinates.size()]);
    Geometry geometry = GeoToolsUtil.createPolygon(array);
    appUser.setGeometry(geometry);
    AppUser2 saved = user2Repository.save(appUser);
    return ResponseEntity.ok().body(saved);
  }



}
