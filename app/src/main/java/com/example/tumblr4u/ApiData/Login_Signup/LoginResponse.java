
package com.example.tumblr4u.ApiData.Login_Signup;

        import com.example.tumblr4u.ApiData.Login_Signup.NewLoginResponse.Meta;
        import com.example.tumblr4u.ApiData.Login_Signup.NewLoginResponse.Res;
        import com.google.gson.annotations.Expose;
        import com.google.gson.annotations.SerializedName;

/**
 * This class is the template for the login response
 * */
public class LoginResponse {
    @SerializedName("meta")
    @Expose
    private com.example.tumblr4u.ApiData.Login_Signup.NewLoginResponse.Meta meta;
    @SerializedName("res")
    @Expose
    private Res res;

    /**
     * Getter of the meta data
     * */
    public com.example.tumblr4u.ApiData.Login_Signup.NewLoginResponse.Meta getMeta() {
        return meta;
    }

    /**
     * Setter of the meta data
     * @param meta The meta data of the request
     * */
    public void setMeta(com.example.tumblr4u.ApiData.Login_Signup.NewLoginResponse.Meta meta) {
        this.meta = meta;
    }

    /**
     * Getter of the response data
     * */
    public Res getResponse() {
        return res;
    }


    /**
     * Setter of the response
     * @param res The response of the request
     * */
    public void setRes(Res res) {
        this.res = res;
    }

}
