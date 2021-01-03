package com.cn.commo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author CommoResult
 * @time 2020/12/25-11:28
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommoResult {

    private String message;
    private String status;
    private Object data;

    public CommoResult success(String message,String status,Object data){
        CommoResult commoResult = new CommoResult();
        commoResult.setMessage(message);
        commoResult.setStatus(status);
        commoResult.setData(data);
        return commoResult;
    }


    public CommoResult success(String status,Object data){
        CommoResult commoResult = new CommoResult();
        commoResult.setMessage("查询成功");
        commoResult.setStatus(status);
        commoResult.setData(data);
        return commoResult;
    }

    public CommoResult success(Object data){
        CommoResult commoResult = new CommoResult();
        commoResult.setMessage("查询成功");
        commoResult.setStatus("100");
        commoResult.setData(data);
        return commoResult;
    }


    public CommoResult filed(String message,String status,Object data){
        CommoResult commoResult = new CommoResult();
        commoResult.setMessage(message);
        commoResult.setStatus(status);
        commoResult.setData(data);
        return commoResult;
    }

    public CommoResult filed(String status,Object data){
        CommoResult commoResult = new CommoResult();
        commoResult.setMessage("查询失败");
        commoResult.setStatus(status);
        commoResult.setData(data);
        return commoResult;
    }

    public CommoResult filed(Object data){
        CommoResult commoResult = new CommoResult();
        commoResult.setMessage("查询失败");
        commoResult.setStatus("104");
        commoResult.setData(data);
        return commoResult;
    }

    public CommoResult filed(){
        CommoResult commoResult = new CommoResult();
        commoResult.setMessage("查询失败");
        commoResult.setStatus("104");
        commoResult.setData(null);
        return commoResult;
    }


}
