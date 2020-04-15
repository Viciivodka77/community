package life.majiang.community.controller;

import life.majiang.community.dto.CommentDTO;
import life.majiang.community.dto.QuestionDTO;
import life.majiang.community.enums.CommentTypeEnum;
import life.majiang.community.service.CommentService;
import life.majiang.community.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@Controller
public class QuestionController {

    @Autowired
    private QuestionService questionService;

    @Autowired
    private CommentService commentService;

    @GetMapping("/question/{id}")
    public String question(@PathVariable(name = "id")Long id,
                           Model model){
        QuestionDTO questionDTO = questionService.getQuestionById(id);
        List<QuestionDTO> relatedQuestions = questionService.selectRelated(questionDTO);
        List<CommentDTO> commentDTOList = commentService.listByTargetId(id , CommentTypeEnum.QUESTION);
        model.addAttribute("commentDTOList",commentDTOList);
        //增加阅读数
        questionService.incView(id);
        model.addAttribute("questionDTO",questionDTO);
        model.addAttribute("relatedQuestions",relatedQuestions);
        return "question";
    }
}
