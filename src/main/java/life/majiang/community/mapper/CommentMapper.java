package life.majiang.community.mapper;

import life.majiang.community.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface CommentMapper {

    void insert(Comment comment);
}
