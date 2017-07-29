package com.louisgeek.classichu.login;


import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;

import com.classichu.classichu.app.ClassicApplication;
import com.classichu.classichu.basic.listener.OnViewClickEnabledListener;
import com.classichu.classichu.basic.tool.SharedPreferencesTool;
import com.classichu.classichu.basic.tool.ToastTool;
import com.classichu.classichu.basic.widget.ClassicInputLayout;
import com.classichu.classichu.classic.ClassicMvpFragment;
import com.classichu.dialogview.manager.DialogManager;
import com.classichu.dialogview.ui.ClassicDialogFragment;
import com.google.gson.Gson;
import com.louisgeek.classichu.R;
import com.louisgeek.classichu.all.AppInfoDataManager;
import com.louisgeek.classichu.login.adapter.AgencyBaseAdapter;
import com.louisgeek.classichu.login.bean.AgencyBean;
import com.louisgeek.classichu.login.bean.AgencyBeanWrapper;
import com.louisgeek.classichu.login.bean.UserLoginBean;
import com.louisgeek.classichu.login.contract.LoginContract;
import com.louisgeek.classichu.login.presenter.LoginPresenterImpl;
import com.louisgeek.classichu.main.MainActivity;
import com.louisgeek.classichu.setting.SettingActivity;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Vector;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link UserLoginFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class UserLoginFragment extends ClassicMvpFragment<LoginPresenterImpl>
        implements LoginContract.View {

    private String SP_LAST_USER="SP_LAST_USER";
    private String SP_LAST_ADMIN_USER="SP_LAST_ADMIN_USER";
    public UserLoginFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment UserLoginFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static UserLoginFragment newInstance(String param1, String param2) {
        UserLoginFragment fragment = new UserLoginFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }


    @Override
    protected int setupLayoutResId() {
        return R.layout.fragment_user_login;
    }

    @Override
    protected void initView(View rootLayout) {
        id_cil_username = findById(R.id.id_cil_username);
        id_cil_password = findById(R.id.id_cil_password);
        if ("2".equals(mParam1)){
            id_cil_username.setText((CharSequence) SharedPreferencesTool.get(SP_LAST_ADMIN_USER,""));
        }else {
            id_cil_username.setText((CharSequence) SharedPreferencesTool.get(SP_LAST_USER,""));
        }
        idBtnLogin = findById(R.id.id_btn_login);
    }

    Button idBtnLogin;
    ClassicInputLayout id_cil_username;
    ClassicInputLayout id_cil_password;

    @Override
    protected void initListener() {

        setOnViewClickEnabledListener(idBtnLogin, new OnViewClickEnabledListener() {
            @Override
            protected void onViewClick(View v) {

                //
                String username = id_cil_username.getText();
                String password = id_cil_password.getText();
                if (TextUtils.isEmpty(username)) {
                    id_cil_username.setError("用户账号不能为空");
                    id_cil_username.requestFocus();
                    return;
                }
                if ("2".equals(mParam1)){
                    SharedPreferencesTool.put(SP_LAST_ADMIN_USER,username);
                    startAty(SettingActivity.class);
                    return;
                }
              /*fixme  if (TextUtils.isEmpty(password)) {
                    id_cil_password.setError("用户密码不能为空");
                    id_cil_password.requestFocus();
                    return;
                }*/
                SharedPreferencesTool.put(SP_LAST_USER,username);
                //获取机构
                mPresenter.getAgency(!"".equals(username) ? username : null);

            }
        });

        idBtnLogin.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                //  showMessage("ewqeqweqweq");
                toMainAty();
                return true;
            }
        });
    }

    @Override
    public void showProgress() {
        DialogManager.showLoadingDialog(getActivity(), "登录中...", false);
    }

    @Override
    public void hideProgress() {
        DialogManager.hideLoadingDialog();
    }

    @Override
    public void showMessage(String msg) {
        ToastTool.showImageWarn(msg);
    }

    @Override
    public void setupData(Object o) {

    }

    @Override
    public void setupMoreData(Object o) {

    }

    @Override
    public void toMainAty() {
        startAty(MainActivity.class);
    }

    @Override
    public void loginSuccess(UserLoginBean userLoginBean) {
        //
        AppInfoDataManager.getInstance().setUserLoginBean(userLoginBean);

        UserLoginBean.LonginUserBean user = userLoginBean.getLonginUser();
        AppInfoDataManager.getInstance().getAuthBean().Account = user.getYHDM();
        AppInfoDataManager.getInstance().getAuthBean().Name = user.getYHXM();
        AppInfoDataManager.getInstance().getAuthBean().PWD = "";
        AppInfoDataManager.getInstance().getAuthBean().MAC = "";
        AppInfoDataManager.getInstance().getAuthBean().IP = "";
        AppInfoDataManager.getInstance().getAuthBean().JGID = user.getJGID();
       /* String data = "";
        try {
            data = JsonUtil.toJson(authVo);
            data = "Scan" + data;
            data = URLEncoder.encode(data, "UTF-8");
        } catch (IOException e) {
            e.printStackTrace();
        }
        application.authorizationString = data;
         saveUser(user);*/

        List<UserLoginBean.AreasBean> aList = userLoginBean.getAreas();
        Vector<UserLoginBean.AreasBean> vector = new Vector<>(aList);
        // application.setAreaList(vector);

        UserLoginBean.TimeVoBean timeVo = userLoginBean.getTimeVo();
       /* application.betweenTime = DateUtil.getBetween(timeVo.getTime());
        application.JSESSIONID = userlogin.getData().getSessionId();*/

        ClassicApplication classicApplication= ClassicApplication.getInstance();
        Gson gson=new Gson();
        String authorization="Basic" +  gson.toJson(AppInfoDataManager.getInstance().getAuthBean());
        try {
            authorization = URLEncoder.encode(authorization, "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        String sessionId=userLoginBean.getSessionId();
        Log.i("zfq", "loginSuccess: sessionId:"+sessionId);
        classicApplication.getHeadersMap().put("Authorization",authorization);
        //
        //无效classicApplication.getHeadersMap().put("JSESSIONID",sessionId);
        classicApplication.getHeadersMap().put("Cookie","JSESSIONID="+sessionId);

        Intent intent = new Intent(mContext,
                MainActivity.class);
        startActivity(intent);

        // startMqtt();
        // startSettingService();
    }

    @Override
    public void showAgencyDialog(AgencyBeanWrapper agencyBeanWrapper) {
        List<AgencyBean> agencyBeanList = agencyBeanWrapper.getAgencyBeanList();
        if (agencyBeanList.size() > 1) {
            showSelectDialog(agencyBeanList);
        } else {
            String jgid = agencyBeanList.get(0).getJGID();
            goOnDoLogin(jgid);
        }

    }

    private void goOnDoLogin(String jgid) {
        mPresenter.doLogin(id_cil_username.getText(), id_cil_password.getText(), jgid);
    }

    private ClassicDialogFragment mCustomDialogFragment;

    private void showSelectDialog(final List<AgencyBean> agencyBeanList) {
        //
        ListView listView = new ListView(mContext);
        listView.setAdapter(new AgencyBaseAdapter(agencyBeanList));
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                //
                mCustomDialogFragment.dismissAllowingStateLoss();
                //
                goOnDoLogin(agencyBeanList.get(position).getJGID());
            }
        });
        mCustomDialogFragment = new ClassicDialogFragment.Builder(getActivity())
                .setTitle("机构选择")
                // .setMessage(null)
                .setContentView(listView)
                // .setOkText(null)
                //.setCancelText(null)
                .build();
        mCustomDialogFragment.show(getChildFragmentManager(), "mCustomDialogFragment");
    }


    @Override
    protected LoginPresenterImpl setupPresenter() {
        return new LoginPresenterImpl(this);
    }


}
