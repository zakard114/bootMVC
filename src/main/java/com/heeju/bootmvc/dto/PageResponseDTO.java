package com.heeju.bootmvc.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.ToString;

import java.util.List;

//PageResponseDTO is responsible for processing the list of DTOs and
// start/end pages on the screen.
@Getter
@ToString
public class PageResponseDTO<E> {
    private int page;
    private int size;  // Number of items displayed per page
    private int total; // Total number of items
    
    // start page number
    private int start;
    // end page number
    private int end;

    // whether the previous page exists
    private boolean prev;
    // whether the next page exists
    private boolean next;
    
    private List<E> dtoList;

    // Among the properties of Builder, ‘builderMethodName’ is used as Builder() as the
    // basic method name, and is a property provided so that it can be named with
    // a different name.
    // The use of this property is that in the case of an inheritance relationship,
    // a conflict may occur because the name of the parent Builder and the child Builder
    // are the same, so it is thought to be good to use it when you want to specify names
    // for each.
    @Builder(builderMethodName = "withAll")
    public PageResponseDTO(PageRequestDTO pageRequestDTO, List<E> dtoList, int total) {
        if (total <= 0) {
            return;
        }

        this.page = pageRequestDTO.getPage();
        this.size = pageRequestDTO.getSize();

        this.total = total;
        this.dtoList = dtoList;

        this.end = (int) (Math.ceil(this.page / 10.0)) * 10; // Last number on screen
        // To make sure of the last page, which is a kind of practical skill, we decided to
        // discard the decimal point through ceil. The final number inferred is +1 of the
        // leading digit of the current number (page), and the following digits are filled
        // with 0. For example, when it is 21, it becomes 2.1 through (this.page/10.0),
        // and in the ceil process, there is a digit greater than 1 after
        // the decimal point, so it is rounded up and becomes +1.0. That is, it is 3.0.
        // Afterwards, add *10 to make the final page 30. If the current page is 32,
        // the final page is 40. Even if it is 35, it goes through 35/10 and when it is 3.5,
        // it is still rounded to 40.
        // ex) if page=15, the end numbers shown at the bottom of page is 20.
        // > 11...15..."20"
        // https://luanaeun.tistory.com/208

        this.start = this.end - 9; // First number on screen
        // ex) if end is 10 by ceiling the current page=15, the first number shown at
        // the bottom of page is 11. > "11"...15...20
        int last = (int) (Math.ceil((total / (double) size))); // The actual last page number
        // by calculating (the number of total item) / (number of displayed item).
        // Thus, how much item size is allowed per page. When rounding off, decimal points are
        // discarded because the nom & the denom as two integer types are calculated.
        // https://luanaeun.tistory.com/208

        // Summary: Rounding up - If there is more than 1 number after the decimal point,
        // it is raised. When dividing between two integers(ex. int/int),
        // the decimal point disappears.

        this.end = end > last ? last : end;
        // 1. For example, when (item qt.)total=101 and (bottom idx)size=10,
        // int last = (int)(Math.ceil((total/(double) According to size)));,
        // the bottom highest idx# is 11. However, if (current page) this.page is 11,
        // end is this.end = (int)(Math.ceil(this.page/10.0))*10; 20, which results in
        // 9 unnecessary bottom idxs that far exceed the capacity of "item qt."
        // In other words, this is the state of end > last, and in this case, it is reasonable
        // to adopt last that does not accommodate the 9 wasted idx.
        // 2. If this.page=17 then end=20. If somehow item qt. fitted last=21,
        // thus end(20)<last(21). However, the highest idx limit shown on the screen at
        // the bottom is 10 units, so if it exceeds 20, it should not be displayed as
        // 11...17..."21". Thus, it is reasonable for the last bottom idx to follow end=20.
        // 3. If end==last, since both are the same without change, refer to the
        // default variable, end.
        this.prev = this.start > 1;
        // this.prev valid once this.start > 1
        this.next = total > this.end * this.size;
        // total = this.end = max, this.size = max
    }
    
}
