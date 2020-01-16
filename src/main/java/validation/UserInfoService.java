package validation;

import javax.validation.Valid;

/**
 * 用户信息服务类
 */
public class UserInfoService {

    /**
     * UserInfo 输入参数校验
     * @param userInfo
     */
    public void setUserInfo(@Valid UserInfo userInfo){}

    /**
     * UserInfo 返回参数校验
     * @return
     */
    public @Valid UserInfo getUserInfo(){
        return new UserInfo();
    }

    /**
     * 默认构造函数
     */
    public UserInfoService(){}

    /**
     * UserInfo 构造函数校验
     * @param userInfo
     */
    public UserInfoService(@Valid UserInfo userInfo){}
}
