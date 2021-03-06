package com.louisgeek.classichu.api;

import com.louisgeek.classichu.bean.BS_BaseBean;
import com.louisgeek.classichu.bean.BS_BaseListBean;
import com.louisgeek.classichu.login.bean.AgencyBean;
import com.louisgeek.classichu.login.bean.UserLoginBean;
import com.louisgeek.classichu.patient.bean.PatientInfoBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

/**
 * Created by Classichu on 2017-7-17.
 */

public interface UserApi {
    @POST("mobile/user/login")
    Observable<BS_BaseBean<UserLoginBean>> userLogin(@Query("urid") String urid, @Query("pwd") String pwd, @Query("jgid") String jgid);
    @GET("mobile/user/get/agencys")
    Observable<BS_BaseListBean<AgencyBean>> getAgency(@Query("urid") String urid);
    @GET("auth/mobile/patient/get/detail")
    Observable<BS_BaseBean<PatientInfoBean>> getPatientInfoDetail(@Query("zyh") String zyh, @Query("jgid") String jgid);

}
