package cn.itsource.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PageCommon<T> implements Serializable {

    private Integer page;

    private T data;

}
