package com.wyu.takeleave.student;

import com.wyu.takeleave.ValueCallBack;
import com.wyu.takeleave.util.FormBrief;
import com.wyu.takeleave.util.MyOkHttpUtils;
import com.wyu.takeleave.util.UserInfo;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import okhttp3.Call;

interface OnGettingDataListener{
    void gettingSuccess(UserInfo userInfo);
    void gettingFailed();
}

//JSON解析类，这个用于解析GetUser接口


public class StudentModel implements IStudent.Model{

    private final String GETUSER = "http://appcat.site/api/getUser";
    private final String GETFORM = "http://appcat.site/api/student/getTakeLeaveForm";
    private UserInfo localUserInfo = new UserInfo();

    @Override
    public UserInfo getUserInfo() {
        return localUserInfo;
    }

    @Override
    public void getUser(UserInfo userInfo, ValueCallBack<UserInfo> gettingDataListener) {
        try{
            OkHttpUtils
                    .post()
                    .url(GETUSER)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            e.printStackTrace();
                            gettingDataListener.onFail(e.toString());
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            if(!response.equals("")){
                                AUserData data = (AUserData) MyOkHttpUtils.parseJSONWithGSON(response, AUserData.class);
                                //userInfo.setName(data.getContent().getUsername());
                                localUserInfo.setName(data.getContent().getUsername());
                                localUserInfo.setUserType(userInfo.getUserType());
                                localUserInfo.setId(data.getContent().getUserId());
                                localUserInfo.setClassId(data.getContent().getClassId());
                                localUserInfo.setCollege(data.getContent().getCollege());
                                localUserInfo.setMajor(data.getContent().getMajor());
                                gettingDataListener.onSuccess(localUserInfo);
                            }
                        }
                    });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void getTakeLeaveForm(ValueCallBack<ArrayList<FormBrief>> gettingTakeLeaveListener) {
        try{
            OkHttpUtils
                    .post()
                    .url(GETFORM)
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            e.printStackTrace();
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            if(!response.equals("")){
                                StudentLeaveInfo info = (StudentLeaveInfo) MyOkHttpUtils.parseJSONWithGSON(response, StudentLeaveInfo.class);
                                ArrayList<FormBrief> formBriefs = new ArrayList<FormBrief>();
                                for(int i = 0; i < info.getContent().size(); i++){
                                    FormBrief data = new FormBrief();
                                    //data.setAuditor();
                                    data.setDuration(info.getContent().get(i).getTakeDays());
                                    //data.setReply();
                                    //data.setStatus();
                                    SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                                    try {
                                        Date date = simpleDateFormat.parse(info.getContent().get(i).getApplyTime());
                                        data.setTime(date);
                                    }catch (Exception e){
                                        e.printStackTrace();
                                    }
                                    formBriefs.add(data);
                                }
                                gettingTakeLeaveListener.onSuccess(formBriefs);
                            }
                        }
                    });
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
