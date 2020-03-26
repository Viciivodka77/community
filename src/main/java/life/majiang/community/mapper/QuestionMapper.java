package life.majiang.community.mapper;

import life.majiang.community.model.Question;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface QuestionMapper {

    //添加一个问题
    void create(Question question);

    //查询所有问题
    List<Question> selectAllQuestion(Integer offset, Integer size);

    //查询数据条目
    Integer count();
}
