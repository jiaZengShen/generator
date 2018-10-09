package javaCode.exception;

/**
 * Created by hoze on 2018/3/7.
 */
public class CommonException extends Exception {

    private static final long serialVersionUID = 1L;

    /**
     * 错误编码
     */
    private int code;


    /**
     * 构造一个基本异常.
     *
     * @param message
     *            信息描述
     */
    public CommonException(String message)
    {
        super(message);
    }

    /**
     * 构造一个基本异常.
     *
     * @param code
     *            错误编码
     * @param message
     *            信息描述
     */
    public CommonException(int code, String message)
    {
        super(message);
        this.setCode(code);
    }

    /**
     * 构造一个基本异常.
     *
     * @param code
     *            错误编码
     * @param message
     *            信息描述
     */
    public CommonException(int code, String message, Throwable cause)
    {
        super(message, cause);
        this.setCode(code);
    }

    /**
     * 构造一个基本异常.
     *
     * @param message
     *            信息描述
     * @param cause
     *            根异常类（可以存入任何异常）
     */
    public CommonException(String message, Throwable cause)
    {
        super(message, cause);
    }

    public int getCode()
    {
        return code;
    }

    public void setCode(int code)
    {
        this.code = code;
    }


}
