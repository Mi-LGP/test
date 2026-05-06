package com.cl.entity.view;

import com.cl.entity.TuangouActivityEntity;
import com.baomidou.mybatisplus.annotations.TableName;
import org.apache.ibatis.annotations.Param;
import java.util.Date;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonFormat;

/**
 * 拼团活动
 * 后端接口视图类
 * @author 
 * @email 
 * @date 2025-04-06 15:12:26
 */
@TableName("tuangou_activity")
public class TuangouActivityView extends TuangouActivityEntity {
	
	public TuangouActivityView() {
	}
	
}
