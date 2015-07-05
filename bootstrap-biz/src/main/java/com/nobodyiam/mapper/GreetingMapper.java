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
    Greeting getGreeting(@Param("greetingId") String greetingId);

    @Select("SELECT id, content FROM Greeting")
    public List<Greeting> getAllUsers();

    @Insert("INSERT INTO Greeting (content) VALUES (#{content})")
    public int insertGreeting(Greeting greeting);

    @Update("UPDATE Greeting SET\n" +
            "content = #{content}\n" +
            "WHERE ID = #{id}")
    public int updateGreeting(Greeting greeting);
}
