package com.wyu.takeleave.form;

import com.wyu.takeleave.ValueCallBack;
import com.wyu.takeleave.util.MyOkHttpUtils;
import com.wyu.takeleave.util.TakeLeaveForm;
import com.zhy.http.okhttp.OkHttpUtils;
import com.zhy.http.okhttp.callback.StringCallback;

import org.json.JSONObject;

import okhttp3.Call;
import okhttp3.MediaType;

public class FormModel implements IForm.Model{

    private final String PUTLEAVE = "http://appcat.site/api/student/putTakeLeave";
    private final String AUDITINGLEAVE = "http://appcat.site/api/teacher/auditingSingleTakeLeaves";
    private final String CANCLELEAVE = "http://appcat.site/api/student/cancelTakeLeaveApply";

    @Override
    public void putTakeLeaveForm(TakeLeaveForm takeLeaveForm, ValueCallBack<String> putTakeLeaveListener) {
        try {
            JSONObject json = new JSONObject();
            json.put("begin_days" ,takeLeaveForm.getBegin_days());
            json.put("class_id", takeLeaveForm.getClass_id());
            json.put("college", takeLeaveForm.getCollege());
            json.put("dead_days", takeLeaveForm.getDead_days());
            json.put("guardian_name", takeLeaveForm.getGuardian_name());
            json.put("guardian_tel", takeLeaveForm.getGuardian_tel());
            json.put("major", takeLeaveForm.getMajor());
            json.put("reason", takeLeaveForm.getReason());
            json.put("student_tel", takeLeaveForm.getStudent_tel());
            json.put("take_days", takeLeaveForm.getTake_days());
            json.put("user_id", takeLeaveForm.getUser_id());
            json.put("user_name", takeLeaveForm.getUsername());
            OkHttpUtils
                    .postString()
                    .url(PUTLEAVE)
                    .content(json.toString())
                    .mediaType(MediaType.parse("application/json; charset=utf-8"))
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            e.printStackTrace();
                            putTakeLeaveListener.onFail(e.toString());
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            APutLeave info = (APutLeave) MyOkHttpUtils.parseJSONWithGSON(response, APutLeave.class);
                            putTakeLeaveListener.onSuccess(info.getContent());
                        }
                    });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void auditingForm(TakeLeaveForm takeLeaveForm, ValueCallBack<String> auditingListener) {
        try{
            OkHttpUtils
                    .post()
                    .url(AUDITINGLEAVE)
                    .addParams("formIds", takeLeaveForm.getForm_id().toString())
                    .addParams("isPermit", takeLeaveForm.getInstructor_permit().toString())
                    .addParams("reply", takeLeaveForm.getReply())
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            e.printStackTrace();
                            auditingListener.onFail(e.toString());
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            AAuditing info = (AAuditing)MyOkHttpUtils.parseJSONWithGSON(response, AAuditing.class);
                            auditingListener.onSuccess(info.getContent());
                        }
                    });
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void cancelApply(TakeLeaveForm takeLeaveForm, ValueCallBack<String> cancelApplyListener) {
        try{
            OkHttpUtils
                    .post()
                    .url(CANCLELEAVE)
                    .addParams("fromId", takeLeaveForm.getForm_id().toString())  //TODO:后端设了fromId这个键值
                    .build()
                    .execute(new StringCallback() {
                        @Override
                        public void onError(Call call, Exception e, int id) {
                            e.printStackTrace();
                            cancelApplyListener.onFail(e.toString());
                        }

                        @Override
                        public void onResponse(String response, int id) {
                            ACancel info = (ACancel)MyOkHttpUtils.parseJSONWithGSON(response, ACancel.class);
                            cancelApplyListener.onSuccess(info.getContent());
                        }
                    });

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
