package com.wyu.takeleave.student;

import com.wyu.takeleave.ValueCallBack;
import com.wyu.takeleave.util.FormBrief;
import com.wyu.takeleave.util.MyOkHttpUtils;
import com.wyu.takeleave.util.TakeLeaveForm;
import com.wyu.takeleave.util.UserInfo;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import okhttp3.Call;




public class StudentModel implements IStudent.Model{

    private final String GETUSER = "http://appcat.site/api/getUser";
    private final String GETFORM = "http://appcat.site/api/student/getTakeLeaveForm";
    private UserInfo localUserInfo = new UserInfo();
    private AStudentLeaveInfo localForm;

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
                                AStudentData data = (AStudentData) MyOkHttpUtils.parseJSONWithGSON(response, AStudentData.class);
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
    public TakeLeaveForm getTakeLeaveForm(int formId) {
        TakeLeaveForm data = new TakeLeaveForm();
        for(int i = 0; i < localForm.getContent().size(); i++){
            AStudentLeaveInfo form = localForm;
            if(form.getContent().get(i).getFormId() == formId){
                data.setGuardian_tel(form.getContent().get(i).getGuardianTel());
                data.setGuardian_name(form.getContent().get(i).getUsername());
                data.setStudent_tel(form.getContent().get(i).getStudent_tel());
                data.setMajor(form.getContent().get(i).getMajor());
                data.setClass_id(form.getContent().get(i).getClassId());
                data.setCollege(form.getContent().get(i).getCollege());
                data.setUser_id(form.getContent().get(i).getUserId());
                data.setUsername(form.getContent().get(i).getUsername());
                data.setTake_days(form.getContent().get(i).getTakeDays());
                data.setDead_days(form.getContent().get(i).getDeadDays());
                data.setBegin_days(form.getContent().get(i).getBeginDays());
                data.setReason(form.getContent().get(i).getReason());
                data.setForm_id(form.getContent().get(i).getFormId());
                return data;
            }
        }
        return null;
    }

    @Override
    public void getTakeLeaveForms(ValueCallBack<ArrayList<FormBrief>> gettingTakeLeaveListener) {
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
                                AStudentLeaveInfo info = (AStudentLeaveInfo) MyOkHttpUtils.parseJSONWithGSON(response, AStudentLeaveInfo.class);
                                localForm = info;
                                ArrayList<FormBrief> formBriefs = new ArrayList<FormBrief>();
                                for(int i = 0; i < info.getContent().size(); i++){
                                    FormBrief data = new FormBrief();
                                    data.setAuditor(info.getContent().get(i).getAuditor());
                                    data.setDuration(info.getContent().get(i).getTakeDays());
                                    data.setReply(info.getContent().get(i).getReply());
                                    data.setFormID(info.getContent().get(i).getFormId());
                                    if(info.getContent().get(i).getInstructor_permit() != null){
                                        data.setStatus(Integer.valueOf(info.getContent().get(i).getInstructor_permit()));  //已有审核状态
                                    }else{
                                        data.setStatus(999);  //未审核状态
                                    }
                                    data.setTime(info.getContent().get(i).getApplyTime());
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
