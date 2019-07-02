package com.wyu.takeleave.teacher;

import com.wyu.takeleave.ValueCallBack;
import com.wyu.takeleave.student.AStudentData;
import com.wyu.takeleave.util.FormBrief;
import com.wyu.takeleave.util.MyOkHttpUtils;
import com.wyu.takeleave.util.TakeLeaveForm;
import com.wyu.takeleave.util.UserInfo;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import java.util.ArrayList;

import okhttp3.Call;

public class TeacherModel implements ITeacher.Model{

    private final String GETUSER = "http://appcat.site/api/getUser";
    private final String GETFORM = "http://appcat.site/api/teacher/teacherGetTakeLeave";
    private UserInfo localUserInfo = new UserInfo();
    private ATeacherLeaveForm localForm;

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
                                ATeacherData data = (ATeacherData) MyOkHttpUtils.parseJSONWithGSON(response, ATeacherData.class);
                                localUserInfo.setName(data.getContent().getName());
                                localUserInfo.setUserType(userInfo.getUserType());
                                localUserInfo.setId(data.getContent().getTeacherId());
                                localUserInfo.setClassId("170815");  //TODO:这里等接口
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
        //TODO:在拿到整个列表后，存下来，然后通过formId去本地检索并返回这个form
        TakeLeaveForm data = new TakeLeaveForm();
        for(int i = 0; i < localForm.getContent().size(); i++){
            ATeacherLeaveForm form = localForm;
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
                                ATeacherLeaveForm info = (ATeacherLeaveForm) MyOkHttpUtils.parseJSONWithGSON(response, ATeacherLeaveForm.class);
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

    @Override
    public UserInfo getUserInfo() {
        return localUserInfo;
    }
}
