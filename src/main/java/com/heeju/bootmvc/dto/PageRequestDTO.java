package com.heeju.bootmvc.dto;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PageRequestDTO {
    // PageRequestDTO specifies search type and keyword in addition to paging-related
    // information (page/size).
    @Builder.Default
    private int page = 1;
    // When creating an instance through the builder pattern,
    // if you want to initialize a specific field to a specific value,
    // you can use @Builder.Default.
    @Builder.Default
    private int size = 10;
    private String type;    // Types of search t, c, w, tc, tw, twc
    private String keyword;

    // getTypes(): Since the current search conditions are processed as String[]
    // in BoardRepository, a function that returns a string called type as an array is needed.
    public String[] getTypes(){
        if(type==null||type.isEmpty()){
            return null;
        }
        return type.split("");
    }

    // getPageable(String...props): A function that returns the Pageable type used for
    // paging processing.
    public Pageable getPageable(String...props){
        return PageRequest.of(this.page -1, this.size, Sort.by(props).descending());
        // PageRequest.of(int page, int size, Sort sort)
        // Page number, number of data per page, sorting direction
    }

    private String link;

    // getLink(): Configure search conditions and paging conditions as strings.
    public String getLink(){
        if(link==null){
            StringBuilder builder = new StringBuilder();

            builder.append("page="+this.page);
            builder.append("&size="+this.size);

            if(type!=null && type.length()>0){
                builder.append("&type="+type);
            }

            if(keyword!=null){
                try {
                    builder.append("&keyword="+ URLEncoder.encode(keyword,"UTF-8"));
                } catch (UnsupportedEncodingException e){
                }
            }
            link = builder.toString();
        }
        return link;
    }

}
