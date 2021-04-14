package com.whale.boot.web.bean.vo;

import com.whale.boot.web.bean.Article;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TimeLineVo implements Serializable {

    private static final long serialVersionUID = 1L;

    private String year;

    private List<Article> article;
}
