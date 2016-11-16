package com.tenblr.bhargav.tenblr.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.tenblr.bhargav.tenblr.Model.UserInfo.Meta;

/**
 * Created by bhargav on 16/11/16.
 */

public class DeleteResponse {

    @SerializedName("meta")
    @Expose
    private Meta meta;
    @SerializedName("response")
    @Expose
    private Object response;

    /**
     *
     * @return
     * The meta
     */
    public Meta getMeta() {
        return meta;
    }

    /**
     *
     * @param meta
     * The meta
     */
    public void setMeta(Meta meta) {
        this.meta = meta;
    }

    /**
     *
     * @return
     * The response
     */
    public Object getResponse() {
        return response;
    }

    /**
     *
     * @param response
     * The response
     */
    public void setResponse(Object response) {
        this.response = response;
    }
}
