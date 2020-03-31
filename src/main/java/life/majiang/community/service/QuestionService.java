package life.majiang.community.service;

import life.majiang.community.dto.PaginationDTO;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.exception.CustomizeErrorCode;
import life.majiang.community.exception.CustomizeException;
import life.majiang.community.mapper.QuestionMapper;
import life.majiang.community.mapper.UserMapper;
import life.majiang.community.model.Question;
import life.majiang.community.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QuestionService {

    @Autowired
    private QuestionMapper questionMapper;

    @Autowired
    private UserMapper userMapper;

    public PaginationDTO selectAllQuestion(Integer page, Integer size) {
        //查询所有值数目
        Integer totalCount = questionMapper.count();
        Integer totalPage;
        //计算分页总数
        if(totalCount % size == 0){
            totalPage = totalCount / size;
        }else {
            totalPage = totalCount / size + 1;
        }
        //处理意外因素
        if(page < 1){
            page = 1;
        }
        if(page > totalPage){
            page = totalPage;
        }
        //size * (page - 1) 计算数据库查询数值
        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.selectAllQuestion(offset,size);

        List<QuestionDTO> questionDTOList = new ArrayList<>();
        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question : questions) {
            User userById = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            //把Question封装到QuestionDTO
            questionDTO.setUser(userById);
            questionDTOList.add(questionDTO);
        }
        //把QuestionDTO封装到paginationDTO
        paginationDTO.setQuestions(questionDTOList);
        paginationDTO.setPagination(totalCount,totalPage,page,size);
        return paginationDTO;
    }

    public PaginationDTO selectMyQuestion(Long userId, Integer page, Integer size) {
        //查询所有值数目
        Integer totalCount = questionMapper.countByUserId(userId);
        Integer totalPage;
        //计算分页总数
        if(totalCount == 0){
            totalPage = 1;
            page = 1;
        } else if(totalCount % size == 0){
            totalPage = totalCount / size;
        }else {
            totalPage = totalCount / size + 1;
        }
        //处理意外因素
        if(page < 1){
            page = 1;
        }
        if(page > totalPage){
            page = totalPage;
        }
        //size * (page - 1) 计算数据库查询数值
        Integer offset = size * (page - 1);
        List<Question> questions = questionMapper.selectMyQuestion(userId,offset,size);

        List<QuestionDTO> questionDTOList = new ArrayList<>();
        PaginationDTO paginationDTO = new PaginationDTO();
        for (Question question : questions) {
            User userById = userMapper.findById(question.getCreator());
            QuestionDTO questionDTO = new QuestionDTO();
            BeanUtils.copyProperties(question,questionDTO);
            //把Question封装到QuestionDTO
            questionDTO.setUser(userById);
            questionDTOList.add(questionDTO);
        }
        //把QuestionDTO封装到paginationDTO
        paginationDTO.setQuestions(questionDTOList);
        paginationDTO.setPagination(totalCount,totalPage,page,size);
        return paginationDTO;
    }

    public QuestionDTO getQuestionById(Long id) {
        Question question = questionMapper.getQuestionById(id);
        if(question == null){
            throw new CustomizeException(CustomizeErrorCode.QUESTION_NOT_FOUND);
        }
        User creator = userMapper.findById(question.getCreator());
        QuestionDTO questionDTO = new QuestionDTO();
        BeanUtils.copyProperties(question,questionDTO);
        questionDTO.setUser(creator);
        return questionDTO;
    }

    public void createOrUpdate(Question question) {
        if(question.getId() == null){
            //创建
            question.setGmtCreate(System.currentTimeMillis());
            question.setGmtModified(question.getGmtCreate());
            question.setViewCount(0);
            question.setLikeCount(0);
            question.setCommentCount(0);
            questionMapper.create(question);
        }else {
            //更新
            question.setGmtModified(System.currentTimeMillis());
            questionMapper.update(question);
        }

    }

    public void incView(Long id) {
        questionMapper.updateView(id);
    }
}
