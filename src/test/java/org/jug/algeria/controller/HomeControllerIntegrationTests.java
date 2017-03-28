package org.jug.algeria.controller;


import org.jug.algeria.Application;
import org.jug.algeria.HomeService;
import org.jug.algeria.domain.AppUser2;
import org.jug.algeria.repository.User2Repository;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ResourceLoader;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.net.URL;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = Application.class)
public class HomeControllerIntegrationTests {

  @Autowired
  private HomeService homeService;
  @Autowired
  private ResourceLoader resource;
  @Autowired
  private User2Repository user2Repository;

  @Test
  public void insertLargeFile() {

    URL total = resource.getClassLoader().getResource("aaa.KML");
    List<AppUser2> user2s = homeService.parsePlanKml(new File(total.getPath().toString()));
    Iterable<AppUser2> re = user2Repository.save(user2s);
    Assert.assertNotNull(re);
  }

}
