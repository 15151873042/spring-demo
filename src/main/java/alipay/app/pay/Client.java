package alipay.app.pay;

import java.util.UUID;

import org.junit.Test;

import com.alipay.api.AlipayApiException;
import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.alipay.api.domain.AlipayTradeAppPayModel;
import com.alipay.api.domain.AlipayTradeQueryModel;
import com.alipay.api.request.AlipayTradeAppPayRequest;
import com.alipay.api.request.AlipayTradeQueryRequest;
import com.alipay.api.response.AlipayTradeAppPayResponse;
import com.alipay.api.response.AlipayTradeQueryResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class Client {

	public static final String ALI_SERVER_URL = "https://openapi.alipaydev.com/gateway.do";

	public static final String APP_ID = "2016092200568681";
	
	public static final String PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAoIBAQCM7TxhqZLLO8YOvPwyJFm3CmUTC7Aa3CE0Di51jveIRERP2/nrYsiJcCtfbebvNeChpz+KRPLNc1zRexYjX/WzIH6+vpoKzDmnZ3h1e6UVGxvkZWe2sQL9Ts+DD8RZ9JQOdr8+8HsTpqbJjoTTId2VwsSHf1Xnz7rJGSpL6p9AWs+I5JJXmw+E45amj5TxGfBUWvc3iKWZedUPFimV+sIXIUjbrDeox6iV9WRCLOi7lnTRJKj0RtiIY2gWveT4cVmDTJovyPvCT6AsHFldty4yFjss0lyJxjtRguY0FqomrgzuHHBk6R4TZa5FRhxCZR0eaA4zhJklb884V+NTKBH7AgMBAAECggEAbfxI/7+kfVDtRltgSJHuItjOAYtBqfTbRRE8WSt3LLZ6ygjYiEjUUc9lSlkvvsnBwugQww0EoivC6QqzQHCIBYD66ks/VeYFqQ0sCiSxtkMuBZbO1tpXhvKXKaaIi9xCIPe0Up7Er5WE3hGIwG7RzSvwzf8nQH/zOzeUtyMpnsMMcyQ/7lKsmKxjIluVfYt5v7yuSy1SvdQVMLEUXYzUuDGiM1lPqIZT3w1JcnD2HmPKSjUIPRDmNkZVrcLi4ikrYwUhawbtb7PosXEH0s255OzpsopHb3ZLer8Sgp53sO2wNyu5gXsx6pXFv3FFUlnaw/eFRzecN24jXCl6c/XCoQKBgQD8NcPgTrMTe6Ier70S78z4UliVlL/sb8VXNu5+CoAVAY/TUYXWwqcDun+oQ4TtuOvf/Voi0dryc0LO3Y6CBxn+eZ83qAHgx+96prYsHjPWAxqS4MPlSmpUx5k0Db1OgObkjRxmg8Wx5LZ0schcEE33FfEGNZGI9TBrWCZ57EXq3wKBgQCPC18QPNNt1cIuJ8xnAxPf08UrSAionD9MJmcwd3kQKOVkdRdJxCnAUXlV5HUgRYEyKDhiIJ0sI0drDla5JSIxooKKB5sZLty2CoaVVar2EhRjsK3HGZJr1DIQKCVfp5fIrWsGHA4A/A36vwpUaHhC+S2SBLtel8fvU20E6aKYZQKBgQDkqXJPucqODXdszvRjaNqzASWjaFuhZ6zJZ9pOmAKMCQB3HOB1V8HIlZwL2Dt6tD/WsoK4Mv+I9YtKtoDvzrR4jZgXA9HAFEVBNEjSMwfYLrQ3GmVBS3igKN7z3MkXlbLA4FcxcqMYqD3sCwuWvImTTWdas9KhE3u0txUbUWYKjQKBgBiWasu+uNT7Vjpayu61/7eMjiqnYh2hZdvwCfKxxpR5gxymZ89dMZQGLSYPc/aNG+0ELtTnKD0K/16ug0el01/WwDt7nTRR8swzuPh4Vj+f0fNkzioYAiwYuFZAoFP12cg7ztapAds3eCuupNbnciyI6kVcHNzgmBRSMb4TbshlAoGBAITl9yVM+5iVCET16Ivs4lu8wdLr3cKqyV14BqK6LAxNujM7/9qk8ckS95eUaScGpR1U+8lrmcoI79fyb+sMIJHTmvhK+fkLFmoHqzbrqDnYPqdnGTvYWbYM8QfPb8fO4BK2vIz5CtVBuiZIcOxG8ULygLIrlF3nADYOokFMdr0a";

	public static final String ALIPAY_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA7usGOorNQcm/rqJPwrhuCIupEfSZxJl+RvyizWKBTOfceRnxiWqSCE7uaH8vmO9d9rFH4+sjAdjl/X4IR9npZ8PR3TI74oOnBwgdIACibMmgTWDp4UELmHqFtjIq+6pBmZ4kyVO+UygHEMj03MP6Tqvkz90nJTaN1B1xzVTnQvZplTZktvgu7MEVicHvHJ3ifQ+hzV2jnYPZbhDmJU4WqbZFxSVk19n0+KeP5Y3TwxxTSFF830FMvhP6nkGYPOeSsoJJ3/lRiUgI7BTEb6NtEEX0SouGCphagFY09miRe1gq3DGfAQ+CSOVvFaJs/ag+b0m321lSi/suAQ5JCaRK7wIDAQAB";

	public static final String FORMAT = "json";

	public static final String CHARSET = "UTF-8";

	public static final String SIGN_TYPE = "RSA2";

	public static final AlipayClient ALIPAY_CLIENT = new DefaultAlipayClient(ALI_SERVER_URL, APP_ID, PRIVATE_KEY,
			FORMAT, CHARSET, ALIPAY_PUBLIC_KEY, SIGN_TYPE);

	public static final ObjectMapper objectMapper = new ObjectMapper();

	/**
	 *
	 * @Descripton 生成 APP支付订单信息
	 * @author 胡鹏
	 * @date 2020年6月16日 下午1:55:44
	 * @param args
	 * @throws AlipayApiException
	 * @throws JsonProcessingException
	 */
	@Test
	public void getAppPayInformation() throws AlipayApiException, JsonProcessingException {
		String orderNo = UUID.randomUUID().toString();
		System.out.println("业务订单号为：" + orderNo);
		AlipayTradeAppPayModel requestModel = new AlipayTradeAppPayModel();
		requestModel.setSubject("商品名称");// 商品名称
		requestModel.setOutTradeNo(orderNo); // 业务订单号
		requestModel.setTotalAmount("10.05"); // 订单总金额
		AlipayTradeAppPayRequest request = new AlipayTradeAppPayRequest();
		request.setBizModel(requestModel);

		System.out.println("请求：" + objectMapper.writeValueAsString(request));
		AlipayTradeAppPayResponse response = ALIPAY_CLIENT.sdkExecute(request);
		System.out.println("响应：" + objectMapper.writeValueAsString(response));
		if (response.isSuccess()) {
			System.out.println("调用成功");
		} else {
			System.out.println("调用失败");
		}

	}

	/**
	 *
	 * @Descripton 查询订单支付状态
	 * @author 胡鹏
	 * @throws JsonProcessingException
	 * @throws AlipayApiException
	 * @date 2020年6月16日 下午2:32:49
	 */
	@Test
	public void queryOrderStatus() throws JsonProcessingException, AlipayApiException {
		AlipayTradeQueryModel requestModel = new AlipayTradeQueryModel();
		requestModel.setOutTradeNo("6466667b-db53-4db3-8325-0228948e0df4");
		AlipayTradeQueryRequest request = new AlipayTradeQueryRequest();
		request.setBizModel(requestModel);

		System.out.println("请求：" + objectMapper.writeValueAsString(request));
		AlipayTradeQueryResponse response = ALIPAY_CLIENT.execute(request);
		System.out.println("响应：" + objectMapper.writeValueAsString(response));

		if (response.isSuccess()) {
			System.out.println("调用成功");
			System.out.println("订单支付状态为：" + response.getTradeStatus());
		} else {
			System.out.println("调用失败");
		}
	}
}
