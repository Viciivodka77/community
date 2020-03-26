package life.majiang.community.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Data
public class PaginationDTO {
    private List<QuestionDTO> questions;
    private boolean showPrevious;
    private boolean showFirstPage;
    private boolean showNext;
    private boolean showEndPage;
    private Integer currentPage;
    private Integer totalPage;
    private List<Integer> pageList = new ArrayList<>();

    public void setPagination(Integer count, Integer totalPage, Integer page, Integer size) {
        this.currentPage = page;
        pageList.add(currentPage);
        this.totalPage = totalPage;
        for ( int i = 1 ; i <= 3 ; i++){
            if (currentPage - i > 0 ){
                pageList.add( 0, currentPage-i);
            }
            if (page + i <= totalPage){
                pageList.add(currentPage + i);
            }

        }
        //是否展示上一页 第一页
        if(currentPage == 1){
            showPrevious = false;
        }else {
            showPrevious = true;
        }
        //是否展示下一页 最后一页
        if(currentPage == totalPage ){
            showNext = false;
        }else {
            showNext = true;
        }
        //是否展示转跳第一页 不包含第一页就展示
        if (pageList.contains(1)){
            showFirstPage = false;
        }else {
            showFirstPage = true;
        }
        //是否展示转跳最后一页 不包含最后一页就展示
        if (pageList.contains(totalPage)){
            showEndPage = false;
        }else {
            showEndPage = true;
        }
    }
}
