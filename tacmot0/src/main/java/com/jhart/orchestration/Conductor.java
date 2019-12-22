package com.jhart.orchestration;

import java.util.List;


import com.jhart.command.UserBackBean;
import com.jhart.dto.MyResponse;

public interface Conductor {
	
	MyResponse<List<UserBackBean>> updateUser(UserBackBean userBackBean);

}
