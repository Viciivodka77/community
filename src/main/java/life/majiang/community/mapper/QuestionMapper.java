package life.majiang.community.mapper;

import life.majiang.community.model.Question;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionMapper {

    //添加一个问题
    void create(Question question);

    //查询所有问题
    List<Question> selectAllQuestion(Integer offset, Integer size);

    //查询所有问题个数
    Integer count();

    //查询我的所有问题
//    @Select("select * from community.question where creator = #{userId} limit #{offset},#{size}")
//    List<Question> selectMyQuestion(@Param(value = "userId")Integer userId, @Param(value = "offset") Integer offset, @Param(value = "size") Integer size);
    List<Question> selectMyQuestion(Integer userId, Integer offset, Integer size);

    //查询我的问题个数
    Integer countByUserId(Integer userId);

    Question getQuestionById(Integer id);
}
