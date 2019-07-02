package com.wyu.takeleave.form;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.text.InputType;
import android.text.format.DateFormat;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import com.wyu.takeleave.BaseActivity;
import com.wyu.takeleave.IntentActivity;
import com.wyu.takeleave.R;
import com.wyu.takeleave.ValueCallBack;
import com.wyu.takeleave.login.Login;
import com.wyu.takeleave.student.Student;
import com.wyu.takeleave.teacher.Teacher;
import com.wyu.takeleave.util.TakeLeaveForm;
import com.wyu.takeleave.util.UserInfo;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;

public class Form extends BaseActivity<FormPresenter> implements IForm.View{
    private EditText name;
    private EditText id;
    private EditText college;
    private EditText major;
    private EditText classId;
    private EditText tel;
    private EditText guaarder;
    private EditText guaarderTel;

    private Button button;
    private EditText beginTime;
    private EditText deadTime;
    private EditText days;
    private EditText reason;
    private TakeLeaveForm takeLeaveForm;

    private UserInfo userInfo;
    @Override
    protected FormPresenter initPresent() {
        return new FormPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.takeleave;
    }

    @Override
    protected void initView()  {
        takeLeaveForm = new TakeLeaveForm();
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        name = (EditText)findViewById(R.id.name);
        name.setEnabled(false);
        id = (EditText)findViewById(R.id.Id);
        id.setEnabled(false);
        college = (EditText)findViewById(R.id.college);
        college.setEnabled(false);
        major = (EditText)findViewById(R.id.major);
        major.setEnabled(false);
        classId = (EditText)findViewById(R.id.classId);
        classId.setEnabled(false);

        tel = (EditText)findViewById(R.id.tel);
        guaarder = (EditText)findViewById(R.id.guaarder);
        guaarderTel = (EditText)findViewById(R.id.guaarderTel);

        button =(Button)findViewById(R.id.button);
        beginTime = (EditText)findViewById(R.id.beginTime);
        deadTime = (EditText)findViewById(R.id.deadTime);
        days = (EditText)findViewById(R.id.days);
        days.setEnabled(false);
        reason = (EditText)findViewById(R.id.reson);
        button.setText("提交申请");
        //获取用户信息，实现单活动多用
        userInfo = ((UserInfo) getIntent().getSerializableExtra("userInfo"));
        int flag = 1;
        getIntent().getIntExtra("isPut",flag);

        if (flag==0){
            tel.setEnabled(false);
            guaarder.setEnabled(false);
            guaarderTel.setEnabled(false);
            beginTime.setEnabled(false);
            deadTime.setEnabled(false);
            reason.setEnabled(false);

            takeLeaveForm =((TakeLeaveForm) getIntent().getSerializableExtra("takeLeaveForm"));
            if (takeLeaveForm==null){
            }else {
                name.setText(takeLeaveForm.getUsername());
                id.setText(takeLeaveForm.getUser_id());
                college.setText(takeLeaveForm.getCollege());
                major.setText(takeLeaveForm.getMajor());
                classId.setText(takeLeaveForm.getClass_id());
                tel.setText(takeLeaveForm.getStudent_tel());
                guaarder.setText(takeLeaveForm.getGuardian_name());
                guaarderTel.setText(takeLeaveForm.getGuardian_tel());
                beginTime.setText(takeLeaveForm.getBegin_days());
                deadTime.setText(takeLeaveForm.getDead_days());
                days.setText(takeLeaveForm.getTake_days());
                reason.setText(takeLeaveForm.getReason());
            }


            if ("老师".equals(userInfo.getUserType())||"辅导员".equals(userInfo.getUserType())){
                button.setText("同意");
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.auditingForm(takeLeaveForm);
                    }
                });
            }
            if ("学生".equals(userInfo.getUserType())||"班级管理员".equals(userInfo.getUserType())){
                button.setText("取消申请");
                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        presenter.cancelApply(takeLeaveForm);
                    }
                });
            }
        }

        if (flag==1){
            if ("学生".equals(userInfo.getUserType())||"班级管理员".equals(userInfo.getUserType())){
                name.setText(userInfo.getName());
                id.setText(userInfo.getId());
                college.setText(userInfo.getCollege());
                major.setText(userInfo.getMajor());
                classId.setText(userInfo.getClassId());

                beginTime.setInputType(InputType.TYPE_NULL);
                beginTime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar c = Calendar.getInstance();
                        DatePickerDialog dialog = new DatePickerDialog(Form.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                c.set(year, monthOfYear, dayOfMonth);
                                beginTime.setText(DateFormat.format("yyy-MM-dd", c));
                            }
                        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                        dialog.show();
                    }
                });


                deadTime.setInputType(InputType.TYPE_NULL);
                deadTime.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Calendar c = Calendar.getInstance();
                        DatePickerDialog dialog = new DatePickerDialog(Form.this, new DatePickerDialog.OnDateSetListener() {
                            @Override
                            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                                c.set(year, monthOfYear, dayOfMonth);
                                deadTime.setText(DateFormat.format("yyy-MM-dd", c));

                                try {
                                    Date firstdate = format.parse(beginTime.getText().toString());
                                    Date seconddate = format.parse(deadTime.getText().toString());
                                    Calendar calendar = Calendar.getInstance();
                                    calendar.setTime(firstdate);
                                    int cnt = 0;
                                    while(calendar.getTime().compareTo(seconddate)!=0){
                                        calendar.add(Calendar.DATE, 1);
                                        cnt++;
                                    }
                                    days.setText(String.valueOf(cnt));
                                    takeLeaveForm.setBegin_days(beginTime.getText().toString());
                                    takeLeaveForm.setDead_days(deadTime.getText().toString());
                                    takeLeaveForm.setTake_days(String.valueOf(cnt));
                                }catch (ParseException e){
                                    e.printStackTrace();
                                }catch (NullPointerException e){
                                    Toast.makeText(Form.this,"请先选择开始时间",Toast.LENGTH_SHORT).show();
                                    e.printStackTrace();
                                }
                            }
                        }, c.get(Calendar.YEAR), c.get(Calendar.MONTH), c.get(Calendar.DAY_OF_MONTH));
                        dialog.show();
                    }
                });

                button.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ArrayList<String> check = new ArrayList<>();
                        takeLeaveForm.setUsername(userInfo.getName());
                        takeLeaveForm.setUser_id(userInfo.getId());
                        takeLeaveForm.setCollege(userInfo.getCollege());
                        takeLeaveForm.setClass_id(userInfo.getClassId());
                        takeLeaveForm.setMajor(userInfo.getMajor());
                        takeLeaveForm.setApplyTime(new Date());
                        takeLeaveForm.setStudent_tel(tel.getText().toString());
                        takeLeaveForm.setGuardian_name(guaarder.getText().toString());
                        takeLeaveForm.setGuardian_tel(guaarderTel.getText().toString());

                        check.add(tel.getText().toString().trim());
                        check.add(guaarder.getText().toString().trim());
                        check.add(guaarderTel.getText().toString().trim());
                        check.add(deadTime.getText().toString().trim());
                        check.add(beginTime.getText().toString().trim());
                        check.add(reason.getText().toString().trim());

                        Boolean isFull = false;
                        for (String str:check){
                            if (!str.equals("")){
                                isFull = true;
                            }else {
                                isFull = false;
                                Toast.makeText(Form.this,"请填完整个表单",Toast.LENGTH_SHORT).show();
                                break;
                            }
                        }
                        if (isFull){
                            presenter.putTakeLeaveForm(takeLeaveForm);
                        }

                    }
                });
            }
        }





    }

    @Override
    protected void onPrepare() {

    }


    @Override
    public void handleApplySuccess() {
        if ("学生".equals(userInfo.getUserType())||"班级管理员".equals(userInfo.getUserType())){
            Intent intent = new Intent(Form.this,Student.class);
            intent.putExtra("userInfo",userInfo);
            startActivity(intent);
            //销毁本activity，并回收内存
            IntentActivity.finishActivity(Form.this);
        }

        if ("老师".equals(userInfo.getUserType())||"辅导员".equals(userInfo.getUserType())){
            Intent intent = new Intent(Form.this,Teacher.class);
            intent.putExtra("userInfo",userInfo);
            startActivity(intent);
            //销毁本activity，并回收内存
            IntentActivity.finishActivity(Form.this);
        }
    }

    @Override
    public void handleApplyFail(String msg) {
        Toast.makeText(Form.this,msg,Toast.LENGTH_SHORT).show();
    }
}
