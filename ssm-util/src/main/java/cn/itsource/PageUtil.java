package cn.itsource;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class PageUtil {

    public PageUtil(int curr,int size,int total){

        this.pageSize = size;

        this.totalPage = total % size == 0 ? total/size : (total/size) + 1;

        this.currPage = curr < 1 ? 1 : curr;

        this.currPage = curr > this.totalPage ? this.totalPage : this.currPage;

        skips = (this.currPage - 1) * this.pageSize;

        this.totalCount = total;

    }

    private int currPage = 1;   //当前页

    private int pageSize = 10;  //每页显示条数

    private int totalPage = 0;  //总页数

    private int totalCount = 0; //总条数

    private int skips = 0;  //跳过的值，即sql中的start值

    private List rows = new ArrayList();    //用来存放查询到的数据集合

}