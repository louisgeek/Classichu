package com.louisgeek.classichu.api;

import com.louisgeek.classichu.bean.BS_BaseBean;
import com.louisgeek.classichu.patient.bean.PatientInfoBean;

import io.reactivex.Observable;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by Classichu on 2017-7-21.
 */

public interface PatientApi {
    @GET("auth/mobile/patient/get/detail")
    Observable<BS_BaseBean<PatientInfoBean>> getPatientInfoDetail(@Query("zyh") String zyh, @Query("jgid") String jgid);
}
