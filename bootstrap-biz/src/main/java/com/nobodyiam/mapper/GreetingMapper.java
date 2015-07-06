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
    Greeting getGreeting(@Param("greetingId") long greetingId);

    @Select("SELECT id, content FROM Greeting limit #{limit} offset #{offset}")
    List<Greeting> getGreetings(@Param("limit") int limit, @Param("offset") int offset);

    @Insert("INSERT INTO Greeting (content) VALUES (#{content})")
    long insertGreeting(Greeting greeting);

    @Update("UPDATE Greeting SET content = #{content} WHERE ID = #{id}")
    long updateGreeting(Greeting greeting);
}
