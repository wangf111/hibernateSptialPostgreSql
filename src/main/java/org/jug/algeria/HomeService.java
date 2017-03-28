package org.jug.algeria;

import com.vividsolutions.jts.geom.Geometry;
import org.geotools.kml.KML;
import org.geotools.kml.KMLConfiguration;
import org.geotools.xml.PullParser;
import org.jug.algeria.domain.AppUser2;
import org.opengis.feature.simple.SimpleFeature;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by wangf on 2017/3/27.
 */
@Service
public class HomeService {

  public List<AppUser2> parsePlanKml(File kml) {
System.out.println(kml.exists());
    List<AppUser2> appUser2s = new ArrayList<>();
    try (InputStream inputStream = new FileInputStream(kml)) {
      PullParser parser = new PullParser(new KMLConfiguration(), inputStream, KML.Placemark);
      SimpleFeature f;
      int i = 0;
      while ((f = (SimpleFeature) parser.parse()) != null) {
        Geometry geometry = (Geometry) f.getDefaultGeometry();

        AppUser2 appUser2 = new AppUser2();
        appUser2.setGeometry(geometry);
        appUser2.setUsername("geometry"+i++);
        appUser2s.add(appUser2);

      }
    } catch (Exception e) {
      System.out.println(e);
    }

    return appUser2s;
  }
}
