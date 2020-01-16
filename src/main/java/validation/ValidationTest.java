package validation;


import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.executable.ExecutableValidator;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Set;

/**
 * 验证器
 * @author cbpro
 */
public class ValidationTest {

    /**
     * 验证器对象
     */
    private Validator validator;

    /**
     * 待验证对象
     */
    private UserInfo userInfo;

    /**
     * 验证结果集合
     */
    private Set<ConstraintViolation<UserInfo>> set;

    /**
     * 高级验证结果集合
     */
    private Set<ConstraintViolation<UserInfoService>> otherSet;

    /**
     * 初始化
     */
    @Before
    public void init(){
        //初始化验证器
        validator = Validation.buildDefaultValidatorFactory().getValidator();
        //用户信息初始化
        userInfo = new UserInfo();
        userInfo.setUserId("admin");
        userInfo.setUserName("capo");
        userInfo.setPassword("123456");
        //userInfo.setPhone("15835222222");
        userInfo.setAge(20);
        Calendar calendar = Calendar.getInstance();
        calendar.set(2005,2,2);
        userInfo.setBirthday(calendar.getTime());
        userInfo.setEmail("123456@gmail@.com");

        UserInfo firend = new UserInfo();
        firend.setUserId("test")
                .setUserName("123")
                .setPassword("123456")
                .setPhone("15811111111");
        userInfo.setFirends(new ArrayList(){{add(firend);}});
    }

    /**
     * 结果打印
     */
    @After
    public void print(){
        set.forEach(item -> {
            System.out.println(item.getMessage());
        });
    }

    /**
     * 普通验证
     */
    @Test
    public void validation(){
        set = validator.validate(userInfo);
    }

    /**
     * 分组验证
     */
    @Test
    public void group(){
        set = validator.validate(userInfo, UserInfo.RegisterGroup.class);
    }

    /**
     * 组序列验证
     */
    @Test
    public void groupSort(){
        set = validator.validate(userInfo, UserInfo.Group.class);
    }

    /**
     * 对方法输入参数进行验证
     */
    @Test
    public void paramValidation() throws NoSuchMethodException {
        //获取校验执行器
        ExecutableValidator executableValidator = validator.forExecutables();

        //验证对象
        UserInfoService userInfoService = new UserInfoService();

        //验证方法
        Method method = userInfoService.getClass().getMethod("setUserInfo", UserInfo.class);

        //输入参数
        Object[] parameterValues = new Object[]{new UserInfo()};

        //对方法输入参数进行校验
        otherSet = executableValidator.validateParameters(userInfoService,method,parameterValues);
    }

    /**
     * 对方法返回参数进行验证
     */
    @Test
    public void returnValueValidation() throws InvocationTargetException, IllegalAccessException, NoSuchMethodException {
        //获取校验执行器
        ExecutableValidator executableValidator = validator.forExecutables();

        //验证对象
        UserInfoService userInfoService = new UserInfoService();

        //验证方法
        Method method = userInfoService.getClass().getMethod("getUserInfo");

        //调用方法得到返回值
        Object returnValue = method.invoke(userInfoService);

        //对方法输入参数进行校验
        otherSet = executableValidator.validateReturnValue(userInfoService,method,returnValue);
    }

    /**
     * 对构造方法传参进行验证
     */
    @Test
    public void constructorValidation() throws NoSuchMethodException {
        //获取校验执行器
        ExecutableValidator executableValidator = validator.forExecutables();

        //验证的构造函数
        Constructor constructor = UserInfoService.class.getConstructor(UserInfo.class);

        //构造函数的传参
        Object[] paramObjects = new Object[]{new UserInfo()};

        //对方法输入参数进行校验
        otherSet = executableValidator.validateConstructorParameters(constructor,paramObjects);
    }

}
