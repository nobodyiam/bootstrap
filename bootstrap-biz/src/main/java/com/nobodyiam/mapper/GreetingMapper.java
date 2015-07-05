package com.nobodyiam.mapper;

import com.nobodyiam.dto.Greeting;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;

import java.util.List;

/**
 * Created by Jason on 7/5/15.
 */
public interface GreetingMapper {
    @Select("SELECT id, content FROM Greeting WHERE id = #{greetingId}")
    Greeting getGreeting(@Param("greetingId") int greetingId);

    @Select("SELECT id, content FROM Greeting")
    List<Greeting> getAllGreetings();

    @Insert("INSERT INTO Greeting (content) VALUES (#{content})")
    int insertGreeting(Greeting greeting);

    @Update("UPDATE Greeting SET\n" +
            "content = #{content}\n" +
            "WHERE ID = #{id}")
    int updateGreeting(Greeting greeting);
}
