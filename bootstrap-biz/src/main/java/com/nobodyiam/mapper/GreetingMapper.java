package com.nobodyiam.mapper;

import com.nobodyiam.dto.Greeting;
import org.apache.ibatis.annotations.*;

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
    @SelectKey(before = false, resultType = long.class, keyProperty = "id", statement = "call identity()")
    int insertGreeting(Greeting greeting);

    @Update("UPDATE Greeting SET content = #{content} WHERE ID = #{id}")
    int updateGreeting(Greeting greeting);
}
