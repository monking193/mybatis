package result;


import lombok.Data;

/**
 * 自定义的api异常
 * @author rstyro
 *
 */
@Data
public class ApiException extends RuntimeException{
	private static final long serialVersionUID = 1L;
	private int status;
	private String message;
	private Object data;
	private Exception exception;
	public ApiException() {
		super();
	}

	public ApiException(String message) {
		super(message);
		this.message=message;
	}

	public ApiException(int status, String message, Object data, Exception exception) {
		super(message,exception);
		this.status = status;
		this.message = message;
		this.data = data;
		this.exception = exception;
	}
	public ApiException(ApiResultEnum apiResultEnum) {
		this(apiResultEnum.getStatus(),apiResultEnum.getMessage(),null,null);
	}
	public ApiException(ApiResultEnum apiResultEnum, Object data) {
		this(apiResultEnum.getStatus(),apiResultEnum.getMessage(),data,null);
	}
	public ApiException(ApiResultEnum apiResultEnum, Object data, Exception exception) {
		this(apiResultEnum.getStatus(),apiResultEnum.getMessage(),data,exception);
	}

	public static long getSerialVersionUID() {
		return serialVersionUID;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Object getData() {
		return data;
	}

	public void setData(Object data) {
		this.data = data;
	}

	public Exception getException() {
		return exception;
	}

	public void setException(Exception exception) {
		this.exception = exception;
	}
}
