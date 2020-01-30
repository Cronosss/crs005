package ww.rent005.sys.common;

/**
 * 常量
 * @Author: cronos
 * @Date: 2020/1/30 12:02
 * @Version: 1.0
 **/
public interface Constast {
	
	/**
	 * 状态码
	 * 
	 */
	public static final Integer OK=200;
	public static final Integer ERROR=-1;
	
	/**
	 * 默认密码
	 */
	public static final String USER_DEFAULT_PWD="123456";
	
	/**
	 * 菜单权限类型
	 */
	public static final String TYPE_MNEU = "menu";
	public static final String TYPE_PERMISSION = "permission";
	/**
	 * 可用状态
	 */
	public static final Object AVAILABLE_TRUE = 1;
	public static final Object AVAILABLE_FALSE = 0;
	
	/**
	 * 用户类型
	 */
	public static final Integer USER_TYPE_SUPER = 0;
	public static final Integer USER_TYPE_NORMAL = 1;
	
	/**
	 * 展开类型
	 */
	public static final Integer SPREAD_TRUE = 1;
	public static final Integer SPREAD_FALSE = 0;
	
	/**
	 * 默认图片
	 */
	public static final String IMAGES_DEFAULTGOODSIMG_IMG = "images/defaultimg.png";

}
