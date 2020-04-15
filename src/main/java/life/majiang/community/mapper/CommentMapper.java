package life.majiang.community.mapper;

import life.majiang.community.model.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface CommentMapper {

    void insert(Comment comment);

    Comment selectById(Long id);

    List<Comment> selectAllCommentById(Long id);


}
