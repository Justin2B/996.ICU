package validation;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.hibernate.validator.constraints.Length;

import javax.validation.GroupSequence;
import javax.validation.Valid;
import javax.validation.constraints.*;
import javax.validation.groups.Default;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;

/**
 * 待验证对象实体类
 * 用户信息类
 *
 * @author cbpro
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UserInfo {

    /**
     * 组序列验证:按顺序依次验证
     */
    @GroupSequence( {LoginGroup.class,RegisterGroup.class,Default.class})
    public interface Group {}

    /**
     * 登录场景验证分组
     */
    public interface LoginGroup {}

    /**
     * 注册场景验证分组
     */
    public interface RegisterGroup {}


    /**
     * 用户ID
     * NotNull:必须设置，可以为空字符串
     */
    @NotNull(message = "用户ID不能为空", groups = LoginGroup.class)
    private String userId;

    /**
     * 用户名
     * NotEmpty:必须设置，不能为空字符串，可以为空格
     */
    @Length(min = 3, max = 10, message = "用户名长度在3-10位")
    @NotEmpty(message = "用户名不能为空")
    private String userName;

    /**
     * 用户密码
     * NotBlank：必须设置，不能为空字符串和空格
     */
    @NotBlank(message = "密码不能为空")
    @Length(min = 6, max = 6, message = "密码长度为6")
    private String password;

    /**
     * 年龄
     */
    @Min(value = 18, message = "年龄必须大于18岁")
    @Max(value = 60, message = "年龄必须小于60岁")
    private Integer age;

    /**
     * 邮箱
     */
    @Email(message = "邮箱格式错误", groups = RegisterGroup.class)
    private String email;

    /**
     * 手机号
     */
    @Phone(message = "手机号格式有误")
    private String phone;

    /**
     * 生日
     */
    @Past(message = "生日格式有误")
    private Date birthday;

    /**
     * 好友列表
     * Valid:级联验证
     */
    @Size(min = 1, message = "好友数量不能少于1")
    private List<@Valid UserInfo> firends;
}
