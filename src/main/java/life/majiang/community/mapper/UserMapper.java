package life.majiang.community.mapper;

import life.majiang.community.model.User;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface UserMapper {

    //添加用户
    void insert(User user);

    User findByToken(String token);

    //根据id查询用户
    User findById(Integer id);
}
