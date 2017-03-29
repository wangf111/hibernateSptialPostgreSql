package org.jug.algeria.batch;

import org.jug.algeria.domain.AppUser2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by wangf on 2017/3/29.
 */
@Service
public class BatchService {
  @Autowired
  private DataSource dataSource;
  private final int batchSize = 500;

  public void batchInsert(List<AppUser2> appUser2List)  {
    try {
      String sql = "insert into app_user2 (username, geometry) values (?, ?)";
      Connection connection = dataSource.getConnection();
      PreparedStatement ps = connection.prepareStatement(sql);


      int count = 0;

      for (AppUser2 employee : appUser2List) {

        ps.setString(1, employee.getUsername());
        ps.setObject(2, employee.getGeometry());
        ps.addBatch();

        if (++count % batchSize == 0) {
          ps.executeBatch();
        }
      }
      ps.executeBatch(); // insert remaining records
      ps.close();
      connection.close();
    } catch (Exception e) {

    }
  }
}
