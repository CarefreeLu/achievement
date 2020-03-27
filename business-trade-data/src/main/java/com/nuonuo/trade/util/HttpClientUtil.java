package com.nuonuo.trade.util;

import com.alibaba.fastjson.JSONObject;
import com.nuonuo.trade.constant.LogCodeConstant;
import com.nuonuo.trade.http.HttpClientException;
import com.nuonuo.trade.http.HttpPoolProperties;
import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.*;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.entity.ByteArrayEntity;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.entity.mime.MultipartEntity;
import org.apache.http.entity.mime.content.ContentBody;
import org.apache.http.entity.mime.content.FileBody;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.ssl.SSLContextBuilder;
import org.apache.http.util.EntityUtils;

import javax.net.ssl.SSLContext;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.concurrent.TimeUnit;

public class HttpClientUtil extends Thread {

	/**
	 * utf-8字符集
	 */
	private static final String UTF8 = "utf-8";

	/**
	 * Content Type
	 */
	public static final String APPLICATION_JSON = "application/json";
	private static CloseableHttpClient httpClient;
	private static PoolingHttpClientConnectionManager connManager;

	static {
		try {
			// 证书受信配置
			SSLContext sslContext = SSLContextBuilder.create().useProtocol(SSLConnectionSocketFactory.SSL)
					.loadTrustMaterial((x, y) -> true).build();
			RequestConfig config = RequestConfig.custom().setConnectTimeout(5000).setSocketTimeout(5000).build();
			// SSLContext.getDefault()
			LayeredConnectionSocketFactory sslsf = new SSLConnectionSocketFactory(sslContext, NoopHostnameVerifier.INSTANCE);
			Registry<ConnectionSocketFactory> socketFactoryRegistry = RegistryBuilder.<ConnectionSocketFactory>create()
					.register("http", PlainConnectionSocketFactory.INSTANCE)
					.register("https", sslsf)
					.build();
			connManager = new PoolingHttpClientConnectionManager(socketFactoryRegistry);
			connManager.setMaxTotal(HttpPoolProperties.getMaxTotal());
			connManager.setDefaultMaxPerRoute(HttpPoolProperties.getMaxPerRoute());
			httpClient = HttpClients.custom()
					.setConnectionManager(connManager)
					// 证书受信配置
					.setDefaultRequestConfig(config)
					.setSSLHostnameVerifier((x, y) -> true)
					.build();
			new HttpClientUtil().start();
		} catch (NoSuchAlgorithmException e) {
			LogUtils.outLogError(null,null, LogCodeConstant.HTTP_CLIENT_POOL,null,"创建http客户端异常",e);
		} catch (Exception e){
			LogUtils.outLogError(null,null, LogCodeConstant.HTTP_CLIENT_POOL,null,"创建http客户端异常",e);
		}
	}

	@Override
	public void run() {
			while (true) {
				try {
					Thread.sleep(3000);
					//关闭异常连接
					connManager.closeExpiredConnections();
					//关闭空闲连接
					connManager.closeIdleConnections(6, TimeUnit.SECONDS);
					//暂时添加，稳定后去除
//					LogUtils.outLogInfoLocal("连接池情况:"+connManager.getTotalStats().toString());
				} catch (Exception e) {
					LogUtils.outLogError(null,null,LogCodeConstant.HTTP_CLIENT_POOL,null,"清理异常连接异常", e);
				}
			}
	}

	/**
	 * 功能描述：获取远程资源byte数据
	 *
	 * @param url
	 * @param timeout
	 * @return {@link byte[]}
	 * @throws
	 * @author Jianhui Lu
	 * @date 2019/7/9 16:15
	 */
	public static byte[] getRemoteResource(String url, int timeout) throws IOException
	{
		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(getRequestConfig(timeout));
		CloseableHttpResponse response = httpClient.execute(httpGet);

		if (response != null && response.getStatusLine().getStatusCode() == 200)
		{
			HttpEntity entity = response.getEntity();
			InputStream inputStream = entity.getContent();
			byte[] buffer = new byte[1024];
			int len;
			ByteArrayOutputStream bos = new ByteArrayOutputStream();
			while ((len = inputStream.read(buffer)) != -1)
			{
				bos.write(buffer, 0, len);
			}
			bos.close();
			inputStream.close();
			return bos.toByteArray();
		}

		throw new IOException("获取资源失败,资源地址：" + url);
	}

	/**
	 * 执行HTTP get 方法获取结果
	 *
	 * @param url     URL
	 * @param timeout 超时时间（毫秒）
	 * @return HTTP response
	 */
	public static String get(String url, int timeout) {
		return get(url,null,timeout);
	}

	/**
	 * 执行HTTP get 方法获取结果
	 *
	 * @param url     URL
	 * @param timeout 超时时间（毫秒）
	 * @return HTTP response
	 */
	public static String get(String url,String contentType ,int timeout) {

		HttpGet httpGet = new HttpGet(url);
		httpGet.setConfig(getRequestConfig(timeout));
		if(StringUtils.isNotBlank(contentType)){
			httpGet.setHeader("Content-type", contentType);
		}
		return getResponse(httpGet);
	}

	/**
	 * 执行HTTP post 方法获取结果
	 *
	 * @param url       URL
	 * @param data      Post数据
	 * @param contentType Content-type
	 * @param timeout   超时时间（毫秒）
	 * @return HTTP response
	 */
	public static String postString(String url, String data, String contentType, int timeout) {
		return post(url,new StringEntity(data, Consts.UTF_8),contentType,timeout);
	}

	/**
	 * 执行HTTP post 方法获取结果
	 *
	 * @param url      URL
	 * @param params Post数据
	 * @param timeout   超时时间（毫秒）
	 * @return HTTP response
	 */
	public static String postForm(String url, Map<String, String> params, int timeout) {
		List<NameValuePair> param = new ArrayList<>();
		for (Entry<String, String> entity : params.entrySet()) {
			param.add(new BasicNameValuePair(entity.getKey(), entity.getValue()));
		}
		return post(url,new UrlEncodedFormEntity(param, Consts.UTF_8),null,timeout);
	}

	/**
	 * 执行HTTP post 方法获取结果,不打印info日志
	 *
	 * @param url      URL
	 * @param params Post数据
	 * @param timeout   超时时间（毫秒）
	 * @return HTTP response
	 */
	public static String postFormNoLog(String url, Map<String, String> params, int timeout) throws HttpClientException
	{
		List<NameValuePair> reqParamList = new ArrayList<>();
		if(MapUtils.isNotEmpty(params)){
			for (Entry<String, String> entity : params.entrySet()) {
				reqParamList.add(new BasicNameValuePair(entity.getKey(), entity.getValue()));
			}
		}
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(getRequestConfig(timeout));
		httpPost.setEntity(new UrlEncodedFormEntity(reqParamList, Consts.UTF_8));

		CloseableHttpResponse response = null;
		String res = null;
		try {
			response = httpClient.execute(httpPost);
			if (response.getStatusLine().getStatusCode() == 200 && response.getEntity() != null) {
				res = EntityUtils.toString(response.getEntity());
			}
		} catch (Exception e) {
			throw new HttpClientException("http连接异常或超时",e.getCause());
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return res;
	}

	/**
	 * 获得响应信息，（当前只有国票接口使用）
	 * @param request
	 * @return
	 * @throws HttpClientException
	 */
	public static String getResponseNoLog(HttpUriRequest request) throws HttpClientException{
		CloseableHttpResponse response = null;
		String res;
		try {
			response = httpClient.execute(request);
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("body",response.getEntity()!=null?EntityUtils.toString(response.getEntity()):null);
			if(response.getStatusLine().getStatusCode() == HttpStatus.SC_OK){
				jsonObject.put("success",true);
			}else{
				jsonObject.put("success",false);
			}
			res = JSONObject.toJSONString(jsonObject);
		} catch (Exception e) {
			throw new HttpClientException("http连接异常或超时",e.getCause());
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return res;
	}
	/**
	 * 上传pdf文件
	 *
	 * @param url 上传地址
	 * @param file 上传文件
	 * @return HTTP response
	 */
	public static String postPdfMedia(String url, File file){
		ContentType contentType = ContentType.create("multipart/form-data", Consts.UTF_8);
		MultipartEntity mpEntity = new MultipartEntity(); //文件传输
		ContentBody cbFile = new FileBody(file);
		mpEntity.addPart("pdf", cbFile);
		return post(url, mpEntity, contentType.toString(),5000);
	}


	/**
	 * 执行HTTP post 方法获取结果
	 *
	 * @param url       URL
	 * @param contentType Content-type（若不指定，传入null）
	 * @param timeout   超时时间（毫秒）
	 * @return HTTP response
	 */
	public static String post(String url, HttpEntity entity, String contentType, int timeout) {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(getRequestConfig(timeout));
		if (entity != null) {
			httpPost.setEntity(entity);
		}
		if (contentType != null) {
			httpPost.setHeader("Content-type", contentType);
		}
		return getResponse(httpPost);
	}

	/**
	 * 执行HTTP post 方法获取结果
	 *
	 * @param url       URL
	 * @param contentType Content-type（若不指定，传入null）
	 * @param timeout   超时时间（毫秒）
	 * @return HTTP response
	 */
	public static String post(String url, HttpEntity entity, String contentType, int timeout,String charset) {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(getRequestConfig(timeout));
		if (entity != null) {
			httpPost.setEntity(entity);
		}
		if (contentType != null) {
			httpPost.setHeader("Content-type", contentType);
		}
		return getResponse(httpPost,charset);
	}

	/**
	 * 执行HTTP post 方法获取结果
	 *
	 * @param url       URL
	 * @param contentType Content-type（若不指定，传入null）
	 * @param timeout   超时时间（毫秒）
	 * @return HTTP response
	 */
	public static String httpPost(String url, HttpEntity entity, String contentType, int timeout, String charset) throws IOException {
		return httpPost(url,entity,contentType,timeout,charset,null);
	}

	/**
	 * 执行HTTP post 方法获取结果
	 *
	 * @param url       URL
	 * @param data      Post数据
	 * @param contentType Content-type
	 * @param timeout   超时时间（毫秒）
	 * @return HTTP response
	 */
	public static String httpPostString(String url, String data, String contentType, int timeout) throws IOException {
		return httpPost(url,new StringEntity(data, Consts.UTF_8),contentType,timeout,UTF8);
	}

	/**
	 * 执行HTTP post 方法获取结果
	 *
	 * @param url URL
	 * @param data Post数据
	 * @param contentType Content-type
	 * @return HTTP response
	 */
	public static String httpPostString(String url, String data, String contentType) throws IOException {
		return httpPost(url,new StringEntity(data, Consts.UTF_8),contentType,-1,UTF8);
	}

	/**
	 * 执行HTTP post 方法获取结果
	 *
	 * @param url       URL
	 * @param contentType Content-type（若不指定，传入null）
	 * @param timeout   超时时间（毫秒）
	 * @param headers   头信息
	 * @return HTTP response
	 */
	public static String httpPost(String url, HttpEntity entity, String contentType, int timeout, String charset,
								  Map<String,String> headers) throws IOException {
		HttpPost httpPost = new HttpPost(url);
		if(timeout>0){
			httpPost.setConfig(getRequestConfig(timeout));
		}else {
			httpPost.setConfig(getRequestConfig());
		}
		if (entity != null) {
			httpPost.setEntity(entity);
		}
		if (contentType != null) {
			httpPost.setHeader("Content-type", contentType);
		}
		if (headers != null && headers.size() > 0) {
			for (Entry<String,String> entry : headers.entrySet()) {
				httpPost.setHeader(entry.getKey(), entry.getValue());
			}
		}
		String params = null;
		if(entity!=null){
			params = EntityUtils.toString(entity);
		}
		String result = null;
		CloseableHttpResponse response = httpClient.execute(httpPost);
		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity entityResponse = response.getEntity();
			if (entityResponse != null) {
				if(StringUtils.isNotBlank(charset)){
					result = EntityUtils.toString(entityResponse,charset);
				}else {
					result = EntityUtils.toString(entityResponse);
				}
				LogUtils.outLogInfo(null,url,LogCodeConstant.HTTP_CLIENT,LogCodeConstant.RESPONSE,
						url + ",getResponse:" + result + ",params:" + params);
			}else{
				LogUtils.outLogInfo(null,url,LogCodeConstant.HTTP_CLIENT,LogCodeConstant.RESPONSE,
						url + ",getResponse error,entity is null." + ",params:" + params);
			}
		}else{
			LogUtils.outLogInfo(null,url,LogCodeConstant.HTTP_CLIENT,LogCodeConstant.RESPONSE,
					url + ",getResponse error,statusCode:" + response.getStatusLine().getStatusCode() + ",params:" + params);
		}
		response.close();
		return result;
	}

	/**
	 * 执行HTTP post 方法获取结果
	 *
	 * @param url       URL
	 * @param contentType Content-type（若不指定，传入null）
	 * @param timeout   超时时间（毫秒）
	 * @return HTTP response
	 */
	public static String httpGet(String url, String contentType, int timeout, String charset) throws IOException {
		HttpGet httpGost = new HttpGet(url);
		httpGost.setConfig(getRequestConfig(timeout));
		if (contentType != null) {
			httpGost.setHeader("Content-type", contentType);
		}
		String result = null;
		CloseableHttpResponse response = httpClient.execute(httpGost);
		if (response.getStatusLine().getStatusCode() == 200) {
			HttpEntity entityResponse = response.getEntity();
			if (entityResponse != null) {
				if(StringUtils.isNotBlank(charset)){
					result = EntityUtils.toString(entityResponse,charset);
				}else {
					result = EntityUtils.toString(entityResponse);
				}
				LogUtils.outLogInfo(null,url,LogCodeConstant.HTTP_CLIENT,LogCodeConstant.RESPONSE,
						url + ",getResponse:" + result);
			}else{
				LogUtils.outLogInfo(null,url,LogCodeConstant.HTTP_CLIENT,LogCodeConstant.RESPONSE,
						url + ",getResponse error,entity is null.");
			}
		}else{
			LogUtils.outLogInfo(null,url,LogCodeConstant.HTTP_CLIENT,LogCodeConstant.RESPONSE,
					url + ",getResponse error,statusCode:" + response.getStatusLine().getStatusCode());
		}
		response.close();
		return result;
	}



	public static byte[] postOfBox(String url, byte[] postxml, int timeout) {
		HttpPost httpPost = new HttpPost(url);
		httpPost.setConfig(getRequestConfig(timeout));
		httpPost.setEntity(new ByteArrayEntity(postxml));
		httpPost.setHeader("Content-type", "text/xml");
		return getResponseOfBox(httpPost);
	}

	/**
	 * 获取HTTP请求配置
	 *
	 * @param timeout 超时时间
	 * @return RequestConfig
	 */
	private static RequestConfig getRequestConfig(int timeout) {
		return RequestConfig.custom()
				.setSocketTimeout(timeout)
				.setConnectTimeout(timeout)
				.setConnectionRequestTimeout(timeout)
				.build();
	}

	/**
	 * 从配置获取HTTP请求配置
	 *
	 * @return RequestConfig
	 */
	private static RequestConfig getRequestConfig() {
		return RequestConfig.custom()
				.setSocketTimeout(HttpPoolProperties.getSocketTimeout())
				.setConnectTimeout(HttpPoolProperties.getConnectTimeout())
				.setConnectionRequestTimeout(HttpPoolProperties.getRequestTimeout())
				.build();
	}

	/**
	 * 获取响应结果
	 *
	 * @param request 请求
	 * @return HTTP response
	 */
	private static String getResponse(HttpUriRequest request){
		return getResponse(request,null);
	}

	/**
	 * 获取响应结果
	 *
	 * @param request 请求
	 * @param charset 编码
	 * @return HTTP response
	 */
	private static String getResponse(HttpUriRequest request,String charset) {
		CloseableHttpResponse response = null;
		String params = null;
		String res = null;
		try {
			try {
				if(request instanceof HttpPost){
					HttpEntity reqEntity = ((HttpPost) request).getEntity();
					if (reqEntity != null && !(reqEntity instanceof MultipartEntity)) {
						params = EntityUtils.toString(reqEntity);
					}
				}
			} catch (Exception e) {
				LogUtils.outLogError(null,null, LogCodeConstant.HTTP_CLIENT,request.getURI().toString(),"getReqEntity error, exception:",e);
			}
			response = httpClient.execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					if(StringUtils.isNotBlank(charset)){
						res = EntityUtils.toString(entity,charset);
					}else {
						res = EntityUtils.toString(entity);
					}
					LogUtils.outLogInfo(null,request.getURI().toString(),LogCodeConstant.HTTP_CLIENT,LogCodeConstant.RESPONSE,
							request.getURI() + ",getResponse:" + res + ",params:" + params);
				}else{
					LogUtils.outLogInfo(null,request.getURI().toString(),LogCodeConstant.HTTP_CLIENT,LogCodeConstant.RESPONSE,
							request.getURI() + ",getResponse error,entity is null." + ",params:" + params);
				}
			}else{
				LogUtils.outLogInfo(null,request.getURI().toString(), LogCodeConstant.HTTP_CLIENT,LogCodeConstant.RESPONSE,
						request.getURI() + ",getResponse error,statusCode:" + response.getStatusLine().getStatusCode() + ",params:" + params);
			}
		} catch (Exception e) {
			LogUtils.outLogError(null,null, LogCodeConstant.HTTP_CLIENT,request.getURI()+"","请求异常"+ ",params:" + params,e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return res;
	}

	/**
	 * 获取盒子响应结果
	 *
	 * @param request 请求对象
	 * @return HTTP response
	 */
	private static byte[] getResponseOfBox(HttpRequestBase request) {
		CloseableHttpResponse response = null;
		InputStream is = null;
		try {
			response = httpClient.execute(request);
			if (response.getStatusLine().getStatusCode() == 200) {
				HttpEntity entity = response.getEntity();
				if (entity != null) {
					is = entity.getContent();
					ByteArrayOutputStream outStream = new ByteArrayOutputStream();
					//创建一个Buffer字符串
					byte[] buffer = new byte[1024 * 10];
					//每次读取的字符串长度，如果为-1，代表全部读取完毕
					int len;
					//使用一个输入流从buffer里把数据读取出来
					while ((len = is.read(buffer)) != -1) {
						//用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
						outStream.write(buffer, 0, len);
					}
					byte[] responseByte = outStream.toByteArray();
					int retryCount = 1;
					return writeBoxResponse(new ByteArrayInputStream(responseByte), responseByte,retryCount);
				}else{
					LogUtils.outLogInfo(null,request.getURI().toString(),LogCodeConstant.HTTP_CLIENT,LogCodeConstant.RESPONSE,
							request.getURI() + ",getResponse error,entity is null.");
				}
			}else{
				LogUtils.outLogInfo(null,request.getURI().toString(),LogCodeConstant.HTTP_CLIENT,LogCodeConstant.RESPONSE,
						request.getURI() + ",getResponse error,entity is null." + response.getStatusLine().getStatusCode());
			}
		} catch (Exception e) {
			LogUtils.outLogError(null,null, LogCodeConstant.HTTP_CLIENT,request.getURI()+"","请求异常",e);
		} finally {
			if (response != null) {
				try {
					response.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			try {
				if (is != null) {
					is.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}

	/**
	 * 获取51盒子的返回结果
	 *
	 * @param inputStream 输入流
	 * @param responseByte 响应结果
	 * @param retryCount 当前重试次数
	 * @return HTTP response
	 */

	private static byte [] writeBoxResponse(InputStream inputStream,byte[] responseByte,int retryCount) {
		if(inputStream == null){
			return null;
		}
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		//每次读取的字符串长度，如果为-1，代表全部读取完毕
		int len;
		byte[] bytes = null;
		byte[] buffer;
		try {
			//创建一个Buffer字符串
			buffer = new byte[inputStream.available()];
			//使用一个输入流从buffer里把数据读取出来
			while ((len = inputStream.read(buffer)) != -1) {
				//用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
				outStream.write(buffer, 1, len - 1);
			}
			bytes = outStream.toByteArray();
			while (bytes.length % 8 != 0) {
				if (retryCount > 10){
					bytes = null;
					break;
				}
				retryCount++;
				inputStream.close();
				outStream.close();
				writeBoxResponse(new ByteArrayInputStream(responseByte), responseByte,retryCount);
			}
		} catch (Exception e) {
			LogUtils.outLogError(null,null, LogCodeConstant.HTTP_CLIENT,null,"获取51盒子的返回结果异常",e);
		}finally {
			try {
				inputStream.close();
				outStream.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return bytes;
	}

}
