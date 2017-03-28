package org.jug.algeria.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.vividsolutions.jts.geom.*;
import org.geotools.geojson.feature.FeatureJSON;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.opengis.feature.simple.SimpleFeature;

import java.io.StringWriter;

public class GeoToolsUtil {
    private static GeometryFactory geometryFactory = JTSFactoryFinder.getGeometryFactory(null);
    private static final ObjectMapper mapper = new ObjectMapper();

    static {
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    }

    private GeoToolsUtil() {
    }

    public static Point createPoint(Coordinate coordinates) {

        return geometryFactory.createPoint(coordinates);
    }

    public static LineString createLineString(Coordinate[] coordinates) {
        return geometryFactory.createLineString(coordinates);
    }
  public static Polygon createPolygon(Coordinate[] coordinates) {
    return geometryFactory.createPolygon(coordinates);
  }

  public static String getGeoJson(SimpleFeature feature) {

    try (StringWriter stringWriter = new StringWriter()) {
      FeatureJSON featureJSON = new FeatureJSON();
      featureJSON.writeFeature(feature, stringWriter);
      return stringWriter.toString();
    } catch (Exception e) {
      System.out.println(e);
    }

    return "";
  }


}
